package org.zhire.service.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zhire.dao.Photo;
import org.zhire.dao.Sort;
import org.zhire.repository.PhotoRepository;
import org.zhire.repository.SortRepository;
import org.zhire.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private SortRepository sortRepository;

    private static List<Sort> sorts = new LinkedList<>();

    private static Map<Long, List<Photo>> listMap = new LinkedHashMap<>();


    @Override
    public Photo save(MultipartFile uploadFile, HttpServletRequest request) {
        String path = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File file = new File("/uploads", path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = UUID.randomUUID().toString().replace("-", "").toUpperCase()
                + uploadFile.getOriginalFilename();
        try {
            uploadFile.transferTo(new File(file, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Photo photo = new Photo();
        photo.setCtime(System.currentTimeMillis());
        photo.setTitle("image");
        photo.setUrl("");
        photo.setThumbUrl("");
        return null;
    }

    @Override
    public List<Sort> getSort() {
        if (CollUtil.isEmpty(sorts)) {
            List<Sort> list = sortRepository.findAll();
            sorts.addAll(list);
        }
        supplyAsync(() -> {
            List<Sort> list = sortRepository.findAll();
            if (list.size() != sorts.size()) {
                sorts.clear();
                sorts.addAll(list);
            }
            return sorts;
        });
        return sorts;
    }

    @Override
    public List<Photo> getSortByOne(Long id) {
        if (CollUtil.isEmpty(listMap.get(id))) {
            List<Photo> list = photoRepository.findAllByClassId(id);
            listMap.put(id, list);
        }
        supplyAsync(() -> {
            List<Photo> list = photoRepository.findAllByClassId(id);
            if (listMap.get(id).size() != list.size()) {
                listMap.clear();
                listMap.put(id, list);
            }
            return listMap;
        });
        return listMap.get(id);
    }

    @Override
    public Photo getPhotoOne(Long id) {
        Optional<Photo> photo = photoRepository.findById(id);
        return photo.orElseGet(Photo::new);
    }
}
