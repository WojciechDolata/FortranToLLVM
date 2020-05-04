package fortran.program.helpers;

import fortran.program.statements.Expression;
import fortran.program.statements.expressions.VarRef;
import fortran.program.statements.expressions.*;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class ExpressionCreator {
    public static Expression create(ParseTreeNode n, TypeName typeName) {
        while (n.getChildren().size() == 1) {
            n = n.getChild(0);
        }
        switch (n.getRule()) {
            case "varRef":
                return new VarRef(n.getLabel(), typeName);
            case "aexpr0":
                return new AEXPR0(n, typeName);
            case "aexpr1":
                return new AEXPR1(n, typeName);
//            case "aexpr3":
//                return new AEXPR1(n, typeName);
            case "logicalConstant":
                return new LogicalConstant(n.getLabel(), typeName);
            default:
                return new Expression(typeName);
        }
    }
}
