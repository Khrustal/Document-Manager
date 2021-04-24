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
    public static final String SQL_INSERT_DIRECTORY = "INSERT INTO storable (parent_id, name, author_id, type, free_access, status,\n" +
            "                      creation_DT, description, priority, document_type, ancestor_id)\n" +
            "                      VALUES (?, ?, ?, ?::storable_types, ?, ?::statuses, ?, null, null, null, null)";

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
        Optional<Directory> result = null;
        Connection c = new DbConnector().connect();
        try (PreparedStatement findDirectory = c.prepareStatement(SQL_SELECT_DIRECTORY))
        {
            findDirectory.setLong(1, id);
            ResultSet rs = findDirectory.executeQuery();
            while ( rs.next() ) {
                Long directoryId = rs.getLong("id");

                //Get parent Directory
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
        try (PreparedStatement createDirectory = c.prepareStatement(SQL_INSERT_DIRECTORY))
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
}
