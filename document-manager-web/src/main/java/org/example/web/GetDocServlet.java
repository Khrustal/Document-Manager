package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.documentmanagerdao.DocumentDao;
import org.example.documentmanagerdao.DocumentDaoImpl;
import org.example.documentmanagerdto.DirectoryDto;
import org.example.documentmanagerdto.DocumentDto;
import org.example.documentmanagermodel.Document;
import org.example.documentmanagerservices.*;
import org.example.mapper.DirectoryMapper;
import org.example.mapper.DocumentMapper;

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

        DocumentDto dto = DocumentMapper.convert(document.get());

        ObjectMapper mapper = new ObjectMapper();

        String result = mapper.writeValueAsString(dto);

        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        pw.print(result);
    }
}
