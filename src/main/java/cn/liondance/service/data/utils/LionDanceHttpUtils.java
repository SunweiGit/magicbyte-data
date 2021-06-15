package cn.liondance.service.data.utils;

import com.google.common.collect.Maps;
import io.swagger.models.HttpMethod;
import lombok.Builder;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * The type Lion dance http utils.
 *
 * @author sunwei
 */
@Builder
public final class LionDanceHttpUtils {

  public static OkHttpClient client = new OkHttpClient();

  /**
   * Get string.
   *
   * @param url the url
   * @param headers the headers
   * @return the string
   * @throws IOException the io exception
   */
  public static String get(String url, Map<String, String> headers) throws IOException {
    return newCall(HttpMethod.GET, url, headers, null);
  }

  /**
   * Get string.
   *
   * @param url the url
   * @return the string
   * @throws IOException the io exception
   */
  public static String get(String url) throws IOException {
    return newCall(HttpMethod.GET, url, Maps.newHashMap(), null);
  }

  /**
   * Head string.
   *
   * @param url the url
   * @param headers the headers
   * @return the string
   * @throws IOException the io exception
   */
  public static String head(String url, Map<String, String> headers) throws IOException {
    return newCall(HttpMethod.HEAD, url, headers, null);
  }

  /**
   * Head string.
   *
   * @param url the url
   * @return the string
   * @throws IOException the io exception
   */
  public static String put(String url) throws IOException {
    return newCall(HttpMethod.PUT, url, Maps.newHashMap(), null);
  }

  /**
   * Post string.
   *
   * @param url the url
   * @param requestBody the request body
   * @return the string
   * @throws IOException the io exception
   */
  public static String post(String url, RequestBody requestBody) throws IOException {
    return newCall(HttpMethod.POST, url, Maps.newHashMap(), requestBody);
  }

  /**
   * Post string.
   *
   * @param url the url
   * @param headers the headers
   * @param requestBody the request body
   * @return the string
   * @throws IOException the io exception
   */
  public static String post(String url, Map<String, String> headers, RequestBody requestBody)
      throws IOException {
    return newCall(HttpMethod.POST, url, headers, requestBody);
  }

  /**
   * Put string.
   *
   * @param url the url
   * @param requestBody the request body
   * @return the string
   * @throws IOException the io exception
   */
  public static String put(String url, RequestBody requestBody) throws IOException {
    return newCall(HttpMethod.PUT, url, Maps.newHashMap(), requestBody);
  }

  /**
   * Put string.
   *
   * @param url the url
   * @param headers the headers
   * @param requestBody the request body
   * @return the string
   * @throws IOException the io exception
   */
  public static String put(String url, Map<String, String> headers, RequestBody requestBody)
      throws IOException {
    return newCall(HttpMethod.PUT, url, headers, requestBody);
  }

  /**
   * Patch string.
   *
   * @param url the url
   * @param requestBody the request body
   * @return the string
   * @throws IOException the io exception
   */
  public static String patch(String url, RequestBody requestBody) throws IOException {
    return newCall(HttpMethod.PATCH, url, Maps.newHashMap(), requestBody);
  }

  /**
   * Patch string.
   *
   * @param url the url
   * @param headers the headers
   * @param requestBody the request body
   * @return the string
   * @throws IOException the io exception
   */
  public static String patch(String url, Map<String, String> headers, RequestBody requestBody)
      throws IOException {
    return newCall(HttpMethod.PATCH, url, headers, requestBody);
  }

  /**
   * Delete string.
   *
   * @param url the url
   * @param requestBody the request body
   * @return the string
   * @throws IOException the io exception
   */
  public static String delete(String url, RequestBody requestBody) throws IOException {
    return newCall(HttpMethod.DELETE, url, Maps.newHashMap(), requestBody);
  }

  /**
   * Delete string.
   *
   * @param url the url
   * @param headers the headers
   * @param requestBody the request body
   * @return the string
   * @throws IOException the io exception
   */
  public static String delete(String url, Map<String, String> headers, RequestBody requestBody)
      throws IOException {
    return newCall(HttpMethod.DELETE, url, headers, requestBody);
  }

  private static String newCall(
      HttpMethod method, String url, Map<String, String> headers, RequestBody requestBody)
      throws IOException {
    Request.Builder builder = new Request.Builder().url(url);
    if (!headers.isEmpty()) {
      builder.headers(Headers.of(headers));
    }
    switch (method) {
      case POST:
        builder.post(requestBody);
        break;
      case PUT:
        builder.put(requestBody);
        break;
      case PATCH:
        builder.patch(requestBody);
        break;
      case DELETE:
        builder.delete(requestBody);
        break;
      case HEAD:
        builder.head();
        break;
      case GET:
      default:
        builder.get();
        break;
    }
    try (Response response = client.newCall(builder.build()).execute()) {
      if (response.isSuccessful()) {
        String responseBody = response.body().string();
        // ... do something with response
        return responseBody;
      } else {
        throw new RuntimeException("" + response);
      }
    } catch (IOException e) {
      // ... handle IO exception
      throw e;
    }
  }
}
