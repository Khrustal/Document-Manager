package org.example.documentmanagerdao;

import org.example.documentmanagermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectoryDaoImpl implements DirectoryDao{

    private static final Logger logger
            = LoggerFactory.getLogger(DirectoryDaoImpl .class);

    public static final String SQL_SELECT_ALL_DIRECTORIES = "SELECT * FROM storable WHERE type = 'DIRECTORY'";
    public static final String SQL_SELECT_DIRECTORY = "SELECT * FROM storable WHERE id = ? AND type = 'DIRECTORY'";
    public static final String SQL_CREATE_DIRECTORY = "INSERT INTO storable (parent_id, name, author_id, type, free_access, status,\n" +
            "                      creation_DT, description, priority, document_type, ancestor_id)\n" +
            "                      VALUES (?, ?, ?, ?::storable_types, ?, ?::statuses, ?, null, null, null, null)";
    public static final String SQL_UPDATE_DIRECTORY = "UPDATE  storable SET name = ? WHERE id = ?";
    public static final String SQL_SELECT_CONTENTS= "SELECT * FROM storable WHERE parent_id = ? ORDER BY creation_dt";
    public static final String SQL_SELECT_CONTENTS_FROM_CORE = "SELECT * FROM storable WHERE parent_id IS NULL ORDER BY creation_dt";


    @Override
    public List<Directory> findAll() {
        List<Directory> directories = new ArrayList<Directory>();

        Connection c = new DbConnector().connect();
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_DIRECTORIES);
            while ( rs.next() ) {
                Long id = rs.getLong("id");

                //Get parent
                Long parentId = rs.getLong("parent_id");
                DirectoryDao directoryDao = new DirectoryDaoImpl();
                Optional<Directory> parentDirectory = directoryDao.find(parentId);

                String name = rs.getString("name");

                //Find author by id
                Long userId = rs.getLong("author_id");
                UserDao userDao = new UserDaoImpl();
                User user = userDao.find(userId);

                StorableType type = StorableType.valueOf(rs.getString("type"));
                Statuses status = Statuses.valueOf(rs.getString("status"));
                Boolean freeAccess = rs.getBoolean("free_access");
                Timestamp creation_DT = rs.getTimestamp("creation_dt");

                Directory directory = new Directory(id, parentDirectory, user, name, type, status,
                        creation_DT, freeAccess);
                directories.add(directory);
            }
            rs.close();
            statement.close();
            c.close();
        } catch ( Exception e ) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Retrieved documents from database successfully");
        return directories;
    }

    @Override
    public Optional<Directory> find(Long id) {
        Optional<Directory> result = Optional.empty();
        Connection c = new DbConnector().connect();
        try (PreparedStatement findDirectory = c.prepareStatement(SQL_SELECT_DIRECTORY))
        {
            findDirectory.setLong(1, id);
            ResultSet rs = findDirectory.executeQuery();
            while ( rs.next() ) {
                Long directoryId = rs.getLong("id");

                //Get parent org.example.documentmanagermodel.Directory
                Long parentId = rs.getLong("parent_id");
                DirectoryDao directoryDao = new DirectoryDaoImpl();
                Optional<Directory> parent = directoryDao.find(parentId);

                String name = rs.getString("name");

                //Find author by id
                Long userId = rs.getLong("author_id");
                UserDao userDao = new UserDaoImpl();
                User user = userDao.find(userId);

                StorableType type = StorableType.valueOf(rs.getString("type"));
                Statuses status = Statuses.valueOf(rs.getString("status"));
                Boolean freeAccess = rs.getBoolean("free_access");
                Timestamp creation_DT = rs.getTimestamp("creation_dt");

                result = Optional.of(new Directory(directoryId, null, user, name, type, status,
                        creation_DT, freeAccess));
            }
            rs.close();
            findDirectory.close();
            c.close();
        } catch (Exception e) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Retrieved directory from database successfully");
        return result;
    }

    @Override
    public void create(Directory directory) {
        Connection c = new DbConnector().connect();
        try (PreparedStatement createDirectory = c.prepareStatement(SQL_CREATE_DIRECTORY))
        {
            if(null != directory.getParent()) {
                createDirectory.setLong(1, directory.getParent().get().getId());
            }
            else {
                createDirectory.setNull(1, Types.NULL);
            }
            createDirectory.setString(2, directory.getName());
            createDirectory.setLong(3, directory.getAuthor().getId());
            createDirectory.setString(4, directory.getType().name());
            createDirectory.setBoolean(5, directory.getFreeAccess());
            createDirectory.setString(6, directory.getStatus().toString());
            createDirectory.setTimestamp(7, directory.getCreation_DT());
            createDirectory.executeUpdate();
            createDirectory.close();
            c.close();
        } catch (Exception e) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Saved directory in database successfully");
    }

    @Override
    public void update(Long id, String name) {
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        Optional<Directory> directory = directoryDao.find(id);
        Connection c = new DbConnector().connect();
        if(directory.isEmpty()) {
            logger.info("Directory not found");
        }
        else {
            try (PreparedStatement updateDirectory = c.prepareStatement(SQL_UPDATE_DIRECTORY)) {
                updateDirectory.setString(1, name);
                updateDirectory.setLong(2, id);
                updateDirectory.executeUpdate();
                updateDirectory.close();
                c.close();
            } catch (Exception e) {
                logger.info("Error while executing SQL statement");
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            logger.info("Updated directory in database successfully");
        }
    }

     //Return List ofStorable, but contents initialized as Document or Directory
    @Override
    public List<Storable> getContents(Long directoryId) {
        List<Storable> contents = new ArrayList<>();

        DirectoryDao directoryDao = new DirectoryDaoImpl();
        Connection c = new DbConnector().connect();
        String sqlStatement = (directoryId == null) ? SQL_SELECT_CONTENTS_FROM_CORE : SQL_SELECT_CONTENTS;
        try(PreparedStatement statement = c.prepareStatement(sqlStatement)) {
            Optional<Directory> parentDirectory = Optional.empty();
            if(null == directoryId) {
                parentDirectory = Optional.empty();
            }
            else {
                statement.setLong(1, directoryId);
            }
            ResultSet rs = statement.executeQuery();
            while ( rs.next() ) {

                Long id = rs.getLong("id");
                StorableType type = StorableType.valueOf(rs.getString("type"));
                Long userId = rs.getLong("author_id");
                UserDao userDao = new UserDaoImpl();
                User author = userDao.find(userId);
                Long parentId = rs.getLong("parent_id");
                parentDirectory = directoryDao.find(parentId);
                String name = rs.getString("name");
                Statuses status = Statuses.valueOf(rs.getString("status"));
                Boolean freeAccess = rs.getBoolean("free_access");
                Timestamp creation_DT = rs.getTimestamp("creation_dt");

                if(type == StorableType.DOCUMENT) {
                    String description = rs.getString("description");
                    Priorities priority = Priorities.valueOf(rs.getString("priority"));

                    //Find DocType
                    Long typeId = rs.getLong("document_type");
                    DocTypeDao docTypeDao = new DocTypeDaoImpl();
                    DocType docType = docTypeDao.find(typeId);
                    //DocType docType = docTypeDao.find(typeId);

                    //Find ancestor
                    Long ancestorId = rs.getLong("ancestor_id");
                    DocumentDao documentDao = new DocumentDaoImpl();
                    Optional<Document> ancestor = documentDao.find(ancestorId);
                    Document document = new Document(id, parentDirectory, author, name, type, status,
                            creation_DT, freeAccess, description, priority, docType, ancestor);
                    contents.add(document);
                }
                else {
                    Directory directory = new Directory(directoryId, parentDirectory, author, name, type, status,
                            creation_DT, freeAccess);
                    contents.add(directory);
                }
            }
            rs.close();
            statement.close();
            c.close();
        } catch ( Exception e ) {
            logger.error("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Retrieved contents from database successfully");
        return contents;
    }
}
