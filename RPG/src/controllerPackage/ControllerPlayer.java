/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package controllerPackage;

import model.Healer;
import model.Warrior;
import rpg.Action;
import rpg.Armor;
import rpg.Attack;
import rpg.Capacity;
import rpg.Character;
import rpg.Edible;
import rpg.Heal;
import rpg.Parry;

/**
 * Class managing the actions of the player
 *
 * @author Gaetan
 */
public class ControllerPlayer extends Controller
{

    public ControllerPlayer(Character c)
    {
        super(c);
    }

    /**
     * Get an action Use the supermethod
     *
     * @param target : the target of the capacity
     * @return the action chosen by the player
     */
    public Action getAction(Character target)
    {
        int choice = DisplayUI.getAction(character);

        switch (choice)
        {
            case 1: // Capacity
                return super.useCapacity(character, target, askCapacity(target));
            case 2: // Item 
                if (character.getNbEdibles() == 0)
                {
                    // No edible in the inventory of the character : cant use an item
                    System.out.println("You don't have any edibles in your inventory");
                    return this.getAction(target);
                } else
                {
                    return super.useItem(character, target, this.askItem());
                }
            default:
                return super.useCapacity(character, target, askCapacity(target));
        }
    }

    /**
     * Ask the player for a capacity
     *
     * @param target the target selected before
     * @return
     */
    private Capacity askCapacity(Character target)
    {
        Capacity capacity;

        int choice = DisplayUI.getCapacityChoice();

        switch (choice)
        {
            case 1:
                if (character.getClass() == Healer.class)
                {
                    System.out.println("A Healer can't do this");
                    return this.askCapacity(target);
                }
                capacity = new Attack(character, target);
                break;
            case 2:
                if (character.getClass() == Warrior.class)
                {
                    System.out.println("A Warrior can't do this");
                    return this.askCapacity(target);
                }
                capacity = new Heal(character, target);
                break;
            case 3:
                capacity = new Parry(character);
                break;
            default:
                capacity = new Attack(character, target);
                break;
        }

        return capacity;
    }

    /**
     * Get an item to use from the character
     *
     * @param target
     * @return
     */
    private Edible askItem()
    {
        int choice = DisplayUI.getItemToUse(character);

        if (character.getInventory().get(choice).getClass() == Edible.class)
        {
            return (Edible) character.getInventory().get(choice);
        } else
        {
            System.out.println("You can not use this item. Please, pick an edible");
            return askItem();
        }
    }
}
