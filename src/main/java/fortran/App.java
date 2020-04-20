package fortran;

import fortran.tools.FortranParseTreeCreator;
import org.snt.inmemantlr.tree.ParseTree;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        FortranParseTreeCreator fortranParseTreeCreator = new FortranParseTreeCreator();
        ParseTree myParseTree = fortranParseTreeCreator.getParseTree("HelloWorld.f");

//        System.out.println(myParseTree.toXml());
    }
}
