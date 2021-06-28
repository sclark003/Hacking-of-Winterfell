package com.example;

public class Army {
    public String army_name = "";
    public int no_units = 0;
    public int no_infantry = 0;
    public int unit_defence = 0;
    public int unit_damage = 0;
    public int infantry_defence = 0;
    public int infantry_damage = 0;
    public int totalDefence = 0;
    public int totalDamage = 0;

    public int totalUnitDefence(){
        int defence = no_units*unit_defence;
        return defence;
    }

    public int totalUnitDamage(){
        int damage = no_units*unit_damage;
        return damage;
    }

    public int totalInfantryDefence(){
        int defence = no_infantry*infantry_defence;
        return defence;
    }

    public int totalInfantryDamage(){
        int damage = no_infantry*infantry_damage;
        return damage;
    }

    public int calcDamage(){
        int damage_unit = totalUnitDamage();
        int damage_inf = totalInfantryDamage();
        int damage = damage_unit + damage_inf;
        return damage;
    }

    public int calcDefence(){
        int defence_unit = totalUnitDefence();
        int defence_inf = totalInfantryDefence();
        int defence = defence_unit + defence_inf;
        return defence;
    }
}
