package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.Arrays;

public class LEXPR1 extends TwoArgumentExpression {
    public LEXPR1(ParseTreeNode n, TypeName t) {
        super(n, t, Arrays.asList(".or."), Arrays.asList("or"));
    }
}