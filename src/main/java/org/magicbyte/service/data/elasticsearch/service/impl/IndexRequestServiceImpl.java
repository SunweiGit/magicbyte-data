package org.magicbyte.service.data.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.magicbyte.service.data.elasticsearch.service.IndexRequestService;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author sunwei
 */
@Service
@AllArgsConstructor
public class IndexRequestServiceImpl implements IndexRequestService {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public IndexResponse index(String index, String id, JSONObject source) throws IOException {
        IndexRequest indexRequest = new IndexRequest()
                .index(index)
                .id(id)
                .source(source);
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }


}
