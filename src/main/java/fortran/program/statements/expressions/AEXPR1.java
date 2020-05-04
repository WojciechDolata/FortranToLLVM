package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.Arrays;

public class AEXPR1 extends TwoArgumentExpression {
    public AEXPR1(ParseTreeNode n, TypeName t) {
        super(n, t, Arrays.asList("*", "/"), Arrays.asList("mul", "udiv"));
    }
}
