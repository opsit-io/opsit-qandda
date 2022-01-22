package io.opsit.qandda;

import io.opsit.qandda.IAnalyzer;
import io.opsit.qandda.Module;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class TestUtils  {
    public final static String TEST_DATA = "src/test/resources/";
    
    public final static String readFile(String pathStr) throws IOException {
        final Path path = Path.of(pathStr);
        final byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    public final static String[] ARRAY_OF_STRINGS = new String[0];
    
    public final static String[] listFiles(String location, String pattern) throws IOException {
        final PathMatcher pathMatcher =
            FileSystems.getDefault().getPathMatcher("glob:" + location + pattern);
        final List<String> files = new ArrayList<String>();
            
		Files.walkFileTree(Path.of(location),
                           new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path,
                                                 BasicFileAttributes attrs) throws IOException {
                    //System.out.println("Visiting '" + path + "'");
                    if (pathMatcher.matches(path)) {
                        //System.out.println(path);
                        files.add(path.toString());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
                    return FileVisitResult.CONTINUE;
                }
		});
        return files.toArray(ARRAY_OF_STRINGS);
    }

    public static String getFileName(String str) {
        final Path path = Path.of(str);
        return path.getFileName().toString();
    }
}

