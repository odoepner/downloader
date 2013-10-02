package net.doepner.io;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.junit.Test;

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
        final Iterable<Path> results = downloader.download();

        final Iterator<Path> pathIterator = results.iterator();
        checkFileExists(pathIterator);
        checkFileExists(pathIterator);
    }

    private void checkFileExists(Iterator<Path> iterator) {
        assertTrue(iterator.hasNext());
        final Path path = iterator.next();
        assertTrue("Downloaded file should exist!", path.toFile().exists());
    }
}
