package ch.sbi.scraper.Library.DataTypes;

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

    public Page getPage(int index) {
        return null;
    }
}
