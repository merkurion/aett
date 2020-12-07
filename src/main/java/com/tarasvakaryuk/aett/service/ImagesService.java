package com.tarasvakaryuk.aett.service;

import com.tarasvakaryuk.aett.configuration.AuthConfig;
import com.tarasvakaryuk.aett.dao.PictureRepository;
import com.tarasvakaryuk.aett.entity.AuthResponse;
import com.tarasvakaryuk.aett.entity.PageDetails;
import com.tarasvakaryuk.aett.entity.Picture;
import com.tarasvakaryuk.aett.specs.PictureSpecification;
import com.tarasvakaryuk.aett.specs.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImagesService {

    private final AuthConfig authConfig;
    private final PictureRepository pictureRepository;

    @Value(value = "${ae.api.images.page.url}")
    private String apiImagesPageUrl;

    @Value(value = "${ae.api.images.details.url}")
    private String apiImagesDetailsUrl;

    public void uploadImages() {
        ArrayList<Picture> pictures = getPictures(null);
        pictures.forEach(picture -> {
            picture = getPictureDetails(picture.getId());
            pictureRepository.save(picture);
        });
    }

    public ArrayList<Picture> getPictures(Integer pageNumber) {
        if (pageNumber == null) pageNumber = 1;
        ArrayList<Picture> pictures = new ArrayList<>();
        while (true) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            AuthResponse authResponse = authConfig.getAuth();
            httpHeaders.setBearerAuth(authResponse.getToken());
            HttpEntity<String> request = new HttpEntity<>("body", httpHeaders);
            ResponseEntity<PageDetails> response = restTemplate
                    .exchange(apiImagesPageUrl + pageNumber, HttpMethod.GET, request, PageDetails.class);
            PageDetails pageDetails = response.getBody();
            pictures.addAll(pageDetails.getPictures());
            if (!pageDetails.getHasMore()) {
                break;
            }
            pageNumber++;
        }

        return pictures;
    }

    public Picture getPictureDetails(String pictureId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        AuthResponse authResponse = authConfig.getAuth();
        httpHeaders.setBearerAuth(authResponse.getToken());
        HttpEntity<String> request = new HttpEntity<>("body", httpHeaders);
        ResponseEntity<Picture> response = restTemplate
                .exchange(apiImagesDetailsUrl + pictureId, HttpMethod.GET, request, Picture.class);

        return response.getBody();
    }

    public ArrayList<Picture> searchPictures(List<SearchCriteria> searchTerm) {
        PictureSpecification pictureSpecification = new PictureSpecification(searchTerm);

        return (ArrayList<Picture>) pictureRepository.findAll(pictureSpecification);
    }
}
