package ch.sbi.scraper.library.utility;

import javax.xml.transform.Source;
import java.io.IOException;

/**
 * Created by sbi on 11.04.16.
 */
public interface SourceBuilder {
    Source getBoardSource() throws IOException;
}
