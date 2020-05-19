package fortran.program.statements;

import fortran.program.statements.loops.DoWithEndDo;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.LinkedList;
import java.util.List;

public class ProgramBody extends ProgramSection {
    private List<ProgramSection> statements = new LinkedList<>();

    public ProgramBody(List<ParseTreeNode> children) {
        for (var child : children) {
            while (child.getChildren().size() == 1) {
                child = child.getChild(0);
            }
            if(child.hasChildren() && child.getChild(0).getRule().equals("lblRef")) {
                statements.add(new Label(child.getChild(0)));
                child = child.getChild(1);
            }
            while (child.getChildren().size() == 1) {
                child = child.getChild(0);
            }
            switch (child.getRule()) {
                case "lblRef":
                    statements.add(new GoTo(child));
                    break;
                case "typeStatement":
                    statements.add(new Declaration(child));
                    break;
                case "assignmentStatement":
                    statements.add(new Assignment(child));
                    break;
                case "ifStatement":
                    statements.add(new IfStatement(child));
                    break;
                case "doWithEndDo":
                    statements.add(new DoWithEndDo(child));
                    break;
            }

        }
    }

    @Override
    public void process() {
        statements.forEach(ProgramSection::process);
    }
}
