package fortran.tools;

import fortran.program.helpers.TypeName;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ParsingProcessor {
    private static final ParsingProcessor INSTANCE = new ParsingProcessor();
    private Map<String, Stack<String>> variables = new HashMap<>();
    private Map<String, TypeName> variableType = new HashMap<>();
    private Integer index = 0;
    private Stack<String> program = new Stack<>();
    private Integer ifCount = 0;

    public Integer addAndGetNewIf() {
        return ifCount++;
    }

    public void addLine(String line) {
        program.push("\t" + line);
    }

    public void addLabel(String label) {
        program.push(label + ":");
    }

    public String getProgram() {
        StringBuilder stringBuilder = new StringBuilder();
        for(var line: program) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

    public void addNewVariable(String key) {
        if (variables.containsKey(key)) {
            index++;
            variables.get(key).push(index.toString());
        } else {
            variables.put(key, new Stack<>());
            variables.get(key).push(key);
        }
    }

    public String addNewTmp(TypeName typeName) {
        index++;
        variables.put(index.toString(), new Stack<>());
        variables.get(index.toString()).push(index.toString());
        variableType.put(index.toString(), typeName);
        return index.toString();
    }

    public boolean hasVariable(String key) {
        return variables.containsKey(key);
    }

    public String findOriginalVariableName(String name) {
        AtomicReference<String> originalName = new AtomicReference<>();
        variables.forEach((key, stack) -> {
            if (stack.contains(name)) originalName.set(key);
        });

        variables.get(originalName.get()).push(originalName.get());

        return originalName.get();
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
}