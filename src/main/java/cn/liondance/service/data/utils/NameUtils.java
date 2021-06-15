package cn.liondance.service.data.utils;

import lombok.Builder;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author sunwei */
@Builder
public class NameUtils {
  /**
   * Camel name string.
   *
   * @param name the name user_name
   * @return the string userName
   */
  public static String camelName(String name) {
    StringBuilder result = new StringBuilder();
    if ((name == null) || (name.isEmpty())) {
      return "";
    }
    String s = "_";
    if (!name.contains(s)) {
      return name.toLowerCase();
    }
    String[] camels = name.split(s);
    for (String camel : camels) {
      if (!camel.isEmpty()) {
        if (result.length() == 0) {
          result.append(camel.toLowerCase());
        } else {
          result.append(camel.substring(0, 1).toUpperCase());
          result.append(camel.substring(1).toLowerCase());
        }
      }
    }
    return result.toString();
  }

  /**
   * 驼峰转下划线
   *
   * @param humpString created by hbd 20160722
   * @return string
   */
  public static String humpToUnderline(String humpString) {
    if (StringUtils.isEmpty(humpString)) {
      return "";
    }
    String regexStr = "[A-Z]";
    Matcher matcher = Pattern.compile(regexStr).matcher(humpString);
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      String g = matcher.group();
      matcher.appendReplacement(sb, "_" + g.toLowerCase());
    }
    matcher.appendTail(sb);
    if (sb.charAt(0) == '_') {
      sb.delete(0, 1);
    }
    return sb.toString();
  }
}
