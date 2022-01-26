package io.opsit.qandda;


public interface IAnalyzer  {
    public IComponent analyzeSpec(String fileName, String content)
        throws AnalyzerException;
    public boolean recognizeSpec(String fileName, String content);
}
