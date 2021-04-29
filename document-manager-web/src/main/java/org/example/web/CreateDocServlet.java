package org.example.web;

import org.example.documentmanagerdao.*;
import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.Document;
import org.example.documentmanagermodel.Priorities;
import org.example.documentmanagermodel.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Optional;

public class CreateDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/create_doc.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse resp)
            throws ServletException, IOException {
        Long parentId = Long.parseLong(rq.getParameter("parent_id"));
        Long authorId = Long.parseLong(rq.getParameter("author_id"));
        String name = rq.getParameter("name");
        Boolean freeAccess = Boolean.parseBoolean(rq.getParameter("free_access"));
        String description = rq.getParameter("description");
        Priorities priority = Priorities.valueOf(rq.getParameter("priority"));
        UserDao userDao = new UserDaoImpl();
        User user = userDao.find(authorId);
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        Optional<Directory> parent = directoryDao.find(parentId);
        DocumentDao documentDao = new DocumentDaoImpl();
        Document document = new Document(null, parent, user,
                name,
                org.example.documentmanagermodel.StorableType.DOCUMENT,
                org.example.documentmanagermodel.Statuses.CURRENT,
                new Timestamp(System.currentTimeMillis()), freeAccess,
                description,
                priority,
                new org.example.documentmanagermodel.DocType(1L, "Fax"),
                null);
        documentDao.create(document);
        PrintWriter pw = resp.getWriter();
        pw.println("Document created");
    }
}
