package view.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Book;

/**
 * BookPanel - The panel for adding/editing a book specifically.
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
 * @version PA4 (Dec 07 2020)
 */
public class BookPanel extends PubPanel {
    
    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------
    private static final long serialVersionUID = -2544194172607528818L;
    
    private JLabel cityLabel;
    private JLabel publisherLabel;

    private JTextField cityTextField;
    private JTextField publisherTextField;
    
    /**
     * Default-Constructor.
     */
    public BookPanel() {
        
        super();
        
    }
    
    /**************************** public methods *************************/
    
    /**
     * clearTextFields - clears all the text fields for next time.
     */
    public void clearTextFields() {
        
        super.clearTextFields();
        cityTextField.setText( "" );
        publisherTextField.setText( "" );
        
    }
    
    /**
     * getEnteredCity - returns the current text contained
     * within CityTextField.
     * 
     * @return the entered city
     */
    public String getEnteredCity() {
        
        String enteredCity = null;
        
        if ( cityTextField.getText() != null ) {
            enteredCity = cityTextField.getText();
        }
        
        return enteredCity;
        
    }
    
    /**
     * getEnteredPublisher - returns the current text contained
     * within publisherTextField.
     * 
     * @return the entered publisher
     */
    public String getEnteredPublisher() {
        
        String enteredPublisher = null;
        
        if ( publisherTextField.getText() != null ) {
            enteredPublisher = publisherTextField.getText();
        }
        
        return enteredPublisher;
        
    }
    
    /**
     * setTextFields - sets all the text fields to the selected Book's
     * paramaters. Used for editing said Book.
     * 
     * @param book - the Book to edit
     */
    public void setTextFields( Book book ) {

        super.setTextFields( book );
        cityTextField.setText( book.getCity() );
        publisherTextField.setText( book.getPublisher() );
        
    }
    
    /**************************** protected methods *************************/
    
    /**
     * addComponents - add components to panels.
     * 
     * *DDM* - added more
     */
    @Override
    protected void addComponents() {

        JPanel gridPanel = super.getGridPanel();
        
        gridPanel.add( super.getAuthorLabel() );
        gridPanel.add( super.getAuthorTextField() );
        gridPanel.add( super.getTitleLabel() );
        gridPanel.add( super.getTitleTextField() );
        gridPanel.add( cityLabel );
        gridPanel.add( cityTextField );
        gridPanel.add( publisherLabel );
        gridPanel.add( publisherTextField );
        gridPanel.add( super.getYearLabel() );
        gridPanel.add( super.getYearTextField() );
        
        super.addComponents();
        
    }

    /**
     * createComponents - instantiate necessary components.
     * 
     * *DDM* - created more
     */
    @Override
    protected void createComponents() {
        
        super.createComponents();
        
        cityLabel = new JLabel( "City:" );
        publisherLabel = new JLabel( "Publisher:" );
        
        cityTextField = new JTextField();
        publisherTextField = new JTextField();
        
    }

    /**
     * setPanel - set params for panels.
     * 
     * *DDM* - added more
     */
    @Override
    protected void setPanel() {

        super.setPanel();
        
        super.getGridPanel().setLayout( new GridLayout( 5, 2 ) );
        
    }

}
