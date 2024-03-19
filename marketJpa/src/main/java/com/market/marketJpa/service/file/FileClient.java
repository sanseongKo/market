package com.market.marketJpa.service.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class FileClient {
    private static final String BLANK = "";
    private static final String DOT = ".";
    private static final String SLASH = "/";

    /**
     * @Return: 파일이 저장된 경로(파일이 null이라면 예외처리하지 않고 file_url을 빈 문자열 처리합니다.)
     */
    public String createFile(
            MultipartFile file,
            LocalDate now,
            String topLevelDirectoryPath,
            UUID verifyId
    ) {
        Optional<MultipartFile> fileOptional = Optional.ofNullable(file);

        if (fileOptional.isEmpty()) {
            return BLANK;
        }

        String nowString = now.toString();
        String baseDirectoryPath = getBaseDirectoryPath(topLevelDirectoryPath, nowString);
        createDirectory(baseDirectoryPath);

        String originalFileName = Optional.ofNullable(file.getOriginalFilename()).orElseThrow();
        String fullDirectoryPath = getFullDirectoryPath(originalFileName, baseDirectoryPath, verifyId);

        try {
            createFile(file, fullDirectoryPath);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return fullDirectoryPath;
    }

    private void createFile(MultipartFile file, String key) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(key);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
    }

    private void createDirectory(String baseDirectoryPath) {
        File tempFile = new File(baseDirectoryPath);
        System.out.println(tempFile.getAbsolutePath());
        if(!tempFile.exists()) {
            tempFile.mkdirs();
        }
    }

    private String getFullDirectoryPath(
            String originalFileName,
            String baseDirectoryPath,
            UUID verifyId
    ) {
        int index = originalFileName.lastIndexOf(DOT);
        String extension = originalFileName.substring(index + 1).toLowerCase();
        String fileNameToSave = verifyId + DOT + extension;

        return baseDirectoryPath + fileNameToSave;
    }

    private String getBaseDirectoryPath(String topLevelDirectoryPath, String nowString) {
        return topLevelDirectoryPath + SLASH + nowString + SLASH;
    }
}
