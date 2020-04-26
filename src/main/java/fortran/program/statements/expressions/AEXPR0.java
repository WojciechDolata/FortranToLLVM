package fortran.program.statements.expressions;

import fortran.program.helpers.ExpressionCreator;
import fortran.program.helpers.TypeName;
import fortran.program.statements.Expression;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AEXPR0 extends ArithmeticExpression {
    public AEXPR0(ParseTreeNode n, TypeName t) {
        super(n, t, "+-", Arrays.asList("add", "sub"));
    }
}