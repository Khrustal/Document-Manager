import java.util.List;

public class DocumentController {
    DocumentRepository documentRepository;
    DirectoryRepository directoryRepository;
    UserRepository userRepository;

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
