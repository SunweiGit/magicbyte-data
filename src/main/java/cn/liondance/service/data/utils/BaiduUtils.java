package cn.liondance.service.data.utils;


import lombok.Builder;
import okhttp3.FormBody;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * The enum Crad status enum.
 *
 * @author sunwei
 */
@Builder
public class BaiduUtils {
    /**
     * 百度翻译 APP ID
     */
    private static final String TRANSLATE_APP_ID = "20210411000772630";
    /**
     * 百度翻译 KEY
     */
    private static final String TRANSLATE_KEY = "8qyPSSnJ7HfZwxI9OVcT";
    /**
     * 百度翻译 URL
     */
    private static final String TRANSLATE_URL = "https://fanyi-api.baidu.com/api/trans/vip/translate?";
    /**
     * 在线语音合成 URL
     */
    private static final String SPEECH_SYNTHESIS_URL = "https://ai.baidu.com/aidemo";

    /**
     * Translate.
     * 参阅文档
     * https://fanyi-api.baidu.com/doc/21
     * 成功返回如下
     * {"from":"zh","to":"en","trans_result":[{"src":"\u82f9\u679c","dst":"Apple"}]}
     * 失败返回如下
     * {"error_code":"52003","error_msg":"UNAUTHORIZED USER"}
     * 错误码	含义	                                解决方法
     * 52000	成功
     * 52001	请求超时	                            重试
     * 52002	系统错误	                            重试
     * 52003	未授权用户	                        请检查您的appid是否正确，或者服务是否开通
     * 54000	必填参数为空	                        请检查是否少传参数
     * 54001	签名错误	                            请检查您的签名生成方法
     * 54003	访问频率受限	                        请降低您的调用频率，或进行身份认证后切换为高级版/尊享版
     * 54004	账户余额不足	                        请前往管理控制台为账户充值
     * 54005	长query请求频繁	                    请降低长query的发送频率，3s后再试
     * 58000	客户端IP非法	                        检查个人资料里填写的IP地址是否正确，可前往开发者信息-基本信息修改，可前往开发者信息-基本信息修改
     * 58001	译文语言方向不支持	                    检查译文语言是否在语言列表里
     * 58002	服务当前已关闭	                        请前往管理控制台开启服务
     * 90107	认证未通过或未生效	                    请前往我的认证查看认证进度
     *
     * @param q      the q 请求翻译 query	UTF-8 编码
     * @param from   the from 翻译源语言	可设置为 auto
     * @param to     the to 翻译目标语言	不可设置为 auto               以下字段仅开通了词典、TTS用户需要填写
     * @param tts    the tts 是否显示语音合成资源	0 - 显示 1 - 不显示
     * @param dict   the dict 是否显示词典资源	0 - 显示 1 - 不显示               以下字段仅开通了”我的术语库“用户需要填写
     * @param action the action 判断是否需要使用自定义术语干预 API	1 - 是 0 - 否
     * @return the string
     * @throws IOException the io exception
     */
    public static String translate(String q, String from, String to, Integer tts, Integer dict, Integer action) throws IOException {
        int salt = (int) (Math.random() * (90000000 - 10000000) + 10000000);
        StringBuilder stringBuilder = new StringBuilder()
                .append(TRANSLATE_APP_ID)
                .append(q)
                .append(salt)
                .append(TRANSLATE_KEY);
        String sign = DigestUtils.md5DigestAsHex(stringBuilder.toString().getBytes());
        StringBuilder url = new StringBuilder()
                .append(TRANSLATE_URL)
                .append("q=" + URLEncoder.encode(q, "UTF-8"))
                .append("&from=" + from)
                .append("&to=" + to)
                .append("&appid=" + TRANSLATE_APP_ID)
                .append("&salt=" + salt)
                .append("&sign=" + sign)
                .append("&tts=" + tts)
                .append("&dict=" + dict)
                .append("&action=" + action);
        return LionDanceHttpUtils.get(url.toString());
    }


    /**
     * Speech synthesis string.
     *
     * @param tex the tex
     * @return the string
     * @throws IOException the io exception
     */
    public static String speechSynthesis(String tex) throws IOException, InterruptedException {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        formBodyBuilder.add("type", "tns")
                .add("per", "4103")
                .add("spd", "3")
                .add("pit", "5")
                .add("vol", "15")
                .add("aue", "6")
                .add("tex", tex);
        return LionDanceHttpUtils.post(SPEECH_SYNTHESIS_URL, formBodyBuilder.build());
    }

}
