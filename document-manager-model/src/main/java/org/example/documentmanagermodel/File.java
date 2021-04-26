package org.example.documentmanagermodel;

public class File {
    private final Long id;
    private String name;
    private String path;
    private String type;

    public File(Long id, String name, String path, String type) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
