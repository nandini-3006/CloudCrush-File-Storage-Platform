package com.CloudCrush.metadata_service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    @Cacheable(value = "filesByOwner", key = "#owner")
    public List<FileMetadata> getFilesByOwner(String owner) {
        return fileRepository.findByOwner(owner);
    }

    @CacheEvict(value = "filesByOwner", key = "#file.owner")
    public FileMetadata save(FileMetadata file) {
        return fileRepository.save(file);
    }

    public FileMetadata getFileByFilenameAndOwner(String filename, String owner) {
        return fileRepository.findByFilenameAndOwner(filename, owner)
                .orElseThrow(() -> new RuntimeException("File not found for user: " + owner));
    }

    @CacheEvict(value = "filesByOwner", key = "#owner")
    @Transactional
    public void deleteByFilenameAndOwner(String filename, String owner) {
        fileRepository.deleteByFilenameAndOwner(filename, owner);
    }
    public List<FileMetadata> searchFilesByFilename(String owner, String filename) {
        return fileRepository.findByOwnerAndFilenameContainingIgnoreCase(owner, filename);
    }
}