package L_33_HW_01;

public class RemoteControl {
    private Television television;

    public RemoteControl(Television television) {
        this.television = television;
    }

    public void on(int channelNumber) {
        Channel channel = television.getChannelByNumber(channelNumber);
        if (channel != null) {
            TVShow tvShow = channel.getRandomtvShow();
            if (tvShow != null) {
                System.out.println("Сейчас идет передача: " + tvShow.getName() + " на канале " + channelNumber);
            } else {
                System.out.println("На канале " + channelNumber + " нет передач");
            }
        } else {
            System.out.println("Канала с номером " + channelNumber + " не существует");
        }
    }
}
