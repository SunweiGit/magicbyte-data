package cn.liondance.service.data;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@SpringBootTest
class ConfigApplicationTests {

  @Test
  void contextLoads() {
    ArrayList<Object> list = Lists.newArrayList();
    list.add("AMG2100015");
    list.add("ASG2100538");
    list.add("ASG2100546");
    list.add("ASG2100564");
    list.add("ASG2100565");
    list.add("ASG2100570");
    list.add("ASG2100571");
    list.add("ASG2100572");
    list.add("ASG2100576");
    list.add("ASG2100580");
    list.add("DAG2100882");
    list.add("DAG2100884");
    list.add("DAG2100914");
    list.add("DAG2100924");
    list.add("DAG2100926");
    list.add("DAG2100928");
    list.add("DMG2100026");
    list.add("HMG2100032");
    list.add("HSG2103402");
    list.add("HSG2103786");
    list.add("HSG2103832");
    list.add("HSG2104005");
    list.add("HSG2104027");
    list.add("HSG2104033");
    list.add("HSG2104069");
    list.add("HSG2104070");
    list.add("HSG2104096");
    list.add("HSG2104114");
    list.add("ISG2101397");
    list.add("ISG2101409");
    list.add("ISG2101437");
    list.add("ISG2101441");
    list.add("ISG2101445");
    list.add("ISG2101447");
    list.add("ISG2101453");
    list.add("ISG2101456");
    list.add("ISG2101459");
    list.add("ISG2101465");
    list.add("ISG2101466");
    list.add("ISG2101469");
    list.add("ISG2101473");
    list.add("ISG2101477");
    list.add("ISG2101478");
    list.add("ISG2101481");
    list.add("MAE2100172");
    list.add("MAE2100197");
    list.add("MAE2100203");
    list.add("MAE2100204");
    list.add("MAE2100205");
    list.add("MAE2100210");
    list.add("MAE2100213");
    list.add("MAE2100215");
    list.add("MAE2100216");
    list.add("NSG2101000");
    list.add("NSG2101019");
    list.add("NSG2101028");
    list.add("NSG2101032");
    list.add("NSG2101036");
    list.add("PCG2100384");
    list.add("PCG2100389");
    list.add("PCG2100391");
    list.add("PCG2100398");
    list.add("PCG2100399");
    list.add("PCG2100401");
    list.add("PCG2100404");
    list.add("PMG2100037");
    list.add("PSG2101146");
    list.add("PSG2101150");
    list.add("PSG2101153");
    list.add("PSG2101154");
    list.add("PSG2101163");
    list.add("PSG2101171");
    list.add("PSG2101179");
    list.add("SSG2101196");
    list.add("SSG2101203");
    list.add("STG2100037");
    list.add("VSG2100121");
    list.add("VSG2100122");
    list.add("VSG2100123");
    list.add("HSG2103989");
    list.add("HSG2104049");
    list.add("HSG2104076");
    list.add("HSG2104102");
    list.add("ISG2101410");
    list.add("ISG2101463");
    list.add("ISG2101471");
    list.add("PCG2100400");
    list.add("SSG2101099");
    list.add("SSG2101226");
    list.add("ISG2101452");
    list.add("SSG2101181");
    list.stream()
        .forEach(
            o -> {
              OkHttpClient client = new OkHttpClient();
              Request request =
                  new Request.Builder()
                      .header(
                          "authorization",
                          "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiU2FsZXNNS1TliJvlu7rkvJrorq7kvIHkuJrlvq7kv6Hmi5zorr_lnKjnur_mi5zorr_osIPnoJTmnajmo67nlKjmiLfop5LoibIiLCJ1bmlxdWVfbmFtZSI6IjcwMjI0MTExNSIsInVzZXJpZCI6IjkzYmQ5ODUyNGFlZjRkYTRhNTE1YjNmMzNmMjk0ZDU1IiwiaXNzIjoicmVzdGFwaXVzZXIiLCJhdWQiOiIwOThmNmJjZDQ2MjFkMzczY2FkZTRlODMyNjI3YjRmZCIsImV4cCI6MTYxOTI3MDkyOCwibmJmIjoxNjE5MTg0NDg4fQ.Ajnx9AOq81kX-ne_58T9NWmPP4jzA0fASYtFZOYssDI")
                      .url(
                          "https://jbrain-zuul.myxjp.com/API-XJP/bizconf/initMeetingPoster?id=" + o)
                      .build();
              try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                  // ... handle failed request
                }
                String responseBody = response.body().string();
                // ... do something with response
                log.error("responseBody [{}]", responseBody);
              } catch (IOException e) {
                // ... handle IO exception
              }
            });
  }
}
