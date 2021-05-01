package org.example.web;

import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.DirectoryDaoImpl;
import org.example.documentmanagermodel.Storable;
import org.example.documentmanagerservices.DirectoryService;
import org.example.documentmanagerservices.DirectoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetContentsServlet extends HttpServlet {

    private DirectoryService directoryService;

    @Override
    public void init() throws ServletException {
        directoryService = new DirectoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.parseLong(rq.getParameter("id"));
        List<Storable> contents = directoryService.getContents(id);
        PrintWriter pw = resp.getWriter();
        for(Storable content : contents) {
            pw.println("Name: " + content.getName());
        }
    }
}
