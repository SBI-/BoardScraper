package ch.sbi.scraper.Library;

import ch.sbi.scraper.Library.DataTypes.Board;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public final class Forum extends Reader {
    private HashMap<Integer, Board> boards = new HashMap<Integer, Board>();

    public Forum(String url) throws IOException {
        super(url);

        // class nu seems to be what contains a board title
        Elements nu = document.getElementsByClass("nu");
        // nu, however, is used for multiple types of links, so we filter out the ones we don't need
        nu.removeClass("wht");

        for (Element e : nu) {
            // we only want boards, but not threads.
            if (e.attr("href").contains("board")) {
                Board board = new Board(e.text(), e.attr("abs:href"));
                boards.put(board.getId(), board);
            }
        }
    }

    public Board getBoardById(Integer id) {
        return boards.get(id);
    }

    public Map<Integer, Board> getBoards() {
        return Collections.unmodifiableMap(boards);
    }
}
