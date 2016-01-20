/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

/**
 * Represents a weapon
 *
 * @author p1509413
 */
public class Weapon extends Item
{

    //-------------- Attributes -----------------------*

    private int manageability;  //ease of use of weapon(depends on dexterity)

    //----------------------- Getter --------------------------------
    public int getManageability()
    {
        return manageability;
    }

    //----------------------- Setter --------------------------------
    public void setManageability(int manageability)
    {
        this.manageability = manageability;
    }

    /**
     * Constructor
     *
     * @param name Name of weapon
     * @param w Weight of weapon
     * @param efficiency Efficiency of weapon
     * @param managable manageability of weapon
     */
    public Weapon(String name, int w, int efficiency, int managable)
    {
        super(name, w, true);
        Effect tempEffect = new Effect(Ability.STRENGTH, efficiency, 1000);
        super.setEffect(tempEffect);
        this.manageability = managable;
    }

}
