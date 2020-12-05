package com.tarasvakaryuk.aett.service;

import com.tarasvakaryuk.aett.configuration.AuthConfig;
import com.tarasvakaryuk.aett.dao.PictureRepository;
import com.tarasvakaryuk.aett.dto.AuthResponse;
import com.tarasvakaryuk.aett.dto.PageDetails;
import com.tarasvakaryuk.aett.dto.Picture;
import com.tarasvakaryuk.aett.specs.PictureSpecification;
import com.tarasvakaryuk.aett.specs.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ImagesService {

    final AuthConfig authConfig;
    final PictureRepository pictureRepository;

    @Value(value = "${ae.api.images.page.url}")
    private String apiImagesPageUrl;

    @Value(value = "${ae.api.images.details.url}")
    private String apiImagesDetailsUrl;

    public void uploadImages(){
        ArrayList<Picture> pictures = getPictures(null);
        for (Picture picture:
             pictures) {

            picture = getPictureDetails(picture.getId());
            pictureRepository.save(picture);
        }
    }

    public ArrayList<Picture> getPictures(Integer pageNumber) {
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
                    .exchange(apiImagesPageUrl +pageNumber, HttpMethod.GET, request, PageDetails.class);
            PageDetails pageDetails = response.getBody();
            pictures.addAll(pageDetails.getPictures());
            if (!pageDetails.getHasMore()){
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

    public ArrayList<Picture> searchPictures(ArrayList<SearchCriteria> searchTerm) {
        PictureSpecification pictureSpecification = new PictureSpecification();
        for (SearchCriteria searchCriteria:
                searchTerm) {
            pictureSpecification.add(searchCriteria);
        }

        return (ArrayList<Picture>) pictureRepository.findAll(pictureSpecification);
    }
}
