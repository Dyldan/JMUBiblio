package controller;

import java.io.IOException;

import model.Bibliography;
import model.Publication;
import utilities.BiblioFinals;

/**
 * This class handles the search functionality for JMUBiblio.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's PA03 Solution, Starter Kit
 *                      
 * Modifications: 12/06/2020 *DDM* (PA4) - Removed console functionality
 *                                       - Adjusted so it works with GUI
 * 
 * @author Michael Norton && Dylan Moreno
 * @version PA04 (Dec 09 2020), PA03 (Nov 1 2020)
 */
public class BiblioSearch implements BiblioFinals {

    private Bibliography bibliography;

    /**
     * Explicit value constructor.
     * 
     * @param bib The Bibliography object
     */
    public BiblioSearch( Bibliography bib ) {

        bibliography = bib;

    } // explicit value constructor
    
    
    // ********************* Public Methods ***********************
    

    /**
     * Search the Bibliography for Publications matching the user-specified
     * criteria.
     * 
     * *DDM* - completely overhauled to work with the GUI
     * 
     * @param type The AUTHOR or TITLE
     * @param searchTerm the incoming search term
     * @return a Bibliography containing the result of the search
     * @throws IOException The IOException
     */
    public Bibliography search( int type, String searchTerm )
                    throws IOException {

        Bibliography newResultSet; // the new search results
        Bibliography resultSet = null; // the search results to return

        if ( bibliography == null ) {
            resultSet = getResultSet( type, searchTerm );
        } else {
            newResultSet = getResultSet( type, searchTerm );
            resultSet = bibliography.intersection( newResultSet );
        }

        return resultSet;

    } // method search()
    
    // ********************* Private Methods ***********************

    /**
     * Return true if the String check contains term.
     * 
     * @param check The String to check
     * @param term The substring we're looking for
     * @return true if the String check contains term
     */
    private boolean contains( String check, String term ) {

        return check.toLowerCase().indexOf( term.toLowerCase() ) > -1;

    } // method startsWith

    /**
     * Return true if the String check ends with term.
     * 
     * @param check The String to check
     * @param term The substring we're looking for
     * @return true if the String check ends with term
     */
    private boolean endsWith( String check, String term ) {

        return check.toLowerCase().endsWith( term.toLowerCase() );

    } // method endsWith

    /**
     * Get the expression inside of a "NOT" expression - tbe meaning of this
     * will be inverted.
     * 
     * @param expression The express inside a NOT expression
     * @return the stripped expression
     */
    private String getNotExpression( String expression ) {

        String toCheck = null;

        // check to make sure this is well formed first
        if ( isLegalNotExpression( expression ) ) {
            toCheck = expression.substring( 2, expression.length() - 1 );
        }

        return toCheck;

    } // method invertCheck

    /**
     * Return the operation to be performed.
     * 
     * @param term The expression to check
     * @return the operation to be performed (as a char)
     */
    private char getOperation( String term ) {

        char operation = (char) 0;
        String operations = "" + STARTS_WITH + ENDS_WITH + NOT;

        if ( term != null && term.trim().length() > 0 ) {
            if ( operations.indexOf( term.charAt( 0 ) ) > -1 ) {
                operation = term.charAt( 0 );
            } else {
                operation = CONTAINS;
            }
        }

        return operation;

    } // method getOperation

    /**
     * Return a Bibliography containing the selected Publications.
     * 
     * @param type The AUTHOR or TITLE
     * @param term The term we're searching for
     * 
     * @return an Publication (set) containing the selected Publications
     */
    private Bibliography getResultSet( int type, String term ) {

        Bibliography resultSet = new Bibliography();

        for ( int i = 0; i < bibliography.size(); i++ ) {

            Publication file = bibliography.get( i );
            String check = null;

            if ( file != null ) {

                switch ( type ) {
                    case AUTHOR:
                        check = file.getAuthor();
                        break;
                    case TITLE:
                        check = file.getTitle();
                        break;

                } // end switch
                
            }

            check = check.trim(); // trim excess space

            if ( includeItem( check, term ) ) {
                resultSet.add( file );
            }

        } // end for

        return resultSet;

    } // method getResultSet

    /**
     * Determine if an item should be included in the search list.
     * 
     * @param check The word or expression to search
     * @param term The String we are looking for
     * 
     * @return true if it is found
     */
    private boolean includeItem( String check, String term ) {

        boolean contains = false;

        if ( check != null ) {
            if ( term != null & term.length() > 0 ) {
                switch ( getOperation( term ) ) {
                    case STARTS_WITH:
                        contains = startsWith( check, term.substring( 1 ) );
                        break;
                    case ENDS_WITH:
                        contains = endsWith( check, term.substring( 1 ) );
                        break;
                    case CONTAINS:
                        contains = contains( check, term );
                        break;
                    case NOT:
                        String newTerm = getNotExpression( term );
                        if ( newTerm != null ) {
                            contains = !includeItem( check, newTerm );
                        }

                } // end switch

            } // end if
            
        } // end if

        return contains;

    } // method includeItem

    /**
     * Is this a legal "Not" expression?
     * 
     * @param check The String to check
     * @return true if this is a legal expression
     */
    private boolean isLegalNotExpression( String check ) {

        boolean isLegal = false;

        if ( check != null && check.length() > 2 ) {
            if ( check.charAt( 0 ) == '!' ) {
                if ( check.charAt( 1 ) == '(' ) {
                    if ( check.charAt( check.length() - 1 ) == ')' ) {
                        isLegal = true;
                    }
                }
            }
        }

        return isLegal;

    } // method isLegalNotExpression

    /**
     * Return true if the String check starts with term.
     * 
     * @param check The String to check
     * @param term The substring we're looking for
     * 
     * @return true if the String check starts with term
     */
    private boolean startsWith( String check, String term ) {

        return check.toLowerCase().startsWith( term.toLowerCase() );

    } // method startsWith

} // class BiblioSearch
