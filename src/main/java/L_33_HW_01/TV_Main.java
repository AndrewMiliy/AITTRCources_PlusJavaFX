package L_33_HW_01;

public class TV_Main {
    public static void main(String[] args) {
        // Создаем телевизор и добавляем каналы
        Television tv = new Television();
        Channel channel1 = new Channel(1);
        channel1.addTransmission(new Transmission("Новости"));
        channel1.addTransmission(new Transmission("Фильм 'Матрица'"));
        tv.addChannel(channel1);

        Channel channel2 = new Channel(2);
        channel2.addTransmission(new Transmission("Сериал 'Друзья'"));
        channel2.addTransmission(new Transmission("Мультфильм 'Шрек'"));
        tv.addChannel(channel2);

        RemoteControl remote = new RemoteControl(tv);

        remote.on(1);  // Выбор канала номер 1
        remote.on(2);  // Выбор канала номер 2
    }
}
