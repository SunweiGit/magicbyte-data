package cn.liondance.service.data.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @Author sunwei
 *
 * @date ：Created in 2020/1/20 -14:58
 * @description：Successful result
 * @modified By：
 * @version: 1.0$
 */
@Data
@Builder
@ApiModel(value = "返回结果类", description = "返回结果类")
public class Result<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(example = "结果返回时间戳")
  private Long timestamp;

  @ApiModelProperty(example = "结果返回状态码")
  private int status;

  @ApiModelProperty(example = "结果返回消息")
  private String message;

  @ApiModelProperty(example = "结果返回内容")
  private T data;

  public static Result ok() {
    return Result.builder()
        .status(HttpStatus.OK.value())
        .message(HttpStatus.OK.name())
        .timestamp(System.currentTimeMillis())
        .build();
  }

  public static Result error() {
    return Result.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
        .timestamp(System.currentTimeMillis())
        .build();
  }

  public Result setData(T data) {
    this.data = data;
    return this;
  }
}
