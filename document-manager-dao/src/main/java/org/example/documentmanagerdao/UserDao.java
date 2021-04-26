package org.example.documentmanagerdao;

import org.example.documentmanagermodel.Storable;
import org.example.documentmanagermodel.User;

import java.util.List;

public interface UserDao {
    public User find(Long id);
    public List<User> findAll();
}
