package loginsystem;

import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 *
 * @author SamMaloney
 */
public class Friends extends JPanel {
    private boolean DEBUG = false;
 
    public Friends() {
        super(new GridLayout(1,0));
 
        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
 
    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"First Name",
                                        "Last Name",
                                        "Sport",
                                        "# of Years",
                                        "Vegetarian"};
        private Object[][] data = {
        {"Kathy", "Smith",
         "Snowboarding", new Integer(5), new Boolean(false)},
        {"John", "Doe",
         "Rowing", new Integer(3), new Boolean(true)},
        {"Sue", "Black",
         "Knitting", new Integer(2), new Boolean(false)},
        {"Jane", "White",
         "Speed reading", new Integer(20), new Boolean(true)},
        {"Joe", "Brown",
         "Pool", new Integer(10), new Boolean(false)}
        };
 
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
 
        @Override
        public int getRowCount() {
            return data.length;
        }
 
        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }
 
        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
 
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        @Override
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
 
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }
 
            data[row][col] = value;
            fireTableCellUpdated(row, col);
 
            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
 
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();
 
            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        Friends newContentPane = new Friends();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    
    
    
    
}
	/*
	public void testAddFriend() {
		FriendList friendList = new FriendList();
		Friend friend = new Friend("David", 12);
		friendList.addFriend(friend);
		assertTrue(friendList.hasFriend(friend));
	}
	
	public void testRemoveFriend() {
		FriendList friendList = new FriendList();
		Friend friend = new Friend("Bob", 14);
		friendList.addFriend(friend);
		friendList.removeFriend(friend);
		assertFalse(friendList.hasFriend(friend));
	}

	public void testHasFriend() {
		FriendList friendList = new FriendList();
		Friend friend = new Friend("Freddy", 16);
		assertFalse(friendList.hasFriend(friend));
		friendList.addFriend(friend);
		assertTrue(friendList.hasFriend(friend));
	}

	public void testNumFriends() {
		FriendList friendList = new FriendList();
		Friend friend1 = new Friend("David", 12);
		friendList.addFriend(friend1);
		Friend friend2 = new Friend("Leroy", 14);
		friendList.addFriend(friend2);
		int numFriends = friendList.numFriends();
		assertEquals(2, numFriends);
	}
}
*/
    