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

    public int getPageCount() {
        Elements elements = document.getElementsByTag("a");

        for (Element e : elements) {
            if (e.text().contains("letzte")) {
                System.out.println(e);
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + ": " + url;
    }
}
