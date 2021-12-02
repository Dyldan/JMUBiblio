package view.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Article;

/**
 * ArticlePanel - The panel for adding/editing an article specifically.
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
public class ArticlePanel extends PubPanel {
    
    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------
    private static final long serialVersionUID = -2544194172607528818L;

    private JLabel journalLabel;
    private JLabel pagesLabel;
    private JLabel volumeLabel;
    
    private JTextField journalTextField;
    private JTextField pagesTextField;
    private JTextField volumeTextField;
    
    /**
     * Default-Constructor.
     */
    public ArticlePanel() {
        
        super();

    }
    
    /**************************** public methods *************************/
    
    /**
     * clearTextFields - clears all the text fields for next time.
     */
    public void clearTextFields() {
        
        super.clearTextFields();
        journalTextField.setText( "" );
        volumeTextField.setText( "" );
        pagesTextField.setText( "" );
        
    }
    
    /**
     * getEnteredJournal - returns the current text contained
     * within journalTextField.
     * 
     * @return the entered journal
     */
    public String getEnteredJournal() {
        
        String enteredJournal = null;
        
        if ( journalTextField.getText() != null ) {
            enteredJournal = journalTextField.getText();
        }
        
        return enteredJournal;
        
    }
    
    /**
     * getEnteredPages - returns the current text contained
     * within pagesTextField.
     * 
     * @return the entered pages
     */
    public String getEnteredPages() {
        
        String enteredPages = null;
        
        if ( pagesTextField.getText() != null ) {
            enteredPages = pagesTextField.getText();
        }
        
        return enteredPages;
        
    }
    
    /**
     * getEnteredVolume - returns the current text contained
     * within volumeTextField.
     * 
     * @return the entered volume
     */
    public String getEnteredVolume() {
        
        String enteredVolume = null;
        
        if ( volumeTextField.getText() != null ) {
            enteredVolume = volumeTextField.getText();
        }
        
        return enteredVolume;
        
    }
    
    /**
     * setTextFields - sets all the text fields to the selected Article's
     * paramaters. Used for editing said Article.
     * 
     * @param article - the Article to edit
     */
    public void setTextFields( Article article ) {

        super.setTextFields( article );
        journalTextField.setText( article.getJournal() );
        volumeTextField.setText( article.getVolume() );
        pagesTextField.setText( article.getPages() );
        
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
        gridPanel.add( journalLabel );
        gridPanel.add( journalTextField );
        gridPanel.add( volumeLabel );
        gridPanel.add( volumeTextField );
        gridPanel.add( super.getYearLabel() );
        gridPanel.add( super.getYearTextField() );
        gridPanel.add( pagesLabel );
        gridPanel.add( pagesTextField );
        
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
        
        journalLabel = new JLabel( "Journal:" );
        pagesLabel = new JLabel( "Pages:" );
        volumeLabel = new JLabel( "Volume:" );
        
        journalTextField = new JTextField();
        pagesTextField = new JTextField();
        volumeTextField = new JTextField();
        
    }

    /**
     * setPanel - set params for panels.
     * 
     * *DDM* - added more
     */
    @Override
    protected void setPanel() {

        super.setPanel();
        
        super.getGridPanel().setLayout( new GridLayout( 6, 2 ) );
        
    }

}
