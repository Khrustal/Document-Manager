package org.example.documentmanagermodel;

import java.sql.Timestamp;
import java.util.Optional;

public class Directory extends Storable{

    public Directory(Long id, Optional<Directory> parent, User author, String name, StorableType type, Statuses status, Timestamp creation_DT, Boolean freeAccess) {
        super(id, parent, author, name, type, status, creation_DT, freeAccess);
    }
}
