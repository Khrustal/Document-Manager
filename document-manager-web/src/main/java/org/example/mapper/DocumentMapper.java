package org.example.mapper;

import org.example.documentmanagerdto.DocumentDto;
import org.example.documentmanagermodel.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DocumentMapper {
    public static DocumentDto convert(Document document) {
        DocumentDto dto = new DocumentDto();
        dto.setId(document.getId());

        if(document.getParent().isPresent()) {
            dto.setParentDirName(document.getParent().get().getName());
            dto.setParentDirId(document.getParent().get().getId());
        }
        else {
            dto.setParentDirName(null);
            dto.setParentDirId(null);
        }

        dto.setAuthorId(document.getAuthor().getId());
        dto.setAuthorName((document.getAuthor().getLogin()));

        dto.setName(document.getName());
        dto.setStatus(document.getStatus().name());

        Date date = new Date(document.getCreation_DT().getTime());
        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);
        dto.setCreation_DT(formattedDate);

        dto.setFreeAccess(document.getFreeAccess());
        dto.setPriority(document.getPriority().name());
        dto.setDocTypeId(document.getDocType().getId());
        dto.setDocTypeName(document.getDocType().getType());

        if(document.getAncestor().isPresent()) {
            dto.setAncestorId(document.getAncestor().get().getId());
        }
        else {
            dto.setAncestorId(null);
        }

        return dto;
    }
}
