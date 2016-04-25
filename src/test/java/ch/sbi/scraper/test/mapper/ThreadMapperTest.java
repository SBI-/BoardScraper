package ch.sbi.scraper.test.mapper;

import ch.sbi.scraper.datatype.marshalling.*;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.mapper.ThreadMapper;
import ch.sbi.scraper.test.helper.TestSourceBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadMapperTest {
    private final ThreadMapper mapper;

    public ThreadMapperTest() {
        SourceBuilder testSourceBuilder = new TestSourceBuilder();
        this.mapper = new ThreadMapper(testSourceBuilder);
    }

    @Test
    public void testGetThread_Id() throws Exception {
        ch.sbi.scraper.datatype.marshalling.Thread thread = mapper.getThread(1);
        assertNotNull(thread);
        assertNotNull(thread.getPosts());
        int firstPagePosts = Integer.valueOf(thread.getPosts().getCount());
        assertTrue(firstPagePosts == 30);
        int postCount = thread.getNumberOfReplies().getValue().intValue();
        assertTrue(postCount == 282);
    }

    @Test
    public void testGetThread_Id_Page() throws Exception {
        ch.sbi.scraper.datatype.marshalling.Thread thread = mapper.getThread(1, 7);
        assertTrue(thread.getPosts().getPage().intValue() == 7);
    }

    @Test
    public void testGetPages_Stream() throws Exception {
    }
}