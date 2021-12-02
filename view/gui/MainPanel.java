package view.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.BiblioListener;
import model.Publication;

/**
 * MainPanel - The starting list panel.
 *
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's Starter Kit
 *                      
 * Modifications: 12/06/2020 *DDM* (PA4) - Fixed style problems
 *                                       - Added JList functionality
 *                                          w/ DefaultListModel
 *                                       - Changed 'exitButton' to 'quitButton'
 *                                       - Added enabling and disabling main 
 *                                          buttons feature
 *
 * Opening Panel for listing publications and offering choices for actions
 *
 * @author Michael Norton && Dylan Moreno
 * @version PA04 (Nov 14 2020)
 */
public class MainPanel extends AbstractPanel {

    // ---------------------------------------------------------------------
    // Declarations
    // ---------------------------------------------------------------------
    private static final long serialVersionUID = 8921461284374081509L;

    private CompoundBorder internalBorder;
    
    private DefaultListModel< Publication > listModel;
     
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton quitButton;
    private JButton searchButton;
    
    private JList< Publication > biblioList;
    
    private JPanel midPanel;
    private JPanel southPanel;
    
    private JScrollPane listPane;

    /**
     * Default constructor.
     */
    public MainPanel() {

        super();
        Component[] comps = this.getComponents();
//        for ( int i = 0; i < comps.length; i++ ) {
//            System.out.println( comps[i] instanceof JPanel );
//        }
        
    } // constructor

    /**************************** public methods *************************/
    
    /**
     * disableButtons - disable all of the home buttons ( except for add and
     * exit ).
     */
    public void disableAllButtons() {
        
        addButton.setEnabled( true );
        editButton.setEnabled( false );
        deleteButton.setEnabled( false );
        searchButton.setEnabled( false );
        quitButton.setEnabled( true );
        
    }
    
    /**
     * disableModifyButtons - disable the home modify buttons ( add and edit ).
     * Same as disableAllButtons(), except this function does not disable
     * the search button.
     */
    public void disableModifyButtons() {
        
        addButton.setEnabled( true );
        editButton.setEnabled( false );
        deleteButton.setEnabled( false );
        searchButton.setEnabled( true );
        quitButton.setEnabled( true );
        
    }
    
    /**
     * enableButtons - enable all of the home buttons.
     */
    public void enableAllButtons() {
        
        addButton.setEnabled( true );
        editButton.setEnabled( true );
        deleteButton.setEnabled( true );
        searchButton.setEnabled( true );
        quitButton.setEnabled( true );
        
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
     * *DDM* - organized for aesthetics
     */
    @Override
    protected void addComponents() {

        midPanel.add( listPane ); // put our stuff in the middle

        // add buttons to south panel
        southPanel.add( addButton );
        southPanel.add( editButton );
        southPanel.add( deleteButton );
        southPanel.add( searchButton );
        southPanel.add( quitButton );
        
        add( midPanel, BorderLayout.CENTER );
        add( southPanel, BorderLayout.SOUTH );

    } // method addComponents

    /**
     * createComponents - instantiate necessary components.
     * 
     * *DDM* - created new components
     */
    @Override
    protected void createComponents() {
        
        internalBorder = new CompoundBorder(
                        new EmptyBorder( 10, 10, 10, 10 ), new EtchedBorder() );
        
        listModel = new DefaultListModel<>();
        biblioList = new JList<>( listModel );
        listPane = new JScrollPane( biblioList );
        
        addButton = new JButton( "Add" );
        editButton = new JButton( "Edit" );
        deleteButton = new JButton( "Delete" );
        searchButton = new JButton( "Search" );
        quitButton = new JButton( "Quit" );
        
        midPanel = new JPanel();
        southPanel = new JPanel();

    } // method createComponents

    /**
     * setListeners - set ActionListeners for all components on the main panel.
     * 
     * *DDM* - added JList listener
     */
    @Override
    protected  void setListeners() {

        BiblioListener listener = BiblioListener.getInstance();
  
        Component[] panelItems = southPanel.getComponents();

        for ( int i = 0; i < panelItems.length; i++ ) {
            ( (JButton) panelItems[ i ] ).addActionListener( listener );
        }
        
        biblioList.addListSelectionListener( listener );

    } // method setListeners

    /**
     * setPanel - set params for panels.
     */
    @Override
    protected void setPanel() {

        setLayout( new BorderLayout() ); 
        midPanel.setLayout( new BoxLayout( midPanel, BoxLayout.Y_AXIS ) );

    } // method setPanel

    /**
     * setParameters - set the labels and action commands.
     */
    @Override
    protected void setParameters() {
        
        midPanel.setBorder( internalBorder );
        
    } // method setParameters

} // class ChoicePanel
