package io.opsit.qandda;

public class AnalyzerException extends Exception {
    public AnalyzerException(String str) {
        super(str);
    }

    public AnalyzerException(String str,Exception ex) {
        super(str, ex);
    }
}
