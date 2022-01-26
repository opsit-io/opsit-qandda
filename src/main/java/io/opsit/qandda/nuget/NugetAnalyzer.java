package io.opsit.qandda.nuget;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Component;


public class NugetAnalyzer implements  IAnalyzer  {
    public Component analyzeSpec(String fileName, String content) {
        return null;
    }
    
    public boolean recognizeSpec(String fileName, String content) {
        return false;
    }
}
