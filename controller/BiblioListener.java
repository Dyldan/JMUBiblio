package controller;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Article;
import model.Book;
import model.Publication;
import utilities.BiblioFinals;
import utilities.Utilities;
import view.gui.AddPanel;
import view.gui.ArticlePanel;
import view.gui.BookPanel;
import view.gui.MainPanel;
import view.gui.PopupFrame;
import view.gui.SearchPanel;
import view.gui.MainFrame;

/**
 * BibliogListener.java - primary listener class for BibliogList application.
 *
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's Starter Kit
 *                      
 * Modifications: 12/06/2020 *DDM* (PA4) - Added reading/writing functionality
 *                                       - Added functionality to handle input
 *                                         for the rest of the program, 
 *                                         including adding Books and Articles, 
 *                                         deleting, editing, and searching
 *                                       - Removed handleMenuEvents()
 *
 * @author Michael Norton && Dylan Moreno
 * @version PA4 (Dec 09 2020), PA04 (Jun 29 2009)
 */
public class BiblioListener implements ActionListener, WindowListener,
                BiblioFinals, FocusListener, ListSelectionListener {
    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------

    private static BiblioListener listener; // singleton
    
    private BiblioControl biblioControl = BiblioControl.getInstance();

    /**
     * constructor.
     */
    private BiblioListener() {

        // empty constructor -- required for singleton

    } // constructor

    
    /***************************** public methods ************************/

    
    /**
     * actionPerformed - handle button pushes (etc.).
     * 
     * *DDM* - added more cases
     *
     * @param e (ActionEvent)
     */
    public void actionPerformed( ActionEvent e ) {

        MainFrame frame = MainFrame.getInstance();

        if ( frame.getPanel() instanceof MainPanel ) {
            
            handleMainPanelEvents( e );
            
        } else if ( frame.getPanel() instanceof AddPanel ) {
            
            handleAddPanelEvents( e );
            
        } else if ( frame.getPanel() instanceof ArticlePanel ) {
            
            handleArticlePanelAddEvents( e );
            
        } else if ( frame.getPanel() instanceof BookPanel ) {

            handleBookPanelAddEvents( e );

        } else if ( frame.getPanel() instanceof SearchPanel ) {
            
            handleSearchPanelEvents( e );
            
        } 

    } // method actionPerformed
    
    /**
     * focusGained - handle text field focus gained.
     */
    @Override
    public void focusGained( FocusEvent e ) {

        MainFrame frame = MainFrame.getInstance();
        SearchPanel searchPanel = (SearchPanel) frame.getPanel( SEARCH_PANEL );
        JTextField comp;
        
        if ( e.getSource() instanceof JTextField ) {
            
            comp = (JTextField) e.getSource();
            
            // disable whichever textField was not selected
            switch ( comp.getName() ) {
                
                case "" + AUTHOR:
                    searchPanel.disableTextField( TITLE );
                    break;
                case "" + TITLE:
                    searchPanel.disableTextField( AUTHOR );
                    break;
                default:
                    // do nothing
                    
            }
                
        }
        
    }

    /**
     * valueChanged - gets the list selection event.
     * 
     * @param e the list selection event
     */
    @Override
    public void valueChanged( ListSelectionEvent e ) {

        MainFrame frame = MainFrame.getInstance();
        MainPanel mainPanel = (MainPanel) frame.getPanel( MAIN_PANEL );
       
        if ( e.getSource() != null ) {
            mainPanel.enableAllButtons();
        }
        
    }
    
    /**
     * windowClosing - handle Window Closing events.
     *
     * @param e (WindowEvent)
     */
    public void windowClosing( WindowEvent e ) {
        
        closeApplication();

    } // method windowClosing

    /**
     * windowOpened - handle Window Opening events.
     *
     * *DDM* can now read the file
     *
     * @param e - the WindowEvent
     */
    public void windowOpened( WindowEvent e ) {

        biblioControl.readFile(); // read the file

    } // method windowOpened

    
    /**************************** private methods ************************/

    
    /**
     * changeAddEdit - changes whether ArticlePanel and BookPanel should
     * currently be used for either adding or editing.
     * 
     * @param which - the function to switch to
     */
    private void changeAddEdit( int which ) {
        
        MainFrame frame = MainFrame.getInstance();
        
        ( (ArticlePanel) frame.getPanel( ARTICLE_PANEL ) )
                        .setCurrentFunction( which );
        ( (BookPanel) frame.getPanel( BOOK_PANEL ) )
                        .setCurrentFunction( which );

    }
    
    /**
     * closeApplication - close the application.
     * 
     * *DDM* can now write the file
     */
    private void closeApplication() {

        biblioControl.writeFile(); // write the file
        
        System.exit( 0 );

    } // method closeApplication

    /**
     * handleAddPanelEvents - deal with events from AddPanel.
     * 
     * *DDM* changed name and updated functionality
     * 
     * @param event The AWTEvent
     */
    private void handleAddPanelEvents( AWTEvent event ) {

        MainFrame frame = MainFrame.getInstance();
        JPanel newPanel;

        ActionEvent e = (ActionEvent) event;
        JButton comp = (JButton) e.getSource();

        switch ( comp.getText() ) {
            case "Article":
                newPanel = frame.getPanel( ARTICLE_PANEL );
                frame.setPanel( newPanel );
                frame.setTitle( ADD_ARTICLE_TITLE );
                break;
            case "Book":
                newPanel = frame.getPanel( BOOK_PANEL );
                frame.setPanel( newPanel );
                frame.setTitle( ADD_BOOK_TITLE );
                break;
            case "Cancel":
                returnHome(); // return to MainPanel
                break;
            default:
                returnHome(); // return to MainPanel
        }
        
    }
    
    /**
     * handleArticlePanelAddEvents - deal with ADD events from ArticlePanel.
     * 
     * @param event the AWTEvent
     */
    private void handleArticlePanelAddEvents( AWTEvent event ) {
        
        MainFrame frame = MainFrame.getInstance();
        JPanel newPanel;
        
        ActionEvent e = (ActionEvent) event;
        JButton comp = (JButton) e.getSource();
        
        switch ( comp.getText() ) {
            
            case "Save":
                
                newPanel = frame.getPanel();
                ArticlePanel articlePanel = (ArticlePanel) newPanel;
                
                String author = articlePanel.getEnteredAuthor();
                String journal = articlePanel.getEnteredJournal();
                String pages = articlePanel.getEnteredPages();
                String title = articlePanel.getEnteredTitle();
                String volume = articlePanel.getEnteredVolume();
                String year = articlePanel.getEnteredYear();
                
                if ( !Utilities.isValidWord( author ) ) {
                    PopupFrame.showMessage( "The entered 'author' is invalid."
                                    + " Please re-enter." );
                } else if ( !Utilities.isValidWord( title ) ) {
                    PopupFrame.showMessage( "The entered 'title' is invalid."
                                    + " Please re-enter." );
                } else if ( !Utilities.isValidYear( year ) ) {
                    PopupFrame.showMessage( "The entered 'year' is invalid."
                                    + " Please re-enter." );
                } else {
                    
                    // add the Article (if applicable)
                    if ( articlePanel.getCurrentFunction() == ADD ) {
                        biblioControl.addPublication( new Article( author,
                                        title, journal, volume, year, pages ) );
                    // edit the Article (if applicable)
                    } else if ( articlePanel.getCurrentFunction() == EDIT ) {
                        newPanel = (MainPanel) frame.getPanel( MAIN_PANEL );
                        biblioControl.editPublication( ( (MainPanel) newPanel )
                                        .getList().getSelectedIndex(),
                                        new Article( author, title, journal,
                                                        volume, year, pages ) );
                    }

                    articlePanel.clearTextFields(); // clear the text fields
                    returnHome(); // return to MainPanel
                    
                }
                
                break;
                
            case "Cancel":
                
                returnHome(); // return to MainPanel
                break;
                
            default:
                
                // do nothing
                
        }
        
    }
    
    /**
     * handleBookPanelAddEvents - deal with ADD events from BookPanel.
     * 
     * @param event the AWTEvent
     */
    private void handleBookPanelAddEvents( AWTEvent event ) {
        
        MainFrame frame = MainFrame.getInstance();
        JPanel newPanel;
        
        ActionEvent e = (ActionEvent) event;
        JButton comp = (JButton) e.getSource();
        
        switch ( comp.getText() ) {
            
            case "Save":
                
                newPanel = frame.getPanel();
                BookPanel bookPanel = (BookPanel) newPanel;
                
                String author = bookPanel.getEnteredAuthor();
                String city = bookPanel.getEnteredCity();
                String publisher = bookPanel.getEnteredPublisher();
                String title = bookPanel.getEnteredTitle();
                String year = bookPanel.getEnteredYear();
                
                if ( !Utilities.isValidWord( author ) ) {
                    PopupFrame.showMessage( "The entered 'author' is invalid."
                                    + " Please re-enter." );
                } else if ( !Utilities.isValidWord( title ) ) {
                    PopupFrame.showMessage( "The entered 'title' is invalid."
                                    + " Please re-enter." );
                } else if ( !Utilities.isValidYear( year ) ) {
                    PopupFrame.showMessage( "The entered 'year' is invalid."
                                    + " Please re-enter." );
                } else {
                    
                    // add the Book (if applicable)
                    if ( bookPanel.getCurrentFunction() == ADD ) {
                        biblioControl.addPublication( new Book( author, title,
                                        city, publisher, year ) );
                    // edit the Book (if applicable)
                    } else if ( bookPanel.getCurrentFunction() == EDIT ) {
                        newPanel = (MainPanel) frame.getPanel( MAIN_PANEL );
                        biblioControl.editPublication( ( (MainPanel) newPanel )
                                        .getList().getSelectedIndex(),
                                        new Book( author, title, city,
                                                        publisher, year ) );
                    }

                    bookPanel.clearTextFields(); // clear the text fields
                    returnHome(); // return to MainPanel
                    
                }
                
                break;
                
            case "Cancel":
                
                returnHome(); // return to MainPanel
                break;
                
            default:
                
                // do nothing
                
        }
        
    }
    
    /**
     * handleDeletePopupEvents - handle events the delete confirmation Popup.
     *
     * @param choice - which button was pressed
     */
    private void handleDeletePopupEvents( int choice ) {
        
        MainFrame frame = MainFrame.getInstance();
        MainPanel mainPanel = (MainPanel) frame.getPanel( MAIN_PANEL );
        
        switch ( choice ) {
            
            case POPUP_DELETE_OK:
                
                int which = mainPanel.getList().getSelectedIndex();
                biblioControl.deletePublication( which );
                biblioControl.updateJList();
                break;
                
            default:
                
                returnHome(); // return to MainPanel
                
        }
        
    }

    /**
     * handleMainPanelEvents - deal with events from MainPanel (the window with
     * the Bibliography and the choice buttons).
     *
     * *DDM* - added functionality 
     *       - changed name ( was handleMainButtonEvents )
     *
     * @param event (AWTEvent)
     */
    private void handleMainPanelEvents( AWTEvent event ) {

        MainFrame frame = MainFrame.getInstance();

        JPanel newPanel = frame.getPanel( MAIN_PANEL );

        ActionEvent e = (ActionEvent) event;
        JButton comp = (JButton) e.getSource();

        switch ( comp.getText() ) {
            
            case "Add":
                
                // change to Adding
                changeAddEdit( ADD );
                
                // switch to AddPanel
                newPanel = frame.getPanel( ADD_PANEL );
                frame.setTitle( ADD_PUBLICATION_TITLE );
                frame.setPanel( newPanel );
                
                break;
                
            case "Edit":
                
                Publication pubToEdit = ( (MainPanel) newPanel )
                                .getList().getSelectedValue();

                // change to Editing
                changeAddEdit( EDIT );
                
                // switch to the correct PubPanel
                if ( pubToEdit != null && pubToEdit instanceof Article ) {
                    newPanel = frame.getPanel( ARTICLE_PANEL );
                    frame.setTitle( EDIT_ARTICLE_TITLE );
                    frame.setPanel( newPanel );
                    ( (ArticlePanel) newPanel )
                                    .setTextFields( (Article) pubToEdit );
                } else if ( pubToEdit != null && pubToEdit instanceof Book ) {
                    newPanel = frame.getPanel( BOOK_PANEL );
                    frame.setTitle( EDIT_BOOK_TITLE );
                    frame.setPanel( newPanel );
                    ( (BookPanel) newPanel ).setTextFields( (Book) pubToEdit );
                }
                
                break;
                
            case "Delete":
                
                int choice = -1;
                Publication pubToDel = ( (MainPanel) newPanel )
                    .getList().getSelectedValue();
                
                if ( pubToDel != null && pubToDel instanceof Article ) {
                    choice = PopupFrame.showDeleteConfirmation( pubToDel,
                                    DELETE_ARTICLE_TITLE );
                } else if ( pubToDel != null && pubToDel instanceof Book ) {
                    choice = PopupFrame.showDeleteConfirmation( pubToDel,
                                    DELETE_BOOK_TITLE );
                }
                
                handleDeletePopupEvents( choice );
                
                break;
                
            case "Search":
                
                newPanel = frame.getPanel( SEARCH_PANEL );
                frame.setTitle( SEARCH_PUBLICATIONS_TITLE );
                frame.setPanel( newPanel );
                break;
                
            default:
                closeApplication();

        } // end switch

    } // method handleChoiceEvents
    
    /**
     * handleSearchPanelEvents - deal with events from SearchPanel.
     * 
     * *DDM* - finished functionality
     *
     * @param event (AWTEvent)
     */
    private void handleSearchPanelEvents( AWTEvent event ) {
        
        // get the listModel
        MainFrame frame = MainFrame.getInstance();
        SearchPanel searchPanel = (SearchPanel) frame.getPanel( SEARCH );
        JList< Publication > biblioList = searchPanel.getList();
        DefaultListModel< Publication > listModel;
        listModel = (DefaultListModel< Publication >) biblioList
                        .getModel();
        
        // cast the event to a JButton
        ActionEvent e = (ActionEvent) event;
        JButton comp = (JButton) e.getSource();
        
        // switch
        switch ( comp.getText() ) {
            
            case "Search For...":
                
                String authorSearch = searchPanel.getEnteredAuthor()
                                .toLowerCase().trim();
                String titleSearch = searchPanel.getEnteredTitle().toLowerCase()
                                .trim();

                // determine if its author or title, then search
                if ( Utilities.isValidWord( authorSearch )
                                && !Utilities.isValidWord( titleSearch ) ) {
                    biblioControl.searchPublications( authorSearch, AUTHOR );
                } else if ( Utilities.isValidWord( titleSearch )
                                && !Utilities.isValidWord( authorSearch ) ) {
                    biblioControl.searchPublications( titleSearch, TITLE );
                } else {
                    PopupFrame.showMessage(
                                    "Invalid search. Please try again." );
                }
                
                // clear the text fields
                searchPanel.clearTextFields();
                // reenable everything
                searchPanel.enableTextFields();

                break;
                
            case "Clear Search":
                
                // clear the text fields
                searchPanel.clearTextFields();
                // reenable everything
                searchPanel.enableTextFields();
                // clear the search results
                biblioControl.clearSearchResults();
                // clear the JList
                listModel.clear();
                
                break;
                
            case "Return":
                
                // clear the text fields
                searchPanel.clearTextFields();
                // reenable the text fields
                searchPanel.enableTextFields();
                // clear the search results only if the JList is empty
                // (clean slate)
                if ( listModel.size() == 0 ) {
                    biblioControl.clearSearchResults();
                }
                // return home
                returnHome();
                break;
                
            default:
                
                // do nothing
                
        } // end switch
        
    }
    
    /**
     * returnHome - returns back to MainPanel.
     */
    private void returnHome() {
        
        MainFrame frame = MainFrame.getInstance();
        
        MainPanel home = (MainPanel) frame.getPanel( MAIN_PANEL );
        frame.setPanel( home );
        frame.setTitle( TITLE_STRING );
        
    }
    
    // ********************* Unused Interface Methods ***********************

    

    /**
     * Unused Method.
     * 
     * @param e Event Object
     */
    @Override
    public void focusLost( FocusEvent e ) {

        // unused
        
    }
    
    /**
     * Unused Method.
     * 
     * @param e Event Object
     */
    public void windowActivated( WindowEvent e ) {

        // unused

    }

    /**
     * Unused Method.
     * 
     * @param e Event Object
     */
    public void windowClosed( WindowEvent e ) {

        // unused

    }

    /**
     * Unused Method.
     * 
     * @param e Event Object
     */
    public void windowDeactivated( WindowEvent e ) {

        // unused

    }

    /**
     * Unused Method.
     * 
     * @param e Event Object
     */
    public void windowDeiconified( WindowEvent e ) {

        // unused

    }

    /**
     * Unused Method.
     * 
     * @param e Event Object
     */
    public void windowIconified( WindowEvent e ) {

        // unused
    }

    
    /************************* Singleton method *****************************/

    
    /**
     * Create Singleton for BiblioListener.
     * 
     * @return the one and only BiblioListener
     */
    public static BiblioListener getInstance() {

        if ( listener == null ) {
            listener = new BiblioListener();
        }

        return listener;
    }

} // class BiblioListener
