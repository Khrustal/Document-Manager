package org.example.web;

import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.DirectoryDaoImpl;
import org.example.documentmanagerdao.UserDao;
import org.example.documentmanagerdao.UserDaoImpl;
import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.User;
import org.example.documentmanagerservices.DirectoryService;
import org.example.documentmanagerservices.DirectoryServiceImpl;
import org.example.documentmanagerservices.UserService;
import org.example.documentmanagerservices.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Optional;

public class CreateDirServlet extends HttpServlet {

    private UserService userService;
    private DirectoryService directoryService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        directoryService = new DirectoryServiceImpl();
    }

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
        Boolean freeAccess = Boolean.parseBoolean(rq.getParameter("free_access"));
        User user = userService.find(authorId);
        Optional<Directory> parent = directoryService.find(parentId);

        Directory directory = new Directory(null, parent, user, name, org.example.documentmanagermodel.StorableType.DIRECTORY,
                org.example.documentmanagermodel.Statuses.CURRENT, new Timestamp(System.currentTimeMillis()), freeAccess);

        directoryService.create(directory);

        PrintWriter pw = resp.getWriter();
        pw.println("Directory created");
    }
}
