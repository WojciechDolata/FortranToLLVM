package fortran.program.statements;

import fortran.tools.ParsingProcessor;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class Assignment extends ProgramSection {
    private VarRef varRef;
    private Expression expression;


    public Assignment(ParseTreeNode n) {
        varRef = new VarRef(n.getChild(0).getLabel());
        expression = new Expression(n.getChild(1), parsingProcessor.getVariableType(varRef.getValue()));
    }

    @Override
    public String convert() {
        String converted = expression.convert() + parsingProcessor.getVariableType(varRef.getValue()).getLLVMType() + "* %" + parsingProcessor.getLastVariableOccurrence(varRef.getValue()) + "\n";
        parsingProcessor.currentLine++;
        parsingProcessor.addNewVariable(varRef.getValue(),parsingProcessor.getVariableType(varRef.getValue()));
        String loading;
        loading = "%" + parsingProcessor.getLastVariableOccurrence(varRef.getValue()) + " = load " + parsingProcessor.getVariableType(varRef.getValue()).getLLVMType() + "* %" + parsingProcessor.getPreviousVariableOccurrence(varRef.getValue()) + "\n";
        return converted + loading;
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
