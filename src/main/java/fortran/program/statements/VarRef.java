package fortran.program.statements;

public class VarRef extends ProgramSection{
    private String value;

    public VarRef(String value) {
        this.value = value;
    }

    @Override
    public String convert() {
        return this.value;
    }

    public String getValue() {
        return value;
    }
}
