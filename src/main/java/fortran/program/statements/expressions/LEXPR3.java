package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.Arrays;

public class LEXPR3 extends OneArgumentExpression {
    public LEXPR3(ParseTreeNode n, TypeName t) {
        super(n, t, Arrays.asList(".not."), Arrays.asList("fneg"));
    }
}