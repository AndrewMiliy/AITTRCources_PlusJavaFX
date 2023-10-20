package L_33_HW_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Channel {
    private int number;
    private List<Transmission> transmissions;

    public Channel(int number) {
        this.number = number;
        this.transmissions = new ArrayList<>();
    }

    public void addTransmission(Transmission transmission) {
        transmissions.add(transmission);
    }

    public int getNumber() {
        return number;
    }

    public Transmission getRandomTransmission() {
        if (transmissions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(transmissions.size());
        return transmissions.get(index);
    }
}
