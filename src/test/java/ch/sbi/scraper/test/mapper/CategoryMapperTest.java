package ch.sbi.scraper.test.mapper;

import ch.sbi.scraper.datatype.marshalling.Board;
import ch.sbi.scraper.datatype.marshalling.Category;
import ch.sbi.scraper.mapper.CategoryMapper;
import ch.sbi.scraper.test.helper.SourceBuilderMock;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CategoryMapperTest {
    private final CategoryMapper mapper;

    public CategoryMapperTest() {
        SourceBuilderMock testSourceBuilder = new SourceBuilderMock();
        this.mapper = new CategoryMapper(testSourceBuilder);
    }

    @Test
    public void getCategories() throws Exception {
        List<Category> categories1 = mapper.getCategories();
        assertEquals(10, categories1.size());
        // a second call needs to be the same object, because of lazy loading.
        List<Category> categories2 = mapper.getCategories();
        assertSame(categories1, categories2);
    }

    @Test
    public void getBoard() throws Exception {
        Optional<Board> board = mapper.getBoard(14);
        assertTrue(board.isPresent());
        board = mapper.getBoard(1);
        assertFalse(board.isPresent());
    }
}