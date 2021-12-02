package view.gui;

import javax.swing.JOptionPane;

import model.Publication;

/**
 * PopupFrame - gives different Pop-up Dialogs.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      None
 *                      
 * Modifications: None
 * 
 * @author Dylan Moreno
 * @version PA4 (Dec 08 2020)
 */
public class PopupFrame extends JOptionPane {

    private static final long serialVersionUID = -6863603748544759477L;

    /**
     * showDeleteConfirmation - shows the pop-up prompting the user to confirm
     * deletion.
     * 
     * @param pub - the incoming Publication to delete
     * @param title - the title of the pop-up
     * @return the chosen option
     */
    public static int showDeleteConfirmation( Publication pub, String title ) {
        
        // send the message popup; maybe add message type?
        int choice = showConfirmDialog( null,
                        "Are you sure you want to delete: \""
                                        + pub.getShortTitle() + "\"?",
                        title, JOptionPane.OK_CANCEL_OPTION );
        
        return choice; // return the choice
        
    }
    
    /**
     * showMessage - shows a pop-up with the incoming messsage.
     * 
     * @param message - the incoming message
     */
    public static void showMessage( String message ) {
        
        showMessageDialog( null, message );
        
    }
    
}
