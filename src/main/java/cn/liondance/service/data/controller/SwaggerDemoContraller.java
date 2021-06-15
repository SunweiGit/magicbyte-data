package cn.liondance.service.data.controller;

import cn.liondance.service.data.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author sunwei */
@Slf4j
@RestController
@RequestMapping("Swagger")
@Api(value = "API 测试")
public class SwaggerDemoContraller {

  @PostMapping("/demo")
  @ApiOperation(value = "post请求调用示例", notes = "invokePost说明", httpMethod = "POST")
  public Result invokePost(
      @ApiParam(name = "传入对象", value = "传入json格式", required = true) @RequestBody String input) {
    return Result.ok().setData("sss");
  }
}
