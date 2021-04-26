import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DocTypeDaoTest {

    @Before
    public void setUpTestDatabase() {
        Connection c = new DbConnector().createTestDb();
    }

    @After
    public void clearDatabase() {
        Connection c = new DbConnector().cleanTestDb();
    }

    @Test
    public void testFindAll() {
        DocTypeDao docTypeDao = new DocTypeDaoImpl();
        List<DocType> docTypes = docTypeDao.findAll();
        assertEquals("Fax", docTypes.get(0).getType());
        assertEquals("Order", docTypes.get(1).getType());
        assertEquals("Topic", docTypes.get(2).getType());
    }

    @Test
    public void testFind() {
        DocTypeDao docTypeDao = new DocTypeDaoImpl();
        DocType result = docTypeDao.find(1L);
        assertEquals("Fax", result.getType());
    }

}
