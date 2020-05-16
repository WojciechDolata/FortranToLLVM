package fortran.program.statements.loops;

import fortran.program.statements.ProgramBody;
import fortran.program.statements.ProgramSection;
import org.snt.inmemantlr.tree.ParseTreeNode;

public class DoWithEndDo extends ProgramSection {

    private ProgramBody doBody;
    private Iterator iterator;
    private String beginLabel;
    private String endLabel;

    public DoWithEndDo(ParseTreeNode n) {
        int labelCount = parsingProcessor.addAndGetNewLoop();
        beginLabel = "loopBegining" + labelCount;
        endLabel = "loopEnd" + labelCount;

        iterator = new Iterator(n.getChild(0), labelCount);
        doBody = new ProgramBody(n.getChild(1).getChildren());
    }

    @Override
    public void process() {
        parsingProcessor.addLabel(beginLabel);

        iterator.makeAssignment();

//        should change all i occurrences in body to nextVal
        doBody.process();

        iterator.createRest();

        parsingProcessor.addLabel(endLabel);


    }
}
