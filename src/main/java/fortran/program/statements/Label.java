package fortran.program.statements;

import org.snt.inmemantlr.tree.ParseTreeNode;

public class Label extends ProgramSection {
    private String name;

    public Label(ParseTreeNode n) {
        this.name = n.getLabel();
    }

    @Override
    public void process() {
        parsingProcessor.addLabel("label" + name);
    }
}
