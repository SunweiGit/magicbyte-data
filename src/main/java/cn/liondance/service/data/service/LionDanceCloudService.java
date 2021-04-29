package cn.liondance.service.data.service;

/**
 * The interface Cencent cloud service.
 *
 * @author sunwei
 */
public interface LionDanceCloudService {

    /**
     * Update text audio.
     */
    void updateTextAudio();

    /**
     * Update text translate.
     */
    void updateTextTranslate();


    /**
     * Update translate.
     *
     * @param tableName the table name
     * @param field     the field
     */
    void updateTranslate(String tableName, String field);

    /**
     * Update audio.
     *
     * @param tableName the table name
     * @param field     the field
     */
    void updateAudio(String tableName, String field);

}
