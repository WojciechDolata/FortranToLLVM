package fortran;

import fortran.program.statements.MainProgram;
import fortran.program.statements.ProgramSection;
import fortran.tools.FPTCreator;
import fortran.tools.ParsingProcessor;
import org.snt.inmemantlr.tree.ParseTree;

import java.io.File;

public class CompilerInstance {
    private static ParsingProcessor parsingProcessor;
    private FPTCreator fptCreator;

    public CompilerInstance() {
        parsingProcessor = ParsingProcessor.getInstance();
        fptCreator = new FPTCreator();
    }

    public String compileFromFile(File file) {

        ParseTree myParseTree = fptCreator.getParseTree(file);
        ProgramSection programSection = new MainProgram(myParseTree.getNodes().get(3));
        programSection.process();
        return parsingProcessor.getProgram();
    }
}
