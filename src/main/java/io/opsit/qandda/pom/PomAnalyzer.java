package io.opsit.qandda.pom;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;
import io.opsit.qandda.AnalyzerException;
import static io.opsit.qandda.Utils.json2map;
import static io.opsit.qandda.Utils.xml2map;
import static io.opsit.qandda.Utils.getString;
import static io.opsit.qandda.Utils.getList;
import static io.opsit.qandda.Utils.getMap;
import static io.opsit.qandda.Utils.concat;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.Model;
import org.apache.maven.model.License;
import org.apache.maven.model.Dependency;
//import org.apache.maven.model.building.ModelProblemCollector;
//import org.apache.maven.model.building.ModelProblemCollectorRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
///import org.apache.maven.model.interpolation.ModelInterpolator;
// import org.apache.maven.model.interpolation.StringSearchModelInterpolator;
import org.codehaus.plexus.interpolation.StringSearchInterpolator;
import org.codehaus.plexus.interpolation.InterpolationException;
import org.codehaus.plexus.interpolation.MapBasedValueSource;

public class PomAnalyzer implements IAnalyzer {
    public Module analyzeSpec(String fileName, String content) throws AnalyzerException {
        Module result = mkModule();
        MavenXpp3Reader reader = new MavenXpp3Reader();
        InputStream is = new ByteArrayInputStream(content.getBytes());
        InputStreamReader r = new InputStreamReader(is);
        try {
            Model model = reader.read(r);
            StringSearchInterpolator ssi = mkInterpolator(model);
            System.out.println(model);
            result.setVersion(ssi.interpolate(model.getVersion()));
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


    protected List<Module> getDependencies( StringSearchInterpolator ssi, Model model) throws Exception {
        List<Module> results = new ArrayList<Module>();
        for (Dependency dep : model.getDependencies()) {
            Module mod = new Module();
            mod.setGroupName(ssi.interpolate(dep.getGroupId()));
            mod.setVersion(ssi.interpolate(dep.getVersion()));
            mod.setName(ssi.interpolate(dep.getArtifactId()));
            mod.setScope(ssi.interpolate(dep.getScope()));
            results.add(mod);
        }
        return results;
    }
    
    protected String getLicense(StringSearchInterpolator ssi, Model m) throws InterpolationException {
        final List <License> ls  = m.getLicenses();
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
        StringSearchInterpolator ssi =  new StringSearchInterpolator();
        ssi.addValueSource( new MapBasedValueSource( model.getProperties() ) );
        //ssi.addValueSource( new MapBasedValueSource( model.getProperties() ) );
        return ssi;
    }
    
    protected Module mkModule() {
        final Module m = new Module();
        m.setPackager("maven");
        return m;
    }

    public boolean recognizeSpec(String fileName, String content) {
        return "pom.xml".equalsIgnoreCase(fileName);
   } 
}

