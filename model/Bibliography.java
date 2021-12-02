package model;
import java.util.ArrayList;

/**
 * Collection class to store the individual Publication objects.
 * 
 * Modfications: **MLN PA02  - Changed bibliography from array to ArrayList that
 *                             functions as a set 
 *                           - Added copy constructor 
 *                           - Added contains method 
 *                           - Added set methods (intersection, difference, 
 *                             union, subset) 
 *                           - Replaced deleteLast with delete to delete a 
 *                             specific publication 
 *                           - Modified get to get from ArrayList
 *                           - Modified add method to add in alpha order and to 
 *                             disallow duplicates 
 *                           - Added private methods to support add in alpha 
 *                             order
 *              (11/1/2020)  - Fixed subset method.
 *                           - Adjusted copy constructor to handle Articles/
 *                             Books
 *                           - Modified to work as Singleton
 * 
 * @author Michael L. Norton
 * @version PA03 (Nov 1 2020), PA02 (Oct 8, 2020), PA01 (Sep 10 2020)
 */
public class Bibliography {

    // **MLN added for PA3 (Singleton Pattern)
    private static Bibliography collection;   
        
    private ArrayList< Publication > bibliography;
    
    /**
     * Default constructor - instantiate array and initialize.
     * 
     * **MLN (PA3) - made private to accommodate Singleton pattern
     * 
     * Modification: Change to ArrayList, remove 'last'
     */
    public Bibliography() {
        
        bibliography = new ArrayList< Publication>();

    }


    /**
     * Copy constructor.
     * 
     * **MLN (PA2) -- new for PA2
     * 
     * @param other the Bibliography to copy
     */
    public Bibliography( Bibliography other ) {
        
        bibliography = new ArrayList< Publication >();

        if ( other != null ) {
            for ( int i = 0; i < other.size(); i++ ) {

                Publication pub;

                pub = other.get( i ) instanceof Book
                                ? new Book( other.get( i ) )
                                : new Article( other.get( i ) );

                bibliography.add( pub );
            }
        }
    }
    
    
    
    /**
     * Add a publication to the collection. If successful, then increment
     * "last". If array is full, then expand array (private expand method).
     * 
     * Modification: Add in alpha order to ArrayList
     * 
     * @param pub the Publication object to add
     * @return true if the addition was successful (Publication.canAdd() returns
     *         true.
     */
    public boolean add( Publication pub ) {

        boolean success = false;
        
        if ( pub != null ) {
            if ( pub.canAdd() ) {
                if ( !bibliography.contains( pub ) ) {
                    if ( bibliography.size() == 0 || isLast( pub ) ) {
                        append( pub );
                    } else { 
                        insert( pub );
                    }
                }
                success = true;

            }
        }
        
        return success;
    }

    /**
     * Does this bibliography contain the publication? Passthrough of ArrayList
     * method.
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param pub The Publication to check
     * @return true if this publication is contained in this bibliography
     */
    public boolean contains( Publication pub ) {
        
        return bibliography.contains( pub );
    }
    
    /**
     * Remove the Publication specified by the parameter.
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param which Which item to delete.
     * @return true if a valid index
     */
    public boolean delete( int which ) {
        
        boolean success = false; // assume this will not work
        
        if ( isInBounds( which ) ) {
            
            bibliography.remove( which );
            success = true;
            
        } // end if
        
        return success;
    }
    
    /**
     * Return the difference between this set and the other set (A - B).
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param other The other set
     * @return the difference between the sets
     */
    public Bibliography difference( Bibliography other ) {

        Bibliography difference;
        
        if ( other != null ) {
            difference = new Bibliography();
            for ( Publication file : bibliography ) {
                if ( !other.contains( file ) ) {
                    difference.add( file );
                }
            }
        } else {
            // if other is null, then difference should equal the current set.
            // We can use the copy constructor for this.
            difference = new Bibliography( this );
        }

        return difference;

    }
    
    /**
     * Retrieve the Publication object at the specified position. If position is
     * out of bounds or if position is not populated, return null.
     * 
     * **MLN (PA2) - modified for PA2 to use ArrayList
     * 
     * @param which - the position from which to get
     * @return the
     */
    public Publication get( int which ) {

        Publication pubToReturn = null;
        
        if ( isInBounds( which ) ) {
            
            pubToReturn = bibliography.get( which );
        }
        
        return pubToReturn;
    }

    /**
     * Return the intersection of this set and the other set.
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param other The other set
     * @return the intersection of the sets
     */
    public Bibliography intersection( Bibliography other ) {

        Bibliography intersection = new Bibliography();

        if ( other != null ) {

            for ( Publication file : bibliography ) {
                if ( other.contains( file ) ) {
                    intersection.add( file );
                }
            }
        } else {
            
            intersection = null;
        }

        return intersection;
    }
    
    /**
     * Return the size of the collection (how many items does it hold). 
     * 
     * **MLN (PA2) - Modified for PA2 to return size of ArrayList
     * 
     * @return the value of "last".
     */
    public int size() {

        return bibliography.size();
        
    }

    /**
     * Is other a subset of this?
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param other the other bibliography
     * @return true if other is a subset of this
     */
    public boolean subset( Bibliography other ) {

        boolean isSubset = true;

        // check only if other is not null & size of other is less than or
        // equal to the current set.
        if ( other != null && other.size() <= size() ) {
            for ( int i = 0; i < other.size() & isSubset; i++ ) {
                if ( !bibliography.contains( other.get( i ) ) ) {
                    isSubset = false;
                }
            }
        } else if ( other != null ) {
            isSubset = false;
        }
        
        return isSubset;
    }
    
    /**
     * Return the union of the 2 sets.
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param other the other bibliography
     * @return the union of the sets
     */
    public Bibliography union( Bibliography other ) {

        Bibliography union = new Bibliography( this );

        if ( other != null ) {
            for ( int i = 0; i < other.size(); i++ ) {
                union.add( other.get( i ) );
            }
        }

        return union;

    }    
    
    /************************* private methods ****************************/

    /**
     * Append the Publication to the end of the bibliography.
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param file The Publication to append
     */
    private void append( Publication file ) {
        
        if ( file != null ) {
            bibliography.add( file );
        }

    } // method append
    
    
    /**
     * Insert the Publication into its proper place in the bibliography.
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param file The Publication to insert
     */
    private void insert( Publication file ) {
        
        if ( file != null ) {
            int counter = 0;
            String compFile = file.getSortString();

            while ( compFile.compareTo( 
                bibliography.get( counter ).getSortString() ) >= 0 ) {
                counter++;
            }

            bibliography.add( counter, file );

        } // end if
        
    } // method insert
    
    
    /**
     * Returns true if the parameter is in bounds (> 0 and <= last).
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param which file?
     * @return true if it is here
     */
    private boolean isInBounds( int which ) {
        
        return which > -1 && which < size();

    } // method isInBounds
    
    
    /**
     * Checks to see if the Publication should go at the end of the bibliography
     * based on the sort string of the Publication.
     * 
     * **MLN (PA2) - added for PA2
     * 
     * @param file The Publication to check
     * @return true if this Publication should go at the end of the bibliography
     */
    private boolean isLast( Publication file ) {
        
        boolean isLast = false;
        
        if ( file != null ) {
            String compFile = file.getSortString();
            String lastFile = bibliography.get( bibliography.size() - 1 ).
                            getSortString();
        
            if ( compFile.compareTo( lastFile ) >= 0 ) {
                isLast = true;
            }
        
        } // end if
        
        return isLast;

    } // method isLast
    
    /************************ static methods ******************************/
    
    /**
     * Singleton method to ensure a single instance of the Bibliography.
     * 
     * **MLN (PA2) - Added for PA2
     *
     * @return an instance of Library
     */
    public static Bibliography getInstance() {
        
        if ( collection == null ) {
            collection = new Bibliography();
        }
        
        return collection;
    
    } // static method getInstance
    

}
