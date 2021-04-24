import java.sql.Timestamp;
import java.util.List;

public class Document extends Storable{
    private String description;
    private Priorities priority;
    private DocType docType;
    private User ancestor;

    public Document(Long id, Storable parentId, User author, String name,
                    Types type, Statuses status, Timestamp creation_DT, Boolean freeAccess,
                    String description, Priorities priority, DocType docType, User ancestor) {
        super(id, parentId, author, name, type, status, creation_DT, freeAccess);
        this.description = description;
        this.priority = priority;
        this.docType = docType;
        this.ancestor = ancestor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priorities getPriority() {
        return priority;
    }

    public void setPriority(Priorities priority) {
        this.priority = priority;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public User getAncestor() {
        return ancestor;
    }

    public void setAncestor(User ancestor) {
        this.ancestor = ancestor;
    }
}
