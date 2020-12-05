package com.tarasvakaryuk.aett.controllers;

import com.tarasvakaryuk.aett.dto.Picture;
import com.tarasvakaryuk.aett.service.ImagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@RequiredArgsConstructor
@Api("/images")
@Controller
@RequestMapping(value = "/images")
public class ImagesController {

    final ImagesService imagesService;

    @ApiOperation(value = "Get images", response = ArrayList.class, httpMethod = "GET", consumes = "application/json", produces = "application/json", protocols = "http")
    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseBody
    public ArrayList<Picture> getPictures(@RequestParam(required = false) Integer pageNumber) {
        return imagesService.getAllPictures(pageNumber);
    }
}
