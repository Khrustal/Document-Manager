import java.util.List;

public class DirectoryController {
    DirectoryRepository directoryRepository;
    UserRepository userRepository;
    FinderService finderService;

     public void create(String name, Directory parent) {}

     public List<DirectoryContentsDto> getContents(Long id) {
         List<Storable> contents = finderService.findContents(id);
         return null; //ToDo make conversion
     }

    public void update(String name, Directory parent) {}

    public void delete() {}
}
