package com.cm.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile file);

    String getURLFromPublicId(String publicId);
}
