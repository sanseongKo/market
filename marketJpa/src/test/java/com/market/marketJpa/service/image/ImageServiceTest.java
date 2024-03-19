package com.market.marketJpa.service.image;

import com.market.marketJpa.repository.image.ImageRepository;
import com.market.marketJpa.service.file.FileClient;
import com.market.marketJpa.vo.image.Image;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ImageServiceTest {
    @Autowired
    private FileClient fileClient;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;

    @AfterEach
    void tearDown() {
        imageRepository.deleteAllInBatch();
    }

    @DisplayName("생성된 파일 경로와 Image 테이블에 들어간 파일 경로와 같다.")
    @Test
    void createImage() {
        //given
        String fileName = "테스트 파일.jpg";
        String originalFilename = "테스트 파일.jpg";
        byte[] byteFile = "byteFile".getBytes();
        MultipartFile file = new MockMultipartFile(fileName, originalFilename, null, byteFile);

        LocalDate now = LocalDate.now();
        UUID verifyId = UUID.randomUUID();

        //when
        Image image = imageService.createImage(file, now, verifyId);

        //then
        Image savedImage = imageRepository.findById(image.getImgId()).get();
        assertThat(savedImage.getImgUrl()).isEqualTo("./images/" + now + "/" + verifyId + ".jpg");
    }

    @DisplayName("이미지가 없는 경우 Image 엔티티는 만들어지지 않는다.")
    @Test
    void createImageWithoutImage() {
        //given
        LocalDate now = LocalDate.now();
        UUID verifyId = UUID.randomUUID();

        //when
        Image image = imageService.createImage(null, now, verifyId);

        //then
        assertThatThrownBy(() -> imageRepository.findById(image.getImgId()).get())
                .isInstanceOf(NullPointerException.class);
    }
}
