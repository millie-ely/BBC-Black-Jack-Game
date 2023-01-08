package main.UserModel;

public enum Status {
    WIN(1),
    PROGRESS(0),
    BUST(-1);

    int value;
    Status(int v) {
        value = v;
    }
    public int getValue() {
        return value;
    }
}