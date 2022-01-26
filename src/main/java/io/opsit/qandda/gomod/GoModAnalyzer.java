package io.opsit.qandda.gomod;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Component;


public class GoModAnalyzer implements  IAnalyzer  {
    public Component analyzeSpec(String fileName, String content) {
        return null;
    }
    
    public boolean recognizeSpec(String fileName, String content) {
        return false;
    }
}
