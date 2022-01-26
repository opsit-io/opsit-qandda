package io.opsit.qandda.npm;

import static io.opsit.qandda.TestUtils.TEST_DATA;
import static io.opsit.qandda.TestUtils.getFileName;
import static io.opsit.qandda.TestUtils.listFiles;
import static io.opsit.qandda.TestUtils.readFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import io.opsit.qandda.Component;



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
    public void TestAnalyze() throws Exception {
        String file = TEST_DATA + "/" + "npm/opsit/package.json";
        String content = readFile(file);
        assertNotNull(content.length());
        assertTrue(content.length() > 0);
        String fileName = getFileName(file);
        Component m = this.analyzer.analyzeSpec(fileName, content);
        assertNotNull(m);
        assertEquals("opsit", m.getName());
        assertEquals("1.21.0", m.getVersion());
        assertEquals("npm", m.getPackager());
        assertNotNull(m.getDependencies());
        assertNotNull(m.getDependencies().size()>0);
        // FIXME really test deps
        //System.out.println("Component: " + m);
    }
}

