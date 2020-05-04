package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.Arrays;

public class LEXPR4 extends TwoArgumentExpression {
    public LEXPR4(ParseTreeNode n, TypeName t) {
        super(n, t, Arrays.asList(".lt.", ".le.", ".eq.", ".ne.", ".gt.", ".ge."), Arrays.asList("icmp slt", "icmp sle", "icmp eq", "icmp ne", "icmp sgt", "icmp sge"));
    }
}
