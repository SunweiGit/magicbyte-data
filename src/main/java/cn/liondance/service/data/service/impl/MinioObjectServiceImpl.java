package cn.liondance.service.data.service.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import cn.liondance.service.data.entity.MinioObject;
import cn.liondance.service.data.service.MinioObjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Minio object service.
 *
 * @author sunwei
 */
@Service
@AllArgsConstructor
public class MinioObjectServiceImpl implements MinioObjectService {
    private final RestHighLevelClient  restHighLevelClient;
    private final MinioClient minioClient;


    @Override
    public MinioObject putObject(MinioObject minioObject, MultipartFile file) throws Exception {
        PutObjectArgs putObjectArgs = PutObjectArgs
                .builder()
                .bucket(minioObject.getBucket())
                .object(minioObject.getObject())
                .contentType(file.getContentType())
                .stream(file.getInputStream(),file.getSize(),-1)
                .build();
        try {
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
      //  minioObjectRepository.save(minioObject);
        return minioObject;
    }
}
