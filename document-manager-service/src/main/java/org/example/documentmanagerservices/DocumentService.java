package org.example.documentmanagerservices;

import org.example.documentmanagermodel.Document;

import java.util.Optional;

public interface DocumentService {
    public Optional<Document> find(Long id);
    public void create(Document document);
}
