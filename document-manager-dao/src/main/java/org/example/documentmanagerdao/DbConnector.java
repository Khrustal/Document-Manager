package org.example.documentmanagerdao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {

    public static final String CREATE_SCRIPT_PATH = "C:\\Users\\III\\Documents\\JavaProjects\\" +
            "org.example.documentmanagermodel.Document-Manager\\org.example.documentmanagermodel.Document-Manager\\document-manager-dao\\src\\main\\resources\\DB_Create_Test_Case.sql";

    public static final String CLEAN_SCRIPT_PATH = "C:\\Users\\III\\Documents\\JavaProjects\\" +
            "org.example.documentmanagermodel.Document-Manager\\org.example.documentmanagermodel.Document-Manager\\document-manager-dao\\src\\main\\resources\\DB_Drop_Tables.sql";

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

    public Connection connectTest() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/document_manager_test",
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

    public Connection createTestDb() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/document_manager_test",
                            "postgres", "Bosporez3");
            ScriptRunner sr = new ScriptRunner(c);
            Reader reader = new BufferedReader((new FileReader(CREATE_SCRIPT_PATH)));
            sr.setLogWriter(null);
            sr.runScript(reader);
        } catch (Exception e) {
            logger.info("Error while connecting to Database");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        logger.info("Connected to database successfully");
        logger.info("Script executed successfully");
        return c;
    }

    public Connection cleanTestDb() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/document_manager_test",
                            "postgres", "Bosporez3");
            ScriptRunner sr = new ScriptRunner(c);
            Reader reader = new BufferedReader((new FileReader(CLEAN_SCRIPT_PATH)));
            sr.setLogWriter(null);
            sr.runScript(reader);
        } catch (Exception e) {
            logger.info("Error while connecting to Database");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        logger.info("Connected to database successfully");
        logger.info("Script executed successfully");
        return c;
    }
}
