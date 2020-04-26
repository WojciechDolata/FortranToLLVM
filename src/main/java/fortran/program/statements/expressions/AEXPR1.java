package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.Arrays;

public class AEXPR1 extends ArithmeticExpression {
    public AEXPR1(ParseTreeNode n, TypeName t) {
        super(n, t, "*/", Arrays.asList("mul", "udiv"));
    }
}