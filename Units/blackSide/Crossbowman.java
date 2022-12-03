package HW_OOP_Java_5.Units.blackSide;

import HW_OOP_Java_5.System.Vector2D;
import HW_OOP_Java_5.Units.Unit;

import java.util.List;
import java.util.Random;

public class Crossbowman extends Unit {
    private int shots;

    public Crossbowman(List<Unit> ownGang, List<Unit> enemyGang, int x, int y) {
        super(6, 3, new int[]{1, 3}, 10, 4, "Арбалетчик");
        shots = 16;
        super.ownGang = ownGang;
        super.position = new Vector2D(x, y);
        super.enemyGang = enemyGang;
        this.quantity = new Random().nextInt(2, 5);
    }

    @Override
    public void step() {
        for (Unit unit : super.ownGang) {
            if (unit.getName().equals("Крестьянин")) {
                if (!unit.getStatus().equals("Занят") && !unit.getStatus().equals("Мертв")) {
                    shots++;
                    unit.setAction("Занят");
                    break;
                }
            }
        }
        if (shots > 0) {
            double dist = Double.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < enemyGang.size(); i++) {
                double tmp = enemyGang.get(i).getPosition().getDist(this.position);
                if (dist > tmp && !enemyGang.get(i).getStatus().equals("Мертв")) {
                    dist = tmp;
                    index = i;
                }
            }
            if (index >= 0) {
                enemyGang.get(index).getHit(speed > dist ? getDamage(enemyGang.get(index)) : getDamage(enemyGang.get(index)) / 2);
                shots--;
            }
        }
    }

    @Override
    public String getInfo() {
        return name + "-> " + super.getInfo() + ", \uD83D\uDCA5" + shots;
    }
}
