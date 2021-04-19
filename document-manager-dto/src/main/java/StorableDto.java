public class StorableDto {
    private String classname;
    private Long id;
    private Storable parentId;
    private User author;
    private String name;

    //To understand Document or Directory is sent
    public void setClassname(Class<?> object) {
        this.classname = object.getName();
    }

    public String getClassname() {
        return classname;
    }

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
