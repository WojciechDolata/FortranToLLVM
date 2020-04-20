package fortran;

import org.antlr.v4.runtime.ParserRuleContext;
import org.snt.inmemantlr.GenericParser;
import org.snt.inmemantlr.listener.DefaultListener;
import org.snt.inmemantlr.listener.DefaultTreeListener;
import org.snt.inmemantlr.tree.ParseTree;
import org.snt.inmemantlr.utils.FileUtils;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ParseTreeCreator parseTreeCreator = new ParseTreeCreator();
        ParseTree myParseTree = parseTreeCreator.getParseTree("HelloWorld.f");

        System.out.println(myParseTree.toString());
    }
}
