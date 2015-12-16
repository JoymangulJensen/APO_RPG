/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

/**
 * Class used to interact with AI & player
 * 
 * @author p1509413
 */
public class Controller 
{
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
        // TODO : au moment de l'appel de cette méthode, enlever l'objet de l'inventaire
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