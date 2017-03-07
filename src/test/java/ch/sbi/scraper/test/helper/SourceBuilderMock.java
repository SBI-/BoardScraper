package ch.sbi.scraper.test.helper;

import ch.sbi.scraper.library.utility.SourceBuilder;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * SourceBuilder for reading single xml files from disk.
 *
 * <p>
 *     This is necessary for not loading data from the internet when executing tests, which makes it easier
 *     to spot problems with the code vs. problems with networking. Still not an optimal solution, but
 *     better than relying on networking.
 * </p>
 */
public class SourceBuilderMock implements SourceBuilder {
    private final String boardFile = "/board/board_%d_page_%d.xml";
    private final String threadFile = "/thread/thread_%d_page%d.xml";
    private final String categoriesFile = "/categories/boards.xml";

    @Override
    public Source getCategoriesSource() {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(categoriesFile);
        return new StreamSource(resourceAsStream);
    }

    @Override
    public Source getBoardSource(long id, long page) {
        String file = String.format(boardFile, id, page);
        InputStream resourceAsStream = this.getClass().getResourceAsStream(file);
        return new StreamSource(resourceAsStream);
    }

    @Override
    public Source getThreadSource(long id, long page) {
        String file = String.format(threadFile, id, page);
        InputStream resourceAsStream = this.getClass().getResourceAsStream(file);
        return new StreamSource(resourceAsStream);
    }
}
