package ch.sbi.scraper.library.utility;

import javax.xml.transform.Source;

public interface SourceBuilder {
    Source getCategoriesSource();
    Source getBoardSource(long id, long page);
    Source getThreadSource(long id, long page);
}
