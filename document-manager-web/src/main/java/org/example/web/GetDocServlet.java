package org.example.web;

import org.example.documentmanagerdao.DocumentDao;
import org.example.documentmanagerdao.DocumentDaoImpl;
import org.example.documentmanagermodel.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class GetDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.parseLong(rq.getParameter("id"));
        DocumentDao documentDao = new DocumentDaoImpl();
        Optional<Document> document = documentDao.find(id);
        PrintWriter pw = resp.getWriter();
        pw.println("Name: " + document.get().getName());
        pw.println("Description: " + document.get().getDescription());
    }
}
