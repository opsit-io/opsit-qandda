package io.opsit.qandda.gradle;

import io.opsit.qandda.Component;
import io.opsit.qandda.IAnalyzer;

public class GradleAnalyzer implements IAnalyzer {
  public Component analyzeSpec(String fileName, String content) {
    return null;
  }

  public boolean recognizeSpec(String fileName, String content) {
    return false;
  }
}
