package fortran.program.statements;

import fortran.program.helpers.ExpressionCreator;
import fortran.program.statements.expressions.VarRef;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class Assignment extends ProgramSection {
    private VarRef varRef;
    private Expression expression;

    public Assignment(ParseTreeNode n) {
        String val = n.getChild(0).getLabel();
        varRef = new VarRef(val, parsingProcessor.getVariableType(val));
        expression = ExpressionCreator.create(n.getChild(1), parsingProcessor.getVariableType(varRef.getValue()));
    }

    @Override
    public void process() {
        expression.process();
        String converted = "store " + parsingProcessor.getVariableType(varRef.getValue()).getLLVMType() + " " + expression.getAddress() + ", " + parsingProcessor.getVariableType(varRef.getValue()).getLLVMType() + "* %" + parsingProcessor.findOriginalVariableName(varRef.getValue()) + ", align " + parsingProcessor.getVariableType(parsingProcessor.findOriginalVariableName(varRef.getValue())).getSize();
        parsingProcessor.addLine(converted);
    }





    public VarRef getVarRef() {
        return varRef;
    }

    public void setVarRef(VarRef varRef) {
        this.varRef = varRef;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

}
