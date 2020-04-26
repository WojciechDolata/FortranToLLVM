package fortran.program.statements;

import fortran.program.helpers.TypeName;

public class VarRef extends Expression{
    private String value;
    private boolean isConst = false;

    @Override
    public String getAddress() {
        if(!isConst) {
            return "%" + parsingProcessor.getLastVariableOccurrence(value);
        }
        return this.getValue();
    }

    public VarRef(String value, TypeName t) {
        super(t);
        if(value.matches("[0-9].*")) {
            isConst = true;
        }
        this.value = value;
    }

    @Override
    public String convert() {
        return this.value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void process() {
        if (!isConst) {
            parsingProcessor.addNewVariable(value);
            String currentVariableName = parsingProcessor.getLastVariableOccurrence(value);
            String variableType = getTypeName().getLLVMType();
            String loading = "%" + currentVariableName + " = load " + variableType + ", " + variableType
                    + "* %" + value + ", align " + getTypeName().getSize();
            parsingProcessor.addLine(loading);
        }
    }
}
