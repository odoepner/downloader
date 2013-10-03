package net.doepner.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Downloads files from URLs
 */
public class DownloaderImpl implements Downloader {

    private final Path downloadDir;
    private final UrlHelper urlHelper = new UrlHelper();

    private final Set<URL> urls = new HashSet<>();

    public DownloaderImpl(Path downloadDir) {
        this.downloadDir = downloadDir;
    }

    @Override
    public void add(String url) throws MalformedURLException {
        urls.add(new URL(url));
    }

    @Override
    public Iterable<Path> download() throws IOException {
        final Set<Path> results = new HashSet<>();
        for (URL url : urls) {
            results.add(download(url));
        }
        return results;
    }

    private Path download(URL url) throws IOException {
        final URLConnection c = url.openConnection();
        final ReadableByteChannel rbc = Channels.newChannel(c.getInputStream());

        final String filename = urlHelper.getFilename(url, c.getContentType());
        final File file = downloadDir.resolve(filename).toFile();

        try (final FileOutputStream fos = new FileOutputStream(file)) {
            final FileChannel channel = fos.getChannel();
            channel.transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        return file.toPath();
    }
}
