package org.example.documentmanagerdto;

import java.sql.Timestamp;

public class DocumentDto {
    private Long id;
    private String parentDirName;
    private Long parentDirId;
    private String authorName;
    private Long authorId;
    private String name;
    private String description;
    private String status;
    private String creation_DT;
    private boolean freeAccess;
    private String priority;
    private String docTypeName;
    private Long docTypeId;
    private Long ancestorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentDirName() {
        return parentDirName;
    }

    public void setParentDirName(String parentDirName) {
        this.parentDirName = parentDirName;
    }

    public Long getParentDirId() {
        return parentDirId;
    }

    public void setParentDirId(Long parentDirId) {
        this.parentDirId = parentDirId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreation_DT() {
        return creation_DT;
    }

    public void setCreation_DT(String creation_DT) {
        this.creation_DT = creation_DT;
    }

    public boolean isFreeAccess() {
        return freeAccess;
    }

    public void setFreeAccess(boolean freeAccess) {
        this.freeAccess = freeAccess;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public Long getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(Long docTypeId) {
        this.docTypeId = docTypeId;
    }

    public Long getAncestorId() {
        return ancestorId;
    }

    public void setAncestorId(Long ancestorId) {
        this.ancestorId = ancestorId;
    }
}
