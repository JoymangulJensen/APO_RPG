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
 * Class representing the "class" of character Healer
 *
 * @author p1509413
 */
public final class Healer extends Character
{

    /**
     * Maximum Health of the class Healer
     */
    protected final int MAX_HEALTH_HEALER = 100;

    public Healer()
    {
        super();
        this.setMaxHealth(MAX_HEALTH_HEALER);
    }

    /**
     * Init the inventory of the healer
     */
    @Override
    protected void initInventory()
    {
        super.inventory.add(new Edible("MinorHealthPotion", 10, true, new Effect(Ability.HEALTH, 50, 1)));
        super.inventory.add(new Edible("MinorHealthPotion", 10, true, new Effect(Ability.HEALTH, 50, 1)));
        super.inventory.add(new Edible("MinorHealthPotion", 10, true, new Effect(Ability.HEALTH, 50, 1)));
        super.inventory.add(new Edible("MajorHealthPotion", 10, true, new Effect(Ability.HEALTH, 100, 1)));
        super.inventory.add(new Edible("MajorHealthPotion", 10, true, new Effect(Ability.HEALTH, 100, 1)));
        super.inventory.add(new Edible("MajorHealthPotion", 10, true, new Effect(Ability.HEALTH, 100, 1)));
        super.inventory.add(new Weapon("BeautifulStaff", 60, 90, 50));
        this.armors[0] = new Armor("LightArmor", 10, 50);
        this.armors[1] = new Armor("DarkArmor", 50, 85);
        this.equipeArmor(1);
    }
}
