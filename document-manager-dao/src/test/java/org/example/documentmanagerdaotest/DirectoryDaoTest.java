package org.example.documentmanagerdaotest;

import org.example.documentmanagerdao.DbConnector;
import org.example.documentmanagermodel.Directory;
import org.example.documentmanagermodel.Statuses;
import org.example.documentmanagermodel.StorableType;
import org.example.documentmanagermodel.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class DirectoryDaoTest {

    public final User user = new User(2L, "ivan", "ivan", "ivanov@mail.ru", false);
    public final Directory AnimalsDirectory = new Directory(null, Optional.empty(), user, "Animals",
            StorableType.DIRECTORY, Statuses.CURRENT, new Timestamp(System.currentTimeMillis()), true);
    public final Directory catsDirectory = new Directory(null, Optional.of(AnimalsDirectory), user, "Cats",
            StorableType.DIRECTORY, Statuses.CURRENT, new Timestamp(System.currentTimeMillis()), true);

    @Before
    public void setUpTestDatabase() {
        Connection c = new DbConnector().createTestDb();
    }

    @After
    public void clearDatabase() {
        Connection c = new DbConnector().cleanTestDb();
    }


  //TODO
  //directoryDao connects to main database, instead of TestDb
    @Test
    public void testFindAll() {
//        org.example.documentmanagerdao.DirectoryDao directoryDao = new org.example.documentmanagerdao.DirectoryDaoImpl();
//        List<org.example.documentmanagermodel.Directory> result = directoryDao.findAll();
//        assertEquals(AnimalsDirectory.getAuthor().getLogin(), result.get(1).getAuthor().getLogin());
//        assertEquals(catsDirectory.getAuthor().getLogin(), result.get(0).getAuthor().getLogin());
//        assertEquals(AnimalsDirectory.getName(), result.get(1).getName());
//        assertEquals(catsDirectory.getName(), result.get(0).getName());
//        assertEquals(AnimalsDirectory.getStatus(), result.get(1).getStatus());
//        assertEquals(catsDirectory.getStatus(), result.get(0).getStatus());
//        assertEquals(AnimalsDirectory.getFreeAccess(), result.get(1).getFreeAccess());
//        assertEquals(catsDirectory.getFreeAccess(), result.get(0).getFreeAccess());
    }
}
