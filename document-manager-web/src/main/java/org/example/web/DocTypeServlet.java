package org.example.web;

import org.example.documentmanagermodel.DocType;
import org.example.documentmanagerservices.DocTypeService;
import org.example.documentmanagerservices.DocTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DocTypeServlet extends HttpServlet {

    private DocTypeService docTypeService;

    @Override
    public void init() throws ServletException {
        docTypeService = new DocTypeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter pw = response.getWriter();
        pw.write("Document types\n");
        List<DocType> docTypes = docTypeService.findAll();
        for(DocType docType : docTypes) {
            pw.println(docType.getType());
        }
    }
}