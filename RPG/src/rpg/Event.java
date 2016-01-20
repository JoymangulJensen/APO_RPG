/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

import java.util.ArrayList;

/**
 * Class representing an event of the game
 * 
 * @author seljo
 */
public abstract class Event
{

    protected ArrayList<Character> playerCharacters;
    protected ArrayList<Character> aiCharacters;
    
    /**
     * Message to display when the event is launch
     */
    protected String introMessage = "";

    public void setIntroMessage(String introMessage)
    {
        this.introMessage = introMessage;
    }

    /**
     * Constructor setting the list of characters
     *
     * @param playerChacraters List of player character
     * @param aiChacraters List of AI character
     */
    public Event(ArrayList<Character> playerChacraters, ArrayList<Character> aiChacraters)
    {
        this.playerCharacters = playerChacraters;
        this.aiCharacters = aiChacraters;
    }
    
    /**
     * Constructor setting the list of characters
     *
     * @param playerChacraters List of player character
     * @param aiChacraters List of AI character
     * @param message to display when the event is launch
     */
    public Event(ArrayList<Character> playerChacraters, ArrayList<Character> aiChacraters, String message)
    {
        this.playerCharacters = playerChacraters;
        this.aiCharacters = aiChacraters;
        this.introMessage = message;
    }

    /**
     * Launch the event
     */
    public void launch()
    {
        System.out.println("New Event !" + this.introMessage);
    }

}
