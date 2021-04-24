import java.util.List;

public class DirectoryController {
    DirectoryDao directoryDao;
    UserDao userDao;
    FinderService finderService;

     public void create(String name, Directory parent) {}

     public List<DirectoryContentsDto> getContents(Long id) {
         List<Storable> contents = finderService.findContents(id);
         return null; //ToDo make conversion
     }

    public void update(String name, Directory parent) {}

    public void delete() {}
}
