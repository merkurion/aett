package com.tarasvakaryuk.aett.schedulers;

import com.tarasvakaryuk.aett.service.ImagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImageUploadScheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final ImagesService imagesService;

    @Scheduled(fixedRate = 10000)
    public void uploadImages() {
        log.info("Uploading images start: {}", dateFormat.format(new Date()));
        imagesService.uploadImages();
        log.info("Uploading images end: {}", dateFormat.format(new Date()));
    }
}
