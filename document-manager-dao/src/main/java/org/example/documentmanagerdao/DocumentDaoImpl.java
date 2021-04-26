package org.example.documentmanagerdao;

import org.example.documentmanagermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DocumentDaoImpl implements DocumentDao{

    private static final Logger logger
            = LoggerFactory.getLogger(DocumentDaoImpl .class);

    public static final String SQL_SELECT_ALL_DOCUMENTS = "SELECT * FROM storable WHERE type = 'DOCUMENT'";
    public static final String SQL_CREATE_DOCUMENT = "INSERT INTO storable (parent_id, name, author_id, type, free_access, status,\n" +
            "                      creation_DT, description, priority, document_type, ancestor_id)\n" +
            "                      VALUES (?, ?, ?, 'DOCUMENT', ?, ?::statuses, CURRENT_TIMESTAMP,\n" +
            "                              ?, ?::priorities, ?, ?)";

    @Override
    public List<Document> findAll() {
        List<Document> documents = new ArrayList<Document>();

        Connection c = new DbConnector().connect();
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_DOCUMENTS);
            while ( rs.next() ) {
                Long id = rs.getLong("id");

                //Get parent
                Long parentId = rs.getLong("parent_id");
                DirectoryDao directoryDao = new DirectoryDaoImpl();
                Optional<Directory> parentDirectory = directoryDao.find(parentId);

                //Find author by id
                Long userId = rs.getLong("author_id");
                UserDao userDao = new UserDaoImpl();
                User author = userDao.find(userId);

                String name = rs.getString("name");
                StorableType type = StorableType.valueOf(rs.getString("type"));
                Statuses status = Statuses.valueOf(rs.getString("status"));
                Boolean freeAccess = rs.getBoolean("free_access");
                Timestamp creation_DT = rs.getTimestamp("creation_dt");
                String description = rs.getString("description");
                Priorities priority = Priorities.valueOf(rs.getString("priority"));

                //Find org.example.documentmanagermodel.DocType
                Long typeId = rs.getLong("document_type");
                DocTypeDao docTypeDao = new DocTypeDaoImpl();
                DocType docType = docTypeDao.find(typeId);

                //Find ancestor
                Long ancestorId = rs.getLong("ancestor_id");
                DocumentDao documentDao = new DocumentDaoImpl();
                Storable ancestor = documentDao.find(ancestorId);

                Document document = new Document(id, parentDirectory, author, name, type, status,
                        creation_DT, freeAccess, description, priority, docType, ancestor);
                documents.add(document);
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
        return documents;
    }

    @Override
    public Document find(Long id) { //TODO
        return null;
    }

    @Override
    public void create(Document document) {
        Connection c = new DbConnector().connect();
        try (PreparedStatement createDocument = c.prepareStatement(SQL_CREATE_DOCUMENT))
        {
            if(null != document.getParent()) {
                createDocument.setLong(1, document.getParent().get().getId());
            }
            else {
                createDocument.setNull(1, Types.NULL);
            }
            createDocument.setString(2, document.getName());
            createDocument.setLong(3, document.getAuthor().getId());
            createDocument.setBoolean(4, document.getFreeAccess());
            createDocument.setString(5, document.getStatus().toString());
            createDocument.setString(6, document.getDescription());
            createDocument.setString(7, document.getPriority().name());
            createDocument.setLong(8, document.getDocType().getId());
            if(null != document.getAncestor()) {
                createDocument.setLong(9, document.getAncestor().getId());
            }
            else {
                createDocument.setNull(9, Types.NULL);
            }
            createDocument.executeUpdate();
            createDocument.close();
            c.close();
        } catch (Exception e) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Saved document in database successfully");
    }
}
