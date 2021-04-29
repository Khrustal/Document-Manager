package org.example.documentmanagermodel;

import java.sql.Timestamp;
import java.util.Optional;

public class Document extends Storable{
    private String description;
    private Priorities priority;
    private DocType docType;
    private Optional<Document> ancestor;

    public Document(Long id, Optional<Directory> parentId, User author, String name,
                    StorableType type, Statuses status, Timestamp creation_DT, Boolean freeAccess,
                    String description, Priorities priority, DocType docType, Optional<Document> ancestor) {
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

    public Optional<Document> getAncestor() {
        return ancestor;
    }

    public void setAncestor(Optional<Document> ancestor) {
        this.ancestor = ancestor;
    }
}
