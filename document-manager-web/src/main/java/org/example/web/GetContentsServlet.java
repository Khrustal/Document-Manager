package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.DirectoryDaoImpl;
import org.example.documentmanagerdto.DirectoryDto;
import org.example.documentmanagerdto.DocumentDto;
import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.Document;
import org.example.documentmanagermodel.Storable;
import org.example.documentmanagerservices.DirectoryService;
import org.example.documentmanagerservices.DirectoryServiceImpl;
import org.example.mapper.DirectoryMapper;
import org.example.mapper.DocumentMapper;

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

        DocumentDto docDto = null;
        DirectoryDto dirDto = null;

        ObjectMapper mapper = new ObjectMapper();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        for(Storable content : contents) {
            if(content.getClass() == Document.class) {
                docDto = DocumentMapper.convert((Document) content);
                pw.println(mapper.writeValueAsString(docDto));
            }
            else {
                dirDto = DirectoryMapper.convert((Directory) content);
                pw.println(mapper.writeValueAsString(dirDto));
            }
        }

    }
}
