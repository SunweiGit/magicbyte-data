package org.magicbyte.service.data.service;


import org.elasticsearch.search.SearchHits;

import java.io.IOException;

/**
 * The interface Cognitive card service.
 *
 * @author sunwei
 */
public interface TopicService {


    /**
     * Recommend info search hits.
     *
     * @param page     the page
     * @param size     the size
     * @param userInfo the user info
     * @return the search hits
     * @throws IOException the io exception
     */
    SearchHits recommendInfo(int page, int size, String userInfo) throws IOException;

    /**
     * Search search hits.
     *
     * @param page     the page
     * @param size     the size
     * @param search   the search
     * @param userInfo the user info
     * @return the search hits
     * @throws IOException the io exception
     */
    SearchHits search(int page, int size, String search, String userInfo) throws IOException;

    /**
     * Find by parent id search hits.
     *
     * @param page     the page
     * @param parentid the parentid
     * @param userInfo the user info
     * @return the search hits
     * @throws IOException the io exception
     */
    SearchHits findByParentId(int page, String parentid, String userInfo) throws IOException;
}
