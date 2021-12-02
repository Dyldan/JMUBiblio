package model;
/**
 * Book class extends Publication.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's PA03 Solution
 * 
 * Modifications: 11/1/2020) - New for PA3 
 *                           - Subclass of Publication - handle
 *                              unique aspects for books
 *    *DDM* (PA4) 12/06/2020 - Removed character limit on toString()
 * 
 * @author Michael Norton && Dylan Moreno
 * @version PA04 (Dec 09 2020), PA03 (Nov 1 2020)
 */
public class Book extends Publication {

    private String city;
    private String publisher;

    /**
     * Explicit Value Constructor.
     * 
     * @param author The Author
     * @param title The Title
     * @param city The City
     * @param publisher The Publisher
     * @param year The Year
     */
    public Book( String author, String title, String city, String publisher,
                    int year ) {

        super( author, title, year );
        setCity( city );
        setPublisher( publisher );

    }

    /**
     * Explicit Value Constructor  (overloaded).
     * 
     * @param author The Author
     * @param title The Title
     * @param city The City
     * @param publisher The Publisher
     * @param year The Year
     */
    public Book( String author, String title, String city, String publisher,
                    String year ) {

        super( author, title, year );
        setCity( city );
        setPublisher( publisher );

    }

    /**
     * Copy constructor.
     * 
     * **MLN (PA2) -- Added for PA2
     * 
     * @param other the other publication to copy
     */
    public Book( Publication other ) {

        super( other );
        city = new String( ( (Book) other ).getCity() );
        publisher = new String( ( (Book) other ).getPublisher() );
    }

    /**
     * Is this Publication equal to the Publication object in the parameter.
     * 
     * @param incoming - the object to compare
     * @return true if the contents are the same
     */
    public boolean equals( Object incoming ) {

        boolean areEqual = false;

        if ( incoming instanceof Book ) {
            Book compPub = (Book) incoming;

            if ( super.equals( incoming ) && city.equals( compPub.getCity() )
                            && publisher.equals( compPub.getPublisher() ) ) {

                areEqual = true;
            }
        }

        return areEqual;
    }

    /**
     * Return the city of the publication. This attribute is optional.
     * 
     * @return the city of the publication
     */
    public String getCity() {

        return city;
    }

    /**
     * Return the publisher for the publication. This attribute is optional.
     * 
     * @return the publisher for the publication
     */
    public String getPublisher() {

        return publisher;
    }

    /**
     * Set the value of the city. If the incoming parameter is null, set it to
     * an empty string.
     * 
     * @param city the city of publication
     */
    public void setCity( String city ) {

        this.city = city;

        if ( this.city == null ) {
            this.city = "";
        }

    }

    /**
     * Set the value of the publisher. If the incoming parameter is null, set it
     * to an empty string.
     * 
     * @param publisher the publisher for the publication
     */
    public void setPublisher( String publisher ) {

        this.publisher = publisher;

        if ( this.publisher == null ) {
            this.publisher = "";
        }
    }

    /**
     * Return the formatted author title for the book.
     * 
     * *DDM* - added .trim() on city
     *       - removed character limit
     * 
     * @return the formatted string for author, title
     */
    public String toString() {

        String displayString = getAuthor() + ", " + getTitle();

        String bibCity = city.trim().length() > 0 ? city : "N/A";
        String bibYear = getYear() != 0 ? "" + getYear() : "N/A";

        displayString += " (" + bibCity + ", " + bibYear + ")";

        return displayString;
    }

}
