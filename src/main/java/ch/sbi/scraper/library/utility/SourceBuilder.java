package ch.sbi.scraper.library.utility;

import javax.xml.transform.Source;

/**
 * Created by sbi on 11.04.16.
 */
public interface SourceBuilder {
    Source getCategoriesSource();
    Source getBoardSource(long id);
    Source getBoardSource(long id, long page);
    Source getThreadSource(long id);
    Source getThreadSource(long id, long page);
}
