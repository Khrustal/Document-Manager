import java.util.List;

public interface DocumentDao {
    public List<Document> findAll();
    public Document find(Long id);
    public void create(Document document);
}
