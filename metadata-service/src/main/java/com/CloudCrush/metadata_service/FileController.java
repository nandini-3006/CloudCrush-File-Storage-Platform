package com.CloudCrush.metadata_service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/metadata")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final StorageClient storageClient;
    private final JWTUtil jwtUtil;
    @Autowired
    private NotificationController notificationController;

    @PostMapping("/upload")
    public ResponseEntity<FileMetadata> uploadMetadata(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) throws IOException {

        // ✅ Extract user email from JWT
        String email = jwtUtil.extractEmail(request.getHeader("Authorization"));

        // ✅ Upload file to storage-service (S3)
        ResponseEntity<String> uploadResponse = storageClient.uploadFile(file);
        String fileUrl = uploadResponse.getBody();

        // ✅ Create metadata
        FileMetadata metadata = new FileMetadata();
        metadata.setFilename(file.getOriginalFilename());
        metadata.setSize(file.getSize());
        metadata.setOwner(email);
        metadata.setUrl(fileUrl);

        // ✅ Save in DB
        FileMetadata saved = fileService.save(metadata);
        notificationController.sendNotification("✅ File uploaded successfully: " + file.getOriginalFilename());
        return ResponseEntity.ok(saved);

    }

    @GetMapping("/my-files")
    public ResponseEntity<List<FileMetadata>> getMyFiles(HttpServletRequest request) {
        // ✅ Extract email from JWT
        String email = jwtUtil.extractEmail(request.getHeader("Authorization"));

        // ✅ Fetch from DB
        List<FileMetadata> files = fileService.getFilesByOwner(email);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(
            @PathVariable String filename,
            HttpServletRequest request) {

        // ✅ Extract email from JWT
        String email = jwtUtil.extractEmail(request.getHeader("Authorization"));

        // ✅ Fetch metadata
        FileMetadata metadata = fileService.getFileByFilenameAndOwner(filename, email);
        if (metadata == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not found or unauthorized".getBytes());
        }

        // ✅ Call storage-service using file URL
        ResponseEntity<byte[]> response = storageClient.downloadFile(metadata.getUrl());
        notificationController.sendNotification("✅ File Downloaded successfully: " + metadata.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(response.getBody());
    }

    @GetMapping("/owner/{owner}")
    public ResponseEntity<List<FileMetadata>> getUserFiles(@PathVariable String owner) {
        return ResponseEntity.ok(fileService.getFilesByOwner(owner));
    }

    @GetMapping("/{owner}/{filename}")
    public ResponseEntity<FileMetadata> getFile(
            @PathVariable String owner,
            @PathVariable String filename) {
        return ResponseEntity.ok(fileService.getFileByFilenameAndOwner(filename, owner));
    }


    // ✅ Delete File
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam String filename, HttpServletRequest request) {
        String email = jwtUtil.extractEmail(request.getHeader("Authorization"));

        FileMetadata file = fileService.getFileByFilenameAndOwner(filename, email);
        if (file == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not found or unauthorized");
        }
        DTO req = new DTO();
        req.setFileUrl(file.getUrl());

        storageClient.deleteFile(req);
        fileService.deleteByFilenameAndOwner(filename, email);
        notificationController.sendNotification("✅ File Deleted successfully: " + filename);
        return ResponseEntity.ok("File deleted successfully");
    }


    @GetMapping("/filenames")
    public ResponseEntity<List<String>> getUserFilenames(HttpServletRequest request) {
        String email = jwtUtil.extractEmail(request.getHeader("Authorization"));
        List<FileMetadata> files = fileService.getFilesByOwner(email);

        // extract filenames only
        List<String> filenames = files.stream()
                .map(FileMetadata::getFilename)
                .toList();

        return ResponseEntity.ok(filenames);
    }
    @GetMapping("/search")
    public ResponseEntity<List<FileMetadata>> searchFiles(
            @RequestParam("query") String query,
            HttpServletRequest request) {

        String email = jwtUtil.extractEmail(request.getHeader("Authorization"));
        List<FileMetadata> files = fileService.searchFilesByFilename(email, query);
        return ResponseEntity.ok(files);
    }
}

