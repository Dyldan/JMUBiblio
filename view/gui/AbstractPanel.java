package view.gui;

import javax.swing.JPanel;

import utilities.BiblioFinals;

/**
 * AbstractPanel.java - Template for JPanel objects.
 *
 * Acknowledgements: none 
 * Modifications: none
 *
 * @author Michael Norton
 * @version 06/29/2009 (pa04)
 */
public abstract class AbstractPanel extends JPanel implements BiblioFinals {

    private static final long serialVersionUID = -6582845574041478617L;

    /**
     * Default constructor.
     */
    public AbstractPanel() {

        createComponents();
        setParameters();
        setPanel();
        addComponents();
        setListeners();

    } // constructor

    /*************************** abstract methods ************************/

    /**
     * addComponents - add components to panels.
     */
    protected abstract void addComponents();

    /**
     * createComponents - instantiate necessary components (including
     * containers).
     */
    protected abstract void createComponents();

    /**
     * setListeners - set listeners for components on the panel.
     */
    protected abstract void setListeners();

    /**
     * setPanel - set parameters for panels.
     */
    protected abstract void setPanel();

    /**
     * setParmeters - set parameters for components on form.
     */
    protected abstract void setParameters();

} // class AbstractPanel
