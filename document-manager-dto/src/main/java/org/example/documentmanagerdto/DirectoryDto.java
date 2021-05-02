package org.example.documentmanagerdto;

import java.util.Date;

public class DirectoryDto {
    private Long id;
    private String parentDirName;
    private Long parentDirId;
    private String name;
    private String status;
    private String creation_DT;
    private boolean freeAccess;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
