package org.example.documentmanagerservices;

import org.example.documentmanagerdao.DocTypeDao;
import org.example.documentmanagerdao.DocTypeDaoImpl;
import org.example.documentmanagermodel.DocType;

import java.util.List;

public class DocTypeServiceImpl implements DocTypeService{

    private final DocTypeDao docTypeDao = new DocTypeDaoImpl();

    @Override
    public List<DocType> findAll() {
        return docTypeDao.findAll();
    }
}
