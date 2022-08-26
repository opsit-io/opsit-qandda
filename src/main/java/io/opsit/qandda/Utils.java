package io.opsit.qandda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
  private static final ObjectMapper mapper = new ObjectMapper();
  private static final XmlMapper xmlMapper = new XmlMapper();

  /**
   * Convert json to java Map.
   */
  public static Map<String, Object> json2map(String str) throws AnalyzerException {
    try {
      @SuppressWarnings("unchecked")
      final Map<String, Object> map = mapper.readValue(str, Map.class);
      return map;
    } catch (JsonProcessingException ex) {
      throw new AnalyzerException("Failed to parse JSON", ex);
    }
  }

  /**
   * Convert xml to java Map.
   */
  public static Map<String, Object> xml2map(String str) throws AnalyzerException {
    try {
      @SuppressWarnings("unchecked")
      final Map<String, Object> map = xmlMapper.readValue(str, Map.class);
      return map;
    } catch (JsonProcessingException ex) {
      throw new AnalyzerException("Failed to parse XML", ex);
    }
  }

  /**
   * Return key value from map if it is a string, null otherwise.
   */
  public static String getString(Map<String,Object> m, String key) {
    Object obj = m.get(key);
    if (null == obj || !(obj instanceof String)) {
      return null;
    }
    return (String) obj;
  }

  /**
   * Return key value from map if it is a List, null otherwise.
   */
  @SuppressWarnings("unchecked")
  public static List<Object> getList(Map<String,Object> m, String key) {
    Object obj = m.get(key);
    if (null == obj || !(obj instanceof List)) {
      return null;
    }
    return (List<Object>) obj;
  }

  /**
   * Return key value from map if it is a Map, null otherwise.
   */
  @SuppressWarnings("unchecked")
  public static Map<String,Object> getMap(Map<String,Object> m, String key) {
    Object obj = m.get(key);
    if (null == obj || !(obj instanceof Map)) {
      return null;
    }
    return (Map<String,Object>) obj;
  }

  /**
   * Concatenate lists elements into a new list.
   */
  @SafeVarargs
  public static <T> List<T> concat(List<T>... lists) {
    final List<T> result = new ArrayList<T>();
    for (List<T> list : lists) {
      if (null != list) {
        result.addAll(list);
      }
    }
    return result;
  }
}
