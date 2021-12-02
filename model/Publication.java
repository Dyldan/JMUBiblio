package model;

import utilities.BiblioFinals;
import utilities.Utilities;

/**
 * A class to store individual publications.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's PA03 Solution
 * 
 * Modifications: 10/6/2020 -  added overloaded constructor to accept year 
 *                             as String
 *                           - added overloaded setYear method to accept year
 *                             as String
 *                           - added getSortString method to set basis for
 *                             sorting in Bibliography
 *                11/11/2020 - Made abstract and removed Book/Article specific
 *                             attributes and methods
 *    *DDM* (PA4) 12/06/2020 - Changed author/title validation methods
 * 
 * @author Michael Norton && Dylan Moreno
 * @version PA04 (Dec 09 2020), PA03 (Nov 1 2020), PA02 (Oct 6 2020),
 *          PA01 (Sep 10 2020)
 */
public abstract class Publication implements BiblioFinals {

    private int year;

    private String author;
    private String title;

   /**
     * Explicit Value Constructor.
     * 
     * @param author - the author for the publication
     * @param title - the title of the publication
     * @param year - the year of the publication
     */
    public Publication( String author, String title, int year ) {

        setAuthor( author );
        setTitle( title );
        setYear( year );
    }

    /**
     * Explicit Value Constructor (overloaded).
     * 
     * **MLN (PA2) -- Added for PA2
     * 
     * @param author - the author for the publication
     * @param title - the title of the publication
     * @param year - the year of the publication
     */
    public Publication( String author, String title, String year ) {

        setAuthor( author );
        setTitle( title );
        setYear( year );
    }

    /**
     * Copy constructor.
     * 
     * **MLN (PA2) -- Added for PA2
     * 
     * @param other the other publication to copy
     */
    public Publication( Publication other ) {
        
        author = new String( other.getAuthor() );
        title = new String( other.getTitle() );
        year = other.getYear();
    }
    
    /**
     * Can this publication be added? True if author & title have values and
     * year is valid (0 or between 1450 & current year + 1).
     * 
     * @return true if this item can be added to the bibliography
     */
    public boolean canAdd() {

        return isValidAuthor() && isValidTitle() && isValidYear();
    }

    /**
     * Is this Publication equal to the Publication object in the parameter.
     * 
     * @param incoming - the object to compare
     * @return true if the contents are the same
     */
    public boolean equals( Object incoming ) {

        boolean areEqual = false;
        Publication compPub = (Publication) incoming;

        if ( author.equals( compPub.getAuthor() )
                        && title.equals( compPub.getTitle() )
                        && year == compPub.getYear() ) {

            areEqual = true;
        }

        return areEqual;
    }

    /**
     * Return the author of the publication. This attribute must exist and have
     * a length > 0.
     * 
     * @return the author of the publication
     */
    public String getAuthor() {

        return author;
    }
    
    /**
     * Get author, title up to 60 characters.
     * 
     * @return the short title
     */
    public String getShortTitle() {
        
        String shortTitle = author + ", " + title;
        
        if ( shortTitle.length() > 60 ) {
            shortTitle = shortTitle.substring( 0,  57 ) + "...";
        }
        
        return shortTitle;        
    }
    
    /**
     * Return the string used to sort publications in the bibliography. Force
     * to lower case to account for e e cummings.
     * 
     * **MLN (PA2) - Added for PA02
     * 
     * @return the sort string
     */
    public String getSortString() {
        
        return ( author + title + year ).toLowerCase();
    }
    
    
    /**
     * Return the title of the publication. This attribute must exist and have a
     * length > 0.
     * 
     * @return the title of the publication
     */
    public String getTitle() {

        return title;
    }

    /**
     * Return the year of the publication. Must be 0 or a year between 1450 and
     * one year after the current year.
     * 
     * @return the year of publication
     */
    public int getYear() {

        return year;
    }

    /**
     * Set the value of the author. If the incoming parameter is invalid (null
     * or length == 0) then return false, else return true.
     * 
     * @param author the author of the publication
     * @return true if valid, false otherwise
     */
    public boolean setAuthor( String author ) {

        boolean success = true;

        this.author = author;

        if ( !isValidAuthor() ) {
            this.author = "";
            success = false;
        }

        return success;
    }

    /**
     * Set the value of the title. If the incoming parameter is invalid (null or
     * length == 0) then return false, else return true.
     * 
     * @param title the title of the publication
     * @return true if valid, false otherwise
     */
    public boolean setTitle( String title ) {

        boolean success = true;

        this.title = title;

        if ( !isValidTitle() ) {
            this.title = "";
            success = false;
        }

        return success;
    }

    /**
     * Set the value of the year. If the incoming parameter is valid (0 or
     * between 1450 and current year + 1, return true, else return false
     * 
     * @param year the year of the publication
     * @return true if valid, false otherwise
     */
    public boolean setYear( int year ) {

        boolean success = true;

        this.year = year;

        if ( !isValidYear() ) {
            success = false;
        }

        return success;
    }
    
    /**
     * Overloaded method to allow the conversion of a String to an int. Needed
     * for loading from file.
     * 
     * **MLN (PA2) - new for PA2
     * 
     * @param year the String version of year
     * @return true if the string can be converted to a valid year
     */
    public boolean setYear( String year ) {
        
        boolean success = false;
        
        try {
            this.year = Integer.parseInt( year );
            success = isValidYear();
            
        } catch ( NumberFormatException e ) {
            
            success = false;
        }
        
        return success;
    }

    /**
     * Return the formatted author title for the book (73 characters max).
     * 
     * @return the formatted string for author, title
     */
    public abstract String toString();

    /****************************** private methods ***************************/

    /**
     * Is the author valid (length > 0)?
     * 
     * *DDM* updated validation method
     * 
     * @return true if valid, false otherwise
     */
    private boolean isValidAuthor() {

        return Utilities.isValidWord( author );
    }

    /**
     * Is the title valid (length > 0)?
     * 
     * *DDM* updated validation method
     * 
     * @return true if valid, false otherwise
     */
    private boolean isValidTitle() {

        return Utilities.isValidWord( title );

    }

    /**
     * Is this a valid year?
     * 
     * @return true if valid, false otherwise
     */
    private boolean isValidYear() {

        return year == 0 || ( year >= 1450 && year <= CURRENT_YEAR + 1 );

    }

}
