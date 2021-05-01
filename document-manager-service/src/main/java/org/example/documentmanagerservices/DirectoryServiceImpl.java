package org.example.documentmanagerservices;

import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.DirectoryDaoImpl;
import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.Storable;

import java.util.List;
import java.util.Optional;

public class DirectoryServiceImpl implements DirectoryService{

    private final DirectoryDao directoryDao = new DirectoryDaoImpl();

    @Override
    public Optional<Directory> find(Long id) {
        return directoryDao.find(id);
    }

    @Override
    public void create(Directory directory) {
        directoryDao.create(directory);
    }

    @Override
    public List<Storable> getContents(Long id) {
        return directoryDao.getContents(id);
    }
}
