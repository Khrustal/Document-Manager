import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger
            = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        //Logger demo
        logger.info("Example log from {}", Main.class.getSimpleName());


        //List all document types
        DocTypeDao docTypeDao = new DocTypeDaoImpl();
        List<DocType> docTypes = docTypeDao.findAll();

        System.out.println("Document types: ");
        for(DocType docType : docTypes) {
            System.out.println(docType.getType());
        }

        //List all directories
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        List<Directory> directories = directoryDao.findAll();

        System.out.println("Directories: ");
        for(Directory directory : directories) {
            System.out.println(directory.getName());
        }

        //Get Directory with id = 2
        Directory directory = directoryDao.find(2L);
        System.out.println(directory.getName());

        //Get All Documents
        DocumentDao documentDao = new DocumentDaoImpl();
        List<Document> documents = documentDao.findAll();
        for(Document document : documents) {
            System.out.println(document.getName());
        }
    }
}
