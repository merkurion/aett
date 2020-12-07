package com.tarasvakaryuk.aett.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDetails {

    private List<Picture> pictures;
    private Integer page;
    private Integer pageCount;
    private Boolean hasMore;

}
