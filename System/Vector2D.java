package HW_OOP_Java_5.System;

public class Vector2D {
    int x, y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEquals(Vector2D opposit) {
        return opposit.y == y && opposit.x == x;
    }

    public double getDist(Vector2D opposit) {
        return Math.sqrt(Math.pow(opposit.x - this.x, 2) + Math.pow(opposit.y - this.y, 2));
    }
}
