package Segregation;

public enum Characteristic {
    TwoTypes(2), ThreeTypes(3);

    private final int value;

    private Characteristic(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
