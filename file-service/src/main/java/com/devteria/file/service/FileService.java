package com.devteria.file.service;

import com.devteria.file.dto.response.FileResponse;
import com.devteria.file.mapper.FileMgmtMapper;
import com.devteria.file.repository.FileMgmtRepository;
import com.devteria.file.repository.FileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileService {

    FileMgmtRepository fileMgmtRepository;
    FileRepository fileRepository;
    FileMgmtMapper fileMgmtMapper;

    public FileResponse uploadFile(MultipartFile file) throws IOException {

        // Store file
        var fileInfo = fileRepository.store(file);

        // Create file management info
        var fileMgmt = fileMgmtMapper.toFileInfo(fileInfo);
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        fileMgmt.setOwnerId(userId);

        return FileResponse.builder()
                .originalFileName(file.getOriginalFilename())
                .url(fileInfo.getUrl())
                .build();
    }
}
