package ch.sbi.scraper.library.utility;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sbi on 11.04.16.
 */
public class UrlSourceBuilder implements SourceBuilder {
    private String base;

    public UrlSourceBuilder(String base) {
        this.base = base;
    }

    public Source getBoardSource() throws IOException {
        String address = String.format("%s%s", base, "xml/boards.php");
        //URL url = new URL(address);
        return new StreamSource(address);
    }
}
