package io.opsit.qandda.gem;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Component;


public class GemAnalyzer implements  IAnalyzer  {
    public Component analyzeSpec(String fileName, String content) {
        return null;
    }
    
    public boolean recognizeSpec(String fileName, String content) {
        return false;
    }
}
