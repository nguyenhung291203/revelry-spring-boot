package com.develop.revelryspringboot.service.minio;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    String upload(MultipartFile file);

    byte[] download(String bucket, String name);
}
