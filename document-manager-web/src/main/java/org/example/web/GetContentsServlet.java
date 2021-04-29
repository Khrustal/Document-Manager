package org.example.web;

import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.DirectoryDaoImpl;
import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.Storable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class GetContentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.parseLong(rq.getParameter("id"));
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        List<Storable> contents = directoryDao.getContents(id);
        PrintWriter pw = resp.getWriter();
        for(Storable content : contents) {
            pw.println("Name: " + content.getName());
        }
    }
}
