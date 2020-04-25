package fortran.tools;

import fortran.program.statements.ProgramSection;
import fortran.program.statements.MainProgram;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class ParsingConverter {

    private ProgramSection convertToStatement(ParseTreeNode n) {
        return new MainProgram(n);
    }
}
