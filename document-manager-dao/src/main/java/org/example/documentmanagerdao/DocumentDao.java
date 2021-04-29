package org.example.documentmanagerdao;

import org.example.documentmanagermodel.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentDao {
    public List<Document> findAll();
    public Optional<Document> find(Long id);
    public void create(Document document);
}
