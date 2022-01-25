package io.opsit.qandda;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Utils {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();
    
    public static Map<String,Object> json2map(String str)
        throws AnalyzerException {
        try {
            final Map<String,Object> map =
                mapper.readValue(str, Map.class);
            return map;
        } catch (JsonProcessingException  ex) {
            throw new AnalyzerException("Failed to parse JSON",  ex);
        }
    }

    public static Map<String,Object> xml2map(String str)
        throws AnalyzerException {
        try {
            final Map<String, Object> map = xmlMapper.readValue(str, Map.class);
            return map;
        } catch (JsonProcessingException  ex) {
            throw new AnalyzerException("Failed to parse XML",  ex);
        } 
    }

    
    public static String getString(Map m, String key) {
        Object obj = m.get(key);
        if (null == obj || !(obj instanceof String)) {
            return null;
        }
        return (String)obj;
    }

    public static List getList(Map m, String key) {
        Object obj = m.get(key);
        if (null == obj || !(obj instanceof List)) {
            return null;
        }
        return (List)obj;
    }

    public static Map getMap(Map m, String key) {
        Object obj = m.get(key);
        if (null == obj || !(obj instanceof Map)) {
            return null;
        }
        return (Map)obj;
    }

    
    public static<T>  List<T> concat(List<T> ... lists) {
        final List<T> result = new ArrayList<T>();
        for (List<T> list : lists) {
            if (null != list) {
                result.addAll(list);
            }
        }
        return result;
    }
}
