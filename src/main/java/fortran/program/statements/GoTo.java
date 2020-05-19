package fortran.program.statements;

import org.snt.inmemantlr.tree.ParseTreeNode;

public class GoTo extends ProgramSection {
    private String label;
    public GoTo(ParseTreeNode n) {
        label = "label" + n.getLabel();
    }

    @Override
    public void process() {
        parsingProcessor.addLine("br label %" + label);
    }
}
