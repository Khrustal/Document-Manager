import java.sql.Timestamp;
import java.util.List;

public abstract class Storable {
    private final Long id;
    private Storable parentId;
    private final User author;
    private String name;
    private Types type;
    private Statuses status;
    private Timestamp creation_DT;
    private Boolean freeAccess;

    public Storable(Long id, Storable parentId, User author, String name,
                    Types type, Statuses status, Timestamp creation_DT,
                    Boolean freeAccess) {
        this.id = id;
        this.parentId = parentId;
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

    public Storable getParentId() {
        return parentId;
    }

    public void setParentId(Storable parentId) {
        this.parentId = parentId;
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

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
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
