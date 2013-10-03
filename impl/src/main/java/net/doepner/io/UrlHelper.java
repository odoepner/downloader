package net.doepner.io;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Helps with converting a URL to a filename
 */
public class UrlHelper {

    public String getFilename(URL url, String contentType) {
        final String safeUrl = urlEncode(url);
        final String extension = getFilenameExtension(contentType);
        return safeUrl.endsWith(extension) ? safeUrl : (safeUrl + extension);
    }

    private String urlEncode(URL url) {
        try {
            return URLEncoder.encode(url.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFilenameExtension(String type) {
        final int extStart = type.indexOf('/') + 1;
        final int semicolonIndex = type.indexOf(';');
        final int extEnd = semicolonIndex == -1 ? type.length() : semicolonIndex;
        return '.' + type.substring(extStart, extEnd);
    }
}
