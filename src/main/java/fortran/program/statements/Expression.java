package fortran.program.statements;

import fortran.program.helpers.TypeName;
import fortran.program.statements.expressions.AEXPR0;
import fortran.tools.ParsingProcessor;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class Expression extends ProgramSection {

    private String type;
    private ProgramSection programSection;
    private TypeName expectedType;

    public Expression(ParseTreeNode n, TypeName typeName) {
        expectedType = typeName;
        while (n.getChildren().size() == 1) {
            n = n.getChild(0);
        }
        switch (n.getRule()) {
            case "varRef":
                type = "const";
                programSection = new VarRef(n.getLabel());
                break;
            case "aexpr0":
                type = "aexpr0";
                programSection = new AEXPR0(n, expectedType);
            default:
                type = "wrong";
                programSection = new ProgramSection();
        }
    }

    @Override
    public String convert() {
        switch (type) {
            case "const":
                return "store " + expectedType.getLLVMType() + " " + programSection.convert() + ", ";
        }
        return "";
    }
}
