import java.util.List;

public class DirectoryContentsDto {
    private Long id;
    private Storable parentId;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Storable getParentId() {
        return parentId;
    }

    public void setParentId(Storable parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
