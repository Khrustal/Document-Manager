package org.example.mapper;

import org.example.documentmanagerdto.DirectoryDto;
import org.example.documentmanagermodel.Directory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectoryMapper {

    public static DirectoryDto convert(Directory directory) {
        DirectoryDto dto = new DirectoryDto();
        dto.setId(directory.getId());

        if(directory.getParent().isPresent()) {
            dto.setParentDirName(directory.getParent().get().getName());
            dto.setParentDirId(directory.getParent().get().getId());
        }
        else {
            dto.setParentDirName(null);
            dto.setParentDirId(null);
        }

        dto.setName(directory.getName());
        dto.setStatus(directory.getStatus().name());

        Date date = new Date(directory.getCreation_DT().getTime());
        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);
        dto.setCreation_DT(formattedDate);

        dto.setFreeAccess(directory.getFreeAccess());
        return dto;
    }

}
