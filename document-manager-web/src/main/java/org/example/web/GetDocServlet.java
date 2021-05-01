package org.example.web;

import org.example.documentmanagerdao.DocumentDao;
import org.example.documentmanagerdao.DocumentDaoImpl;
import org.example.documentmanagermodel.Document;
import org.example.documentmanagerservices.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class GetDocServlet extends HttpServlet {

    private DocumentService documentService;

    @Override
    public void init() throws ServletException {
        documentService = new DocumentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.parseLong(rq.getParameter("id"));
        Optional<Document> document = documentService.find(id);
        PrintWriter pw = resp.getWriter();
        pw.println("Name: " + document.get().getName());
        pw.println("Description: " + document.get().getDescription());
    }
}
