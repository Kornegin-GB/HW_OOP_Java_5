package HW_OOP_Java_5.Units.whiteSide;

import HW_OOP_Java_5.System.Vector2D;
import HW_OOP_Java_5.Units.Unit;

import java.util.List;
import java.util.Random;

public class Wizard extends Unit {
    private boolean magic;

    public Wizard(List<Unit> ownGang, List<Unit> enemyGang, int x, int y) {
        super(17, 12, new int[]{-5, -5}, 30, 9, "Волшебник");
        magic = true;
        super.ownGang = ownGang;
        super.position = new Vector2D(x, y);
        super.enemyGang = enemyGang;
        this.quantity = new Random().nextInt(2, 3);
    }

    @Override
    public String getInfo() {
        return name + "-> " + super.getInfo();
    }

    @Override
    public void step() {
        double minHealth = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < ownGang.size(); i++) {
            if (ownGang.get(i).getHealth() < ownGang.get(i).getMAX_HEALTH()) {
                if (ownGang.get(i).getHealth() < minHealth) {
                    if (!ownGang.get(i).getStatus().equals("Мертв")) {
                        minHealth = ownGang.get(i).getHealth();
                        minIndex = i;
                    }
                }
            }
        }
        if (minIndex >= 0) {
            ownGang.get(minIndex).setHealth(ownGang.get(minIndex).getHealth() - this.damage[0]);
            if (ownGang.get(minIndex).getHealth() > ownGang.get(minIndex).getMAX_HEALTH()) {
                ownGang.get(minIndex).setHealth(ownGang.get(minIndex).getMAX_HEALTH());
            }
        }
    }
}
