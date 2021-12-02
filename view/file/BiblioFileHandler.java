package view.file;

import java.io.IOException;

import model.Article;
import model.Bibliography;
import model.Book;
import model.Publication;
import utilities.BiblioFinals;

/**
 * FileHandler - Read/Write ASCII File.
 * 
 * Modifications: 10/10/2020 - New for PA2 - moved from BiblioControl
 *                 11/1/2020 - Modified to handle books and articles
 * 
 * 
 * @author Michael Norton
 * @version PA3 (Nov 1 2020), PA2 (Oct 10 2020)
 */
public class BiblioFileHandler implements BiblioFinals {

    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------
    private BiblioFileIO fileIO;
    private Bibliography bibliography;

    /**
     * Default constructor.
     * 
     * @param bib The Bibliography object
     */
    public BiblioFileHandler( Bibliography bib ) {

        fileIO = new BiblioFileIO();
        bibliography = bib;

    } // default constructor

    /**
     * Read the file if it exists and populate the Library.
     * 
     * **MLN (PA3) - Modified to read book or article from text file
     * 
     * @throws IOException the IOException
     */
    public void readFile() throws IOException {

        String line = null;
        String badLines = "";

        // open the bibliography data file if it exists
        if ( fileIO.open( BiblioFileIO.READER ) ) {

            // read first line from the library file
            line = fileIO.readLine();

            while ( line != null ) {

                String[] fields = line.split( "\\|" );
                int bookType = getType( fields[ 0 ] );

                // if a valid book or article
                if ( bookType != -1 ) {

                    Publication pub = null;

                    // skip malformed records
                    if ( bookType == BOOK && fields.length == 6 ) {

                        pub = new Book( fields[ 1 ].trim(), fields[ 2 ].trim(),
                                        fields[ 3 ].trim(), fields[ 4 ].trim(),
                                        fields[ 5 ].trim() );

                    } else if ( bookType == ARTICLE && fields.length == 7 ) {

                        pub = new Article( fields[ 1 ].trim(),
                                        fields[ 2 ].trim(), fields[ 3 ].trim(),
                                        fields[ 4 ].trim(), fields[ 5 ].trim(),
                                        fields[ 6 ].trim() );

                    }
                    // add the publication to the bibliography and get the
                    // next publication if not null & can add.
                    if ( pub != null && pub.canAdd() ) {

                        bibliography.add( pub );
                    } else {
                        badLines += line + NL;
                    }

                } else {
                    badLines += line + NL;
                }

                // get the next publication
                line = fileIO.readLine();

            } // end while

            // close the reader (not necessary but we'll do it anyway)
            fileIO.close( BiblioFileIO.READER );

            writeMalformed( badLines );

        } // end if

    } // method readFiles

    /**
     * write the bibliography to bibliography.txt.
     * 
     * **MLN (PA3) - Modified to write book or article in proper for
     * 
     * @throws IOException the IOException
     */
    public void writeFile() throws IOException {

        String line = null;

        // open the library data file for writing
        fileIO.open( BiblioFileIO.WRITER );

        // read through the library
        for ( int i = 0; i < bibliography.size(); i++ ) {

            // write the library file
            Publication pub = bibliography.get( i );

            line = pub.getAuthor() + "|" + pub.getTitle() + "|";

            if ( pub instanceof Book ) {

                Book book = (Book) pub;

                line = BOOK + "|" + line
                                + ( book.getCity().length() == 0 ? " "
                                                : book.getCity() )
                                + "|"
                                + ( book.getPublisher().length() == 0 ? " "
                                                : book.getPublisher() )
                                + "|" + pub.getYear() + "|";
            } else {

                Article article = (Article) pub;

                line = ARTICLE + "|" + line
                                + ( article.getJournal().length() == 0 ? " "
                                                : article.getJournal() )
                                + "|"
                                + ( article.getVolume().length() == 0 ? " "
                                                : article.getVolume() )
                                + "|" + pub.getYear() + "|" 
                                + ( article.getPages().length() == 0 ? " "
                                                : article.getPages() ) + "|";

            }

            fileIO.write( line );

        } // end for

        // close the writer
        fileIO.close( BiblioFileIO.WRITER );

    } // method writeMalformed

    /**************************** private methods ****************************/

    /**
     * Is this a book or article. -1 if neither
     * 
     * **MLN (PA3) - New for PA3
     * 
     * @param type the first attribute from the txt file
     * @return 0 if book, 1 if article
     */
    private int getType( String type ) {

        int bookOrArticle = -1;

        if ( type.length() == 1 ) {
            if ( "01".indexOf( type ) != -1 ) {
                bookOrArticle = Integer.parseInt( type );
            }
        }

        return bookOrArticle;
    }

    // method writeFiles

    /**
     * Write the malformed lines to the malformed.err file.
     * 
     * @param badLines a String containing the malformed lines
     * @throws IOException the IOException
     */
    private void writeMalformed( String badLines ) throws IOException {

        BiblioFileIO malformed = new BiblioFileIO( "malformed.err" );

        malformed.open( BiblioFileIO.WRITER );

        String[] malArray = badLines.split( "\\R" );

        for ( String line : malArray ) {
            malformed.write( line );
        }

        malformed.close( BiblioFileIO.WRITER );

    }

}
