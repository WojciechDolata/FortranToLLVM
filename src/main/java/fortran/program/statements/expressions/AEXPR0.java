package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import fortran.program.statements.Expression;
import fortran.program.statements.ProgramSection;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.LinkedList;
import java.util.List;

public class AEXPR0 extends ProgramSection {
    private List<Expression> expressions = new LinkedList<>();
    private List<Character> signs = new LinkedList<>();

    public AEXPR0(ParseTreeNode n, TypeName t) {
        int letfParenthesisCount = 0;
        for(int i = 0; i < n.getLabel().length(); i++) {
            char current = n.getLabel().charAt(i);
            if((current == '-' || current == '+') && letfParenthesisCount == 0) {
                signs.add(current);
            } else if (current == '(') {
                letfParenthesisCount++;
            } else if (current == ')') {
                letfParenthesisCount--;
            }
        }
        for( var a: n.getChildren()) {
            expressions.add(new Expression(a, t));
        }
    }

    @Override
    public String convert() {
        StringBuilder whole = new StringBuilder();
        for(int i = 0; i < signs.size(); i++) {
            String tmp = expressions.get(i).convert()
        }
        return "should add";
    }
}
