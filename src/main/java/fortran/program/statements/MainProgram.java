package fortran.program.statements;

import org.snt.inmemantlr.tree.ParseTreeNode;

public class MainProgram extends ProgramSection {
    private ProgramBody programBody;

    public MainProgram(ParseTreeNode n) {
        programBody = new ProgramBody(n.getChild(0).getChildren());
    }

    @Override
    public void process() {
        parsingProcessor.addLine("define i32 @main() {");
        programBody.process();
        parsingProcessor.addLine("}");
    }
}
