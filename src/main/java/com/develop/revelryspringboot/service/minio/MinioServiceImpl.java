package com.develop.revelryspringboot.service.minio;

import static com.develop.revelryspringboot.constant.error.FileErrorCode.FILE_DOWNLOAD_FAILED;
import static com.develop.revelryspringboot.constant.error.FileErrorCode.FILE_UPLOAD_FAILED;

import java.util.Objects;

import jakarta.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.develop.revelryspringboot.exception.ApiException;
import com.develop.revelryspringboot.utils.ConverterUtils;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    private static final String BUCKET = "resources";
    private final MinioClient minioClient;

    @PostConstruct
    private void init() {
        createBucket();
    }

    @SneakyThrows
    private void createBucket() {
        final var found = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(MinioServiceImpl.BUCKET).build());
        if (!found) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket(MinioServiceImpl.BUCKET).build());

            final var policy =
                    """
						{
						"Version": "2012-10-17",
						"Statement": [
						{
							"Effect": "Allow",
							"Principal": "*",
							"Action": "s3:GetObject",
							"Resource": "arn:aws:s3:::%s/*"
							}
						]
						}
					"""
                            .formatted(MinioServiceImpl.BUCKET);
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(MinioServiceImpl.BUCKET)
                    .config(policy)
                    .build());
        } else {
            log.info("Bucket %s đã tồn tại.".formatted(MinioServiceImpl.BUCKET));
        }
    }

    @SneakyThrows
    @Override
    public String upload(@NonNull final MultipartFile file) {
        log.info("Bucket: {}, file size: {}", BUCKET, file.getSize());
        final var fileName = file.getOriginalFilename();
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET)
                    .object(fileName)
                    .contentType(
                            Objects.isNull(file.getContentType()) ? "image/png; image/jpg;" : file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build());
        } catch (Exception ex) {
            log.error("Error saving image \n {} ", ex.getMessage());
            throw new ApiException(FILE_UPLOAD_FAILED);
        }
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(io.minio.http.Method.GET)
                .bucket(BUCKET)
                .object(fileName)
                .build());
    }

    @Override
    public byte[] download(String bucket, String name) {
        try (GetObjectResponse inputStream = minioClient.getObject(
                GetObjectArgs.builder().bucket(bucket).object(name).build())) {
            String contentLength = inputStream.headers().get(HttpHeaders.CONTENT_LENGTH);
            int size = StringUtils.isEmpty(contentLength) ? 0 : Integer.parseInt(contentLength);
            return ConverterUtils.readBytesFromInputStream(inputStream, size);
        } catch (Exception e) {
            throw new ApiException(FILE_DOWNLOAD_FAILED);
        }
    }
}
