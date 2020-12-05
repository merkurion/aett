package com.tarasvakaryuk.aett.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Setter
public class Picture implements Serializable {

    private static final long serialVersionUID = 1479248742064970361L;

    private String id;
    @JsonProperty("cropped_picture")
    private String croppedPicture;

}
