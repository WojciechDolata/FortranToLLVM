package fortran.tools;

import org.snt.inmemantlr.tree.ParseTree;
import org.snt.inmemantlr.tree.ParseTreeNode;
import org.snt.inmemantlr.tree.ParseTreeProcessor;

public class FortranParseTreeProcessor extends ParseTreeProcessor<String, String> {

    public FortranParseTreeProcessor(ParseTree parseTree) {
        super(parseTree);
    }
    @Override
    public String getResult() {
        // when all nodes have been processed, the result is available in the smap
        // value of the root node which is returned here
        return smap.get(parseTree.getRoot());
    }
    @Override
    protected void initialize() {
        // initialize smap - a data structure that keeps track of the intermediate
        // values for every node
        parseTree.getNodes().forEach(n -> smap.put(n, n.getLabel()));
    }
    // This operation is executed for each and every node in left to right and
    // bottom up order. Non-leaf nodes are processed only if all of their siblings
    // have been already processed
    @Override
    protected void process(ParseTreeNode n) {
        if(n.getRule().equals("expression")){
            int n0 = Integer.parseInt(smap.get(n.getChild(0)));
            int n1 = Integer.parseInt(smap.get(n.getChild(2)));
            int result = 0;
            switch(smap.get(n.getChild(1))) {
                case "+":
                    result = n0 + n1;
                    break;
                case "-":
                    result = n0 - n1;
                    break;
            }
            // store computation result of addition subtraction for current node
            smap.put(n, String.valueOf(result));
        } else {
            // when node is no expression NT, propate child node value 1:1
            // to parent
            simpleProp(n);
        }
    }
}
