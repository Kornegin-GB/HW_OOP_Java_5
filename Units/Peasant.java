package HW_OOP_Java_5.Units;

import HW_OOP_Java_5.System.Vector2D;

import java.util.List;
import java.util.Random;

public class Peasant extends Unit {
    private boolean delivery;

    public Peasant(List<Unit> ownGang, List<Unit> enemyGang, int x, int y) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Крестьянин");
        delivery = true;
        super.ownGang = ownGang;
        super.position = new Vector2D(x, y);
        super.enemyGang = enemyGang;
        this.quantity = new Random().nextInt(1, 5);
    }

    @Override
    public void step() {
        if (status.equals("Занят")) {
            status = "Стоит";
        }
    }

    @Override
    public String getInfo() {
        return name + "-> " + super.getInfo();
    }
}
