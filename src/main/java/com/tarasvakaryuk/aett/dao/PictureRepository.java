package com.tarasvakaryuk.aett.dao;

import com.tarasvakaryuk.aett.dto.Picture;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends CrudRepository<Picture, String>, JpaSpecificationExecutor<Picture> {

    Optional<Picture> findById(String id);

    List<Picture> findAllById(String id);
}
