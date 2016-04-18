package ch.sbi.scraper.library.utility;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

/**
 * Created by sbi on 11.04.16.
 */
public class UrlSourceBuilder implements SourceBuilder {
    private final String categories;
    private final String base;
    private final String board;

    public UrlSourceBuilder(String base) {
        this.base = base;
        this.categories = String.format("%s%s", base, "xml/boards.php");
        this.board = String.format("%s%s?BID=", base, "xml/board.php");
    }

    public Source getCategoriesSource() {
        return new StreamSource(categories);
    }

    public Source getBoardSource(int id) {
        String address = String.format("%s%d", board, id);
        return new StreamSource(address);
    }

    public Source getBoardSource(int id, int page) {
        String address = String.format("%s%d&page=%d", board, id, page);
        return new StreamSource(address);
    }
    
}
