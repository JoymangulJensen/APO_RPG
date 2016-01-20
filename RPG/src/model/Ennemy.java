/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package model;

import rpg.Character;

/**
 * Class ennemy used for AI characters
 *
 * @author Gaetan
 */
public final class Ennemy extends Character
{

    /**
     * Maximum Health of the class Enemy
     */
    protected final int MAX_HEALTH_ENNEMY = 100;

    /**
     * Constructor
     */
    public Ennemy()
    {
        super();
        this.setMaxHealth(MAX_HEALTH_ENNEMY);
        this.initInventory();
    }
}
