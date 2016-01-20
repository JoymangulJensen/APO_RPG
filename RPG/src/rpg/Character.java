/*
 * Projet - APO Création d'un RPG
 * Décembre 2015
 * Gaëtan MARTIN & Jensen JOYMANGUL
 */
package rpg;

import controllerPackage.DisplayCharacter;
import java.util.ArrayList;
import rpgException.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.grea.antoine.utils.Log;

/**
 * Character class : Represents a character of the game
 *
 * @author p1509413
 */
public abstract class Character
{
    // -------------- Attributes

    protected String name;            //Name of the character
    protected Map<Ability, Integer> abilities;    //Stats of the character
    protected int level = 1;          //Current level of the character
    protected int maxWeight = 300;    //Max weight of carriable items
    protected int maxHealth;          //Max Health reachable by the character
    protected int maxDexterity;       //Max Dexterity reachable by the character
    protected List<Item> inventory;   //Inventory of the characters
    protected int currentHealth;      // Current health of the character
    protected Armor armors[];         //armors of the character
    protected Armor currentArmor;     //Current armor of character

    // -------------- Constants
    /**
     * Maximum Health at the creation
     */
    protected final int INIT_MAX_HEALTH = 100;

    /**
     * Maximum sum of abilities
     */
    private final int OVERPOWERED = 500;
    
    /**
     * Ratio of HP regained after a fight
     */
    private final static double HEALTH_REGAIN = 0.4;

    // -------------- Constructors ----------------------------------
    /**
     * Default constructor
     */
    public Character()
    {
        this("Player");
    }

    /**
     * Constructor with arguments
     *
     * @param name Name of Character
     */
    public Character(String name)
    {
        this.armors = new Armor[2];
        this.abilities = new HashMap<>();
        this.inventory = new ArrayList<>();
        this.setName(name);
        this.setMaxHealth(INIT_MAX_HEALTH);
        this.currentHealth = INIT_MAX_HEALTH;
        this.setMaxDexterity(10);
        this.initAbilities();
        this.initInventory();

    }

    // -------------- Getters And Setters -------------------------------
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Map<Ability, Integer> getAbilities()
    {
        return abilities;
    }

    public void setAbilities(Map<Ability, Integer> abilities)
    {
        this.abilities = abilities;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getMaxWeight()
    {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight)
    {
        this.maxWeight = maxWeight;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public int getMaxDexterity()
    {
        return maxDexterity;
    }

    public void setMaxDexterity(int maxDexterity)
    {
        this.maxDexterity = maxDexterity;
    }

    public List<Item> getInventory()
    {
        return inventory;
    }

    public Armor[] getArmors()
    {
        return armors;
    }

    public Armor getCurrentArmor()
    {
        return currentArmor;
    }

    /**
     *
     * @param i 0: equip first armor 1: equip second amor
     * @return success of method
     */
    public boolean equipeArmor(int i)
    {
        if (i == 0 || i == 1)
        {
            this.currentArmor = this.armors[i];
            return true;
        } else
        {
            System.out.println(Main.ANSI_RED + "You cannot equipe an armor you don't possess" + Main.ANSI_RESET);
            return false;
        }
    }

    /**
     * Add an armor in armor inventory(replace an armor also)
     *
     * @param a armor to armor
     * @param i index to replace existing armor (0 or 1)
     * @return success of method
     */
    public boolean addArmorInInventory(Armor a, int i)
    {
        if (i >= 0 || i < this.armors.length)
        {
            this.armors[i] = a;
            return true;
        } else
        {
            System.out.println("Cannot add armor in inventory");
            return false;
        }
    }

    public Weapon getWeapon()
    {
        for (Item i : this.getInventory())
        {
            if (i.getClass() == Weapon.class)
            {
                return (Weapon) i;
            }
        }
        return null;
    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }

    // ---------------------------- Methods --------------------------------------
    /**
     * Apply an effect onto the character
     *
     * @param e : The effect to apply
     */
    public void applyEffect(Effect e)
    {
        Ability a = e.getAbility();
        System.out.println("Applying : " + e.toString());
        if (a == Ability.HEALTH) //Change value of cuurent health if effect is on Ability HEALTH
        {
            this.currentHealth = Math.min(this.maxHealth, this.currentHealth + e.getValue());
        } else    //Change Ability value
        {
            int newValue = this.abilities.get(a) + e.getValue();
            this.abilities.put(a, newValue);
        }
    }

    /**
     * Get the current weight of the inventory
     *
     * @return the weight of the inventory
     */
    public int getInventoryWeight()
    {
        int sum = 0;
        for (Item item : inventory)
        {
            sum += item.getWeight();
        }
        return sum;
    }

    /**
     * Method to remove all effect with a duration of zero(Not effective effect)
     */
    public void removeEffect()
    {
        for (Item i : this.getInventory())
        {
            if (i.getEffect().getDuration() == 0)
            {
                this.removeItem(i);
            }
        }
    }

    /**
     * Remove an item from the inventory
     *
     * @param i item to remove in inventory
     */
    public void removeItem(Item i)
    {
        Log.i("Romoving item : " + i.getName() + " from " + this.getName());
        this.inventory.remove(i);
    }

    /**
     * Add an item in the inventory
     *
     * @param i item to add to inventory
     */
    public void addItem(Item i)
    {
        this.inventory.add(i);
    }

    /**
     * Increment the level
     */
    public void incrementLevel()
    {
        this.level++;
        for (Map.Entry<Ability, Integer> map : abilities.entrySet())
        {
            int newValue = (int) (((int) map.getValue() * 0.2) + map.getValue());
            map.setValue(newValue);
        }
        DisplayCharacter dc = new DisplayCharacter(this);
        System.out.println(Main.ANSI_GREEN + this.getName() + " level up!! Level : " + this.level + Main.ANSI_RESET);
        dc.displayAbilities();
    }

    /**
     * Calculate the sum of the abilities
     *
     * @return the sum of the abilities
     */
    public int sumAbilities()
    {
        int sum = 0;
        for (Map.Entry<Ability, Integer> map : abilities.entrySet())
        {
            Ability ability = map.getKey();
            Integer value = map.getValue();
            sum += value;
        }
        return sum;
    }

    /**
     * Return the value of the given ability
     *
     * @param ability
     * @return the value of the given ability
     */
    public int getAbilityValue(Ability ability)
    {
        return this.abilities.get(ability);
    }

    /**
     * Initialize health at 100(use after each round)
     */
    public void reinitHealth()
    {
        this.currentHealth += (this.currentHealth * HEALTH_REGAIN);
        if (this.currentHealth > this.maxHealth)
        {
            this.currentHealth = this.maxHealth;
        }
    }

    /**
     * Initialize the four abilities present with default values
     */
    public void initAbilities()
    {
        this.abilities = new HashMap();
        this.abilities.put(Ability.DEFENCE, 10);
        this.abilities.put(Ability.HEALTH, 100);
        this.abilities.put(Ability.STRENGTH, 10);
        this.abilities.put(Ability.DEXTERITY, 10);
    }

    /**
     * Initialize the inventory adding few items
     */
    protected void initInventory()
    {
        this.inventory.add(new Weapon("DefaultWeapon", 60, 90, 50));
        this.inventory.add(new Armor("DefaultArmor", 20, 50));
        this.armors[0] = new Armor("DefaultArmor2", 20, 50);
        this.armors[1] = new Armor("DefaultArmor2", 40, 75);
        this.equipeArmor(0);
    }

    /**
     * Get the number of edibles in the inventory
     *
     * @return the number of edibles in the inventory
     */
    public int getNbEdibles()
    {
        int number = 0;
        for (Item i : inventory)
        {
            if (i.getClass() == Edible.class)
            {
                number++;
            }
        }
        return number;
    }

    /**
     * Check that the abilities chose by the player are not too high
     */
    private void checkAbilities()
    {
        int somme = 0;
        for (int i = 0; i < abilities.size(); i++)
        {
            somme += abilities.get(i);
        }
        if (somme > this.OVERPOWERED)
        {
            System.out.println("Overpowered mode activated ! ");
            // Faire un contrôle plus sérieux ! 
        }
    }

    /**
     * Method to test if a character is dead
     *
     * @return true character is dead; false character is still alive
     */
    public boolean isDead()
    {
        if (this.currentHealth <= 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * Check that there is space available in the inventory
     *
     * @param i : The item to add to inventory
     * @throws MaxInventoryException if the limit of the inventory is reached
     */
    public void checkMaxInventory(Item i) throws MaxInventoryException
    {
        if (this.getInventoryWeight() + i.weight > this.maxWeight)
        {
            throw new MaxInventoryException(i);
        }
    }

    /**
     * @param i: Item to check if present in inventory
     * @throws ExistsInventoryException if item i is not in inventory
     */
    public void checkInInventory(Item i) throws ExistsInventoryException
    {
        if (!this.inventory.contains(i))
        {
            throw new ExistsInventoryException(i);
        }
    }

    /**
     * Display the important information about the character
     *
     * @return
     */
    @Override
    public String toString()
    {
        String s = "------------------------------------ \n";
        s += "Name : " + this.name + "\n";
        s += "Health : " + this.currentHealth + "\n";
        s += "Class : " + this.getClass().getSimpleName() + "\n";
        s += "------------------------------------ \n";
        return s;
    }
}
