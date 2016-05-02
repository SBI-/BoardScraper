package ch.sbi.scraper.test.mapper;

import ch.sbi.scraper.datatype.marshalling.*;
import ch.sbi.scraper.datatype.marshalling.Thread;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.mapper.ThreadMapper;
import ch.sbi.scraper.test.helper.TestSourceBuilder;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ThreadMapperTest {
    private final ThreadMapper mapper;

    public ThreadMapperTest() {
        SourceBuilder testSourceBuilder = new TestSourceBuilder();
        this.mapper = new ThreadMapper(testSourceBuilder);
    }

    @Test
    public void getThread_Id() throws Exception {
        Thread thread = mapper.getThread(1);
        assertNotNull(thread);
        assertNotNull(thread.getPosts());
        int firstPagePosts = Integer.valueOf(thread.getPosts().getCount());
        assertEquals(firstPagePosts, 30);
        int postCount = thread.getNumberOfReplies().getValue().intValue();
        assertEquals(postCount, 282);
        int pageCount = thread.getNumberOfPages().getValue().intValue();
        assertEquals(pageCount, 10);
    }

    @Test
    public void getThread_Id_Page() throws Exception {
        Thread thread = mapper.getThread(1, 7);
        assertNotNull(thread.getPosts());
        assertEquals(thread.getPosts().getPage().intValue(), 7);
        assertEquals(180, thread.getPosts().getOffset().intValue());
    }

    @Test
    public void getPages_Stream_All_Pages() throws Exception {
        Stream<Thread> pages = mapper.getPages(1);
        assertEquals(pages.count(), 10);
    }
    
    @Test
    public void getPages_Stream_Same_Thread() throws Exception {
        Stream<Thread> pages = mapper.getPages(1);
        pages.forEach(p -> assertEquals(p.getId().intValue(), 215183));
    }

    @Test
    public void getPages_Stream_No_Empty_Pages() throws Exception {
        Stream<Thread> pages = mapper.getPages(1);
        pages.forEach(p -> assertNotEquals(p.getPosts().getCount(), "0"));
    }
}