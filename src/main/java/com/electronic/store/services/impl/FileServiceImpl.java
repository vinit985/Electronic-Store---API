package com.electronic.store.services.impl;

import com.electronic.store.exception.IncorrectFileUploadException;
import com.electronic.store.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadImage(MultipartFile file, String path) throws IOException {

        String originalFileName = file.getOriginalFilename();
        logger.info("Original file name: {}", originalFileName);

        if (originalFileName == null) {
            return null;
        }

        String extension = originalFileName
                .substring(originalFileName.lastIndexOf('.') + 1)
                .toLowerCase();

        if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("jpeg")) {
            throw new IncorrectFileUploadException(
                    "Only file with extension png, jpg and jpeg is supported"
            );
        }

        String newFileName = UUID.randomUUID().toString();
        String fileNameWithExtension = newFileName + "." + extension;

        File folder = new File(path);
        if (!folder.exists()) {
            logger.info("Creating directory: {}", path);
            folder.mkdirs();
        }

        Path fullPath = Paths.get(path, fileNameWithExtension);
        logger.info("Final file path: {}", fullPath);

        Files.copy(
                file.getInputStream(),
                fullPath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return fileNameWithExtension;
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fileNameWithPath = path + File.separator + name;
        return new FileInputStream(fileNameWithPath);
    }
}
