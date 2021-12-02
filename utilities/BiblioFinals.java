package utilities;

import java.util.Calendar;

/**
 * Finals for the GUI version of the Bibliography program.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 *                   received assistance for this assignment excpet as
 *                   noted below:
 *                   
 *                      Norton's PA3 Solution, Starter Kit
 * 
 * Modifications: **MLN (PA4) - Recreated finals for PA4
 *                12/06/2020 *DDM* (PA4) - Added finals for PA4
 *                                       - Removed antiquated finals
 * 
 * @author Michael Norton && Dylan Moreno
 * @version PA4 (Dec 06 2020)
 */
public interface BiblioFinals {
    
    char STARTS_WITH = '^';
    char ENDS_WITH = '$';
    char CONTAINS = '@';
    char NOT = '!';
    
    int NULL = 0;
    int ADD = 1;
    int EDIT = 2;
    int DELETE = 3;
    int BROWSE = 4;
    int SEARCH = 5;
    int QUIT = 6;
    
    int AUTHOR = 1;
    int TITLE = 2;
    
    int ARTICLE = 1;
    int BOOK = 0;
    
    int INIT_QUIT = 13;
    
    int POPUP_DELETE_OK = 0;
    int POPUP_DELETE_CANCEL = 2;
    int POPUP_DELETE_EXIT = -1;
    
    int MAIN_PANEL = 1;
    int ADD_PANEL = 2;
    int ARTICLE_PANEL = 3;
    int BOOK_PANEL = 4;
    int SEARCH_PANEL = 5;
    
    int READER = 0;
    int WRITER = 1;

    int CURRENT_YEAR = Calendar.getInstance()
                    .get( Calendar.YEAR );
    
    String NL = "\n";

    String TITLE_STRING = "CS159 Bibliography";
    String ADD_PUBLICATION_TITLE = "Add Publication";
    String ADD_ARTICLE_TITLE = "Add Article";
    String ADD_BOOK_TITLE = "Add Book";
    String EDIT_ARTICLE_TITLE = "Edit Article";
    String EDIT_BOOK_TITLE = "Edit Book"; 
    String DELETE_ARTICLE_TITLE = "Delete Article";
    String DELETE_BOOK_TITLE = "Delete Book";
    String SEARCH_PUBLICATIONS_TITLE = "Search Publications";
    


}
