package fortran;

import fortran.program.statements.ProgramSection;
import fortran.program.statements.MainProgram;
import fortran.tools.FPTCreator;
import org.snt.inmemantlr.tree.ParseTree;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        FPTCreator FPTCreator = new FPTCreator();
        ParseTree myParseTree = FPTCreator.getParseTree("Addition.f");

        ProgramSection programSection = new MainProgram(myParseTree.getNodes().get(3));
        System.out.println(programSection.convert());
    }
}
