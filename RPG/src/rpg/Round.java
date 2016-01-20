/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package rpg;
import controllerPackage.DisplayCharacter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import static rpg.Main.*;
import rpgException.CharatcerExistsException;

/**
 *
 * @author seljo
 */
public class Round
{
    private Stack<Action> playerActions; //List of actions for a player
    private Stack<Action> computerActions;   //List of actions for the AI
    private Stack<Action> finalActions;  //merged list of playerActions and computerActions
    private ArrayList<Character> playerCharacters;  //list of player character
    private ArrayList<Character> aiCharacters;      //List of ai character

    //-------------------- Constructor ----------------------------
    public Round(ArrayList<Character> playerCharacters, Stack<Action> playerActions, ArrayList<Character> aiCharacters, Stack<Action> computerActions)
    {
        this.playerActions = playerActions;
        this.computerActions = computerActions;
        this.finalActions = this.merge(playerActions, computerActions);
        this.aiCharacters = aiCharacters;
        this.playerCharacters = playerCharacters;
    }
    
    
    
    /**
     * Method to make an alternate merge of two lists
     * 
     * @param <T> Generic type of list
     * @param a First list
     * @param b Second List
     * @return  Merged List
     */
    public <T> Stack<T> merge(List<T> a, List<T> b) {
        Stack<T> result = new Stack<>();
        int size = Math.max(a.size(), b.size());

        for (int i = 0; i < size; i++) {
            if (i < a.size()) 
            {
                result.add(a.get(i));
            }
            if (i < b.size())
            {
                result.add(b.get(i));
            }
        }
        return result;
    }
    
    /**
     * Method to play a round (execute the actions and reinitialize for next round) 
     */
    public void play()
    {
        System.out.println("");
        System.out.println(ANSI_PURPLE + "***********************************************");
        System.out.println(ANSI_PURPLE + "***********************************************");
        System.out.println(ANSI_PURPLE + "**             Round is starting             **");
        System.out.println(ANSI_PURPLE + "***********************************************");
        System.out.println(ANSI_PURPLE + "***********************************************\n" + ANSI_RESET);
        this.executeActions();
        this.initNextRound();
    }
    
    /**
     * Update the different data  of the character for the next round
     */
    public void initNextRound()
    {
        for(Character c: this.playerCharacters)
        {
            c.removeEffect();
            //c.reinitHealth();
            DisplayCharacter dc = new DisplayCharacter(c);
            System.out.println(Main.ANSI_CYAN + c.getName() + "->" + Main.ANSI_RESET);
            dc.displayHealth();
            System.out.println("");
        }
        for(Character c: this.aiCharacters)
        {
            c.removeEffect();
            //c.reinitHealth();
            DisplayCharacter dc = new DisplayCharacter(c);
            System.out.println(Main.ANSI_CYAN + c.getName() + "->" + Main.ANSI_RESET);
            dc.displayHealth();
            System.out.println("");
        }
        System.out.println("");
        System.out.println(ANSI_PURPLE + "***********************************************");
        System.out.println(ANSI_PURPLE + "*               Round finished                *");
        System.out.println(ANSI_PURPLE + "***********************************************\n" + ANSI_RESET);
    }
    
    /**
     * Executes the actions of the merged list
     */
    public void executeActions()
    {
        while(!this.finalActions.empty())
        {
            Action a = this.finalActions.pop();
            System.out.println("-------------------------------------------------------------------------");
            System.out.println( ANSI_GREEN + "Action launched by : " + a.getSource().getName() +  ANSI_RESET);
            if(a.getTarget() != null)
            {   
                System.out.println(ANSI_RED + "Action target : " +a.getTarget().getName() + ANSI_RESET);
            }
            if(a.getCapacity() != null)
            {
                a.useCapacity();
            } else
            {
                a.useItem();
            }
            //check if the target dies if true then remove all actiosn concerning this character
            if(a.getTarget() != null)  //if action is not a heal or using an item
            {
                if(a.getTarget().isDead())
                {
                    System.out.println("   .-.");
                    System.out.println(" __| |__");
                    System.out.println("[__   __]              " + ANSI_RED + a.getTarget().getName() + ANSI_RESET );
                    System.out.println("   | |           " + ANSI_RED + " was killed by " +  a.getSource().getName() + ANSI_RESET);
                    System.out.println("   | |");
                    System.out.println("   | |");
                    System.out.println("   '-'");
                    this.removeDieActions(a.getTarget());
                    this.removeDieCharacter(a.getTarget());
                    a.getSource().incrementLevel();
                }           
            }
            System.out.println("\n ");
        }
    }
    
    /**
     * Remove he character in the appropriate list of character
     * @param c the decreased character
     */
    public void removeDieCharacter(Character c)
    {
        if(this.aiCharacters.contains(c))
        {
            this.aiCharacters.remove(c);
        }
        else 
        {
            this.playerCharacters.remove(c);
        }
    }
    
    /**
     * Method that remove all actions in the list when a character dies
     * @param c the decreased character
     */
    public void removeDieActions(Character c)
    {
        ArrayList<Action> removeActions;
        removeActions = new ArrayList<>();
        
        Iterator<Action> iterator = this.finalActions.iterator();
        while(iterator.hasNext())
        {
            Action a = iterator.next();
            if(a.getTarget() == c || a.getSource() == c)
            {
                if(!removeActions.contains(a))
                {
                    removeActions.add(a);
                }
            }
        }
        
        for(Action a : removeActions)
        {
            this.finalActions.remove(a);
        }
    }
    
    public void addAction(Action a)
    {
        this.finalActions.push(a);
    }
    
    /**
     * @param c the tested character
     * @throws rpgException.CharatcerExistsException Exception if exception is not found in list
     */
    public void characterExists(Character c) throws CharatcerExistsException
    {
        if (!this.aiCharacters.contains(c) || !this.playerCharacters.contains(c))
        {
            throw new CharatcerExistsException(c);
        }
        
    }
}
