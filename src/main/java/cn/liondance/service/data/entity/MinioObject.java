package cn.liondance.service.data.entity;

import lombok.Data;

/** @author sunwei */
@Data
public class MinioObject {

  private String id;
  private String bucket;
  private String object;
  private String createTime;
}
