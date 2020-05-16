package fortran.program.statements.loops;

import fortran.program.helpers.TypeName;
import fortran.program.statements.ProgramSection;
import lombok.Getter;
import lombok.Setter;
import org.snt.inmemantlr.tree.ParseTreeNode;

@Getter
@Setter
public class Iterator extends ProgramSection{
    private String name;
    private String startValue;
    private String endValue;
    private String iteration;
    private String labelEnd;
    private String labelBegin;
    private Integer labelCount;

    public Iterator(ParseTreeNode n, Integer labelCount) {
        name = n.getChild(0).getLabel();
        startValue = n.getChild(1).getLabel();
        endValue = n.getChild(2).getLabel();
        iteration = n.getChildren().size() == 4 ? n.getChild(3).getLabel() : "1";
        this.labelEnd = "loopEnd" + labelCount;
        this.labelBegin = "loopBegin" + labelCount;
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

        var type = parsingProcessor.getVariableType(name);
        var tmp1 = parsingProcessor.addNewTmp(type);
        var loopcond = parsingProcessor.addNewTmp(new TypeName("logical"));

        parsingProcessor.addLine("%" + tmp1 + " = load " + type.getLLVMType() + ", " + type.getLLVMType() + "* %" + name + ", align " + type.getSize());
        parsingProcessor.addLine("%" + loopcond + " = icmp sle i1 %" + tmp1 + ", " + endValue);

        parsingProcessor.addLine("br i1 %" + loopcond + ", label %" + labelBegin + ", label %" + labelEnd);
    }
}
