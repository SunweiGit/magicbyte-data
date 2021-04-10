package org.magicbyte.service.data.service;


import org.magicbyte.service.data.entity.MinioObject;
import org.springframework.web.multipart.MultipartFile;


/**
 * The interface Minio object service.
 *
 * @author sunwei
 */
public interface BaiduService {


    /**
     * Put object minio object.
     *
     * @param minioObject the minio object
     * @param file        the file
     * @return the minio object
     * @throws Exception the exception
     */
    void putObject(MinioObject minioObject, MultipartFile file) throws Exception;
}
