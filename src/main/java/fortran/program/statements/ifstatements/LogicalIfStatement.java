package fortran.program.statements.ifstatements;

import fortran.program.statements.Expression;
import fortran.program.statements.ProgramBody;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class LogicalIfStatement extends IfSubStatement {
    private int labelCount;
    private Expression condition;
    private ProgramBody executableStatement;

    public LogicalIfStatement(ParseTreeNode n, int labelCount, Expression condition) {
        this.condition = condition;
        this.labelCount = labelCount;
        executableStatement = new ProgramBody(n.getChildren());
    }

    @Override
    public void process() {
        condition.process();
        parsingProcessor.addLine("br i1 " + condition.getAddress() + ", label %then"
                + labelCount + ", label %endthen" + labelCount);

        parsingProcessor.addLabel("then" + labelCount);
        executableStatement.process();
        parsingProcessor.addLabel("endthen" + labelCount);
    }
}
