package io.opsit.qandda.npm;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;
import static io.opsit.qandda.Utils.json2map;
import static io.opsit.qandda.Utils.getString;
import static io.opsit.qandda.Utils.getList;
import static io.opsit.qandda.Utils.getMap;
import static io.opsit.qandda.Utils.concat;
import io.opsit.qandda.AnalyzerException;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class NPMAnalyzer implements  IAnalyzer  {

    
    public Module analyzeSpec(String fileName, String content)
        throws AnalyzerException {
        Module result = mkModule();
        Map <String,Object>map = json2map(content);
        final String name = getString(map, "name");
        result.setName(name);
        result.setDisplayName(name);
        result.setVersion(getString(map, "version"));
        result.setDescription(getString(map, "description"));
        result.setLicense(getString(map, "license"));
        result.setAuthor(getString(map, "author"));
        result.setDependencies(concat(convertDeps(map, "dependencies", null),
                                      convertDeps(map, "devDependencies", "dev")));
        return result;
        
    }

    protected Module mkModule() {
        final Module m = new Module();
        m.setPackager("npm");
        return m;
    }
    
    protected List<Module> convertDeps(Map map, String key, String scope) {
        final List<Module> result = new ArrayList<Module>();
        final Map<String,Object> depsMap = getMap(map, key);
        if (null != depsMap) {
            for (Object obj : depsMap.keySet()) {
                if (null != obj && (obj instanceof String)) {
                    final Module dep = mkModule();
                    dep.setName((String)obj);
                    dep.setScope(scope);
                    result.add(dep);
                }
            }
        }
        return result;
    }
    
    public boolean recognizeSpec(String fileName, String content) {
        return "package.json".equalsIgnoreCase(fileName);
    }
}
