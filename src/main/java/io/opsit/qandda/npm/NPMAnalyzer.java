package io.opsit.qandda.npm;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;


public class NPMAnalyzer implements  IAnalyzer  {
    public Module analyzeSpec(String fileName, String content) {
        return null;
    }
    
    public boolean recognizeSpec(String fileName, String content) {
        return "package.json".equalsIgnoreCase(fileName);
    }
}
