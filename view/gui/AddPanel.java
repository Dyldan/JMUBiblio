package view.gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.BiblioListener;

/**
 * AddPanel - add articles/books.
 *
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's Starter Kit
 *                      
 * Modifications: 12/06/2020 *DDM* (PA4) - Added option to choose
 *                                         between an Article and a Book
 *
 * @author Michael Norton && Dylan Moreno
 * @version PA04 (Dec 08 2020), PA04 (Nov 14 2020)
 */
public class AddPanel extends AbstractPanel {
    
    private static final long serialVersionUID = -829396535517064329L;

    private Box box1;
    
    private CompoundBorder internalBorder;
    private EmptyBorder emptyBorder;
    
    private JButton addArticleButton;
    private JButton addBookButton;
    
    private JButton cancelButton;
      
    private JPanel midPanel;
    private JPanel southPanel;
    
    /**
     * Default constructor.
     */
    public AddPanel() {
        
        super();

    } // constructor

    /**
     * addComponents - add components to panels.
     * 
     * *DDM* - added more
     */
    protected void addComponents() {
               
        box1.add( addArticleButton );
        box1.add( addBookButton );
        
        midPanel.add( box1 );
        
        southPanel.add( cancelButton );
        
        add( midPanel, BorderLayout.CENTER );
        add( southPanel, BorderLayout.SOUTH );
       
    } // method addComponents

    /**
     * createComponents - instantiate necessary components.
     * 
     * *DDM* - created more
     */
    protected void createComponents() {
        
        emptyBorder = new EmptyBorder( 120, 0, 0, 0 );
        internalBorder = new CompoundBorder(
                        new EmptyBorder( 10, 10, 10, 10 ), new EtchedBorder() );
        
        box1 = new Box( BoxLayout.X_AXIS );
        
        addArticleButton = new JButton( "Article" );
        addBookButton = new JButton( "Book" );
        
        cancelButton = new JButton( "Cancel" );
        
        midPanel = new JPanel();
        southPanel = new JPanel();

    } // method createComponents

    /**
     * setListeners - set listeners for components on the panel.
     * 
     * *DDM* - added more
     */
    protected void setListeners() {

        BiblioListener listener = BiblioListener.getInstance();
        
        addArticleButton.addActionListener( listener );
        addBookButton.addActionListener( listener );
        
        cancelButton.addActionListener( listener );

    } // method setListeners

    /**
     * setPanel - set parameters for panels.
     */
    protected void setPanel() {

        setLayout( new BorderLayout() ); 

    } // method setPanel

    /**
     * setParameters - set parameters for components on form.
     * 
     * *DDM* - added more
     */
    protected void setParameters() {

        midPanel.setBorder( internalBorder );
        box1.setBorder( emptyBorder );
        
    } // method setParameters

} // class ArticlePanel
