package io.opsit.qandda.nuget;

import io.opsit.qandda.Component;
import io.opsit.qandda.IAnalyzer;

public class NugetAnalyzer implements IAnalyzer {
  public Component analyzeSpec(String fileName, String content) {
    return null;
  }

  public boolean recognizeSpec(String fileName, String content) {
    return false;
  }
}
