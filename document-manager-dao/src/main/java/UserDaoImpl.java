import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private static final Logger logger
            = LoggerFactory.getLogger(UserDaoImpl.class);

    public static final String SQL_SELECT_USER = "SELECT * FROM \"user\" WHERE id = ?";
    public static final String SQL_SELECT_All_USERS = "SELECT * FROM \"user\"";

    @Override
    public User find(Long id) {
        Connection c = new DbConnector().connect();
        User user = null;
        try (PreparedStatement findUser = c.prepareStatement(SQL_SELECT_USER))
        {
            findUser.setLong(1, id);
            ResultSet rs = findUser.executeQuery();
            while ( rs.next() ) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Boolean admin = rs.getBoolean("admin");
                user = new User(userId, login, password, email, admin);
            }
            rs.close();
            findUser.close();
            c.close();
        } catch ( Exception e ) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Retrieved user from database successfully");
        return user;
    }

    @Override
    public List<User> findAll() {
        Connection c = new DbConnector().connect();
        List<User> users = new ArrayList<>();
        try (PreparedStatement findAllUsers = c.prepareStatement(SQL_SELECT_All_USERS))
        {
            ResultSet rs = findAllUsers.executeQuery();
            while ( rs.next() ) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Boolean admin = rs.getBoolean("admin");
                User user = new User(userId, login, password, email, admin);
                users.add(user);
            }
            rs.close();
            findAllUsers.close();
            c.close();
        } catch ( Exception e ) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Retrieved users from database successfully");
        return users;
    }
}
