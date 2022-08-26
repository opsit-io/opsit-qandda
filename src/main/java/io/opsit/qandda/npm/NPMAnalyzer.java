package io.opsit.qandda.npm;

import static io.opsit.qandda.Utils.concat;
import static io.opsit.qandda.Utils.getMap;
import static io.opsit.qandda.Utils.getString;
import static io.opsit.qandda.Utils.json2map;

import io.opsit.qandda.AnalyzerException;
import io.opsit.qandda.Component;
import io.opsit.qandda.IAnalyzer;
import io.opsit.version.Version;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NPMAnalyzer implements IAnalyzer {

  @Override
  public Component analyzeSpec(String fileName, String content) throws AnalyzerException {
    try {
      Component result = mkComponent();
      Map<String, Object> map = json2map(content);
      final String name = getString(map, "name");
      result.setName(name);
      result.setDisplayName(name);
      result.setVersion(Version.parseVersion(getString(map, "version")));
      result.setDescription(getString(map, "description"));
      result.setLicense(getString(map, "license"));
      result.setAuthor(getString(map, "author"));
      result.setDependencies(
          concat(
              convertDeps(map, "dependencies", null), convertDeps(map, "devDependencies", "dev")));
      return result;
    } catch (Exception ex) {
      throw new AnalyzerException("Failed to parse package.json", ex);
    }
  }

  protected Component mkComponent() {
    final Component m = new Component();
    m.setPackager("npm");
    return m;
  }

  protected List<Component> convertDeps(Map<String, Object> map, String key, String scope)
      throws Exception {
    final List<Component> result = new ArrayList<Component>();
    @SuppressWarnings("unchecked")
    final Map<String, Object> depsMap = getMap(map, key);
    if (null != depsMap) {
      for (Object obj : depsMap.keySet()) {
        if (null != obj && (obj instanceof String)) {
          final Component dep = mkComponent();
          dep.setName((String) obj);
          dep.setScope(scope);

          final Object val = depsMap.get(obj);
          if (val instanceof String) {
            final String verStr = ((String) val);
            // FIXME: support version ranges for deps and various
            //        npm syntaxces for them
            if (verStr.matches("^[0-9].*") && (verStr.contains(" - "))) {
              dep.setVersion(Version.parseVersion(verStr));
            }
          }
          result.add(dep);
        }
      }
    }
    return result;
  }

  public boolean recognizeSpec(String fileName, String content) {
    return null != fileName
        && ("package.json".equalsIgnoreCase(fileName) || fileName.endsWith("package.json"));
  }
}
