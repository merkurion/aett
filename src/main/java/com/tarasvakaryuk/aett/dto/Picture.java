package com.tarasvakaryuk.aett.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Picture implements Serializable {

    private static final long serialVersionUID = 1479248742064970361L;

    @Id
    private String id;
    @JsonProperty("cropped_picture")
    private String croppedPicture;
    private String author;
    private String camera;
    private String tags;
    @JsonProperty("full_picture")
    private String fullPicture;

}
