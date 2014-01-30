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

    public static final Path USER_HOME =
            Paths.get(System.getProperty("user.home"));

    private final Downloader downloader = new DownloaderImpl(USER_HOME);

    @Test
    public void test() throws IOException {

        final String[] urls = {
                "http://oliver.doepner.net/docs/Resume-Oliver-Doepner.pdf",
                "http://google.com",
                // FTP protocol is not working, due to an old Sun JDK bug:
                // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6840419
                //"ftp://ftp.mozilla.org/pub/mozilla.org/firefox/releases/latest/source/",
        };

        for (String url : urls) {
            downloader.add(url);
        }

        int i = 0;
        for (Path path : downloader.download()) {
            assertTrue("Downloaded file should exist!", path.toFile().exists());
            i++;
        }
        assertEquals("Number of results", urls.length, i);
    }

}
