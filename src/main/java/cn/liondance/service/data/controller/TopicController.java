package cn.liondance.service.data.controller;

import cn.liondance.service.data.common.Result;
import cn.liondance.service.data.service.TopicService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * The type Home controller.
 *
 * @author sunwei
 */
@Slf4j
@RestController
@Api(tags = "topic")
@AllArgsConstructor
public class TopicController {

  private final TopicService cognitiveCardService;

  @ApiOperation(value = "创建话题")
  @GetMapping(value = {"createTopic"})
  public Result createTopic(HttpServletRequest request, Integer page, String parentId)
      throws IOException {
    log.error("parentId[{}] page [{}]", parentId, page);
    String userInfo = request.getHeader("userInfo");
    SearchHits searchHits =
        cognitiveCardService.findByParentId(
            page, parentId, StringUtils.isEmpty(userInfo) ? "" : userInfo);
    Optional<SearchHit> optional = Arrays.stream(searchHits.getHits()).findFirst();
    if (optional.isPresent()) {
      Map<String, Object> map = Maps.newHashMap();
      map.put("source", optional.get().getSourceAsMap());
      map.put("totalValue", searchHits.getTotalHits().value);
      return Result.ok().setData(map);
    } else {
      return Result.ok();
    }
  }

  @ApiOperation(value = "创建话题内容")
  @GetMapping(value = {"createTopicContent"})
  public Result createTopicContent(HttpServletRequest request, Integer page, String parentId)
      throws IOException {
    log.error("parentId[{}] page [{}]", parentId, page);
    String userInfo = request.getHeader("userInfo");
    SearchHits searchHits =
        cognitiveCardService.findByParentId(
            page, parentId, StringUtils.isEmpty(userInfo) ? "" : userInfo);
    Optional<SearchHit> optional = Arrays.stream(searchHits.getHits()).findFirst();
    if (optional.isPresent()) {
      Map<String, Object> map = Maps.newHashMap();
      map.put("source", optional.get().getSourceAsMap());
      map.put("totalValue", searchHits.getTotalHits().value);
      return Result.ok().setData(map);
    } else {
      return Result.ok();
    }
  }
}
