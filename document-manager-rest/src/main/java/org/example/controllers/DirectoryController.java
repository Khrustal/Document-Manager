package org.example.controllers;

import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.UserDao;
import org.example.documentmanagerdto.DirectoryContentsDto;
import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.Storable;
import org.example.documentmanagerservices.FinderService;

import java.util.List;

public class DirectoryController {
    DirectoryDao directoryDao;
    UserDao userDao;
    FinderService finderService;

     public void create(String name, Directory parent) {}

     public List<DirectoryContentsDto> getContents(Long id) {
         List<Storable> contents = finderService.findContents(id);
         return null; //ToDo make conversion
     }

    public void update(String name, Directory parent) {}

    public void delete() {}
}
