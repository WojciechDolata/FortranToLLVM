package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.Arrays;

public class LEXPR2 extends TwoArgumentExpression {
    public LEXPR2(ParseTreeNode n, TypeName t) {
        super(n, t, Arrays.asList(".and."), Arrays.asList("and"));
    }
}