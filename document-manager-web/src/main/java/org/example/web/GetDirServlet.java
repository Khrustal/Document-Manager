package org.example.web;

import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.DirectoryDaoImpl;
import org.example.documentmanagermodel.Directory;
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
import java.util.Optional;

public class GetDirServlet extends HttpServlet {

    private DirectoryService directoryService;

    @Override
    public void init() throws ServletException {
        directoryService = new DirectoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.parseLong(rq.getParameter("id"));
        Optional<Directory> directory = directoryService.find(id);
        PrintWriter pw = resp.getWriter();
        pw.println("Name: " + directory.get().getName());
    }
}
