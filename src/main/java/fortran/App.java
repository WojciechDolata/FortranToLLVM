package fortran;

import fortran.program.statements.ProgramSection;
import fortran.program.statements.MainProgram;
import fortran.tools.FPTCreator;
import fortran.tools.ParsingProcessor;
import javafx.application.Application;
import javafx.stage.Stage;
import org.snt.inmemantlr.tree.ParseTree;

/**
 * Hello world!
 *
 */
public class App {
    private static ParsingProcessor parsingProcessor;

    public static void main( String[] args ) {
        parsingProcessor = ParsingProcessor.getInstance();
        FPTCreator FPTCreator = new FPTCreator();
        ParseTree myParseTree = FPTCreator.getParseTree("Addition.f");

        ProgramSection programSection = new MainProgram(myParseTree.getNodes().get(3));
        programSection.process();
        System.out.println(parsingProcessor.getProgram());
    }
}
