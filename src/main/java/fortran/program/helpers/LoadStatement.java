package fortran.program.helpers;

public class LoadStatement {
    private String to;
    private String from;
    private TypeName type;

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public TypeName getType() {
        return type;
    }

    public String getLoadStatement() {
        return "%" + to + " = load " + type.getLLVMType() + "* %" + from + "\n";
    }

    public LoadStatement(String to, String from, TypeName type) {
        this.to = to;
        this.from = from;
        this.type = type;
    }
}
