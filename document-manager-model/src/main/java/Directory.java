import java.sql.Timestamp;
import java.util.List;

public class Directory extends Storable{

    public Directory(Long id, Storable parentId, User author, String name, Types type, Statuses status, Timestamp creation_DT, Boolean freeAccess) {
        super(id, parentId, author, name, type, status, creation_DT, freeAccess);
    }
}
