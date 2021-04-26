package org.example.documentmanagerservices;

import org.example.documentmanagermodel.File;

public interface DocumentEditorService {
    public void editDescription(String description);
    public void addFile(File file);
}
