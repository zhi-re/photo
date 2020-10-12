package org.zhire.service;

import org.springframework.web.multipart.MultipartFile;
import org.zhire.dao.Photo;
import org.zhire.dao.Sort;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface PhotoService {
    Photo save(MultipartFile file, HttpServletRequest request);

    List<Sort> getSort();

    List<Photo> getSortByOne(Long id);

    Photo getPhotoOne(Long id);
}
