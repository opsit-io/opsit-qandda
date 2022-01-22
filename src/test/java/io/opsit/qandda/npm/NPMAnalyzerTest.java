package io.opsit.qandda.npm;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;
import static io.opsit.qandda.TestUtils.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;



public class NPMAnalyzerTest  {
    protected NPMAnalyzer analyzer = new NPMAnalyzer();
    
    @Test
    public void TestRecognize() throws Exception {

        
        String[] files =
            listFiles(TEST_DATA,
                      "npm/*/package.json");
        assertEquals(1, files.length);
        for (String file : files) {
            String content = readFile(file);
            assertNotNull(content.length());
            assertTrue(content.length() > 0);
            String fileName = getFileName(file);
            //System.out.println("Filename=" + fileName);
            boolean result = this.analyzer.recognizeSpec(fileName, content);
            assertTrue(result);
        }
        
    }
    
    @Test
    public void TestAnalyze() {
        
    }
}

