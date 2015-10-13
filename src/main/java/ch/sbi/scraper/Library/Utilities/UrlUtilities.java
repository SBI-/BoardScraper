package ch.sbi.scraper.Library.Utilities;

public final class UrlUtilities {
    /**
     * This really needs to be replaced by a library call, but I haven't decided which library to use yet.
     * @param url URL from which to extrat an id
     * @return Id in url
     */
    public static Integer extractId(String url) {
        int position = url.indexOf('=') + 1;
        String result = url.substring(position);
        return new Integer(result);
    }
}
