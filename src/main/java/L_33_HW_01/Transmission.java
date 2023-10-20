package L_33_HW_01;

public class Transmission {
    private String name;

    public Transmission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "name='" + name + '\'' +
                '}';
    }
}
