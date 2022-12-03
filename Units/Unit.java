package HW_OOP_Java_5.Units;

import HW_OOP_Java_5.System.ImageGame;
import HW_OOP_Java_5.System.Vector2D;

import java.util.List;

public abstract class Unit implements UnitInterface {
    protected final double MAX_HEALTH;
    protected String name;
    protected double health;
    protected int attack;
    protected int protection;
    protected int[] damage;
    protected int speed;
    protected String status;
    protected List<Unit> ownGang, enemyGang;
    protected Vector2D position;
    protected int quantity;


    public Unit(int attack, int protection, int[] damage, double health, int speed, String name) {
        this.attack = attack;
        this.name = name;
        this.protection = protection;
        this.damage = damage;
        this.health = health;
        this.MAX_HEALTH = health;
        this.speed = speed;
        this.status = "Стоит";
    }

    public Vector2D getPosition() {
        return position;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setAction(String status) {
        this.status = status;
    }

    public int getSpeed() {
        return speed;
    }

    public double getDamage(Unit enemy) {
        if (enemy.protection - this.attack == 0) {
            return ((this.damage[0] + this.damage[1]) / 2.0) * this.quantity;
        }
        if (enemy.protection - this.attack < 0) {
            return this.damage[1] * this.quantity;
        }
        return this.damage[0] * this.quantity;
    }

    public void getHit(double damage) {
        double tmpHealth = (this.quantity - 1) * this.MAX_HEALTH + this.health;
        tmpHealth -= damage;
        if (tmpHealth <= 0) {
            this.health = 0;
            this.status = "Мертв";
            this.quantity = 0;
        } else {
            this.quantity = (int) (tmpHealth / this.MAX_HEALTH);
            this.health = this.MAX_HEALTH;
            if (tmpHealth % this.MAX_HEALTH > 0) {
                this.quantity++;
                this.health = tmpHealth % this.MAX_HEALTH;
            }
        }
    }

    @Override
    public String getInfo() {
        return ImageGame.ATTACK + attack +
                ", " + ImageGame.PROTECTION + protection +
                ", " + ImageGame.DAMAGE + (damage[0] + damage[1]) / 2 +
                ", " + ImageGame.HEALTH + health +
                ", " + ImageGame.SPEED + speed +
                ", " + status + ", q:" + quantity + ", [" + position.getX() + ", " + position.getY() + "]";
    }

    @Override
    public void step() {

    }
}
