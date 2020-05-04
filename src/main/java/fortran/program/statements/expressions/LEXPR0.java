package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.Arrays;

public class LEXPR0 extends TwoArgumentExpression {
    public LEXPR0(ParseTreeNode n, TypeName t) {
        super(n, t, Arrays.asList(".eqv.", ".neqv."), Arrays.asList("icmp eq", "icmp ne"));
    }
}
