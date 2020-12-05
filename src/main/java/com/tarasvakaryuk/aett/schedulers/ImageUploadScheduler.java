package com.tarasvakaryuk.aett.schedulers;

import com.tarasvakaryuk.aett.configuration.AuthConfig;
import com.tarasvakaryuk.aett.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ImageUploadScheduler {

    private static final Logger log = LoggerFactory.getLogger(ImageUploadScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final ImagesService imagesService;

    @Scheduled(fixedRate = 10000)
    public void uploadImages() {
        log.info("Uploading images start: {}", dateFormat.format(new Date()));
        imagesService.uploadImages();
        log.info("Uploading images end: {}", dateFormat.format(new Date()));
    }
}
