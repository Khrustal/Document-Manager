import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DocumentDaoImpl implements DocumentDao{

    private static final Logger logger
            = LoggerFactory.getLogger(DocumentDaoImpl .class);

    public static final String SQL_SELECT_ALL_DOCUMENTS = "SELECT * FROM storable WHERE type = 'DOCUMENT'";

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
                Directory parentDirectory = directoryDao.find(parentId);

                //Find author by id
                Long userId = rs.getLong("author_id");
                UserDao userDao = new UserDaoImpl();
                User author = userDao.find(userId);

                String name = rs.getString("name");
                Types type = Types.valueOf(rs.getString("type"));
                Statuses status = Statuses.valueOf(rs.getString("status"));
                Boolean freeAccess = rs.getBoolean("free_access");
                Timestamp creation_DT = rs.getTimestamp("creation_dt");
                String description = rs.getString("description");
                Priorities priority = Priorities.valueOf(rs.getString("priority"));

                //Find DocType
                Long typeId = rs.getLong("document_type");
                DocTypeDao docTypeDao = new DocTypeDaoImpl();
                DocType docType = docTypeDao.find(typeId);

                //Find ancestor
                Long ancestorId = rs.getLong("ancestor_id");
                User ancestor = userDao.find(ancestorId);

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
    public Document find(Long id) {
        return null;
    }

    @Override
    public List<Document> findInDirectory(Long dirId) {
        return null;
    }
}
