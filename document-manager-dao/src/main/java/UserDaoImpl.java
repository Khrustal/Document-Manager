import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDaoImpl implements UserDao{

    private static final Logger logger
            = LoggerFactory.getLogger(UserDaoImpl.class);

    public static final String SQL_SELECT_USER = "SELECT * FROM \"user\" WHERE id = ?";


    @Override
    public User find(Long id) {
        Connection c = new DbConnector().connect();
        User user = null;
        try (PreparedStatement findDUser = c.prepareStatement(SQL_SELECT_USER))
        {
            findDUser.setLong(1, id);
            ResultSet rs = findDUser.executeQuery();
            while ( rs.next() ) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Boolean admin = rs.getBoolean("admin");
                user = new User(userId, login, password, email, admin);
            }
            rs.close();
            findDUser.close();
            c.close();
        } catch ( Exception e ) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Retrieved user from database successfully");
        return user;
    }
}
