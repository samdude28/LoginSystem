package loginsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

public class controller extends JDialog {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;

    public controller(Frame parent) {
        super(parent, "Login", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.black));

        lbUsername.setForeground(Color.white);
        lbPassword.setForeground(Color.white);
        panel.setBackground(Color.black);
  

        btnLogin = new JButton("Login");
        
    
        btnLogin.addActionListener(new ActionListener() {

           
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (authenticate(getUsername(), getPassword())) {
                        JOptionPane.showMessageDialog(controller.this,
                                "Hi " + getUsername() + "! You have successfully logged in.",
                                "Login",JOptionPane.INFORMATION_MESSAGE);
                        succeeded = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(controller.this,
                                "Invalid username or password",
                                "Login",
                                JOptionPane.ERROR_MESSAGE);
                        // reset username and password
                        tfUsername.setText("");
                        pfPassword.setText("");
                        succeeded = false;
                        dispose();
                        
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.setForeground(Color.red);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        
    }
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

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }
    
}
