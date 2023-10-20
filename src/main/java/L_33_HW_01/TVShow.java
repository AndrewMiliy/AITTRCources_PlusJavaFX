package L_33_HW_01;

public class TVShow {
    private String name;

    public TVShow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TVShow{" +
                "name='" + name + '\'' +
                '}';
    }
}
