package cn.liondance.service.data.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.Builder;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * The type Lion dance http utils.
 *
 * @author sunwei
 */
@Builder
public final class LionDanceMinioUtils {
    public static MinioClient minioClient;
    public static String domain="http://118.195.140.140:9000";
    private static String accessKey="minio";
    private static String secretKey="%SQknI%%o5Va82F#akH";


    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(PutObjectArgs.builder().build());
    }

    public static MinioClient getMinioClient() {
        if (null == minioClient) {
            minioClient = MinioClient
                    .builder()
                    .endpoint(domain)
                    .credentials(accessKey, secretKey)
                    .build();
        }
        return minioClient;
    }

}
