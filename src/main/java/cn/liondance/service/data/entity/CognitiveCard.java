package cn.liondance.service.data.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/** @author sunwei */
@Data
public class CognitiveCard implements Serializable {

  private String id;
  private String parentId;
  private String name;
  private String description;
  private JSONObject sound;
  private String image;
  private Integer sort;
  private String createTime;
  private String createBy;
  private String updateTime;
  private String updateBy;
}
