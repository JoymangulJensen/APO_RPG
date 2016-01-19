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
     * Controller
     */
    private static ControllerUI cp;

    /**
     * Maximum number of characters in the team of the player
     */
    private static final int MAX_NB_CHARACTERS = 3;

    /**
     * List of the characters of the player
     */
    private ArrayList<Character> playerCharacters;

    /**
     * List of the characters of the AI
     */
    private ArrayList<Character> aiCharacters;

    /**
     * Stack of events for the scenario
     */
    private Stack<Event> events;

    /**
     * Constructor
     */
    public RPG()
    {
        cp = new ControllerUI();

        aiCharacters = new ArrayList<>();
        playerCharacters = new ArrayList<>();
        events = new Stack<>();

//        Character c = new Character("Selwyn");
//        Character c2 = new Character("Gaetan");
//        DisplayCharacter dc = new DisplayCharacter(c);
//        DisplayCharacter dc2 = new DisplayCharacter(c2);
//        dc.displayName();
//        dc.displayAbilities();
//        dc2.displayName();
//        dc2.displayAbilities();
//        Effect e1 = new Effect(Ability.DEXTERITY, 20, 2);
//        Item i = new Item("Item 1", 20, false);
//        i.setEffect(e1);
//        dc.addItem(i);
//        Weapon w = new Weapon("arme", 60, 90, 50);
//        Armor a = new Armor("armure", 20, 50);
//        dc.addItem(w);
//        dc2.addItem(a);
//        dc.displayAbilities();
//        dc2.displayAbilities();
//        Attack att = new Attack(c, c2);
//        Action act = new Action(c, c2, att);
//        act.useCapacity();
//        Heal hl = new Heal(c2);
//        Action act2 = new Action(c2, hl);
//        dc2.displayAbilities();
//        act2.useCapacity();
        start();
    }

    /**
     * Start the game
     */
    private void start()
    {
        DisplayUI.displayStartText();
        int option = cp.readInt("Please select the number corresponding to your choice : ");
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
        
        events.add(new Fight(playerCharacters, aiCharacters));
    }
}
