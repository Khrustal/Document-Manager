import java.util.List;
import java.util.Optional;

public interface DirectoryDao {
    public List<Directory> findAll();
    public Optional<Directory> find(Long id);
    public void create(Directory directory);
    public void update(Long id, String name);
    public List<Storable> getContents(Long directoryId);
}
