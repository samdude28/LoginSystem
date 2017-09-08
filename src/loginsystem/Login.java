package loginsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author SamMaloney
 */
public class Login {

    /**
     * This method essentially authenticates the user's input with whats in the
     * provided text file.
     * @param username the first item in the array becomes the username
     * @param password the second item in the array becomes the password
     * @return false if the user's input and the information in the text file do
     * not match up.
     * @throws FileNotFoundException if the file is not found
     */
    public static boolean authenticate(String username, String password) throws FileNotFoundException {
        ArrayList<String> list; //just one array list that takes in the username and password from the text file named loginsystem.txt
        try (Scanner cin = new Scanner(new File("loginsystem.txt"))) {
            list = new ArrayList<>();

            while (cin.hasNext()) {
                list.add(cin.next());   //this line reads the words in the text file 
            }
        }
        //int user represents the username
        //int pass represents the password
        //pw is always the one after un so the password will always be after the username in the array
        for(int user = 0; user < list.size(); user+=2){   
            int pass = user + 1;
            if(username.equals(list.get(user)) && password.equals(list.get(pass))) {     
                return true;                                                        
            }    
        }
        return false;
    }

}

    