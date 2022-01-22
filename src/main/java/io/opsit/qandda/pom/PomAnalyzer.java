package io.opsit.qandda.pom;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;


public class PomAnalyzer implements  IAnalyzer  {
    public Module analyzeSpec(String fileName, String content) {
        return null;
    }
    
    public boolean recognizeSpec(String fileName, String content) {
        return false;
    }
}
