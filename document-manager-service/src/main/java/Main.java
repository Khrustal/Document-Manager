import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
        Optional<Directory> directory = directoryDao.find(2L);
        System.out.println(directory.get().getName());

        //Get All Documents
        DocumentDao documentDao = new DocumentDaoImpl();
        List<Document> documents = documentDao.findAll();
        for(Document document : documents) {
            System.out.println(document.getName());
        }

        //Get user with id = 2
        UserDao userDao = new UserDaoImpl();
        User user = userDao.find(2L);
        System.out.println(user.getLogin());

        //Create directory
//        Directory newDirectory = new Directory(null, null, user, "Example", StorableType.DIRECTORY,
//                Statuses.CURRENT, new Timestamp(System.currentTimeMillis()), true);
//        //System.out.println(newDirectory.getType().toString());
//        directoryDao.create(newDirectory);

        //Update directory
//        directoryDao.update(2L, "Test Update");

        //Create document
//        Document newDocument = new Document(null, null, user, "Example document",
//                StorableType.DOCUMENT, Statuses.CURRENT, new Timestamp(System.currentTimeMillis()), true,
//                "Example document", Priorities.LOW, new DocType(1L, "Fax"), null);
//        documentDao.create(newDocument);

        //Find contents of directory with id = 2
        List<Storable> contents = directoryDao.getContents(null);
        for(Storable content : contents) {
            System.out.println(content.getName());
        }
    }
}
