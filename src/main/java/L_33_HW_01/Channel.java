package L_33_HW_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Channel {
    private int number;
    private List<TVShow> tvShows;

    public Channel(int number) {
        this.number = number;
        this.tvShows = new ArrayList<>();
    }

    public void addtvShow(TVShow transmission) {
        tvShows.add(transmission);
    }

    public int getNumber() {
        return number;
    }

    public TVShow getRandomtvShow() {
        if (tvShows.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(tvShows.size());
        return tvShows.get(index);
    }
}
