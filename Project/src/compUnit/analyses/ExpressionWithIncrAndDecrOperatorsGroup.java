package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTInitializer;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTUnaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;
import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/**
 * Rule12_13
 * Binary expression with ++ and -- operators
 */

@RelationBuilder
public class ExpressionWithIncrAndDecrOperatorsGroup implements IRelationBuilder<XCExpression, XCCompUnit>{
	
	@Override
	public Group<XCExpression> buildGroup(XCCompUnit arg0) {
		
		IASTTranslationUnit a = null;
	    ITranslationUnit m = null;
	    Group<XCExpression> res = new Group<>();
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		} 
		catch(CoreException e)
		{
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {			
		public int visit(IASTExpression c) {
				
				if(c instanceof IASTUnaryExpression && c.isPartOfTranslationUnitFile())
				{ 
				
                    	int poD = IASTUnaryExpression.op_postFixDecr;
                    	int prD = IASTUnaryExpression.op_prefixDecr;
      				  	int poI = IASTUnaryExpression.op_postFixIncr;
      				  	int prI = IASTUnaryExpression.op_prefixIncr;
                    	int op = ((IASTUnaryExpression) c).getOperator();
                    	
                    	IASTNode parent = c.getParent();
                    	
                    	if(parent instanceof IASTExpression || parent instanceof IASTInitializer)
                    	{	
                    		if(op == prI || op == prD || op == poI ||op == poD)
                    		{
                    			XCExpression expr = Factory.getInstance().createXCExpression(c);
                    			res.add(expr);
                    		}
                    	}
				}
				
				return PROCESS_CONTINUE;
			}
		};
		
		v.shouldVisitExpressions = true;
		a.accept(v);
		
		return res;
	}
}