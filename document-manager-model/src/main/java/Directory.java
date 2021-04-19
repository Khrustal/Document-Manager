import java.util.List;

public class Directory extends Storable{
    public Directory(Long id, User author, String name, Boolean freeAccess) {
        super(id, author, name, freeAccess);
    }

    public Directory(Long id, Storable parentId, User author, String name, Boolean freeAccess) {
        super(id, parentId, author, name, freeAccess);
    }

    public List<Storable> getContents() {
        return null; //TODO
    }
}
