package com.cm.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.cm.helper.AppConstants;
import com.cm.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        String fileName=UUID.randomUUID().toString();

        try{
            byte data[]=new byte[file.getInputStream().available()];

            file.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id",fileName
            ));

            return this.getURLFromPublicId(fileName);
        }catch(Exception e){
            e.printStackTrace();

            return null;
        }

    }

    @Override
    public String getURLFromPublicId(String publicId) {
        return cloudinary
                .url()
                .transformation(
                new Transformation<>().width(AppConstants.CONTACT_IMAGE_WIDTH).height(AppConstants.CONTACT_IMAGE_HEIGHT).crop(AppConstants.CONTACT_IMAGE_CROP)).generate(publicId);

    }
    
}
