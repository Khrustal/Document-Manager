import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocTypeDaoImpl implements DocTypeDao{

    private static final Logger logger
            = LoggerFactory.getLogger(DocTypeDaoImpl.class);

    public static final String SQL_SELECT_ALL_DOCUMENT_TYPES = "SELECT * FROM document_types";
    public static final String SQL_SELECT_DOCUMENT_TYPE = "SELECT * FROM document_types WHERE id = ?";

    @Override
    public List<DocType> findAll() {
        List<DocType> docTypes = new ArrayList<DocType>();

        Connection c = new DbConnector().connect();
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_DOCUMENT_TYPES);
            while ( rs.next() ) {
                Long id = rs.getLong("id");
                String  typeName = rs.getString("type_name");
                DocType docType = new DocType(id, typeName);
                docTypes.add(docType);
            }
            rs.close();
            statement.close();
            c.close();
        } catch ( Exception e ) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Retrieved document types from database successfully");
        return docTypes;
    }

    @Override
    public DocType find(Long id) {
        DocType result = null;

        Connection c = new DbConnector().connect();
        try (PreparedStatement findDocType = c.prepareStatement(SQL_SELECT_DOCUMENT_TYPE))
        {
            findDocType.setLong(1, id);
            ResultSet rs = findDocType.executeQuery();
            while ( rs.next() ) {
                Long docTypeId = rs.getLong("id");
                String name = rs.getString("type_name");

                result = new DocType(docTypeId, name);
            }
            rs.close();
            findDocType.close();
            c.close();
        } catch (Exception e) {
            logger.info("Error while executing SQL statement");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        logger.info("Retrieved DocType from database successfully");

        return result;
    }
}
