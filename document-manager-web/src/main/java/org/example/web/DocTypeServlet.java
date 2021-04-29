package org.example.web;
import org.example.documentmanagerdao.DocTypeDao;
import org.example.documentmanagerdao.DocTypeDaoImpl;
import org.example.documentmanagermodel.DocType;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DocTypeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter pw = response.getWriter();
        pw.write("Document types\n");
        DocTypeDao docTypeDao = new DocTypeDaoImpl();
        List<DocType> docTypes = docTypeDao.findAll();
        for(DocType docType : docTypes) {
            pw.println(docType.getType());
        }
    }
}