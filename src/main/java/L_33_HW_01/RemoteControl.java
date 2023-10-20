package L_33_HW_01;

public class RemoteControl {
    private Television television;

    public RemoteControl(Television television) {
        this.television = television;
    }

    public void on(int channelNumber) {
        Channel channel = television.getChannelByNumber(channelNumber);
        if (channel != null) {
            Transmission transmission = channel.getRandomTransmission();
            if (transmission != null) {
                System.out.println("Сейчас идет передача: " + transmission.getName() + " на канале " + channelNumber);
            } else {
                System.out.println("На канале " + channelNumber + " нет передач");
            }
        } else {
            System.out.println("Канала с номером " + channelNumber + " не существует");
        }
    }
}
