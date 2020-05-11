package fortran.program.statements.ifstatements;

import fortran.program.statements.Expression;
import fortran.program.statements.ProgramBody;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.LinkedList;
import java.util.List;

public class BlockIfStatement extends IfSubStatement {
    private ProgramBody firstIfBlock;
    public List<ElseIfStatement> elseIfStatements = new LinkedList<>();
    private ProgramBody elseStatement = new ProgramBody(new LinkedList<>());
    private int labelCount;
    private Expression originalCondition;

    public BlockIfStatement(ParseTreeNode n, int labelCount, Expression condition) {
        this.originalCondition = condition;
        this.labelCount = labelCount;
        int i = -1;
        for (var child : n.getChildren()) {
            switch (child.getRule()) {
                case "firstIfBlock":
                    firstIfBlock = new ProgramBody(child.getChildren());
                    break;
                case "elseIfStatement":
                    elseIfStatements.add(new ElseIfStatement(child, i, labelCount));
                    break;
                case "elseStatement":
                    elseStatement = new ProgramBody(child.getChildren());
            }
            i++;
        }
    }

    @Override
    public void process() {

        originalCondition.process();
        parsingProcessor.addLine("br i1 " + originalCondition.getAddress() + ", label %then"
                + labelCount + ", label %endthen" + labelCount);

        parsingProcessor.addLabel("then" + labelCount);
        firstIfBlock.process();
        parsingProcessor.addLabel("endthen" + labelCount);

        for (var elseif : elseIfStatements) {
            elseif.process();
        }

        parsingProcessor.addLabel("else" + labelCount);
        elseStatement.process();
    }
}
