import java.util.List;

public abstract class Storable {
    private final Long id;
    private Storable parentId;
    private final User author;
    private String name;
    private Boolean onModeration;
    private Boolean freeAccess;
    private List<User> moderators;
    private List<User> editors;
    private List<User> readers;

    public Storable(Long id, User author, String name, Boolean freeAccess) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.freeAccess = freeAccess;
    }

    public Storable(Long id, Storable parentId, User author, String name, Boolean freeAccess) {
        this.id = id;
        this.parentId = parentId;
        this.author = author;
        this.name = name;
        this.freeAccess = freeAccess;
    }

    public Long getId() {
        return id;
    }

    public Storable getParentId() {
        return parentId;
    }

    public User getAuthor() {
        return author;
    }

    public Boolean getOnModeration() {
        return onModeration;
    }

    public Boolean getFreeAccess() {
        return freeAccess;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public List<User> getEditors() {
        return editors;
    }

    public List<User> getReaders() {
        return readers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Storable parentId) {
        this.parentId = parentId;
    }

    public void setOnModeration(Boolean onModeration) {
        this.onModeration = onModeration;
    }

    public void setFreeAccess(Boolean freeAccess) {
        this.freeAccess = freeAccess;
    }

    public void addModerator(User moderator) {
        this.moderators.add(moderator);
    }

    public void addEditor(User editor) {
        this.editors.add(editor);
    }

    public void addReader(User reader) {
        this.readers.add(reader);
    }

    public void removeModerator(User moderator) {
        this.moderators.remove(moderator);
    }

    public void removeEditor(User editor) {
        this.editors.remove(editor);
    }

    public void removeReader(User reader) {
        this.readers.remove(reader);
    }
}
