package io.opsit.qandda;

public class AnalyzerException extends Exception {
  public static final long serialVersionUID = 1;

  public AnalyzerException(String str) {
    super(str);
  }

  public AnalyzerException(String str, Exception ex) {
    super(str, ex);
  }
}
