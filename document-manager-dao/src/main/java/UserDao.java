import java.util.List;

public interface UserDao {
    public User find(Long id);
    public List<User> findAll();
}
