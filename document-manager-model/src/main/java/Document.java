import java.util.List;

public class Document extends Storable{
    private String description;
    private List<File> files;
    private Boolean current;
    private VersionManager versionManager;

    public Document(Long id, User author, String name, Boolean freeAccess) {
        super(id, author, name, freeAccess);
    }

    public Document(Long id, Storable parentId, User author, String name, Boolean freeAccess) {
        super(id, parentId, author, name, freeAccess);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<File> getFiles() {
        return files;
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public void removeFile(File file) {
        this.files.remove(file);
    }

    public Boolean isCurrent() {return current;}

    public void setCurrent(Boolean current) {
        this.current = current;
    }
}
