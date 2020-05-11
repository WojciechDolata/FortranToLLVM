package fortran.program.statements.ifstatements;

import org.snt.inmemantlr.tree.ParseTreeNode;

public class ArithmeticIfStatement extends IfSubStatement {
    private int labelCount;
    public ArithmeticIfStatement(ParseTreeNode n, int labelCount) {
        this.labelCount = labelCount;
    }
}
