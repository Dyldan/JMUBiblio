package view.gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.BiblioListener;
import model.Publication;

/**
 * Search Panel.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's Starter Kit
 *                      
 * Modifications: 12/06/2020 *DDM* (PA4) - Added actual functionality
 *                                       - Added design
 * 
 * @author Michael Norton && Dylan Moreno
 * @version PA04 (Dec 09 2020), PA04 (Nov 14 2020)
 */
public class SearchPanel extends AbstractPanel {

    // ---------------------------------------------------------------------
    // Declarations
    // ---------------------------------------------------------------------
    private static final long serialVersionUID = -3502093792929226518L;

    private Box box1;
    private Box box2;
    private Box box3;
    
    private CompoundBorder internalBorderMid;
    private CompoundBorder internalBorderNorth;
    
    private DefaultListModel< Publication > listModel;
    
    private JButton clearSearchButton;
    private JButton returnButton;
    private JButton searchButton;
    
    private JLabel authorLabel;
    private JLabel titleLabel;
    private JLabel topLabel;
    
    private JList< Publication > biblioList;
    
    private JPanel midPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    
    private JScrollPane listPane;
    
    private JTextField authorTextField;
    private JTextField titleTextField;
    
    /**
     * Default constructor.
     */
    public SearchPanel( ) {

        super();

    }
    
    /**************************** public methods *************************/
    
    /**
     * clearTextFields - clears all the text fields.
     */
    public void clearTextFields() {
        
        authorTextField.setText( "" );
        titleTextField.setText( "" );
        
    }
    
    /**
     * disableTextField - disables the specified text field ( AUTHOR or TITLE ).
     * This function also disables the corresponding labels for pure aesthetics.
     * 
     * @param which - which textField to disable
     */
    public void disableTextField( int which ) {
        
        if ( which == AUTHOR ) {
            authorTextField.setEnabled( false );
            authorLabel.setEnabled( false );
        } else if ( which == TITLE ) {
            titleTextField.setEnabled( false );
            titleLabel.setEnabled( false );
        }
        
    }
    
    /**
     * enableTextFields - reenables the textFields (and corresponding label)
     * that are currently disabled.
     */
    public void enableTextFields() {
        
        authorTextField.setEnabled( true );
        authorLabel.setEnabled( true );
        titleTextField.setEnabled( true );
        titleLabel.setEnabled( true );
        
    }
    
    /**
     * getEnteredAuthor - returns the current text contained
     * within authorTextField.
     * 
     * @return the entered author specification
     */
    public String getEnteredAuthor() {
        
        String authorSearch = null;
        
        if ( authorTextField.getText() != null ) {
            authorSearch = authorTextField.getText();
        }
        
        return authorSearch;
        
    }
    
    /**
     * getEnteredTitle - returns the current text contained
     * within titleTextField.
     * 
     * @return the entered title specification
     */
    public String getEnteredTitle() {
        
        String titleSearch = null;
        
        if ( titleTextField.getText() != null ) {
            titleSearch = titleTextField.getText();
        }
        
        return titleSearch;
        
    }
    
    /**
     * getList - get the list.
     * 
     * @return biblioList
     */
    public JList< Publication > getList() {
        
        return biblioList;
        
    }
    
    /**************************** protected methods *************************/

    /**
     * addComponents - add components to panels.
     * 
     * *DDM* - added more
     */
    @Override
    protected void addComponents() {
        
        box1.add( box2 );
        box1.add( box3 );
        
        box2.add( topLabel );
        
        box3.add( authorLabel );
        box3.add( authorTextField );
        box3.add( titleLabel );
        box3.add( titleTextField );
        box3.add( searchButton );
        
        northPanel.add( box1 );
        midPanel.add( listPane ); // put our stuff in the middle
        southPanel.add( clearSearchButton );
        southPanel.add( returnButton );
        
        add( northPanel, BorderLayout.NORTH );
        add( midPanel, BorderLayout.CENTER );
        add( southPanel, BorderLayout.SOUTH );
        
    }

    /**
     * createComponents - instantiate necessary components.
     * 
     * *DDM* - created more
     */
    @Override
    protected void createComponents() {

        internalBorderNorth = new CompoundBorder(
                        new EmptyBorder( 10, 10, 10, 10 ), new EtchedBorder() );
        internalBorderMid = new CompoundBorder(
                        new EmptyBorder( 10, 10, 10, 10 ), new EtchedBorder() );

        box1 = new Box( BoxLayout.Y_AXIS );
        box2 = new Box( BoxLayout.X_AXIS );
        box3 = new Box( BoxLayout.X_AXIS );
        
        returnButton = new JButton( "Return" );
        searchButton = new JButton( "Search For..." );
        clearSearchButton = new JButton( "Clear Search" );

        authorLabel = new JLabel( "Author: " );
        titleLabel = new JLabel( "Title: " );
        topLabel = new JLabel( "^ = starts with, $ = ends with, "
                        + "!() = not, no symbol = contains" );
        
        authorTextField = new JTextField();
        titleTextField = new JTextField();
        
        listModel = new DefaultListModel<>();
        biblioList = new JList<>( listModel );
        listPane = new JScrollPane( biblioList );

        northPanel = new JPanel();
        midPanel = new JPanel();
        southPanel = new JPanel();
                        
    }
    
    /**
     * setListeners - set listeners for components on the panel.
     * 
     * *DDM* - added more listeners
     */
    @Override
    protected void setListeners() {

        BiblioListener listener = BiblioListener.getInstance();
        
        clearSearchButton.addActionListener( listener );
        returnButton.addActionListener( listener );
        searchButton.addActionListener( listener );
        
        authorTextField.addFocusListener( listener );
        titleTextField.addFocusListener( listener );
        
    }

    /**
     * setPanel - set parameters for panels.
     * 
     * *DDM* - added midPanel layout
     */
    @Override
    protected void setPanel() {

        setLayout( new BorderLayout() );
        midPanel.setLayout( new BoxLayout( midPanel, BoxLayout.Y_AXIS ) );
        
    }

    /**
     * setParameters - set parameters for components on form.
     * 
     * *DDM* - now sets borders and names for the textFields
     */
    @Override
    protected void setParameters() {

        northPanel.setBorder( internalBorderNorth );
        midPanel.setBorder( internalBorderMid );
        
        authorTextField.setName( "" + AUTHOR );
        titleTextField.setName( "" + TITLE );

    }

}
