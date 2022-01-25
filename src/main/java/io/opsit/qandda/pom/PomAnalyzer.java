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

// https://stackoverflow.com/questions/11525318/how-do-i-obtain-a-fully-resolved-model-of-a-pom-file
// https://stackoverflow.com/questions/11525318/how-do-i-obtain-a-fully-resolved-model-of-a-pom-file
public class PomAnalyzer implements IAnalyzer {
    public Module analyzeSpec(String fileName, String content) throws AnalyzerException {
        Module result = mkModule();
        MavenXpp3Reader reader = new MavenXpp3Reader();
        InputStream is = new ByteArrayInputStream(content.getBytes());
        InputStreamReader r = new InputStreamReader(is);

        /*ModelProblemCollector problems = new ModelProblemCollector() {
            private List<ModelProblemCollectorRequest> requests =
                    new ArrayList<ModelProblemCollectorRequest>();
                public void add(ModelProblemCollectorRequest req) {
                    requests.add(req);
                }

                public String toString() {
                    return "ModelProblems("+requests.toString()+")";
                }
                };*/
        
        try {
            Model model = reader.read(r);
            System.out.println("\n\nREAD MODEL:\n"+model);

            /*ModelInterpolator modelInterpolator = mkInterpolator();*/
            StringSearchInterpolator ssi = mkInterpolator(model);
            
            /*Model interpolatedModel =
                modelInterpolator.interpolateModel( model,
                                                    model.getProjectDirectory(),
                                                    request,
                                                    problems );*/
        
            System.out.println(model);
            //System.out.println("deps=" + model.getDependencies());
            result.setVersion(ssi.interpolate(model.getVersion()));
            result.setName(ssi.interpolate(model.getArtifactId()));
            result.setDisplayName(ssi.interpolate(model.getName()));
            result.setDescription(ssi.interpolate(model.getDescription()));
            result.setLicense(this.getLicense(ssi, model));
            result.setDependencies(getDependencies(ssi, model));
            
            
            
            //result.setLicense(ssi.interpolate());
            
        } catch (Exception ex) {
            throw new AnalyzerException("Failed to parse pom", ex);
        }

        /*
        Map <String,Object>map = xml2map(content);
        System.out.println(map);
        result.setName(getString(map, "artifactId"));
        result.setVersion(getString(map, "version"));
        result.setDescription(getString(map, "description"));
        result.setGroupName(getString(map, "groupId"));
        result.setLicense(content);
        */
        //result.setLanguage(getString(map, "groupId"));
        //reultt.setL
        //result.setLicense(getString(map, "license"));
        //result.setAuthor(getString(map, "author"));
        //result.setDependencies(concat(convertDeps(map, "dependencies", null),
        //                             convertDeps(map, "devDependencies", "dev")));
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

