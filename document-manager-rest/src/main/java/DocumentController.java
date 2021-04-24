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
