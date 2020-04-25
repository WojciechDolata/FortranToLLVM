package fortran.program.statements;

import fortran.program.helpers.TypeName;
import fortran.tools.ParsingProcessor;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.LinkedList;
import java.util.List;

public class Declaration extends ProgramSection {
    private TypeName typeName;
    private List<String> names;
    private ParsingProcessor parsingProcessor = ParsingProcessor.getInstance();

    public Declaration(TypeName typeName, List<String> names) {
        this.typeName = typeName;
        this.names = names;
    }

    public Declaration(ParseTreeNode n) {
        typeName = new TypeName(n.getChild(0).getLabel());
        names = new LinkedList<>();
        for (var a : n.getChild(1).getChildren()) {
            parsingProcessor.declareNewVariable(a.getLabel(), typeName);
            names.add(a.getLabel());
        }
    }

    @Override
    public String convert() {
        StringBuilder converted = new StringBuilder();
        for (String name: names) {
            converted.append("%").append(name).append(" = alloca ").append(typeName.getLLVMType()).append("\n");
            parsingProcessor.currentLine++;
            parsingProcessor.addNewVariable(name, typeName);
        }
        return converted.toString();
    }
}
