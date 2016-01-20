/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

/**
 * Interface representing a capacity (action) of the player
 *
 * @author Gaetan
 */
public interface Capacity
{

    /**
     * Number of capacity Needed when the user is asked to chose a capacity
     */
    public final static int NUMBER_OF_CAPACITY = 3;

    /**
     * Get the effect of a capacity
     *
     * @return the effect
     */
    public Effect getEffect();

    /**
     * Calculate probability to execute a capacity
     *
     * @param source the characeer launching the capacity
     * @return probability of execution
     */
    public double probaWin(Character source);
}
