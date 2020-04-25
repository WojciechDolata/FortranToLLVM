package fortran.program.statements;

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
            switch (child.getRule()) {
                case "typeStatement":
                    statements.add(new Declaration(child));
                    break;
                case "assignmentStatement":
                    statements.add(new Assignment(child));
                    break;
            }

        }
    }

    @Override
    public String convert() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var statement: statements) {
            stringBuilder.append(statement.convert());
        }
        return stringBuilder.toString();
    }
}
