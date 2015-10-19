
import beans.Text;
import java.io.IOException;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import utility.OutputUtility;

/**
 *
 * @author fabrizioborgia
 */
public class Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Text theText = new Text("images/ADR-1.jpg", Text.VERTICAL);

        OutputUtility.writeMat(theText.getImage(), false);
                
        theText.identifySlices();
        theText.invokeFirstScan();
        
    }

}
