package ch.sbi.scraper.test.mapper;

import ch.sbi.scraper.mapper.CategoryMapper;
import ch.sbi.scraper.test.helper.TestSourceBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {
    private final CategoryMapper mapper;

    public CategoryMapperTest() {
        TestSourceBuilder testSourceBuilder = new TestSourceBuilder();
        this.mapper = new CategoryMapper(testSourceBuilder);
    }

    @Test
    public void getCategories() throws Exception {

    }
}