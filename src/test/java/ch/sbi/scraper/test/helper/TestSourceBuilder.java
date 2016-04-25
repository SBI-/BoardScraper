package ch.sbi.scraper.test.helper;

import ch.sbi.scraper.library.utility.SourceBuilder;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * Created by sbi on 25.04.16.
 */
public class TestSourceBuilder implements SourceBuilder {
    private String boardFile = "/board_page_%d.xml";

    @Override
    public Source getCategoriesSource() {
        return null;
    }

    @Override
    public Source getBoardSource(long id) {
        String file = String.format(boardFile, id);
        InputStream resourceAsStream = this.getClass().getResourceAsStream(file);
        return new StreamSource(resourceAsStream);
    }

    @Override
    public Source getBoardSource(long id, long page) {
        String file = String.format(boardFile, page);
        InputStream resourceAsStream = this.getClass().getResourceAsStream(file);
        return new StreamSource(resourceAsStream);
    }

    @Override
    public Source getThreadSource(long id) {
        return null;
    }

    @Override
    public Source getThreadSource(long id, long page) {
        return null;
    }
}
