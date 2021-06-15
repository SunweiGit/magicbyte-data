package cn.liondance.service.data.controller;

import cn.liondance.service.data.common.Result;
import cn.liondance.service.data.service.LionDanceCloudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * The type Home controller.
 *
 * @author sunwei
 */
@Slf4j
@RestController
@Api(tags = "lionDanceCloud")
@AllArgsConstructor
public class LionDanceCloudController {

  private final LionDanceCloudService lionDanceCloudService;

  /**
   * Update text audio result.
   *
   * @return the result
   * @throws IOException the io exception
   */
  @ApiOperation(value = "更新文本语音")
  @GetMapping(value = {"updateTextAudio"})
  public Result updateTextAudio() throws IOException {
    lionDanceCloudService.updateTextAudio();
    return Result.ok();
  }

  /**
   * Update text translate result.
   *
   * @return the result
   * @throws IOException the io exception
   */
  @ApiOperation(value = "更新文本翻译")
  @GetMapping(value = {"updateTextTranslate"})
  public Result updateTextTranslate() throws IOException {
    lionDanceCloudService.updateTextTranslate();
    return Result.ok();
  }

  /**
   * Update translate result.
   *
   * @param tableName the table name
   * @param field the field
   * @return the result
   * @throws IOException the io exception
   */
  @ApiOperation(value = "更新表字段的翻译信息")
  @GetMapping(value = {"updateTranslate"})
  @ApiImplicitParams({
    @ApiImplicitParam(name = "tableName", value = "表名称", example = "user"),
    @ApiImplicitParam(name = "field", value = "字段名称", example = "name")
  })
  public Result updateTranslate(String tableName, String field) throws IOException {
    lionDanceCloudService.updateTranslate(tableName, field);
    return Result.ok();
  }

  /**
   * Update audio result.
   *
   * @param tableName the table name
   * @param field the field
   * @return the result
   * @throws IOException the io exception
   */
  @ApiOperation(value = "更新表字段的声音信息")
  @GetMapping(value = {"updateAudio"})
  @ApiImplicitParams({
    @ApiImplicitParam(name = "tableName", value = "表名称", example = "user"),
    @ApiImplicitParam(name = "field", value = "字段名称", example = "name")
  })
  public Result updateAudio(String tableName, String field) {
    lionDanceCloudService.updateAudio(tableName, field);
    return Result.ok();
  }
}
