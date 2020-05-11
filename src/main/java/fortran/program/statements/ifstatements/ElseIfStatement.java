package fortran.program.statements.ifstatements;

import fortran.program.helpers.ExpressionCreator;
import fortran.program.helpers.TypeName;
import fortran.program.statements.Expression;
import fortran.program.statements.ProgramBody;
import fortran.program.statements.ProgramSection;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class ElseIfStatement extends ProgramSection {
    private int labelSubCount;
    private int labelCount;
    private Expression condition;
    private static TypeName conditionType = new TypeName("logical");
    private ProgramBody statements;


    public ElseIfStatement(ParseTreeNode n, int labelSubCount, int parentLabelCount) {
        this.labelCount = parentLabelCount;
        this.labelSubCount = labelSubCount;
        condition = ExpressionCreator.create(n.getChild(0), conditionType);
        statements = new ProgramBody(n.getChild(1).getChildren());
    }

    @Override
    public void process() {
        condition.process();
        parsingProcessor.addLine("br i1 " + condition.getAddress() + ", label %elseif"
                + labelCount + labelSubCount + ", label %endelseif" + labelCount + labelSubCount);

        parsingProcessor.addLabel("elseif" + labelCount + labelSubCount);
        statements.process();
        parsingProcessor.addLabel("endelseif" + labelCount + labelSubCount);
    }
}
