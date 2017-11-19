package com.example.timtr.dungeonsandhomework;

/**
 * Created by timtr on 2017-11-18.
 */

public class Boss {

    private int health;
    private int gold;
    private int healthRegen;

    public Boss(int _health, int _gold, int _healthRegen) {
        this.health = _health;
        this.gold = _gold;
        this.healthRegen = _healthRegen;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealthRegen() {
        return healthRegen;
    }

    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
    }

    public void takeDamage() {}

    public void doDamage() {}

    public void regen() {

    }
}
