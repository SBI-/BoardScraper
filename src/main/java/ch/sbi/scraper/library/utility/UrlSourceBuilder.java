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

    public UrlSourceBuilder(String base) {
        this.base = base;
        this.categories = String.format("%s%s", base, "xml/boards.php");
    }

    public Source getCategoriesSource() throws IOException {
        return new StreamSource(categories);
    }
    
}
