package fortran.tools;

import fortran.program.helpers.LoadStatement;
import fortran.program.helpers.TypeName;

import java.util.*;

public class ParsingProcessor {
    public int currentLine = 0;
    private static final ParsingProcessor INSTANCE = new ParsingProcessor();
    private Map<String, Stack<String>> variables = new HashMap<>();
    private Map<String, TypeName> variableType = new HashMap<>();
    private Integer index = 0;
    private Map<Integer, List<LoadStatement>> loadStatements = new HashMap<>();

    public void addNewVariable(String key, TypeName type) {
        if (variables.containsKey(key)) {
            index++;
            variables.get(key).push(index.toString());
        } else {
            variables.put(key, new Stack<>());
            variables.get(key).push(key);
        }
    }

    public void declareNewVariable(String key, TypeName type) {
        variableType.put(key, type);
    }

    public String getLastVariableOccurrence(String key) {
        return variables.get(key).lastElement();
    }

    public String getPreviousVariableOccurrence(String key) {
        return variables.get(key).elementAt(variables.get(key).size()-2);
    }

    public TypeName getVariableType(String key) {
        return variableType.get(key);
    }

    public ParsingProcessor() {
    }

    public static ParsingProcessor getInstance() {
        return INSTANCE;
    }

    public void addLoadStatement(LoadStatement loadStatement) {
        if(loadStatements.containsKey(currentLine)) {
            loadStatements.get(currentLine).add(loadStatement);
        } else {
            var tmpList = new LinkedList<LoadStatement>();
            tmpList.add(loadStatement);
            loadStatements.put(currentLine, tmpList);
        }
    }

    public String getUpdatedWithLoadStatements(String program) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (var line: program.split("\n")) {
            if(loadStatements.containsKey(i)) {
                for( var loadStatement : loadStatements.get(i)) {
                    builder.append(loadStatement.getLoadStatement());
                }
            }
            builder.append(line).append("\n");
            i++;
        }
        return builder.toString();
    }

}