package L_33_HW_01;

import java.util.ArrayList;
import java.util.List;

public class Television {
    private List<Channel> channels;

    public Television() {
        this.channels = new ArrayList<>();
    }

    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    public Channel getChannelByNumber(int number) {
        for (Channel channel : channels) {
            if (channel.getNumber() == number) {
                return channel;
            }
        }
        return null;
    }
}
