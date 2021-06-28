package com.example;

import com.example.Army;

import javax.management.RuntimeErrorException;

public class Solution {

    /* INPUT
	string first_strike_army_name
	int no_of_dragons
	int no_of_white_lords       */

    public static void main(String[] args) {

        /*
        String first_strike_army_name = args[0];
        int no_of_dragons = Integer.parseInt(args[1]);
        int no_of_white_lords = Integer.parseInt(args[2]);

        String output = run(first_strike_army_name, no_of_dragons, no_of_white_lords);
        System.out.println(output); */

        //run("Seven Kingdom Army", 4, 5);   // White Walker Army|6
        //run("Seven Kingdom Army", 4, 1);   // White Walker Army | 6
        //run("Seven Kingdom Army", 10, 5);  // Seven Kingdom Army | 5
        //run("Seven Kingdom Army", 16, 18); // Seven Kingdom Army | 3
        run("Seven Kingdom Army", 2, 6);   // Seven Kingdom Army | 4
        //run("Seven Kingdom Army", -1, 5);  // Invalid parameter provided


    }

     public static void run(String first_strike_army_name, int no_of_dragons, int no_of_white_lords) {

        String result = "";
        int no_of_attack_units;
        int no_of_defend_units;

        if (no_of_dragons<0){
            throw new RuntimeException("Invalid parameter for 'no_of_dragons' provided.");
        }
        if (no_of_white_lords<0){
            throw new RuntimeException("Invalid parameter for 'no_of_white_lords' provided.");
        }

        if (first_strike_army_name == "Seven Kingdom Army"){
            no_of_attack_units = no_of_dragons;
            no_of_defend_units = no_of_white_lords;
        }
        else{
            no_of_attack_units = no_of_white_lords;
            no_of_defend_units = no_of_dragons;
        }

        // initialise armies
        Army Attacker = initialiseArmy(no_of_attack_units, first_strike_army_name, true);
        Army Defender = initialiseArmy(no_of_defend_units, first_strike_army_name, false);

        result = battle(Attacker, Defender);

        /* OUTPUT
	    string result (formatted as army_name|turns)  */

         System.out.println(result);
    }

    public static Army initialiseArmy(int no_units, String attacker_army_name, boolean isAttacker){

        String attack_army_name = "";
        int no_attack_infantry;
        int unit_attack_defence;
        int unit_attack_damage;
        int infantry_attack_defence;
        int infantry_attack_damage;

        String defend_army_name = "";
        int no_defend_infantry;
        int unit_defend_defence;
        int unit_defend_damage;
        int infantry_defend_defence;
        int infantry_defend_damage;

        if (attacker_army_name == "Seven Kingdom Army"){
            attack_army_name = "Seven Kingdom Army";
            no_attack_infantry = 5000;
            unit_attack_defence = 600;
            unit_attack_damage = 600;
            infantry_attack_defence = 2;
            infantry_attack_damage = 2;

            defend_army_name = "White Walker Army";
            no_defend_infantry = 10000;
            unit_defend_defence = 100;
            unit_defend_damage = 50;
            infantry_defend_defence = 3;
            infantry_defend_damage = 1;
        }
        else{
            attack_army_name = "White Walker Army";
            no_attack_infantry = 10000;
            unit_attack_defence = 100;
            unit_attack_damage = 50;
            infantry_attack_defence = 3;
            infantry_attack_damage = 1;

            defend_army_name = "Seven Kingdom Army";
            no_defend_infantry = 5000;
            unit_defend_defence = 600;
            unit_defend_damage = 600;
            infantry_defend_defence = 2;
            infantry_defend_damage = 2;
        }

        Army NewArmy = new Army();
        NewArmy.no_units = no_units;

        if (isAttacker == true){
            NewArmy.army_name = attack_army_name;
            NewArmy.no_infantry = no_attack_infantry;
            NewArmy.unit_defence = unit_attack_defence;
            NewArmy.unit_damage = unit_attack_damage;
            NewArmy.infantry_defence = infantry_attack_defence;
            NewArmy.infantry_damage = infantry_attack_damage;
        }
        else{
            NewArmy.army_name = defend_army_name;
            NewArmy.no_infantry = no_defend_infantry;
            NewArmy.unit_defence = unit_defend_defence;
            NewArmy.unit_damage = unit_defend_damage;
            NewArmy.infantry_defence = infantry_defend_defence;
            NewArmy.infantry_damage = infantry_defend_damage;
        }

        NewArmy.totalDamage = NewArmy.calcDamage();
        NewArmy.totalDefence = NewArmy.calcDefence();

        return NewArmy;
    }


    public static String battle(Army current_attacker, Army current_defender){
        boolean keepBattling = true;
        int round = 0;
        String victor = "";
        while (keepBattling){

            System.out.println("_______");
            System.out.println(current_attacker.army_name);
            System.out.println(current_attacker.no_units);
            System.out.println(current_attacker.no_infantry);
            System.out.println("");
            System.out.println(current_defender.army_name);
            System.out.println(current_defender.no_units);
            System.out.println(current_defender.no_infantry);
            System.out.println("_______");

            Army next_attacker = attackTurn(current_attacker, current_defender);
            Army next_defender = current_attacker;
            round = round + 1;

            if (next_attacker.totalDefence == 0){
                keepBattling = false;
                victor = next_defender.army_name;
            }

            next_attacker.totalDamage = next_attacker.calcDamage();
            next_defender.totalDamage = next_defender.calcDamage();
            current_attacker = next_attacker;
            current_defender = next_defender;
        }
        String result = victor + " | " + Integer.toString(round);
        return result;
    }

    public static Army attackTurn(Army attacker, Army defender){

        int x = attacker.totalDamage;                          // total damage power of attacking army for turn
        defender.totalDefence = defender.totalDefence - x;     // take away attacker's damage from defending army
        if (defender.totalDefence < 0){
            defender.totalDefence = 0;
        }

        // remove defending armies killed units
        int lost_units = x/defender.unit_defence;
        if (lost_units>defender.no_units) {
            lost_units = defender.no_units;
        }

        x = x - defender.totalUnitDefence();                   // total damage power of attacking army after destroying units
        defender.no_units = defender.no_units - lost_units;    // take away attacker's damage from defending army

        // remove defending armies killed infantry
        int lost_infantry = x/defender.infantry_defence;
        if (lost_infantry> defender.no_infantry) {
            lost_infantry = defender.no_infantry;
        }
        defender.no_infantry = defender.no_infantry - lost_infantry;

        return defender;
    }

    //public static int[] defend Turn()


}