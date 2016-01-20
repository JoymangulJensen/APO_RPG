/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

import me.grea.antoine.utils.Dice;

/**
 * Class representing a spell of healing
 *
 * @author Gaetan
 */
public class Heal implements Capacity
{

    private final Character source;
    private final Character target;

    public Heal(Character source, Character target)
    {
        this.source = source;
        this.target = target;
    }

    /**
     * @return Effect of a healing
     */
    @Override
    public Effect getEffect()
    {
        int maxHealth = this.target.getMaxHealth();
        int health = this.target.getAbilityValue(Ability.HEALTH);
        int maxHealthGain = maxHealth - health; //the maximum health point a player can gain
        double diceResult = ((double) Dice.roll(1, 4) / 4);
        int healthGain = (int) (diceResult * maxHealthGain);    //calculate health gain
        return new Effect(Ability.HEALTH, healthGain, 1);
    }

    @Override
    public double probaWin(Character target)
    {
        double maxHealth = (double) target.getMaxHealth();
        double health = (double) target.getAbilityValue(Ability.HEALTH);
        return (1 - (health - maxHealth));
    }
}
