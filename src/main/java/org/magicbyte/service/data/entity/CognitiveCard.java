package org.magicbyte.service.data.entity;

import lombok.Data;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author sunwei
 */
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
