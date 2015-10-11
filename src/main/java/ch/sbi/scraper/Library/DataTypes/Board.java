package ch.sbi.scraper.Library.DataTypes;

/**
 * Created by sbi on 11.10.15.
 */
public final class Board implements ForumItem {
    private final String name;
    private final String url;

    public Board(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name + ": " + url;
    }
}
