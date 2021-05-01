package org.example.documentmanagerservices;

import org.example.documentmanagerdao.UserDao;
import org.example.documentmanagerdao.UserDaoImpl;
import org.example.documentmanagermodel.User;

public class UserServiceImpl implements UserService{

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User find(Long id) {
        return userDao.find(id);
    }
}
