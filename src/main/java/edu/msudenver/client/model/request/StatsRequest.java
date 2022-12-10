package edu.msudenver.client.model.request;

import edu.msudenver.profile.Profile;

import java.math.BigInteger;

public class StatsRequest {

    private Long statsId;
    private Profile profile;
    private int attack;
    private int defence;
    private int hp;
    private int xp;
    private BigInteger currentLevel;
    private int currentCellX;
    private int currentCellY;

    // setter methods

    public void setStatsId(Long statsId) {
        this.statsId = statsId;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setCurrentLevel(BigInteger currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setCurrentCellX(int currentCellX) {
        this.currentCellX = currentCellX;
    }

    public void setCurrentCellY(int currentCellY) {
        this.currentCellY = currentCellY;
    }

    // getter methods

    public Long getStatsId() {
        return statsId;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHp() {
        return hp;
    }

    public int getXp() {
        return xp;
    }

    public BigInteger getCurrentLevel() {
        return currentLevel;
    }

    public int getCurrentCellX() {
        return currentCellX;
    }

    public int getCurrentCellY() {
        return currentCellY;
    }

}
