package io.opsit.qandda.gradle;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;


public class GradleAnalyzer implements  IAnalyzer  {
    public Module analyzeSpec(String fileName, String content) {
        return null;
    }
    
    public boolean recognizeSpec(String fileName, String content) {
        return false;
    }
}
