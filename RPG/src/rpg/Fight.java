/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;

import controllerPackage.ControllerAI;
import controllerPackage.ControllerPlayer;
import controllerPackage.DisplayCharacter;
import controllerPackage.DisplayUI;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class fight : special event
 *
 * @author Gaetan
 */
public class Fight extends Event
{

    /**
     * Constructor initializing the list of characters
     *
     * @param playerChacraters
     * @param aiChacraters
     */
    public Fight(ArrayList<Character> playerChacraters, ArrayList<Character> aiChacraters)
    {
        super(playerChacraters, aiChacraters);
    }

    /**
     * Constructor initializing the list of characters and displaying an intro
     * message
     *
     * @param playerChacraters
     * @param aiChacraters
     * @param message to be display when the fight is launch
     */
    public Fight(ArrayList<Character> playerChacraters, ArrayList<Character> aiChacraters, String message)
    {
        super(playerChacraters, aiChacraters, message);
    }

    /**
     * Launch the fight
     */
    @Override
    public void launch()
    {
        System.out.println(Main.ANSI_PURPLE + "              (O)");
        System.out.println(Main.ANSI_PURPLE + "              <M");
        System.out.println(Main.ANSI_PURPLE + "   o          <M");
        System.out.println(Main.ANSI_PURPLE + "  /| ......  /:M\\------------------------------------------------,,,,,,");
        System.out.println(Main.ANSI_PURPLE + "(O)[]XXXXXX[]I:K+}=====<{H}>================================------------>");
        System.out.println(Main.ANSI_PURPLE + "  \\| ^^^^^^  \\:W/------------------------------------------------''''''");
        System.out.println(Main.ANSI_PURPLE + "   o          <W");
        System.out.println(Main.ANSI_PURPLE + "              <W");
        System.out.println(Main.ANSI_PURPLE + "              (O)" + Main.ANSI_RESET);
        System.out.println(introMessage);
        for (Character c : aiCharacters)
        {
            new DisplayCharacter(c).displayName();
        }
        this.fight();
    }

    /**
     * Method use to generate the actions for a fight and play a round according
     * to these actions
     */
    public void fight()
    {
        while (this.haveWinner() == false)
        {
            ArrayList<ControllerPlayer> playerControllers = new ArrayList<>();
            ArrayList<ControllerAI> aiControllers = new ArrayList<>();

            Stack<Action> playerActions = new Stack<>();
            Stack<Action> aiActions = new Stack<>();

            for (Character player : playerCharacters)
            {
                playerControllers.add(new ControllerPlayer(player));
            }

            for (Character ai : aiCharacters)
            {
                aiControllers.add(new ControllerAI(ai, playerCharacters, aiCharacters));
            }
            for (ControllerAI ca : aiControllers)
            {
                System.out.println("***********************");
                System.out.println("Round of " + ca.getCharacter().getName());
                Action a = ca.getAction();
                aiActions.push(a);
                // new DisplayCharacter(a.getTarget()).displayAll();
            }

            for (ControllerPlayer cp : playerControllers)
            {
                System.out.println("***********************");
                System.out.println("Round of " + cp.getCharacter().getName());
                turn(cp.getCharacter());

                System.out.println("Select a target : ");
                Character target = this.getCharacterByName();
                Action a = cp.getAction(target);
                playerActions.push(a);
                // new DisplayCharacter(target).displayAll();
            }
            Round fightRound = new Round(playerCharacters, playerActions, aiCharacters, aiActions);
            fightRound.play();
        }
    }

    /**
     *
     * @return true if found winner false no winner found
     */
    private boolean haveWinner()
    {
        if (this.playerCharacters.isEmpty())
        {
            System.out.println("              ~~~~~~~~~");
            System.out.println("            /           \\");
            System.out.println("           /             \\");
            System.out.println("          | )           ( |");
            System.out.println("           \\  /C\\   /C\\  /");
            System.out.println("           /  ~~~   ~~~  \\");
            System.out.println("           \\___  .^,  ___/");
            System.out.println("            `| _______ |'");
            System.out.println("         _   | HHHHHHH |   _");
            System.out.println("        ( )  \\         /  ( )");
            System.out.println("       (_) \\  ~~~~^~~~~ ,/ (_)");
            System.out.println("         ~\\ \"\\         /  /~");
            System.out.println("            \\  \\     /  /");
            System.out.println("              \\  \\v/  /");
            System.out.println("               >     <");
            System.out.println("              /  /^\\  \\");
            System.out.println("            /  /     \\  \\");
            System.out.println("        _~/ \"/         \\  \\~_");
            System.out.println("       ( ) /             \\ ( )");
            System.out.println("        (_)               (_)");
            return true;
        } else if (this.aiCharacters.isEmpty())
        {
            System.out.println("                                            .''.");
            System.out.println("                  .''.             *''*    :_\\/_:     . ");
            System.out.println("                 :_\\/_:   .    .:.*_\\/_*   : /\\ :  .'.:.'.");
            System.out.println("              .''.: /\\ : _\\(/_  ':'* /\\ *  : '..'.  -=:o:=-");
            System.out.println("             :_\\/_:'.:::. /)\\*''*  .|.* '.\\'/.'_\\(/_'.':'.'");
            System.out.println("             : /\\ : :::::  '*_\\/_* | |  -= o =- /)\\    '  *");
            System.out.println("              '..'  ':::'   * /\\ * |'|  .'/.\'.  '._____");
            System.out.println("                  *        __*..* |  |     :      |.   |' .---\"|");
            System.out.println("                   _*   .-'   '-. |  |     .--'|  ||   | _|    |");
            System.out.println("                .-'|  _.|  |    ||   '-__  |   |  |    ||      |");
            System.out.println("                |' | |.    |    ||       | |   |  |    ||      |");
            System.out.println("             ___|  '-'     '    \"\"       '-'   '-.'    '`      |___");
            System.out.println("            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("    `YMM'   `MM'                     `7MMF'     A     `7MF'           ");
            System.out.println("      VMA   ,V                         `MA     ,MA     ,V       ");
            System.out.println("       VMA ,V ,pW\"Wq.`7MM  `7MM         VM:   ,VVM:   ,V ,pW\"Wq.`7MMpMMMb.  ");
            System.out.println("        VMMP 6W'   `Wb MM    MM          MM.  M' MM.  M'6W'   `Wb MM    MM  ");
            System.out.println("         MM  8M     M8 MM    MM          `MM A'  `MM A' 8M     M8 MM    MM  ");
            System.out.println("         MM  YA.   ,A9 MM    MM           :MM;    :MM;  YA.   ,A9 MM    MM  ");
            System.out.println("       .JMML. `Ybmd9'  `Mbod\"YML.          VF      VF    `Ybmd9'.JMML  JMML.");

            return true;
        }
        return false;
    }

    /**
     * Display all the characters
     */
    private void displayCharacters()
    {
        this.displayPlayerCharacters();
        this.displayAICharacters();
    }

    /**
     * Display all the characters of the player
     */
    private void displayPlayerCharacters()
    {
        System.out.println("List of the player characters : ");
        for (int i = 0; i < playerCharacters.size(); i++)
        {
            System.out.println(Main.ANSI_BLUE + playerCharacters.get(i).getName() + Main.ANSI_RESET);
        }
    }

    /**
     * Display all the characters of the AI
     */
    private void displayAICharacters()
    {
        System.out.println("List of the AI characters : ");
        for (int i = 0; i < aiCharacters.size(); i++)
        {
            System.out.println(Main.ANSI_PURPLE + aiCharacters.get(i).getName() + Main.ANSI_RESET);
        }
    }

    /**
     * Ask for a target among the AI Characters
     *
     * @return AI character corresponding to the name typed in
     */
    private Character getAICharacterByName()
    {
        this.displayAICharacters();
        String name = DisplayUI.getCharacterName();
        for (Character aiCharacter : aiCharacters)
        {
            if (aiCharacter.getName().equals(name))
            {
                return aiCharacter;
            }
        }
        return this.getAICharacterByName();
    }

    /**
     * Ask for a target among the Player Characters
     *
     * @return player character corresponding to the name typed in
     */
    private Character getPlayerCharacterByName()
    {
        this.displayPlayerCharacters();
        String name = DisplayUI.getCharacterName();
        for (Character playerCharacter : playerCharacters)
        {
            if (playerCharacter.getName().equals(name))
            {
                return playerCharacter;
            }
        }
        return this.getPlayerCharacterByName();
    }

    /**
     * Ask for a target among all the Characters
     *
     * @return character corresponding to the name typed in
     */
    private Character getCharacterByName()
    {
        this.displayCharacters();
        String name = DisplayUI.getCharacterName();
        for (Character playerCharacter : playerCharacters)
        {
            if (playerCharacter.getName().contains(name))
            {
                return playerCharacter;
            }
        }
        for (Character aiCharacter : aiCharacters)
        {
            if (aiCharacter.getName().contains(name))
            {
                return aiCharacter;
            }
        }
        return this.getCharacterByName();
    }

    /**
     * Show all the actions possible : continue, leave game, consult Inventory,
     * etc..
     */
    private void turn(Character character)
    {
        int choice = DisplayUI.getActionTurn();
        switch (choice)
        {
            case 0:
                rpg.RPG.quitGame();
                break;
            case 1:
                new DisplayCharacter(character).displayInventory();
                break;
            case 2:
                new DisplayCharacter(character).displayAll();
                break;
            case 3:
                return;
            case 4:
                if (character.getArmors().length == 0)
                {
                    System.out.println("No armor to equip");
                } else
                {
                    this.askArmor(character);
                }
                break;
            default:
                break;
        }
        turn(character);
    }

    /**
     * Ask the user to equip an armor
     */
    private void askArmor(Character character)
    {
        int choice = DisplayUI.getArmorId(character);
        character.equipeArmor(choice);
    }

}
