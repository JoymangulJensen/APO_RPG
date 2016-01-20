/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package model;

import rpg.Ability;
import rpg.Armor;
import rpg.Character;
import rpg.Edible;
import rpg.Effect;
import rpg.Weapon;

/**
 * Class representing the "class" of character warrior
 *
 * @author p1509413
 */
public final class Warrior extends Character
{

    /**
     * Maximum Health of the class Warrior
     */
    protected final int MAX_HEALTH_WARRIOR = 300;

    public Warrior()
    {
        super();
        this.setMaxHealth(MAX_HEALTH_WARRIOR);
    }

    /**
     * Init the inventory of the warrior
     */
    @Override
    protected void initInventory()
    {
        super.inventory.add(new Edible("MinorHealthPotion", 10, true, new Effect(Ability.HEALTH, 50, 1)));
        super.inventory.add(new Edible("MinorHealthPotion", 10, true, new Effect(Ability.HEALTH, 50, 1)));
        super.inventory.add(new Weapon("BadassWeapon", 60, 90, 50));
        this.armors[0] = new Armor("VerySexyArmor", 20, 50);
        this.armors[1] = new Armor("MoulinRougeArmor", 40, 75);
        this.equipeArmor(0);
    }
}
