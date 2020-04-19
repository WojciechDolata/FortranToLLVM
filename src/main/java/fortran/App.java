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
        ParserRuleContext ctx = new ParserRuleContext();
        DefaultTreeListener defaultTreeListener = new DefaultTreeListener();
        try {
            File files [] = {
                    new File("src/main/grammar/Fortran77Lexer.g4"),
                    new File("src/main/grammar/Fortran77Parser.g4")
            };
            GenericParser gp = new GenericParser(files);
    // 2. load file content into string
            String s = FileUtils.loadFileContent("src/main/examples/HelloWorld.f");
    // 3. set listener for checking parse tree elements. Here you could use any ParseTreeListener implementation. The default listener is used per default
            gp.setListener(defaultTreeListener);
    // 4. compile Lexer and parser in-memory
            gp.compile();
    // 5. parse the string that represents the content of HelloWorld.java
//            ctx = gp.parse(s, "compilationUnit", GenericParser.CaseSensitiveType.NONE);
            ctx = gp.parse(s);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(ctx.toString());
        ParseTree parseTree = defaultTreeListener.getParseTree();
        System.out.println(parseTree.toString());



    }
}
