package io.opsit.qandda.gomod;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;


public class GoModAnalyzer implements  IAnalyzer  {
    public Module analyzeSpec(String fileName, String content) {
        return null;
    }
    
    public boolean recognizeSpec(String fileName, String content) {
        return false;
    }
}
