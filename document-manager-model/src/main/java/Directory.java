import java.sql.Timestamp;

public class Directory extends Storable{

    public Directory(Long id, Directory parentId, User author, String name, Types type, Statuses status, Timestamp creation_DT, Boolean freeAccess) {
        super(id, parentId, author, name, type, status, creation_DT, freeAccess);
    }
}
