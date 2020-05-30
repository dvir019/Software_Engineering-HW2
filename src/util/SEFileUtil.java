package util;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The <code>csv.SEFileUtil</code> class represents a file and allows access to its contents a line at
 * a time, using the method <code>lines</code>, or a word at a time, using the method
 * <code>words</code>. These strings can then be iterated over using a <code>for</code> loop.
 *
 * <P>
 * Example usage:
 *
 * <PRE>
 * csv.SEFileUtil se = new csv.SEFileUtil();
 * for (String s : se.words()) {
 *     // print or process s
 * }
 * </PRE>
 *
 * <P>
 * If each line of the file represents separated data values, because its a CSV file, then the user
 * can get a <code>getCSVParser</code> object to access that data more directly, using one of the
 * <code>getCSVParser</code> methods.
 *
 * <P>
 * Example CSV usage:
 *
 * <PRE>
 * csv.SEFileUtil se = new csv.SEFileUtil("food.csv");
 * for (CSVRecord record : se.getCSVParser()) {
 *     // print or process fields in record
 *     String name = record.get("Name");
 *     // other processing
 * }
 * </PRE>
 *
 * <P>
 */
public class SEFileUtil {

    private String myPath;
    private String mySource;


    /**
     * Create a <code>SEFileUtile</code> object that opens a file represented by the File object
     * passed as a parameter.
     *
     * Useful, for example, when used in conjunction with the <code>DirectoryResource</code> class.
     *
     * @param file the file to be represented by this resource
     */
    public SEFileUtil (File file) {
        initRead(file);
    }

    /**
     * Create a <code>SEFileUtile</code> object that opens a file whose name is passed as a
     * parameter.
     *
     * The named file should be on the current class path to be found.
     *
     * @param filename the name of the file to be opened
     */
    public SEFileUtil (String filename) {
        initRead(filename);
    }



    /**
     * Return entire contents of this opened file as one string.
     *
     * @return a <code>String</code> that is the contents of the open file
     */
    public String asString () {
        return mySource;
    }

    /**
     * Returns a <code>CSVParser</code> object to access the contents of an open file, possibly
     * without a header row.
     *
     * Each line of the file should be formatted as data separated by commas and with/without a
     * header row to describe the column names.
     *
     * @return a <code>CSVParser</code> that can provide access to the records in the file one at a
     *         time
     */
    public CSVParser getCSVParser () {
        return getCSVParser(false, ",");
    }

    /**
     * Returns a <code>CSVParser</code> object to access the contents of an open file, possibly
     * without a header row and a different data delimiter than a comma.
     *
     * Each line of the file should be formatted as data separated by the delimiter passed as a
     * parameter and with/without a header row to describe the column names. This is useful if the
     * data is separated by some character other than a comma.
     *
     * @param withHeader uses first row of data as a header row only if true
     * @param delimiter a single character that separates one field of data from another
     * @return a <code>CSVParser</code> that can provide access to the records in the file one at a
     *         time
     */
    public CSVParser getCSVParser (boolean withHeader, String delimiter) {
        if (delimiter == null || delimiter.length() != 1) {
            System.out.println("SEFileUtile: CSV delimiter must be a single character: " + delimiter);
            System.exit(-1);
        }
        try {
            char delim = delimiter.charAt(0);
            Reader input = new StringReader(mySource);
            if (withHeader) {
                return new CSVParser(input, CSVFormat.EXCEL.withHeader().withDelimiter(delim));
            }
            else {
                return new CSVParser(input, CSVFormat.EXCEL.withDelimiter(delim));
            }
        }
        catch (Exception e) {
            System.out.println("SEFileUtile: cannot read " + myPath + " as a CSV file.");
            System.exit(-1);
        }
        return null;
    }


    // Create from a given File
    private void initRead (File f) {
        try {
            initRead(f.getCanonicalPath());
        }
        catch (Exception e) {
            System.out.println("SEFileUtil: cannot access " + f);
            System.exit(-1);
        }
    }

    // Create from the name of a File
    private void initRead (String fname) {
        try {
            myPath = fname;
            InputStream is = getClass().getClassLoader().getResourceAsStream(fname);
            if (is == null) {
                is = new FileInputStream(fname);
            }
            mySource = initFromStream(is);
        }
        catch (Exception e) {
            System.out.println("SEFileUtil: cannot access " + fname);
            System.exit(-1);
        }
    }

    // store data (keep in sync with URLResource)
    private String initFromStream (InputStream stream) throws Exception {
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuilder contents = new StringBuilder();
            String line = null;
            while ((line = buff.readLine()) != null) {
                contents.append(line + "\n");
            }
            return contents.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        finally {
            try {
                if (buff != null) {
                    buff.close();
                }
            }
            catch (Exception e) {
                // should never happen
            }
        }
        return null;
    }
}

