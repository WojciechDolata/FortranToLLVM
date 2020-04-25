package fortran.program.helpers;

public class TypeName {
    private String type;

    public TypeName(String type) {
        this.type = type.toLowerCase();
    }

    public String getLLVMType() {
        switch (type) {
            case "integer":
                return "i32";
            case "real":
                return "float";
            case "double precision":
                return "double";
            case "logical":
                return "i1";
        }
        return "";
    }

//    public TypeName(String value) {
//        if (value.matches("[1-9]+[0-9]*")) {
//            this.type = "integer";
//        } else if (value.matches("[0-9]*.[0-9]*")) {
//            this.type = "double precision";
//        } else if (value.equals())
//    }

}
