package view.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import utilities.BiblioFinals;
import utilities.Utilities;

/**
 * Class to handle File Input and Output.
 * 
 * Modifications: 10/09/2020 - New for PA2
 * 
 * @author Michael Norton
 * @version PA02 (Oct 9 2020)
 */
public class BiblioFileIO implements BiblioFinals {
    
    private BufferedReader reader;
    private PrintWriter writer;
    private File file;
    
    /**
     * Default Constructor.    
     */
    public BiblioFileIO() {

        file = new File( "bibliography.txt" );
    }
    
    /**
     * Explicit value Constructor.
     * 
     * @param fileName - the name of the file to read/write
     */
    public BiblioFileIO( String fileName ) {
        
        file = new File( "InvalidFileName.err" );
        
        if ( Utilities.isValidWord( fileName ) ) {
            file = new File( fileName );
        }
        
    }
    
    /**
     * Close the reader or writer.
     * 
     * @param which Close READER or WRITER
     * @throws IOException the IOException
     */
    public void close( int which ) throws IOException {
        
        if ( which == READER ) {
            if ( reader != null ) {
                reader.close();
                reader = null;
                
            } // end if
            
        } else if ( writer != null ) {
            writer.close();        
            writer = null;
            
        } // end if

    }
    
    /**
     * Open the reader or writer.
     * 
     * @param which Open READER or WRITER
     * @return true if successful
     * @throws IOException theIOException
     */
    public boolean open( int which ) throws IOException {
        
        boolean canOpen = false;
        
        if ( which == READER ) {
            if ( file.exists() ) {
                canOpen = true;
                reader = new BufferedReader( new FileReader( file ) );
            
            } // end if
            
        } else {
            writer = new PrintWriter( new FileWriter( file ) );
               
            if ( file.canWrite() ) {
                canOpen = true;
            }
                
        } // end else
        
        return canOpen;

    }

    /**
     * Return the string read by the reader.
     * 
     * @return the string read by the reader
     * @throws IOException the IOException
     */
    public String readLine() throws IOException {
        
        String line = null;
        
        if ( reader != null ) {
            line = reader.readLine();
        }
        
        return line;
    }
    
    /**
     * Write a line to the file.
     * 
     * @param line the line to write
     */
    public void write( String line ) {

        if ( Utilities.isValidWord( line ) ) {
            if ( writer != null ) {
                writer.println( line );
            }
        }        
    }    
}
