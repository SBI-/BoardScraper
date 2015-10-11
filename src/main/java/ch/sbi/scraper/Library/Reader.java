package ch.sbi.scraper.Library;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class Reader {
    protected final String url;
    protected final Document document;

    protected Reader(String url) throws IOException {
        this.url = url;
        this.document = Jsoup.connect(url).get();
    }
}
