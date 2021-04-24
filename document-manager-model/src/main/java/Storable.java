import java.sql.Timestamp;
import java.util.Optional;

public abstract class Storable {
    private final Long id;
    private Optional<Directory> parent;
    private final User author;
    private String name;
    private StorableType type;
    private Statuses status;
    private Timestamp creation_DT;
    private Boolean freeAccess;

    public Storable(Long id, Optional<Directory> parentId, User author, String name,
                    StorableType type, Statuses status, Timestamp creation_DT,
                    Boolean freeAccess) {
        this.id = id;
        this.parent = parentId;
        this.author = author;
        this.name = name;
        this.type = type;
        this.status = status;
        this.creation_DT = creation_DT;
        this.freeAccess = freeAccess;
    }

    public Long getId() {
        return id;
    }

    public Optional<Directory> getParent() {
        return parent;
    }

    public void setParent(Optional<Directory> parent) {
        this.parent = parent;
    }

    public User getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorableType getType() {
        return type;
    }

    public void setType(StorableType type) {
        this.type = type;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public Timestamp getCreation_DT() {
        return creation_DT;
    }

    public void setCreation_DT(Timestamp creation_DT) {
        this.creation_DT = creation_DT;
    }

    public Boolean getFreeAccess() {
        return freeAccess;
    }

    public void setFreeAccess(Boolean freeAccess) {
        this.freeAccess = freeAccess;
    }
}
