package loginsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author SamMaloney
 */
public class LoginSystem {

    public static void main(String[] args) {
        
        final JFrame frame = new JFrame("LSU Social");
        final JFrame frame2 = new JFrame("LSU Social");
        final JButton btnLogin = new JButton("Click to login");
        final JButton btnCreate = new JButton("Click to create login");
        final JButton btnAccess = new JButton("Click to access account");
        

        btnLogin.addActionListener((ActionEvent e) -> {
            
            frame.setVisible(false); //you can't see me!
            controller control = new controller(frame);
            control.setVisible(true);
            // if logon successfully
            if (control.isSucceeded()) {
                frame.setVisible(true);
                
                btnLogin.setText("Click to logout");
                frame.getContentPane().add(btnAccess);
                frame.getContentPane().remove(btnCreate);
                
                btnAccess.addActionListener((ActionEvent f) -> {
                    frame.dispose();
                    AccountScreen userscreen = new AccountScreen(frame2);
                    userscreen.setVisible(true);
                    
                });
                btnLogin.addActionListener((ActionEvent g) -> {
                    System.exit(0);
                });
                
            }
        });
        
        
        btnCreate.addActionListener((ActionEvent e) -> {
            Create account = new Create(frame);
            account.setVisible(true);
            
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);    //creates the size of the frame
        frame.setLayout(new GridLayout());
        frame.getContentPane().add(btnLogin);
        frame.getContentPane().add(btnCreate);
        frame.setVisible(true);
        
        
        
    }
}

