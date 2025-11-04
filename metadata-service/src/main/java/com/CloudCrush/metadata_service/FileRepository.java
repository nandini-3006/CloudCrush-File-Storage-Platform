package com.CloudCrush.metadata_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FileRepository extends JpaRepository<FileMetadata, Long> {
    List<FileMetadata> findByOwner(String owner);
    Optional<FileMetadata> findByFilenameAndOwner(String filename, String owner);
    void deleteByFilenameAndOwner(String filename, String owner);
    List<FileMetadata> findByOwnerAndFilenameContainingIgnoreCase(String owner, String filename);
}
