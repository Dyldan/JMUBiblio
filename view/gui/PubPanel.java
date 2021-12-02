package view.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.BiblioListener;
import model.Publication;

/**
 * PubPanel - Parent class for ArticlePanel and BookPanel.
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
public abstract class PubPanel extends AbstractPanel {
    
    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------
    private static final long serialVersionUID = -2544194172607528818L;
    
    private int currentFunction;
    
    private CompoundBorder internalBorder;
    private EmptyBorder emptyBorder;
    
    private JButton cancelButton;
    private JButton saveButton;
    
    private JLabel authorLabel;
    private JLabel titleLabel;
    private JLabel yearLabel;
    
    private JPanel gridPanel;
    private JPanel midPanel;
    private JPanel southPanel;
    
    private JTextField authorTextField;
    private JTextField titleTextField;
    private JTextField yearTextField;
    
    /**
     * Default-Constructor.
     */
    public PubPanel() {
        
        super();
        
        currentFunction = -1; // neither ADD nor EDIT
        
    }
    
    /**************************** public methods *************************/
    
    /**
     * clearTextFields - clears all the text fields for next time.
     */
    public void clearTextFields() {
        
        authorTextField.setText( "" );
        titleTextField.setText( "" );
        yearTextField.setText( "" );
        
    }
    
    /**
     * getAuthorLabel.
     * 
     * @return authorLabel
     */
    public JLabel getAuthorLabel() {
        
        return authorLabel;
        
    }
    
    /**
     * getAuthorTextField.
     * 
     * @return authorTextField
     */
    public JTextField getAuthorTextField() {
        
        return authorTextField;
        
    }
    
    /**
     * getCurrentFunction - returns the current function in use ( ADD or EDIT ).
     * 
     * @return currentFunction
     */
    public int getCurrentFunction() {
        
        return currentFunction;
        
    }
    
    /**
     * getGridPanel - returns this GridPanel.
     * 
     * @return gridPanel
     */
    public JPanel getGridPanel() {
        
        return gridPanel;
        
    }
    
    /**
     * getEnteredAuthor - returns the current text contained
     * within authorTextField.
     * 
     * @return the entered author
     */
    public String getEnteredAuthor() {
        
        String enteredAuthor = null;
        
        if ( authorTextField.getText() != null ) {
            enteredAuthor = authorTextField.getText();
        }
        
        return enteredAuthor;
        
    }
    
    /**
     * getEnteredTitle - returns the current text contained
     * within titleTextField.
     * 
     * @return the entered title
     */
    public String getEnteredTitle() {
        
        String enteredTitle = null;
        
        if ( titleTextField.getText() != null ) {
            enteredTitle = titleTextField.getText();
        }
        
        return enteredTitle;
        
    }
    
    /**
     * getEnteredYear - returns the current text contained
     * within yearTextField.
     * 
     * @return the entered year
     */
    public String getEnteredYear() {
        
        String enteredYear = null;
        
        if ( yearTextField.getText() != null ) {
            enteredYear = yearTextField.getText();
        }
        
        return enteredYear;
        
    }
    
    /**
     * getTitleLabel.
     * 
     * @return titleLabel
     */
    public JLabel getTitleLabel() {
        
        return titleLabel;
        
    }
    
    /**
     * getTitleTextField.
     * 
     * @return titleTextField
     */
    public JTextField getTitleTextField() {
        
        return titleTextField;
        
    }
    
    /**
     * getYearLabel.
     * 
     * @return yearLabel
     */
    public JLabel getYearLabel() {
        
        return yearLabel;
        
    }
    
    /**
     * getYearTextField.
     * 
     * @return yearTextField
     */
    public JTextField getYearTextField() {
        
        return yearTextField;
        
    }
    
    /**
     * setCurrentFunction - sets this panel's current function to the specified
     * function ( ADD or EDIT or -1 ).
     * 
     * @param which - the incoming current function to switch to
     * @return true if the specified function is valid, false otherwise
     */
    public boolean setCurrentFunction( int which ) {

        boolean success = false; // assume it failed

        if ( which == ADD || which == EDIT || which == -1 ) {
            success = true;
            currentFunction = which;
        }

        return success;

    }
    
    /**
     * setTextFields - sets all the text fields to the selected Publication's
     * paramaters. Used for editing said Publication.
     * 
     * @param pub - the Publication to edit
     */
    public void setTextFields( Publication pub ) {

        authorTextField.setText( pub.getAuthor() );
        titleTextField.setText( pub.getTitle() );
        yearTextField.setText( "" + pub.getYear() );
        
    }
    
    /**************************** protected methods *************************/
    
    /**
     * addComponents - add components to panels.
     */
    @Override
    protected void addComponents() {
        
        midPanel.add( gridPanel, BorderLayout.CENTER );
        
        southPanel.add( saveButton );
        southPanel.add( cancelButton );
        
        add( midPanel, BorderLayout.CENTER );
        add( southPanel, BorderLayout.SOUTH );
        
    }

    /**
     * createComponents - instantiate necessary components.
     */
    @Override
    protected void createComponents() {
        
        emptyBorder = new EmptyBorder( 60, 60, 60, 60 );
        internalBorder = new CompoundBorder(
                        new EmptyBorder( 10, 10, 10, 10 ), new EtchedBorder() );
        
        authorLabel = new JLabel( "Author:" );
        titleLabel = new JLabel( "Title:" );
        yearLabel = new JLabel( "Year:" );
        
        authorTextField = new JTextField();
        titleTextField = new JTextField();
        yearTextField = new JTextField();
        
        cancelButton = new JButton( "Cancel" );
        saveButton = new JButton( "Save" );
        
        midPanel = new JPanel();
        southPanel = new JPanel();
        gridPanel = new JPanel();
        
    }

    /**
     * setListeners - set ActionListeners for all components on the main panel.
     */
    @Override
    protected void setListeners() {

        BiblioListener listener = BiblioListener.getInstance();
        
        Component[] panelItems = southPanel.getComponents();

        for ( int i = 0; i < panelItems.length; i++ ) {
            ( (JButton) panelItems[ i ] ).addActionListener( listener );
        }
        
    }

    /**
     * setPanel - set params for panels.
     */
    @Override
    protected void setPanel() {

        setLayout( new BorderLayout() ); 
        midPanel.setLayout( new BorderLayout() );
        
    }

    /**
     * setParameters - set the labels and action commands.
     */
    @Override
    protected void setParameters() {

        midPanel.setBorder( internalBorder );
        gridPanel.setBorder( emptyBorder );
        
    }

}
