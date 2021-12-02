package model;
/**
 * Article extends Publication.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's PA03 Solution
 * 
 * Modifications: 11/1/2020) - New for PA3 
 *                              Subclass of Publication - handle
 *                                unique aspects for articles
 *    *DDM* (PA4) 12/06/2020 - Removed character limit on toString()
 * 
 * @author Michael Norton && Dylan Moreno
 * @version PA04 (Dec 09 2020), PA03 (Nov 1 2020)
 */
public class Article extends Publication {

    private String journal;
    private String pages;
    private String volume;

    /**
     * Explicit value constructor.
     * 
     * @param author The author
     * @param title The title
     * @param journal The journal
     * @param volume The volume
     * @param pages The page range
     * @param year The year
     */
    public Article( String author, String title, String journal, String volume,
                    int year, String pages ) {

        super( author, title, year );
        setJournal( journal );
        setVolume( volume );
        setPages( pages );

    }

    /**
     * Explicit value constructor (overloaded).
     * 
     * @param author The author
     * @param title The title
     * @param journal The journal
     * @param volume The volume
     * @param pages The page range
     * @param year The year
     */
    public Article( String author, String title, String journal, String volume,
                    String year, String pages ) {

        super( author, title, year );
        setJournal( journal );
        setVolume( volume );
        setPages( pages );

    }

    /**
     * Copy constructor.
     * 
     * **MLN (PA2) -- Added for PA2
     * 
     * @param other the other publication to copy
     */
    public Article( Publication other ) {

        super( other );
        
        Article newArticle = (Article) other;
        
        journal = new String( newArticle.getJournal() );
        volume = new String( newArticle.getVolume() );
        pages = new String( newArticle.getPages() );
    }

    /**
     * Is this Publication equal to the Publication object in the parameter.
     * 
     * @param incoming - the object to compare
     * @return true if the contents are the same
     */
    public boolean equals( Object incoming ) {

        boolean areEqual = false;

        if ( incoming instanceof Article ) {
            Article compPub = (Article) incoming;

            if ( super.equals( incoming )
                            && journal.equals( compPub.getJournal() )
                            && volume.equals( compPub.getVolume() )
                            && pages.equals( compPub.getPages() ) ) {

                areEqual = true;
            }
        }

        return areEqual;
    }

    /**
     * Return the journal for the article. This attribute is optional.
     * 
     * @return the journal for the article
     */
    public String getJournal() {

        return journal;
    }

    /**
     * Return the pages for the article. This attribute is optional.
     * 
     * @return the pages for the article
     */
    public String getPages() {

        return pages;
    }
    
    /**
     * Return the volume for the article. This attribute is optional.
     * 
     * @return the volume for the article.
     */
    public String getVolume() {
        
        return volume;
    }

    /**
     * Set the value of the journal. If the incoming parameter is null, set it 
     * to an empty string.
     * 
     * @param journal the journal for the article
     */
    public void setJournal( String journal ) {

        this.journal = journal;

        if ( this.journal == null ) {
            this.journal = "";
        }

    }

    /**
     * Set the value of the pages. If the incoming parameter is null, set it
     * to an empty string.
     * 
     * @param pages the pages for the article
     */
    public void setPages( String pages ) {

        this.pages = pages;

        if ( this.pages == null ) {
            this.pages = "";
        }
    }

    /**
     * Set the value of the volume. If the incoming parameter is null, set it
     * to an empty string.
     * 
     * @param volume the volume for the article
     */
    public void setVolume( String volume ) {

        this.volume = volume;

        if ( this.volume == null ) {
            this.volume = "";
        }
    }

    /**
     * Return the formatted author title for the book.
     * 
     * *DDM* - removed character limit
     * 
     * @return the formatted string for author, title
     */
    public String toString() {

        String displayString = getAuthor() + ", \"" + getTitle() + "\"";

        String bibYear = getYear() != 0 ? "" + getYear() : "N/A";

        displayString += " (" + bibYear + ")";

        return displayString;
    }

}
