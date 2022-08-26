package io.opsit.qandda.pom;

import io.opsit.qandda.AnalyzerException;
import io.opsit.qandda.Component;
import io.opsit.qandda.IAnalyzer;
import io.opsit.version.Version;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.interpolation.InterpolationException;
import org.codehaus.plexus.interpolation.MapBasedValueSource;
import org.codehaus.plexus.interpolation.StringSearchInterpolator;

public class PomAnalyzer implements IAnalyzer {
  @Override
  public Component analyzeSpec(String fileName, String content) throws AnalyzerException {
    Component result = mkComponent();
    MavenXpp3Reader reader = new MavenXpp3Reader();
    InputStream is = new ByteArrayInputStream(content.getBytes());
    InputStreamReader r = new InputStreamReader(is);
    try {
      Model model = reader.read(r);
      StringSearchInterpolator ssi = mkInterpolator(model);
      result.setVersion(Version.parseVersion(ssi.interpolate(model.getVersion())));
      result.setName(ssi.interpolate(model.getArtifactId()));
      result.setDisplayName(ssi.interpolate(model.getName()));
      result.setDescription(ssi.interpolate(model.getDescription()));
      result.setLicense(this.getLicense(ssi, model));
      result.setDependencies(getDependencies(ssi, model));
    } catch (Exception ex) {
      throw new AnalyzerException("Failed to parse pom", ex);
    }
    return result;
  }

  protected List<Component> getDependencies(StringSearchInterpolator ssi, Model model) {
    List<Component> results = new ArrayList<Component>();
    for (Dependency dep : model.getDependencies()) {
      try {
        Component mod = new Component();
        mod.setGroupName(ssi.interpolate(dep.getGroupId()));
        mod.setName(ssi.interpolate(dep.getArtifactId()));
        mod.setScope(ssi.interpolate(dep.getScope()));
        mod.setVersion(Version.parseVersion(ssi.interpolate(dep.getVersion())));
        results.add(mod);
      } catch (Exception ex) {
        // FIXME: diagnostics

      }
    }
    return results;
  }

  protected String getLicense(StringSearchInterpolator ssi, Model m) throws InterpolationException {
    final List<License> ls = m.getLicenses();
    if (null != ls) {
      StringBuilder buf = new StringBuilder();
      for (License l : ls) {
        if (null != l) {
          if (null != l.getName()) {
            String name = ssi.interpolate(l.getName());
            if (name.trim().length() > 0) {
              if (buf.length() > 0) {
                buf.append(", ");
              }
              buf.append(name);
            }
          }
        }
      }
      return buf.toString();
    }
    return null;
  }

  protected StringSearchInterpolator mkInterpolator(Model model) {
    StringSearchInterpolator ssi = new StringSearchInterpolator();
    ssi.addValueSource(new MapBasedValueSource(model.getProperties()));
    // ssi.addValueSource( new MapBasedValueSource( model.getProperties() ) );
    return ssi;
  }

  protected Component mkComponent() {
    final Component m = new Component();
    m.setPackager("maven");
    return m;
  }

  public boolean recognizeSpec(String fileName, String content) {
    return null != fileName
        && ("pom.xml".equalsIgnoreCase(fileName) || fileName.endsWith("/pom.xml"));
  }
}
