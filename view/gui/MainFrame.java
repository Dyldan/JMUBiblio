package view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.BiblioListener;
import utilities.BiblioFinals;

/**
 * MainFrame.java - creates JFrame for application, substituting 
 * multiple panels.
 *
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's Starter Kit
 *                      
 * Modifications: 12/06/2020 *DDM* (PA4) - Fixed style problems
 *                                       - Split add functionality
 *                                       - Removed MenuBar
 *                                       - Removed edit, delete, action panels
 *
 * @author Michael Norton && Dylan Moreno
 * @version PA4 (Dec 06 2020), PA4 (Jun 25 2012)
 */
public class MainFrame extends JFrame implements BiblioFinals {

    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------
    private static final long serialVersionUID = -6721479499467128169L;
    
    private static MainFrame frame; // singleton

    private JPanel addPanel;
    private JPanel articlePanel;
    private JPanel bookPanel;
    private JPanel currentPanel;
    private JPanel mainPanel;
    private JPanel searchPanel;
    
    /**
     * Default constructor.
     */
    private MainFrame() {

        createComponents(); // create needed objects
        setPanel( mainPanel ); // add beginning panel to top-level panel
        setListeners(); // set listeners for components

        setSize( 600, 450 );
        setTitle( TITLE_STRING );
        setVisible( true ); // display this
        centerForm();

    } // constructor

    /**************************** public methods *************************/

    /**
     * getPanel - return the current JPanel.
     *
     * @return JPanel
     */
    public JPanel getPanel() {

        return currentPanel;

    } // method getPanel

    /**
     * getPanel - return the requested JPanel.
     * 
     * *DDM* - changed/added some cases
     *
     * @param choice The user's choice
     * @return JPanel
     */
    public JPanel getPanel( int choice ) {

        JPanel returnPanel;

        switch ( choice ) {
            case MAIN_PANEL:
                returnPanel = mainPanel;
                break;
            case ADD_PANEL:
                returnPanel = addPanel;
                break;
            case ARTICLE_PANEL:
                returnPanel = articlePanel;
                break;
            case BOOK_PANEL:
                returnPanel = bookPanel;
                break;
            case SEARCH_PANEL:
                returnPanel = searchPanel;
                break;
            default:
                returnPanel = mainPanel;

        } // end switch

        return returnPanel;

    } // method getPanel (overloaded)

    /**
     * setPanel - remove the current panel and set to the panel specified by the
     * parameter.
     *
     * @param newPanel (JPanel)
     */
    public void setPanel( JPanel newPanel ) {

        // Remove the current panel, if set
        if ( currentPanel != null ) {
            currentPanel.setVisible( false );
            getContentPane().remove( currentPanel );
        }

        // Add the specified & set this as the current panel
        if ( newPanel != null ) {
            getContentPane().add( newPanel, BorderLayout.CENTER );
            currentPanel = newPanel;
            currentPanel.setVisible( true );
        }
        
    } // method setPanel

    /**************************** private methods ************************/

    /**
     * centerForm.
     *
     * center form on screen
     */
    private void centerForm() {

        Dimension dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dimFrameSize = getSize();

        if ( dimFrameSize.height > dimScreenSize.height ) {
            dimFrameSize.height = dimScreenSize.height;
        }
        if ( dimFrameSize.width > dimScreenSize.width ) {
            dimFrameSize.width = dimScreenSize.width;
        }

        setLocation( ( dimScreenSize.width - dimFrameSize.width ) / 2,
                        ( dimScreenSize.height - dimFrameSize.height ) / 2 );

    } // method centerForm

    /**
     * createComponents - instantiate all components.
     * 
     * *DDM* - removed createMenu() ( this still exists in case it's needed in
     * the future )
     */
    private void createComponents() {

        createPanels();

    } // method createComponents

    /**
     * createPanels - instantiate all panels.
     * 
     * *DDM* - added/removed some panels
     */
    private void createPanels() {

        mainPanel = new MainPanel();
        addPanel = new AddPanel();
        articlePanel = new ArticlePanel();
        bookPanel = new BookPanel();
        searchPanel = new SearchPanel();

    } // method createPanels

    /**
     * setListeners - set the listeners for the components on the Frame.
     */
    private void setListeners() {

        BiblioListener listener = BiblioListener.getInstance();

        this.addWindowListener( listener );

    } // method setListeners
    
    
    /**************************** static methods ************************/
    
    
    /**
     * getInstance - get this instance for use as a singleton.
     * 
     * @return this instance.
     */
    public static MainFrame getInstance() {
        
        if ( frame == null ) {
            frame = new MainFrame();
            
        }
        
        return frame;
    }

}
