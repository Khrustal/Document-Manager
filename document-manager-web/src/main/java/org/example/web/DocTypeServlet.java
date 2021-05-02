package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.AppConfigSpring;
import org.example.documentmanagermodel.DocType;
import org.example.documentmanagerservices.DocTypeService;
import org.example.documentmanagerservices.DocTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigSpring.class);
        docTypeService = (DocTypeService) context.getBean("docTypeService");
        //docTypeService = new DocTypeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws IOException {

        PrintWriter pw = resp.getWriter();
        List<DocType> docTypes = docTypeService.findAll();

        ObjectMapper mapper = new ObjectMapper();

        String result = mapper.writeValueAsString(docTypes);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        pw.print(result);
    }
}