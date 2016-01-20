/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

/**
 * Class representing the effect of an item or a capacity
 * @author Gaetan
 */
public class Effect
{
    /**
     * The ability concerned by the effect
     */
    private final Ability ability;
    
    /**
     * The value/efficiency of the effect
     */
    private int value = 0;
    
    /**
     * Duration of the effect in number of turns
     */
    private int duration = 0; 
    
    /**
     * Max duration of an effect
     */
    private final int MAX_DURATION = 1000;
    
    /**
     * Creation of an effect
     * 
     * @param a  The ability concerned by the effect
     * @param efficiency  The efficiency of the effect
     * @param nbOfTurns  The number of turns the effect is lasting
     */
    public Effect(Ability a, int efficiency, int nbOfTurns)
    {
        this.ability = a;
        this.value = efficiency;
        this.duration = nbOfTurns;
    }
    
    // ------- Setters
    
    public void setDuration(int d)
    {
        this.duration = d;
    }

    // ------- Getters
    
    public Ability getAbility()
    {
        return ability;
    }

    public int getValue()
    {
        return value;
    }

    public int getDuration()
    {
        return duration;
    }
    
    public void reduceDuration()
    {
        this.setDuration(this.getDuration()-1);
    }
    
    @Override
    public String toString()
    {
        String s;
        s = "\tEffect on " + this.getAbility().name() + " with an efficiency of " + this.getValue() ;
        if(this.getDuration()< MAX_DURATION) //would not write duration for armor and weapon
            s+= " for " + this.getDuration() + " round";
        return s;
    }
    
}
