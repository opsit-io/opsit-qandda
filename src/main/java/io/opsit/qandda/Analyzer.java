package io.opsit.qandda;


import io.opsit.qandda.gem.GemAnalyzer;
import io.opsit.qandda.gomod.GoModAnalyzer;
import io.opsit.qandda.npm.NPMAnalyzer;
import io.opsit.qandda.nuget.NugetAnalyzer;
import io.opsit.qandda.pip.PipAnalyzer;
import io.opsit.qandda.pom.PomAnalyzer;

public class Analyzer implements IAnalyzer {
  protected final IAnalyzer[] analyzers =
      new IAnalyzer[] {
        new PomAnalyzer(),
        new NPMAnalyzer(),
        new GemAnalyzer(),
        new GoModAnalyzer(),
        new NugetAnalyzer(),
        new PipAnalyzer()
      };

  @Override
  public IComponent analyzeSpec(String fileName, String content) throws AnalyzerException {
    for (IAnalyzer analyzer : analyzers) {
      if (analyzer.recognizeSpec(fileName, content)) {
        return analyzer.analyzeSpec(fileName, content);
      }
    }
    return null;
  }

  @Override
  public boolean recognizeSpec(String fileName, String content) {
    for (IAnalyzer analyzer : analyzers) {
      if (analyzer.recognizeSpec(fileName, content)) {
        return true;
      }
    }
    return false;
  }
}
