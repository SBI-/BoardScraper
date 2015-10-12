package ch.sbi.scraper.Library.DataTypes;

import java.util.List;

public final class Page implements ForumItem{
    private final int index;

    public Page(int index) {
        this.index = index;
    }

    public List<TopicThread> getThreads() {
        return null;
    }
}
