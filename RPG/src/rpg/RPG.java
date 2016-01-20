 /*
 * Projet - APO Création d'un RPG
 * Décembre 2015
 * Gaëtan MARTIN & Jensen JOYMANGUL
 */
package rpg;

import model.Ennemy;
import model.Warrior;
import model.Healer;
import model.Athlete;
import controllerPackage.ControllerAI;
import controllerPackage.DisplayUI;
import controllerPackage.ControllerUI;
import java.util.ArrayList;
import java.util.Stack;
import me.grea.antoine.utils.*;

/**
 * Main class executable
 *
 * @author p1509413
 */
public class RPG
{

    /**
     * Maximum number of characters in the team of the player
     */
    private static final int MAX_NB_CHARACTERS = 3;

    /**
     * List of the characters of the player
     */
    private final ArrayList<Character> playerCharacters;

    /**
     * List of the characters of the AI
     */
    private final ArrayList<Character> aiCharacters;

    /**
     * Stack of events for the scenario
     */
    private final Stack<Event> events;

    /**
     * Constructor
     */
    public RPG()
    {
        aiCharacters = new ArrayList<>();
        playerCharacters = new ArrayList<>();
        events = new Stack<>();
        start();
    }

    /**
     * Start the game
     */
    private void start()
    {
        DisplayUI.displayStartText();
        int option = ControllerUI.readInt("Please select the number corresponding to your choice : ");
        switch (option)
        {
            case 1:
                newGame();
                break;
            case 2:
                loadGame();
                break;
            case 0:
                quitGame();
                break;
            default:
                System.out.println("No action corresponding to the number : " + option);
                start();
        }
    }

    /**
     * Launch a new game
     */
    private void newGame()
    {
        DisplayUI.displayNewGameText();
        int nbCharacs = DisplayUI.getNbCharacters(MAX_NB_CHARACTERS);
        initGame(nbCharacs);
    }

    /**
     * Initialize the game
     *
     * @param nbCharac
     */
    private void initGame(int nbCharac)
    {
        for (int i = 0; i < nbCharac; i++)
        {
            createNewPlayerCharacter();
        }

        int numberOfAiCharacters = DisplayUI.getDifficulty()
                * Math.max(playerCharacters.size() - 1, 1);

        createNewAICharacter(numberOfAiCharacters);

        this.runGame();
    }

    /**
     * Creation of the characters of the player
     */
    private void createNewPlayerCharacter()
    {
        System.out.println("Creation of character n° " + (playerCharacters.size() + 1));
        String name = DisplayUI.getCharacterName();
        String classChosen = DisplayUI.getPlayerClass();

        Character character;

        switch (classChosen)
        {
            case "Athlete":
                character = new Athlete();
                break;
            case "Healer":
                character = new Healer();
                break;
            default:
                character = new Warrior();
                break;
        }

        character.setName(name);

        System.out.println("Player Character created : ");
        System.out.println(character.toString());

        playerCharacters.add(character);

    }

    /**
     * Creation of the characters of the AI
     */
    private void createNewAICharacter(int numberOfAiCharacters)
    {

        Stack charactersNames = ControllerAI.getStackNames();

        for (int i = 0; i < numberOfAiCharacters; i++)
        {
            Ennemy ennemy = new Ennemy();
            ennemy.setName((String) charactersNames.pop());

            System.out.println("AI Character created : ");
            System.out.println(ennemy.toString());

            aiCharacters.add(ennemy);
        }
    }

    /**
     * Load a previous game
     */
    private void loadGame()
    {
        Log.e("Not implemented yet");
        start();
    }

    /**
     * Quit the game
     */
    public static void quitGame()
    {
        DisplayUI.displayQuitGameMessage();
        System.exit(0);
    }

    /**
     * Launch the game after everything is set & prepared
     */
    private void runGame()
    {
        System.out.println("\nOnce uppon a time, a company of brave comrades walking in a forest");
        initEvents();
        while (!events.isEmpty())
        {
            Event event = events.pop();
            event.launch();
        }
    }

    /**
     * Initialize the stack of events
     */
    private void initEvents()
    {
        events.add(this.getEvent4());
        events.add(this.getEvent3());
        events.add(this.getEvent2());
        events.add(this.getEvent1());
    }

    /**
     * Scenario n°1
     *
     * @return
     */
    private Event getEvent1()
    {
        String message1 = "You are walking in the forest. Suddenly you found the temple!";
        Scenario s1 = new Scenario(playerCharacters, aiCharacters, message1);
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Edible("Fire Potion", 10, true, new Effect(Ability.HEALTH, 10, 1)));
        items.add(new Edible("Avoidance Potion", 10, true, new Effect(Ability.DEFENCE, 1, 1)));
        s1.setItemFound(items);
        return s1;
    }

    /**
     * Scenario n°2
     *
     * @return
     */
    private Event getEvent2()
    {
        String message2 = "You entered in a black room! \n";
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Edible("Dexterity Potion", 10, true, new Effect(Ability.DEXTERITY, 10, 1)));
        items.add(new Edible("Strength Potion", 10, true, new Effect(Ability.STRENGTH, 10, 1)));
        Scenario s2 = new Scenario(playerCharacters, aiCharacters, message2);
        s2.setItemFound(items);
        return s2;
    }

    /**
     * Event n°3 : fight
     *
     * @return
     */
    private Event getEvent3()
    {
        String message3 = "A wild ennemy team appears!";
        Fight s3 = new Fight(playerCharacters, aiCharacters);
        s3.setIntroMessage(message3);
        return s3;
    }

    /**
     * Event n°4 : the end
     *
     * @return
     */
    private Event getEvent4()
    {
        String message4 = "The end - GG Well Play";
        Scenario s4 = new Scenario(playerCharacters, aiCharacters);
        s4.setIntroMessage(message4);
        return s4;
    }
}
