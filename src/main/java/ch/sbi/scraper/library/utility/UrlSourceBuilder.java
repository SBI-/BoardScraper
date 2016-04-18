package ch.sbi.scraper.library.utility;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by sbi on 11.04.16.
 */
public class UrlSourceBuilder implements SourceBuilder {
    private final String categories;
    private final String base;
    private final String board;
    private final String thread;

    public UrlSourceBuilder(String base) {
        this.base = base;
        this.categories = String.format("%s%s", base, "xml/boards.php");
        this.board = String.format("%s%s?BID=", base, "xml/board.php");
        this.thread = String.format("%s%s?TID=", base, "xml/thread.php");
    }

    public Source getCategoriesSource() {
        return new StreamSource(categories);
    }

    public Source getBoardSource(long id) {
        String address = String.format("%s%d", board, id);
        return new StreamSource(address);
    }

    public Source getBoardSource(long id, long page) {
        String address = String.format("%s%d&page=%d", board, id, page);
        return new StreamSource(address);
    }

    public Source getThreadSource(long id) {
        String address = String.format("%s%d", thread, id);
        return new StreamSource(address);
    }

    public Source getThreadSource(long id, long page) {
        String address = String.format("%s%d&page=%d", thread, id, page);
        return new StreamSource(address);
    }

}
