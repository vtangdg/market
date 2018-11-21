package com.example.market.api;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by degang on 2018/8/11
 */
public interface FileService {
    String save(MultipartFile file, String day);
}
