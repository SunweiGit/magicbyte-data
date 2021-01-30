package org.magicbyte.service.data.elasticsearch.service;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.index.IndexResponse;

import java.io.IOException;

/**
 * The interface Index request service.
 *
 * @author sunwei
 */
public interface IndexRequestService {


    /**
     * Index index response.
     *
     * @param index  the index
     * @param id     the id
     * @param source the source
     * @return the index response
     * @throws IOException the io exception
     */
    IndexResponse index(String index, String id, JSONObject source) throws IOException;
}
