package org.example.documentmanagerservices;

import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.Storable;

import java.util.List;
import java.util.Optional;

public interface DirectoryService {
    public Optional<Directory> find(Long id);
    public void create(Directory directory);
    public List<Storable> getContents(Long id);
}
