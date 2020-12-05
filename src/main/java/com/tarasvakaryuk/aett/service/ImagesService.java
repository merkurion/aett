package com.tarasvakaryuk.aett.service;

import com.tarasvakaryuk.aett.configuration.AuthConfig;
import com.tarasvakaryuk.aett.dto.AuthResponse;
import com.tarasvakaryuk.aett.dto.PageDetails;
import com.tarasvakaryuk.aett.dto.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ImagesService {

    final AuthConfig authConfig;

    @Value(value = "${ae.api.images.url}")
    private String apiImagesUrl;

    public ArrayList<Picture> getAllPictures(Integer pageNumber) {
        if(pageNumber == null) pageNumber = 1;
        ArrayList<Picture> pictures = new ArrayList<>();
        while (true) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            AuthResponse authResponse = authConfig.getAuth();
            httpHeaders.setBearerAuth(authResponse.getToken());
            HttpEntity<String> request = new HttpEntity<>("body", httpHeaders);
            ResponseEntity<PageDetails> response = restTemplate
                    .exchange(apiImagesUrl+pageNumber, HttpMethod.GET, request, PageDetails.class);
            PageDetails pageDetails = response.getBody();
            pictures.addAll(pageDetails.getPictures());
            if (!pageDetails.getHasMore()){
                break;
            }
            pageNumber++;
        }

        return pictures;
    }
}
