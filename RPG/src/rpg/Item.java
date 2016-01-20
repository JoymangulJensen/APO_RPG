/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen JOYMANGUL
 */
package rpg;

/**
 * Item class : represents an item / object of the game
 *
 * @author p1509413
 */
public class Item
{

    protected Effect effect;    //Effect of the item
    protected String name = "Default"; // Name of item
    protected int weight;       //Weight of item
    protected boolean using;    //Status if item is in use

    /**
     * Constructor
     *
     * @param n : name of the item
     * @param w : weight of the item
     * @param using : use or not the item
     */
    public Item(String n, int w, boolean using)
    {
        this.name = n;
        this.weight = w;
        this.using = using;
    }

    /**
     * Constructor with also effect as argument
     *
     * @param n : name of the item
     * @param w : weight of the item
     * @param using : use or not the item
     * @param e : effect of item
     */
    public Item(String n, int w, boolean using, Effect e)
    {
        this(n, w, using);
        this.setEffect(e);
    }

    /**
     * Add an effect to the item
     *
     * @param e : The effect to add
     */
    public void setEffect(Effect e)
    {
        this.effect = e;
    }

    // ------ Getters
    public Effect getEffect()
    {
        return effect;
    }

    public String getName()
    {
        return name;
    }

    public int getWeight()
    {
        return weight;
    }

    @Override
    public String toString()
    {
        String s;
        s = "Item : " + this.getName() + " have a wieght of " + this.getWeight() + "\n";
        s += this.getEffect().toString() + "\n";
        return s;
    }
}
