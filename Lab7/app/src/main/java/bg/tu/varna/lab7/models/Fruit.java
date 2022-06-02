package bg.tu.varna.lab7.models;

public class Fruit extends Vegetable {
    private int sugaryIndex;

    public Fruit(String name, Double price, int count, int sugaryIndex) {
        super(name, price, count);
        this.sugaryIndex = sugaryIndex;
    }

    public int getIndex() {
        return sugaryIndex;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Fruit{" +
                "sugaryIndex=" + sugaryIndex +
                '}';
    }
}
