package net.doepner.io;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test Downloader functionality
 */
public class DownloaderImplTest {

    public static final Path USER_HOME = Paths.get(System.getProperty("user.home"));

    private final Downloader downloader = new DownloaderImpl(USER_HOME);

    @Test
    public void test() throws IOException {
        downloader.add("http://oliver.doepner.net");
        downloader.add("http://google.com");

        int i = 0;
        for (Path path : downloader.download()) {
            assertTrue("Downloaded file should exist!", path.toFile().exists());
            i++;
        }
        assertEquals("Number of results", 2, i);
    }

}
