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
    private final String boards;
    private final String base;

    public UrlSourceBuilder(String base) {
        this.base = base;
        this.boards = String.format("%s%s", base, "xml/boards.php");
    }

    public Source getBoardSource() throws IOException {
        return new StreamSource(boards);
    }
}
