package HW_OOP_Java_5.Units.blackSide;

import HW_OOP_Java_5.System.Vector2D;
import HW_OOP_Java_5.Units.Unit;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Spearman extends Unit {
    public Spearman(List<Unit> ownGang, List<Unit> enemyGang, int x, int y) {
        super(4, 5, new int[]{1, 3}, 10, 4, "Копейщик");
        super.ownGang = ownGang;
        super.position = new Vector2D(x, y);
        super.enemyGang = enemyGang;
        this.quantity = new Random().nextInt(15, 20);
    }

    @Override
    public void step() {
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
            if (dist < 2) {
                enemyGang.get(index).getHit(getDamage(enemyGang.get(index)));
            } else {
                Vector2D enemyPosition = enemyGang.get(index).getPosition();
                Vector2D newPosition = new Vector2D(0, 0);
                if (enemyPosition.getY() == this.position.getY()) {
                    newPosition.setY(this.position.getY());
                    if (this.position.getX() - enemyPosition.getX() < 0) {
                        newPosition.setX(this.position.getX() + 1);
                    } else {
                        newPosition.setX(this.position.getX() - 1);
                    }
                } else {
                    newPosition.setX(this.position.getX());
                    if (enemyPosition.getY() - this.position.getY() > 0) {
                        newPosition.setY(this.position.getY() + 1);
                    } else {
                        newPosition.setY(this.position.getY() - 1);
                    }
                }
                AtomicBoolean empty = new AtomicBoolean(true);
                ownGang.forEach(unit -> {
                    if (unit.getPosition().isEquals(newPosition)) {
                        empty.set(false);
                    }
                });
                if (empty.get()) {
                    this.position = newPosition;
                }
            }
        }
    }

    @Override
    public String getInfo() {
        return name + "-> " + super.getInfo();
    }
}
