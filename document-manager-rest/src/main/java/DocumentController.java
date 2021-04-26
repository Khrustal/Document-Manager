import org.example.documentmanagerdao.DirectoryDao;
import org.example.documentmanagerdao.DocumentDao;
import org.example.documentmanagerdao.UserDao;
import org.example.documentmanagerdto.DocumentDto;
import org.example.documentmanagerdto.StorableDto;
import org.example.documentmanagermodel.Directory;

public class DocumentController {
    DocumentDao documentDao;
    DirectoryDao directoryDao;
    UserDao userDao;

    public void create(String name, Directory parent) {}

    public DocumentDto get(Long id) {
        return null;
    }

    public void update(String name, String contents, Directory parent) {}

    public void delete() {}

    public StorableDto find(String name) {
        return null;
    }
}
