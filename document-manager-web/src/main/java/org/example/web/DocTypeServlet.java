package org.example.web;
import org.example.documentmanagerdao.DocTypeDao;
import org.example.documentmanagerdao.DocTypeDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DocTypeServlet", urlPatterns = {"/doctype"})
public class DocTypeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter pw = response.getWriter();
        pw.write("Doctype Here");
        DocTypeDao docTypeDao = new DocTypeDaoImpl();
    }
}