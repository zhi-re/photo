package org.zhire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zhire.dao.Photo;

import java.util.List;


public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByClassId(Long id);
}

