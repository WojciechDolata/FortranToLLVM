package fortran.program.statements;

import fortran.program.helpers.ExpressionCreator;
import fortran.program.helpers.TypeName;
import fortran.program.statements.ifstatements.ArithmeticIfStatement;
import fortran.program.statements.ifstatements.BlockIfStatement;
import fortran.program.statements.ifstatements.IfSubStatement;
import fortran.program.statements.ifstatements.LogicalIfStatement;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class IfStatement extends ProgramSection {
    private Expression condition;
    private static TypeName conditionType = new TypeName("logical");
    private Integer currentIfCount;
    private IfSubStatement subStatement;

    public IfStatement(ParseTreeNode n) {
        currentIfCount = parsingProcessor.addAndGetNewIf();
        condition = ExpressionCreator.create(n.getChild(0), conditionType);
        switch (n.getChild(1).getRule()) {
            case "blockIfStatement":
                subStatement = new BlockIfStatement(n.getChild(1), currentIfCount, condition);
                break;
            case "logicalIfStatement":
                subStatement = new LogicalIfStatement(n.getChild(1), currentIfCount, condition);
                break;
            case "arithmeticIfStatement":
                subStatement = new ArithmeticIfStatement(n.getChild(1), currentIfCount);
                break;
            default:
                subStatement = new IfSubStatement();
        }
    }

    @Override
    public void process() {
        subStatement.process();
    }
}
