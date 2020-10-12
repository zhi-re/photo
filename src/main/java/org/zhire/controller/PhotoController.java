package org.zhire.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zhire.dao.Photo;
import org.zhire.dao.Sort;
import org.zhire.service.PhotoService;
import org.zhire.utils.R;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/get")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping("/save")
    public Map save(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Photo p = photoService.save(file, request);
        return R.ok();
    }

    @RequestMapping("/getSort")
    public R getSort() {
        List<Sort> list = photoService.getSort();
        return R.ok().put("data", list);
    }

    @RequestMapping("/getSortByOne")
    public R getSortByOne(@RequestParam("id") Long id) {
        List<Photo> list = photoService.getSortByOne(id);
        return R.ok().put("data", list);
    }

    @RequestMapping("/getPhotoOne")
    public R getPhotoOne(@RequestParam("id") Long id) {
        Photo photo = photoService.getPhotoOne(id);
        return R.ok().put("data", photo);
    }


}
