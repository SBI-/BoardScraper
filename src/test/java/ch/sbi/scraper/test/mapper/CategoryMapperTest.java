package ch.sbi.scraper.test.mapper;

import ch.sbi.scraper.datatype.marshalling.Category;
import ch.sbi.scraper.mapper.CategoryMapper;
import ch.sbi.scraper.test.helper.TestSourceBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryMapperTest {
    private final CategoryMapper mapper;

    public CategoryMapperTest() {
        TestSourceBuilder testSourceBuilder = new TestSourceBuilder();
        this.mapper = new CategoryMapper(testSourceBuilder);
    }

    @Test
    public void getCategories() throws Exception {
        List<Category> categories1 = mapper.getCategories();
        assertEquals(categories1.size(), 10);
        // a second call needs to be the same object, because of lazy loading.
        List<Category> categories2 = mapper.getCategories();
        assertSame(categories1, categories2);
    }

    @Test
    public void getBoard() throws Exception {
    }
}