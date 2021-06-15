package cn.liondance.service.data.controller;

import cn.liondance.service.data.common.Result;
import cn.liondance.service.data.service.TopicService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Api(tags = "Home")
@AllArgsConstructor
public class HomeController {

  private final TopicService cognitiveCardService;

  /**
   * Redirect http servlet response.
   *
   * @param response the response
   * @param redirect the redirect
   * @return the http servlet response
   * @throws IOException the io exception
   */
  @ApiOperation(value = "请求重定向")
  @ApiParam(name = "redirect", required = true, defaultValue = "http://localhost:3302")
  @GetMapping(value = "/redirect")
  public String redirect(HttpServletResponse response, String redirect) throws IOException {
    return "redirect:/" + redirect;
  }

  /**
   * Forward http servlet request.
   *
   * @param request the request
   * @param response the response
   * @return the http servlet request
   * @throws IOException the io exception
   * @throws ServletException the servlet exception
   */
  @ApiOperation(value = "请求转发")
  @GetMapping(value = {"", "forward"})
  public HttpServletRequest forward(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    System.out.println("doc.html");
    request.getRequestDispatcher("/doc.html").forward(request, response);
    return request;
  }

  @ApiOperation(value = "请求推荐信息")
  @GetMapping(value = {"recommendInfo"})
  public Result recommendInfo(HttpServletRequest request, int page, int size) throws IOException {
    log.error("page[{}]", page);
    String userInfo = request.getHeader("userInfo");
    JSONArray jsonArray = new JSONArray();
    SearchHits searchHits =
        cognitiveCardService.recommendInfo(
            page, size, StringUtils.isEmpty(userInfo) ? "" : userInfo);
    Arrays.stream(searchHits.getHits()).forEach(o -> jsonArray.add(o.getSourceAsMap()));
    return Result.ok().setData(jsonArray);
  }

  @ApiOperation(value = "搜索")
  @GetMapping(value = {"search"})
  public Result search(HttpServletRequest request, int page, int size, String search)
      throws IOException {
    log.error("search[{}]", search);
    JSONArray jsonArray = new JSONArray();
    String userInfo = request.getHeader("userInfo");
    SearchHits searchHits =
        cognitiveCardService.search(
            page,
            size,
            StringUtils.isEmpty(search) ? "" : search,
            StringUtils.isEmpty(userInfo) ? "" : userInfo);
    Arrays.stream(searchHits.getHits())
        .forEach(
            o -> {
              jsonArray.add(o.getSourceAsMap());
            });
    return Result.ok().setData(jsonArray);
  }

  @ApiOperation(value = "通过父ID查询")
  @GetMapping(value = {"findByParentId"})
  public Result findByParentId(HttpServletRequest request, Integer page, String parentId)
      throws IOException {
    log.error("parentId[{}] page [{}]", parentId, page);
    String userInfo = request.getHeader("userInfo");
    SearchHits searchHits =
        cognitiveCardService.findByParentId(
            page, parentId, StringUtils.isEmpty(userInfo) ? "" : userInfo);
    Optional<SearchHit> optional = Arrays.stream(searchHits.getHits()).findFirst();
    if (optional.isPresent()) {
      Map<String, Object> map = Maps.newHashMap();
      Map<String, Object> map1 = optional.get().getSourceAsMap();
      map1.put("sound", JSONObject.parseObject(map1.get("sound").toString()));
      map.put("source", map1);
      map.put("totalValue", searchHits.getTotalHits().value);
      return Result.ok().setData(map);
    } else {
      return Result.ok();
    }
  }
}
