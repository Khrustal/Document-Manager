import java.util.List;

public interface DocTypeDao {
    public List<DocType> findAll();
    public DocType find(Long id);
}
