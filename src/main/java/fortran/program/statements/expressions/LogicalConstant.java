package fortran.program.statements.expressions;

import fortran.program.helpers.TypeName;
import fortran.program.statements.Expression;

public class LogicalConstant extends Expression {
    private String value;

    @Override
    public String getAddress() {
        return this.getValue();
    }

    public LogicalConstant(String value, TypeName t) {
        super(t);
        if(value.equals(".true.")) {
            this.value = "1";
        } else {
            this.value = "0";
        }
    }

    @Override
    public String convert() {
        return this.value;
    }

    public String getValue() {
        return value;
    }
}
