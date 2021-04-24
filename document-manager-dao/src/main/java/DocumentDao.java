import java.util.List;

public interface DocumentDao {
    public List<Document> findAll();
    public Document find(Long id);
    public List<Document> findInDirectory(Long dirId);
}
