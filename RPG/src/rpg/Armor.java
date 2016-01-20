/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;
/**
 * Class representing an armor : defensive item
 * 
 * @author Gaetan
 */
public class Armor extends Item
{
    private int resistance; //resistance of armor

    //----------------------------- Getter -----------------------------
    public int getResistance()
    {
        return resistance;
    }

    //----------------------------- Setter -----------------------------
    public void setResistance(int resistance)
    {
        this.resistance = resistance;
    }
      
    
    /**
     * Constructor
     * @param n Name of armor
     * @param w Wright of armor
     * @param resistance    Resistance of armor
     */
    public Armor(String n, int w, int resistance)
    {
        super(n, w,true);
        this.resistance = resistance;
        Effect temp = new Effect(Ability.DEFENCE,resistance, 1000); //1000 = infinite nulber of round
        super.setEffect(temp);
    }
    
    @Override
    public String toString()
    {
        String s;
        s = "Armor: " + this.getName() + " - Bonus def : " + this.resistance;
                
        /* + " have a wieght of " + this.getWeight() + "\n";
        s+= " Your defence is increased by  " + this.resistance;
        */
        return s;
    }
}
