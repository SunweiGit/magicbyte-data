package cn.liondance.service.data.service.impl;

import cn.liondance.service.data.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author sunwei
 */
@Slf4j
@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final RestHighLevelClient restHighLevelClient;


    @Override
    public SearchHits recommendInfo(int page, int size, String userInfo) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("magic_byte_topic");
        searchRequest.source(new SearchSourceBuilder()
                .fetchSource("src,sound,id,name,description,topic".split(","), null)
                .from((page-1) * size)
                .size(size)
                .sort("sort", SortOrder.DESC)
                .query(new BoolQueryBuilder()
                        .must(QueryBuilders.termQuery("topic.keyword","topic"))
                ));
        log.error("{}", searchRequest.source());
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse.getHits();
    }

    @Override
    public SearchHits search(int page, int size, String search, String userInfo) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("magic_byte_topic");
        searchRequest.source(new SearchSourceBuilder()
                .fetchSource("src,sound,id,name,description,topic".split(","), null)
                .from((page-1) * size)
                .size(size)
                .sort("sort", SortOrder.DESC)
                .query(new BoolQueryBuilder()
                        .mustNot(QueryBuilders.termQuery("topic.keyword","topic"))
                        .must(new BoolQueryBuilder()
                                .should(QueryBuilders.multiMatchQuery(search, "name.zh_CN", "name.en", "description","src"))
                        )));
        log.error("{}", searchRequest.source());
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse.getHits();
    }


    @Override
    public SearchHits findByParentId(int page, String parentid, String userInfo) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("magic_byte_topic");
        searchRequest.source(new SearchSourceBuilder()
                .fetchSource("src,sound,id,name,description,topic,".split(","), null)
                .from((page-1) * 1)
                .size(1)
                .sort("sort", SortOrder.ASC)
                .query(new BoolQueryBuilder()
                        .mustNot(QueryBuilders.termQuery("topic.keyword","topic"))
                        .must(QueryBuilders.termQuery("parentid.keyword", parentid))
                ));
        log.error("{}", searchRequest.source());
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse.getHits();
    }
}
