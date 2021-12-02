package controller;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import model.Bibliography;
import model.Publication;
import utilities.BiblioFinals;
import view.file.BiblioFileHandler;
import view.gui.MainFrame;
import view.gui.MainPanel;
import view.gui.SearchPanel;

/**
 * BiblioControl - The control backend, used so that BiblioListener
 * isn't so overcrowded. Acts as a bridge between 
 * BiblioListener and Bibliography, 
 * BiblioListener and BiblioSearch, and 
 * BiblioListener and BiblioFileHandler.
 * Also handles some functions with the GUI.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      None
 *                      
 * Modifications: 12/06/2020 *DDM* (PA4) - IMPORTANT: Old BiblioControl
 *                                         was deleted and rebuilt from
 *                                         scratch
 *
 * @author Dylan Moreno
 * @version PA4 (Dec 06 2020)
 */
public class BiblioControl implements BiblioFinals {

    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------
    private static BiblioControl biblioControl;
    
    private BiblioFileHandler handler;
    
    private Bibliography bibliography;
    private Bibliography searchResults;
    
    /**
     * Default Constructor.
     */
    private BiblioControl() {
        
        bibliography = new Bibliography();
        handler = new BiblioFileHandler( bibliography );
        
        searchResults = null;
        
    }
    
    /**************************** public methods ************************/
    
    /**
     * addPublication - sends the incoming Publication to the Bibliography
     * in order to be added.
     * 
     * @param pub - the incoming Publication
     * @return true if the Publication was added successfully
     */
    public boolean addPublication( Publication pub ) {
        
        boolean success = false;
        
        if ( pub != null ) {
            success = bibliography.add( pub );
            updateJList();
        }
        
        return success;
        
    }
    
    /**
     * clearSearchResults - set the current search results to null.
     */
    public void clearSearchResults() {
        
        searchResults = null;
        
    }
    
    /**
     * deletePublication - sends the index of the Publication in the
     * bibliography to Bibliography to be removed.
     * 
     * @param which - which index
     * @return true if the Publication was deleted successfully
     */
    public boolean deletePublication( int which ) {
        
        return bibliography.delete( which );
        
    }
    
    /**
     * editPublication - sends the index of the old Publication to be deleted,
     * and replaces it with the new edited version.
     * 
     * @param which - which index
     * @param pub - the incoming Publication
     * @return true if the Publication was edited successfully
     */
    public boolean editPublication( int which, Publication pub ) {
        
        return deletePublication( which ) && addPublication( pub );
        
    }
    
    /**
     * readFile - read a file's contents to the Bibliography.
     */
    public void readFile() {

        try {
            handler.readFile();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        
        updateJList();
        
    }
    
    /**
     * searchPublications - search the Publications and print the results to the
     * JList in SearchPanel.
     * 
     * @param searchTerm - the search term
     * @param type - which criteria type to search ( AUTHOR or TITLE )
     */
    public void searchPublications( String searchTerm, int type ) {
        
        BiblioSearch biblioSearch;
        
        // get the listModel
        MainFrame frame = MainFrame.getInstance();
        SearchPanel searchPanel = (SearchPanel) frame.getPanel( SEARCH );
        JList< Publication > biblioList = searchPanel.getList();
        DefaultListModel< Publication > listModel;
        listModel = (DefaultListModel< Publication >) biblioList.getModel();
        
        // pass BiblioSearch the real Bibliography only if searchResults is null
        if ( searchResults == null ) {
            biblioSearch = new BiblioSearch( bibliography );
        } else {
            biblioSearch = new BiblioSearch( searchResults );
        }
        
        // search
        try {
            searchResults = biblioSearch.search( type, searchTerm );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        
        // clear the JList
        listModel.clear();
        
        // update the display
        if ( searchResults != null ) {
            
            for ( int i = 0; i < searchResults.size(); i++ ) {
                listModel.addElement( searchResults.get( i ) );
            }
            
        }
        
    }
    
    /**
     * updateJList - syncs the GUI MainFrame's JList with the program's
     * Bibliography. Called whenever a Publication is added, deleted, or edited.
     */
    public void updateJList() {
        
        MainFrame frame = MainFrame.getInstance();
        MainPanel mainPanel = (MainPanel) frame.getPanel( MAIN_PANEL );
        JList< Publication > biblioList = mainPanel.getList();
        DefaultListModel< Publication > listModel;
        listModel = (DefaultListModel< Publication >) biblioList.getModel();
        
        // clear the JList before syncing
        listModel.clear();
        
        // sync the Bibliography with the JList
        for ( int i = 0; i < bibliography.size(); i++ ) {
            listModel.addElement( bibliography.get( i ) );
        }
        
        // enable or disable the main MainPanel buttons
        if ( bibliography.size() == 0 ) {
            mainPanel.disableAllButtons();
        } else {
            mainPanel.disableModifyButtons();
        }
        
    }
    
    /**
     * writeFile - write the Bibliogrpahy contents to a file.
     */
    public void writeFile() {
        
        try {
            handler.writeFile();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        
    }
    
    /**************************** static methods ************************/
    
    /**
     * getInstance - returns this singleton instance.
     * 
     * @return biblioControl
     */
    public static BiblioControl getInstance() {
        
        if ( biblioControl == null ) {
            biblioControl = new BiblioControl();
        }
        
        return biblioControl;
        
    }
    
}
