package io.opsit.qandda;


public interface IAnalyzer  {
    public Module analyzeSpec(String fileName, String content);
    public boolean recognizeSpec(String fileName, String content);
}
