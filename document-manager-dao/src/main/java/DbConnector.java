import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {

    private static final Logger logger
            = LoggerFactory.getLogger(DbConnector.class);

    public Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/document_manager",
                            "postgres", "Bosporez3");
        } catch (Exception e) {
            logger.info("Error while connecting to Database");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        logger.info("Connected to database successfully");
        return c;
    }
}
