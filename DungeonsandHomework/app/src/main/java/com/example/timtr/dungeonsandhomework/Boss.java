package com.example.timtr.dungeonsandhomework;

/**
 * Created by timtr on 2017-11-18.
 */

public class Boss {

    private int health;
    private int gold;
    private int healthRegen;
    private String name;

    public Boss(int _health, int _gold, int _healthRegen, String _name) {
        this.health = _health;
        this.gold = _gold;
        this.healthRegen = _healthRegen;
        this.name = _name;
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

    public String getBossName() {
        return name;
    }
}
