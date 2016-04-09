package ch.sbi.scraper.DataTypes.Dto;

import java.io.IOException;
import java.net.URL;

public class Forum {
    private final URL url;

    public Forum getInstance(URL url) {
        return new Forum(url);
    }

    private Forum(URL url) {
        this.url = url;
    }
}