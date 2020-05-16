package fortran.program.statements.loops;

import fortran.program.statements.Assignment;
import fortran.program.statements.Expression;
import fortran.program.statements.ProgramSection;
import fortran.program.statements.expressions.AEXPR0;
import lombok.Getter;
import lombok.Setter;
import org.snt.inmemantlr.tree.ParseTreeNode;


public class Iterator extends ProgramSection{
    private String name;
    private String startValue;
    private String endValue;
    private String iteration;
    private String label;
    private Integer labelCount;

    public Iterator(ParseTreeNode n, Integer labelCount) {
        name = n.getChild(0).getLabel();
        startValue = n.getChild(1).getLabel();
        endValue = n.getChild(2).getLabel();
        iteration = n.getChildren().size() == 4 ? n.getChild(3).getLabel() : "1";
        this.label = "loopEnd" + labelCount;
        this.labelCount = labelCount;
    }

    public void makeAssignment() {
        parsingProcessor.addLine("%" + parsingProcessor.getLastVariableOccurrence(name)
                + " = phi " + parsingProcessor.getVariableType(name).getLLVMType()
                + " [ " + iteration + ", %" + startValue + " ], [ " + "%nextVal" + labelCount + ", %loopBegining" + labelCount + " ]");
    }

    public void createRest() {
        parsingProcessor.addLine("%nextVal" + labelCount + " = add " + parsingProcessor.getVariableType(name).getLLVMType()
                + " %" + parsingProcessor.getLastVariableOccurrence(name) + ", " + iteration);

//        add comparison
    }
}
