package fortran.tools;

import org.antlr.v4.runtime.ParserRuleContext;
import org.snt.inmemantlr.GenericParser;
import org.snt.inmemantlr.listener.DefaultTreeListener;
import org.snt.inmemantlr.tree.ParseTree;
import org.snt.inmemantlr.utils.FileUtils;

import java.io.File;

public class FPTCreator {

    private static String lexerPath = "src/main/grammar/Fortran77Lexer.g4";
    private static String parserPath = "src/main/grammar/Fortran77Parser.g4";
    private static String baseFilePath = "src/main/examples/";

    public ParseTree getParseTree (String fileName) {
        ParserRuleContext ctx = new ParserRuleContext();
        DefaultTreeListener defaultTreeListener = new DefaultTreeListener();
        try {
            File[] files = {
                    new File(lexerPath),
                    new File(parserPath)
            };
            GenericParser gp = new GenericParser(files);
            String s = FileUtils.loadFileContent(baseFilePath + fileName);
            gp.setListener(defaultTreeListener);
            gp.compile();
//            ctx = gp.parse(s, "compilationUnit", GenericParser.CaseSensitiveType.NONE);
            ctx = gp.parse(s);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return defaultTreeListener.getParseTree();
    }

    public ParseTree getParseTree (File file) {
        ParserRuleContext ctx = new ParserRuleContext();
        DefaultTreeListener defaultTreeListener = new DefaultTreeListener();
        try {
            File[] files = {
                    new File(lexerPath),
                    new File(parserPath)
            };
            GenericParser gp = new GenericParser(files);
            String s = FileUtils.loadFileContent(file.getPath());
            gp.setListener(defaultTreeListener);
            gp.compile();
            ctx = gp.parse(s);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return defaultTreeListener.getParseTree();
    }
}
