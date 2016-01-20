/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package controllerPackage;

import java.util.ArrayList;
import java.util.Stack;
import rpg.Action;
import rpg.Attack;
import rpg.Character;
import rpg.Heal;

/**
 * Class managing the actions of the AI
 *
 * @author p1509413
 */
public class ControllerAI extends Controller
{

    /**
     * List of the player Characters - Needed to chose a target
     */
    ArrayList<Character> playerCharacters;

    /**
     * List of the ai Characters - Needed to chose a target (i.e : for a heal)
     */
    ArrayList<Character> aiCharacters;

    /**
     * Indicates that an aiCharacter needs a heal
     */
    private boolean healNeeded = false;

    /**
     * Constructor
     *
     * @param ai : Ai player
     * @param playerCharacters : List of player characters
     * @param aiCharacters : List of ai characters
     */
    public ControllerAI(Character ai,
            ArrayList<Character> playerCharacters,
            ArrayList<Character> aiCharacters)
    {
        super(ai);
        this.playerCharacters = playerCharacters;
        this.aiCharacters = aiCharacters;
    }
    
    /**
     * Return a stack of names for the AI characters
     * @return the stack of name
     */
    public static Stack<String> getStackNames()
    {
        Stack charactersNames = new Stack<>();
        charactersNames.add("Chucky");
        charactersNames.add("Djouk");
        charactersNames.add("John");
        charactersNames.add("Brice");
        charactersNames.add("Finn");
        charactersNames.add("TR8TOR");
        charactersNames.add("Gollum");
        charactersNames.add("Boro");
        charactersNames.add("Frodo");
        
        return charactersNames;
    }

    /**
     * Get the action of the AI
     *
     * @return the action of the AI
     */
    public Action getAction()
    {
        healNeeded();
        Character target = this.choseTarget();

        if (healNeeded)
        {
            return new Action(character, target, new Heal(character, target));
        } else // Attaque par défaut
        {
            return new Action(character, target, new Attack(character, target));
        }
    }

    /**
     * Chose a target from one of the two teams
     *
     * @return the target chosen
     */
    private Character choseTarget()
    {
        if (healNeeded)
        {
            return getCharacterWithLowerHealth(aiCharacters);
        } else
        {
            return getCharacterWithLowerHealth(playerCharacters);
        }
    }

    /**
     * Return the character with the lower health from the list given
     *
     * @param listCharacters
     * @return the character with the lower health from the list given
     */
    private Character getCharacterWithLowerHealth(ArrayList<Character> listCharacters)
    {
        Character characterWithLowerHealth = listCharacters.get(0); // Init to the first
        for (Character c : listCharacters)
        {
            if (characterWithLowerHealth.getCurrentHealth() <= c.getCurrentHealth())
            {
                characterWithLowerHealth = c;
            }
        }
        return characterWithLowerHealth;
    }

    /**
     * Update the field healNeeded : check if a character has less than 50% of
     * his HP
     */
    private void healNeeded()
    {
        boolean aCharacterNeedsHeal = false;
        for (Character ai : aiCharacters)
        {
            // If a character has less than 50% HP
            if (ai.getCurrentHealth() <= ai.getMaxHealth() / 2)
            {
                aCharacterNeedsHeal = true;
            }
        }
        healNeeded = aCharacterNeedsHeal;
    }

}
