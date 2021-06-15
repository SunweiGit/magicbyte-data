package cn.liondance.service.data.service;

import cn.liondance.service.data.entity.MinioObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Minio object service.
 *
 * @author sunwei
 */
public interface MinioObjectService {

  /**
   * Put object minio object.
   *
   * @param minioObject the minio object
   * @param file the file
   * @return the minio object
   * @throws Exception the exception
   */
  MinioObject putObject(MinioObject minioObject, MultipartFile file) throws Exception;
}
