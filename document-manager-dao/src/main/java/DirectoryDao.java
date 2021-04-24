import java.util.List;

public interface DirectoryDao {
    public List<Directory> findAll();
    public Directory find(Long id);
    public void create();
}
