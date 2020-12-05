package com.tarasvakaryuk.aett.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PageDetails {

    private ArrayList<Picture> pictures;
    private Integer page;
    private Integer pageCount;
    private Boolean hasMore;

}
