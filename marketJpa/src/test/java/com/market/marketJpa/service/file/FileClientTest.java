package com.market.marketJpa.service.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class FileClientTest {

    private final FileClient fileClient = new FileClient();

    @DisplayName("특정 폴더 하위에 파일을 생성한 후 파일 경로를 얻는다.")
    @Test
    void createFile() {
        //given
        String fileName = "테스트 파일.jpg";
        String originalFilename = "테스트 파일.jpg";
        byte[] byteFile = "byteFile".getBytes();
        MultipartFile file = new MockMultipartFile(fileName, originalFilename, null, byteFile);

        String topLevelDirectoryPath = "./test_folder";

        LocalDate now = LocalDate.now();
        UUID verifyId = UUID.randomUUID();

        //when
        String filePath = fileClient.createFile(file, now, topLevelDirectoryPath, verifyId);

        //then
        assertThat(filePath).isEqualTo("./test_folder/" + now + "/" + verifyId + ".jpg");
    }

    @DisplayName("파일이 없으면 파일 경로가 빈문자열이 된다.")
    @Test
    void test() {
        //given
        String topLevelDirectoryPath = "./test_folder";

        LocalDate now = LocalDate.now();
        UUID verifyId = UUID.randomUUID();
        //when
        String filePath = fileClient.createFile(null, now, topLevelDirectoryPath, verifyId);
        //then
        assertThat(filePath).isEqualTo("");
    }
}
