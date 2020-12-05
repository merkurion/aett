package com.tarasvakaryuk.aett.dao;

import com.tarasvakaryuk.aett.dto.Picture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends CrudRepository<Picture, String> {

    Optional<Picture> findById(String id);

    List<Picture> findAllById(String id);
}
