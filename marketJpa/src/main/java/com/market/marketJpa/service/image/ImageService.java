package com.market.marketJpa.service.image;

import com.market.marketJpa.repository.image.ImageRepository;
import com.market.marketJpa.service.file.FileClient;
import com.market.marketJpa.vo.image.Image;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ImageService {
    private static final String DIRECTORY_PATH = "./images";

    private final FileClient fileClient;

    private final ImageRepository imageRepository;

    @Transactional
    public Image createImage(MultipartFile image, LocalDate now, UUID verifyId) {
        if (image == null) {
            return null;
        }

        String imgUrl = fileClient.createFile(image, now, DIRECTORY_PATH, verifyId);

        Image entity = new Image(imgUrl);

        return imageRepository.save(entity);
    }
}
