package io.opsit.qandda.pom;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;
import io.opsit.qandda.AnalyzerException;
import static io.opsit.qandda.TestUtils.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;



public class PomAnalyzerTest  {
    protected PomAnalyzer analyzer = new PomAnalyzer();
    
    @Test
    public void TestRecognize() throws Exception {
        String[] files =
            listFiles(TEST_DATA,
                      "pom/*/pom.xml");
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
    public void TestAnalyze() throws Exception {
         String file = TEST_DATA + "/" + "pom/opsit/pom.xml";
         String content = readFile(file);
         assertNotNull(content.length());
         assertTrue(content.length() > 0);
         String fileName = getFileName(file);
         Module m = this.analyzer.analyzeSpec(fileName, content);
         assertNotNull(m);
         assertEquals("opsit-backend", m.getName());
         assertEquals("Opsit Backend", m.getDisplayName());
         assertEquals("1.0.0", m.getVersion());
         assertEquals("maven", m.getPackager());
         assertEquals("The best project ever",
                      m.getDescription());
         // assertNotNull(m.getDependencies());
         // assertNotNull(m.getDependencies().size()>0);
         // // FIXME really test deps
        System.out.println("Module: " + m);
    }
}

