package ch.sbi.scraper.test.mapper;

import ch.sbi.scraper.datatype.marshalling.Thread;
import ch.sbi.scraper.library.utility.SourceBuilder;
import ch.sbi.scraper.mapper.ThreadMapper;
import ch.sbi.scraper.test.helper.SourceBuilderMock;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ThreadMapperTest {
    private final ThreadMapper mapper;

    public ThreadMapperTest() {
        SourceBuilder testSourceBuilder = new SourceBuilderMock();
        this.mapper = new ThreadMapper(testSourceBuilder);
    }

    @Test
    public void getThread_Id() throws Exception {
        Thread thread = mapper.getThread(1);
        assertNotNull(thread);
        assertNotNull(thread.getPosts());
        int firstPagePosts = Integer.valueOf(thread.getPosts().getCount());
        assertEquals(30, firstPagePosts);
        int postCount = thread.getNumberOfReplies().getValue().intValue();
        assertEquals(282, postCount);
        int pageCount = thread.getNumberOfPages().getValue().intValue();
        assertEquals(10, pageCount);
    }

    @Test
    public void getThread_Id_Page() throws Exception {
        Thread thread = mapper.getThread(1, 7);
        assertNotNull(thread.getPosts());
        assertEquals(7, thread.getPosts().getPage().intValue());
        assertEquals(180, thread.getPosts().getOffset().intValue());
    }

    @Test
    public void getPages_Stream_All_Pages() throws Exception {
        Stream<Thread> pages = mapper.getPages(1);
        assertEquals(10, pages.count());
    }
    
    @Test
    public void getPages_Stream_Same_Thread() throws Exception {
        Stream<Thread> pages = mapper.getPages(1);
        pages.forEach(p -> assertEquals(215183, p.getId().intValue()));
    }

    @Test
    public void getPages_Stream_No_Empty_Pages() throws Exception {
        Stream<Thread> pages = mapper.getPages(1);
        pages.forEach(p -> assertNotEquals("0", p.getPosts().getCount()));
    }
}