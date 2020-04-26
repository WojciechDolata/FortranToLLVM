package fortran.program.statements;

import fortran.program.helpers.TypeName;
import fortran.program.statements.expressions.AEXPR0;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class Expression extends ProgramSection {

    private String address;
    private TypeName typeName;

    public Expression(TypeName t) {
        typeName = t;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
