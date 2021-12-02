package utilities;

/**
 * Utilities - collection of useful static methods moved from BiblioControl.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's PA3 Solution, Starter Kit
 *                      
 * Modifications: 10/14/2020 - New for PA2
 *                12/06/2020 *DDM* (PA4) - Removed antiquated functions
 *                                       - Updated isValidWord()
 * 
 * @author Michael Norton && Dylan Moreno
 * @version PA4 (Dec 06 2020), PA02 (Oct 14 2020)
 */
public class Utilities implements BiblioFinals {

    /**
     * Test to see if target is a valid int between lower and upper.
     * 
     * **MLN (PA2) -- Added for PA2
     * 
     * @param target the value to check
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @return true if valid
     */
    public static boolean isValidInt( String target, int lowerBound,
                    int upperBound ) {

        boolean isValid = true;
        int choice = 0;

        try {
            choice = Integer.parseInt( target );

            if ( choice < lowerBound || choice > upperBound ) {
                throw new NumberFormatException();
            }
        } catch ( NumberFormatException e ) {

            isValid = false;
        }

        return isValid;
    }

    /**
     * Is this a valid year?
     * 
     * @param str the year to test
     * @return true if valid, false otherwise
     */
    public static boolean isValidYear( String str ) {

        boolean valid = true;
        int year = 0;

        try {

            year = Integer.parseInt( str );
            valid = year == 0 || ( year >= 1450 && year <= CURRENT_YEAR + 1 );

        } catch ( NumberFormatException e ) {

            valid = false;
        }

        return valid;
    }

    /**
     * Is this a valid word?
     * 
     * *DDM* added check if word is all spaces
     * 
     * @param word The word to test
     * @return true if not null and has length > 0.
     */
    public static boolean isValidWord( String word ) {

        return word != null && word.trim().length() > 0;
    }

//    /**
//     * Validate the year given the string incoming.
//     * 
//     * **MLN (PA2) -- Modified for PA2 - add function add or edit
//     * 
//     * @param function Add or Edit
//     * @param str the string to convert and test
//     * @return the valid year
//     * @throws IOException the IOException
//     */
//    public static int validate( int function, String str ) throws IOException
// {
//
//        BiblioUserIO io = new BiblioUserIO();
//
//        String input = str;
//        String prompt = function == ADD ? String.format( "%-11s", "Year:" )
//                        : "  NewValue - > ";
//
//        if ( input.trim().length() == 0 ) {
//            input = "0";
//        }
//
//        while ( !Utilities.isValidYear( input ) ) {
//
//            io.display( NL );
//            io.display( "Must be a year between 1450 and "
//                            + ( CURRENT_YEAR + 1 ) + ". Please re-enter."
//                            + NL );
//            io.display( prompt );
//            input = io.getInput();
//            io.display( NL );
//        }
//
//        return Integer.parseInt( input );
//    }

//    /**
//     * Ensure string has length > 0.
//     * 
//     * @param prompt the prompt to print
//     * @param str the string to test
//     * 
//     * @return the validated string
//     * @throws IOException the IOException
//     */
//    public static String validate( String prompt, String str )
//                    throws IOException {
//
//        BiblioUserIO io = new BiblioUserIO();
//
//        String input = str;
//
//        while ( input.trim().length() == 0 ) {
//
//            io.display( NL );
//            io.display( "Required entry. Please re-enter." + NL );
//            io.display( String.format( "%-11s", prompt ) );
//
//            input = io.getInput();
//            io.display( NL );
//        }
//
//        return input.trim();
//    }

}
