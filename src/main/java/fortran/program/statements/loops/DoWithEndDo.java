package fortran.program.statements.loops;

import fortran.program.statements.ProgramBody;
import fortran.program.statements.ProgramSection;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class DoWithEndDo extends ProgramSection {

    private ProgramBody doBody;
    private Iterator iterator;
    private String beginLabel;
    private String endLabel;
    private int labelCount;

    public DoWithEndDo(ParseTreeNode n) {
        labelCount = parsingProcessor.addAndGetNewLoop();
        beginLabel = "loopBegining" + labelCount;
        endLabel = "loopEnd" + labelCount;
        iterator = new Iterator(n.getChild(0), labelCount);
        doBody = new ProgramBody(n.getChild(1).getChildren());
    }

    @Override
    public void process() {
        parsingProcessor.addLabel(beginLabel);

        iterator.makeAssignment();

        int beforeIndex = parsingProcessor.getProgramStack().size();

        doBody.process();
//        Swapping out iterator for nextVal (from phi)
        var program = parsingProcessor.getProgramStack();
        for(int i = beforeIndex; i < program.size(); i++) {
            program.set(i, program.get(i).replace("%" + iterator.getName(), "%nextVal" + labelCount));
        }
        parsingProcessor.setProgram(program);

        iterator.createRest();

        parsingProcessor.addLabel(endLabel);


    }
}
