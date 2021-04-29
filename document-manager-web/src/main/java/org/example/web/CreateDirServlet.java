package org.example.web;

import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.DirectoryDaoImpl;
import org.example.documentmanagerdao.UserDao;
import org.example.documentmanagerdao.UserDaoImpl;
import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Optional;

public class CreateDirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/create_dir.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse resp)
            throws ServletException, IOException {
        Long parentId = Long.parseLong(rq.getParameter("parent_id"));
        Long authorId = Long.parseLong(rq.getParameter("author_id"));
        String name = rq.getParameter("name");
        System.out.println(rq.getParameter("free_access"));
        Boolean freeAccess = Boolean.parseBoolean(rq.getParameter("free_access"));
        System.out.println(freeAccess);
        UserDao userDao = new UserDaoImpl();
        User user = userDao.find(authorId);
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        Optional<Directory> parent = directoryDao.find(parentId);
        Directory directory = new Directory(null, parent, user, name, org.example.documentmanagermodel.StorableType.DIRECTORY,
                org.example.documentmanagermodel.Statuses.CURRENT, new Timestamp(System.currentTimeMillis()), freeAccess);

        directoryDao.create(directory);
        PrintWriter pw = resp.getWriter();
        pw.println("Directory created");
    }
}
