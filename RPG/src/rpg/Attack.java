/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

import static java.lang.Math.abs;

/**
 * Class representing a physical attack
 *
 * @author Gaetan
 */
public class Attack implements Capacity
{
    private Character source;
    private Character target;

    public Attack(Character source, Character target)
    {
        this.source = source;
        this.target = target;
    }
    /**
     * Effect of the attack
     *
     * @return Effect of attack(to apply on target character)
     */
    public Effect getEffect()
    {
        int damage = this.source.getWeapon().getEffect().getValue() + source.getAbilities().get(Ability.STRENGTH);
        int defence;
        if(target.getCurrentArmor() != null)
            defence = target.getAbilities().get(Ability.DEFENCE) + target.getCurrentArmor().getResistance();
        else
            defence = target.getAbilities().get(Ability.DEFENCE);
        int netDamage = abs(defence - damage);
        return new Effect(Ability.HEALTH,(-netDamage),1);
    }

    /**
     * Calculate the probability of executing an attack
     * @param source
     * @return probability of executing attack
     */
    @Override
    public double probaWin(Character source)
    {
        int dexterity = source.getAbilities().get(Ability.DEXTERITY);   //dexteriry of attacker
        //get netDexterity by taking into consideration the manageability of weapon
        int netDexterity = dexterity - this.source.getWeapon().getManageability();
        if(netDexterity >= 0)
            return 1;
        if(netDexterity <0 && netDexterity >=-20)
            return 0.8;
        if(netDexterity <-20 && netDexterity >=-40)
            return 0.6;
        if(netDexterity <-40 && netDexterity >=-60)
            return 0.4;
        if(netDexterity <-60 && netDexterity >=-80)
            return 0.2;
        if(netDexterity <-80)
            return 0.1;     
        return 0.5;
    }
}
