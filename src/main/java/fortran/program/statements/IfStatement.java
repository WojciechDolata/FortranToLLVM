package fortran.program.statements;

import fortran.program.helpers.ExpressionCreator;
import fortran.program.helpers.TypeName;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class IfStatement extends ProgramSection {
    private Expression condition;
    private static TypeName conditionType = new TypeName("logical");
    private Integer currentIfCount;

    public IfStatement(ParseTreeNode n) {
        currentIfCount = parsingProcessor.addAndGetNewIf();
        condition = ExpressionCreator.create(n.getChild(0), conditionType);
        var rest = n.getChild(1);
    }

    @Override
    public void process() {
        condition.process();
        parsingProcessor.addLine("br i1 " + condition.getAddress() + ", label %then"
                + currentIfCount.toString() + ", label %else" + currentIfCount.toString());
        parsingProcessor.addLabel("then" + currentIfCount.toString());


        parsingProcessor.addLabel("else" + currentIfCount.toString());
        super.process();
    }
}
