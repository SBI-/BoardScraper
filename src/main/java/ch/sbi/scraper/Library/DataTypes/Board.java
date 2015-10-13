package ch.sbi.scraper.Library.DataTypes;

import ch.sbi.scraper.Library.Reader;
import ch.sbi.scraper.Library.Utilities.UrlUtilities;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public final class Board extends Reader implements ForumItem {
    private final String name;
    private final String url;
    private final Integer id;
    private int pages = 0;

    public Board(String name, String url) throws IOException {
        super(url);
        this.name = name;
        this.url = url;
        // this call should probably be somewhere else, see documentation in extractId
        this.id = UrlUtilities.extractId(url);
    }

    public Integer getId() {
        return id;
    }

    /**
     * Ugliest function on the planet.
     *
     * @return Number of pages in a board
     */
    public int getPageCount() {
        // avoid reading the whole page again for performance reasons
        if (pages != 0) {
            return pages;
        }

        Elements elements = document.getElementsByTag("a");

        try {
            Element lastLink = getLastLink(elements);
            String href = lastLink.attr("href");
            // this also needs to be changed to some kind of library for dealing with urls
            int start = href.indexOf("page=");
            String number = href.substring(start + 5);
            pages = Integer.parseInt(number);
            return pages;
        } catch (Exception e) {
            // this needs to log somewhere that an invalid page was attempted
        }

        pages = 0;
        return pages;

    }

    /**
     * This can probably go somewhere else, and needs a better Exception
     * @return
     */
    private Element getLastLink(Elements elements) throws Exception {
        for (Element e : elements) {
            if (e.text().contains("letzte")) {
                return e;
            }
        }

        throw new Exception("Invalid page format");
    }

    @Override
    public String toString() {
        return name + ": " + url;
    }
}
