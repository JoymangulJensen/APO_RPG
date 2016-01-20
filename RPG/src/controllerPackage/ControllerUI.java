/*
 *  Projet - APO Creation d'un RPG
 *  Decembre 2015
 *  Gaetan MARTIN & Jensen Joymangul
 */
package controllerPackage;

import java.util.Scanner;

/**
 * Class managing the interactions between the player and the game
 *
 * @author p1509413
 */
public class ControllerUI
{

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Read an integer from the console The message is repeated until a correct
     * value is typed in
     *
     * @param message the message to be displayed
     * @return the integer typed in by the user
     */
    public static int readInt(String message)
    {
        int nombre = 0;
        try
        {
            nombre = readIntHelper(message);
        } catch (NumberFormatException e)
        {
            // Log.e("Please, type in an integer");
            return readInt("Please, type in an integer");
        }
        return nombre;
    }

    /**
     * Read an integer from the console The message is repeated until a correct
     * value is typed in. The value must be between min and max included
     *
     * @param message the message to be displayed when displaying the menu
     * @param min the min choice possible
     * @param max the max choice possible
     * @return
     */
    public static int readInt(String message, int min, int max)
    {
        int nombre = readInt(message);
        if (nombre < min || nombre > max)
        {
            return readInt("Please, type in an integer between " + min + " and " + max, min, max);
        } else
        {
            return nombre;
        }
    }

    /**
     * Method helper for read int
     *
     * @param message : the message asking the user
     * @return an integer
     * @throws NumberFormatException if the user typed not in a number
     */
    private static int readIntHelper(String message) throws NumberFormatException
    {
        System.out.println(message);
        String result = scanner.next();
        return Integer.parseInt(result);
    }

    /**
     * Read a string from the console
     *
     * @return the string typed in
     */
    public static String readString(String message)
    {
        System.out.println(message);
        String result = scanner.next();
        return result;
    }
}
