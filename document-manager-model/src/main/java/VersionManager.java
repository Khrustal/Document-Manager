import java.util.ArrayList;
import java.util.List;

public class VersionManager {
    private final Long id;
    private Document current;
    private final List<Document> versions = new ArrayList<Document>();

    /**
     We use only two params, because at first current version is the only version of document
     */
    public VersionManager(Long id, Document current) {
        this.id = id;
        this.current = current;
        versions.add(current);
    }

    public Document getCurrent() {
        return current;
    }

    public void setCurrent(Document current) {
        this.current = current;
    }

    public void addVersion(Document document) {
        this.current = document;
        versions.add(document);
    }

    public Long getId() {
        return id;
    }

    public List<Document> getVersions() {
        return versions;
    }
}
