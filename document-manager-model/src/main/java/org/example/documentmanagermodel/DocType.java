package org.example.documentmanagermodel;

public class DocType {
    private final Long id;
    private String type;

    public DocType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
