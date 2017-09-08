package loginsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class AccountScreen extends JDialog  {

    final JFrame frame2 = new JFrame("LSU Social");
    final JButton btnAdd = new JButton("Click to add a friend");
    final JButton btnRemove = new JButton("Click to remove a friend");
    final JButton btnDisplay = new JButton("Click to display your friends");
    final JButton btnExit = new JButton("Exit");
    final JButton btnLogout = new JButton("Logout");
    
    
    
    
    
    
    
    

    public AccountScreen(Frame parent) {
        
        //add friend to user logged in
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                {
                    frame2.setVisible(false);
                    frame2.setVisible(true);
                    
                    frame2.getContentPane().remove(btnAdd);
                    frame2.getContentPane().remove(btnRemove);
                    frame2.getContentPane().remove(btnDisplay);
                JOptionPane.showMessageDialog(AccountScreen.this,
                                "Please enter username to add friend");
                }
            }
        });  
        
        //Remove friend of user logged in
        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent b) {
                
                frame2.getContentPane().remove(btnAdd);
                frame2.getContentPane().remove(btnRemove);
                frame2.getContentPane().remove(btnDisplay);
            }
        });  
        
        //Display all of the user logged in's friends
        btnDisplay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent c) {
                
                frame2.getContentPane().remove(btnAdd);
                frame2.getContentPane().remove(btnRemove);
                frame2.getContentPane().remove(btnDisplay);
            }
        });  
        
        
        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent d) {
                frame2.dispose();
                AccountScreen userscreen = new AccountScreen(frame2);
            }
        });  
        
        btnLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });  
        
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(1000, 250);    //creates the size of the frame
        frame2.setLayout(new GridLayout());
        frame2.getContentPane().add(btnAdd);
        frame2.getContentPane().add(btnRemove);
        frame2.getContentPane().add(btnDisplay);
        frame2.getContentPane().add(btnLogout);
        frame2.getContentPane().add(btnExit);
        frame2.setVisible(true);
        
    }
    

    
}