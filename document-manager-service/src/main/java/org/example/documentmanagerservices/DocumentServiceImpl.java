package org.example.documentmanagerservices;

import org.example.documentmanagerdao.DocumentDao;
import org.example.documentmanagerdao.DocumentDaoImpl;
import org.example.documentmanagermodel.Document;

import java.util.Optional;

public class DocumentServiceImpl implements DocumentService{

    private final DocumentDao documentDao = new DocumentDaoImpl();

    @Override
    public Optional<Document> find(Long id) {
        return documentDao.find(id);
    }

    @Override
    public void create(Document document) {
        documentDao.create(document);
    }
}
