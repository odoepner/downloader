package net.doepner.io;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

/**
 * A utility that can download files from URLs
 */
public interface Downloader {

    /**
     * @param url A URL that should be downloaded
     */
    void add(String url) throws MalformedURLException;

    /**
     * ]* @return The paths of the downloaded files
     */
    Iterable<Path> download() throws IOException;
}
