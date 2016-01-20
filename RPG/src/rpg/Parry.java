/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

/**
 * Class representing a capacity of defense (parry)
 *
 * @author Gaetan
 */
public class Parry implements Capacity
{

    private final Character source;

    private static final Ability PARRY_ABILITY = Ability.DEFENCE;


    public Parry(Character source)
    {
        this.source = source;
    }

    /**
     * @return Effect of parry
     */
    @Override
    public Effect getEffect()
    {
        return new Effect(PARRY_ABILITY, this.source.getAbilityValue(Ability.DEFENCE), 1);
    }

    @Override
    public double probaWin(Character source)
    {
        double totalAbility = source.getAbilityValue(Ability.DEFENCE) + source.getAbilityValue(Ability.DEXTERITY);
        return (totalAbility / 100);
    }
}
