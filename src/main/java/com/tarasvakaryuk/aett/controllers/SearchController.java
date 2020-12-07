package com.tarasvakaryuk.aett.controllers;

import com.tarasvakaryuk.aett.entity.Picture;
import com.tarasvakaryuk.aett.service.ImagesService;
import com.tarasvakaryuk.aett.specs.SearchCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@Api("/search")
@Controller
@RequestMapping(value = "/search")
public class SearchController {

    private final ImagesService imagesService;

    @ApiOperation(value = "Search images ", response = ArrayList.class, httpMethod = "GET", consumes = "application/json", produces = "application/json", protocols = "http")
    @PostMapping(value = "")
    @ResponseBody
    public ArrayList<Picture> searchPictures(@RequestBody ArrayList<SearchCriteria> searchTerm) {
        return imagesService.searchPictures(searchTerm);
    }
}
