package org.example.documentmanagerdao;

import org.example.documentmanagermodel.DocType;

import java.util.List;

public interface DocTypeDao {
    public List<DocType> findAll();
    public DocType find(Long id);
}
