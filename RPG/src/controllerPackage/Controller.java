/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package controllerPackage;

import rpg.Action;
import rpg.Capacity;
import rpg.Character;
import rpg.Edible;

/**
 * Class used to interact with AI and player
 * 
 * @author p1509413
 */
public class Controller
{
    
    protected Character character;

    public Character getCharacter()
    {
        return character;
    }
    
    public Controller(Character c)
    {
        character = c;
    }
    
    /**
     * Play an action by consuming an item
     * 
     * @param source : the character playing the turn : using an item
     * @param target : the target of the action
     * @param i : the item to use
     * @return the action created
     */
    public Action useItem(Character source, Character target, Edible i)
    {
        source.removeItem(i);
        return new Action(source, target, i);
    }
    
    /**
     * Play an action by using a capacity
     * 
     * @param source : the character playing the turn : using a capacity
     * @param target : the character suffering the capacity
     * @param c : the capacity to use
     * @return the action created
     */
    public Action useCapacity(Character source, Character target, Capacity c)
    {
        return new Action(source, target, c);
    }
}
