package fortran.program.statements.expressions;

import fortran.program.helpers.ExpressionCreator;
import fortran.program.helpers.TypeName;
import fortran.program.statements.Expression;
import org.snt.inmemantlr.tree.ParseTreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OneArgumentExpression extends Expression {
    private List<Expression> expressions = new LinkedList<>();
    private String sign;
    private Map<String,String> operationsMap = new HashMap<>();

    public OneArgumentExpression(ParseTreeNode n, TypeName t, List<String> wantedSigns, List<String> operations) {
        super(t);
        for(int i = 0; i < wantedSigns.size(); i++) {
            operationsMap.put(wantedSigns.get(i), operations.get(i));
        }
        int letfParenthesisCount = 0;
        for(int i = 0; i < n.getLabel().length(); i++) {
            char current = n.getLabel().charAt(i);
            if(current == '(') {
                letfParenthesisCount++;
            } else if(current == ')') {
                letfParenthesisCount--;
            } else if(letfParenthesisCount == 0) {
                int count = n.getLabel().length() - 1 - i;
                String pottentialExpr = String.valueOf(n.getLabel().toCharArray(), i, count);
                for(var currentExprToCheck : wantedSigns) {
                    if(pottentialExpr.startsWith(currentExprToCheck)) {
                        sign = currentExprToCheck;
                    }
                }
            }
        }
        for(var expr: n.getChildren()) {
            expressions.add(ExpressionCreator.create(expr, t));
        }
    }

    @Override
    public void process() {
        expressions.get(0).process();
        StringBuilder tmpValAddress = new StringBuilder(expressions.get(0).getAddress());
//        for(int i = 0; i < signs.size(); i++) {
//            expressions.get(i + 1).process();
//            String tmpVal = parsingProcessor.addNewTmp(getTypeName());
//            String variableType = getTypeName().getLLVMType();
//            String currentOperation = operationsMap.get(signs.get(i));
//            String subValLoading = "%" + tmpVal + " = " + currentOperation + " " + variableType
//                    + " " + tmpValAddress.toString() + ", " + expressions.get(i + 1).getAddress();
//            parsingProcessor.addLine(subValLoading);
//            tmpValAddress = new StringBuilder("%" + tmpVal);
//        }
//        String tmpVal = parsingProcessor.addNewTmp(getTypeName());
        String tmpVal = parsingProcessor.addNewTmp(getTypeName());
        String variableType = getTypeName().getLLVMType();
        String currentOperation = operationsMap.get(sign);
        String subValLoading = "%" + tmpVal + " = " + currentOperation + " " + variableType
                + " " + tmpValAddress.toString();
        parsingProcessor.addLine(subValLoading);
        tmpValAddress = new StringBuilder("%" + tmpVal);
        setAddress(tmpValAddress.toString());
    }

}
