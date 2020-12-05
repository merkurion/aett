package com.tarasvakaryuk.aett.controllers;

import com.tarasvakaryuk.aett.dto.Picture;
import com.tarasvakaryuk.aett.service.ImagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@Api("/images")
@Controller
@RequestMapping(value = "/images")
public class ImagesController {

    final ImagesService imagesService;

    @ApiOperation(value = "Get images", response = ArrayList.class, httpMethod = "GET", consumes = "application/json", produces = "application/json", protocols = "http")
    @GetMapping(value = "")
    @ResponseBody
    public ArrayList<Picture> getPictures(@RequestParam(required = false) Integer pageNumber) {
        return imagesService.getPictures(pageNumber);
    }

    @ApiOperation(value = "Get picture details", response = ArrayList.class, httpMethod = "GET", consumes = "application/json", produces = "application/json", protocols = "http")
    @GetMapping(value = "/{pictureId}")
    @ResponseBody
    public Picture getPictureDetails(@PathVariable("pictureId") String pictureId) {
        return imagesService.getPictureDetails(pictureId);
    }
}
