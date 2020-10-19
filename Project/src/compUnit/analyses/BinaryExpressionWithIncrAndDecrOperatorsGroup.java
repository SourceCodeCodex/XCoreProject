package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTBinaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTUnaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCBinaryExpression;
import project.metamodel.entity.XCCompUnit;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/**
 * Rule12_13
 * Binary expression with ++ and -- operators
 */

@RelationBuilder
public class BinaryExpressionWithIncrAndDecrOperatorsGroup implements IRelationBuilder<XCBinaryExpression, XCCompUnit>{
	
	@Override
	public Group<XCBinaryExpression> buildGroup(XCCompUnit arg0) {
		
		IASTTranslationUnit a = null;
	    ITranslationUnit m = null;
	    Group<XCBinaryExpression> res = new Group<>();
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
				if(c instanceof IASTUnaryExpression)
				{ int op,poD,poI,prD,prI;
				  op = ((IASTUnaryExpression) c).getOperator();
			
				  poD = IASTUnaryExpression.op_postFixDecr;
				  prD = IASTUnaryExpression.op_prefixDecr;
				  poI = IASTUnaryExpression.op_postFixIncr;
				  prI = IASTUnaryExpression.op_prefixIncr;
				  if(op == prI || op == prD || op == poI ||op == poD)
				  {
                     IASTNode parent = c.getParent();
                     if(parent instanceof IASTBinaryExpression)
                     {	int minus,multiply;
                    	 op = ((IASTBinaryExpression) parent).getOperator();
                     	 minus = IASTBinaryExpression.op_minus;
                     	 multiply = IASTBinaryExpression.op_multiply;
                         if(op >= multiply && op<= minus) 
                         {	
                        	XCBinaryExpression expr = Factory.getInstance().createXCBinaryExpression((IASTBinaryExpression)parent);
 							res.add(expr);
       				     }
                    	 
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