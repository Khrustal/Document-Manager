package org.example.documentmanagerservices;

import org.example.documentmanagermodel.Storable;

import java.util.List;

public interface FinderService {
    public List<Storable> findByName(String name);
    public List<Storable> findContents(Long dirId);
}
