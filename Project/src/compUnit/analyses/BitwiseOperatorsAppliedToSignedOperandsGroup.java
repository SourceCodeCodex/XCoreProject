package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTBinaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTUnaryExpression;
import org.eclipse.cdt.core.dom.ast.IBasicType;
import org.eclipse.cdt.core.dom.ast.IType;
import org.eclipse.cdt.core.dom.ast.ITypedef;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;
import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/** Rule 12.7 (required): Bitwise operators shall not be applied to operands whose
 *      underlying type is signed.
 */

@RelationBuilder
public class BitwiseOperatorsAppliedToSignedOperandsGroup implements IRelationBuilder<XCExpression, XCCompUnit>{
	
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
				
			if(c.isPartOfTranslationUnitFile())
			{	if(c instanceof IASTBinaryExpression )
				{ 
				        boolean k1 = false, k2 = false;
                    	int sl = IASTBinaryExpression.op_shiftLeft;
                    	int sr = IASTBinaryExpression.op_shiftRight;
                    	int sla = IASTBinaryExpression.op_shiftLeftAssign;
      				  	int orA = IASTBinaryExpression.op_binaryOrAssign;
      				  	int and = IASTBinaryExpression.op_binaryAnd;
      				    int or = IASTBinaryExpression.op_binaryOr;
                    	int op = ((IASTBinaryExpression) c).getOperator();

                    	if(op == sl || op == sr || (op >= sla && op <= orA )  || (op >= and && op <= or) )
                    	{	
                    		IASTExpression operand1 = ((IASTBinaryExpression) c).getOperand1();
                    	    IASTExpression operand2 = ((IASTBinaryExpression) c).getOperand2();
                    	    IType type1 = operand1.getExpressionType();
                    	    IType type2 = operand2.getExpressionType();
                    	 
                    	    k1 = verifyType(type1);
                    	    k2 = verifyType(type2);
                    	   
                    	    
                    	    if( k1 == true || k2 == true)
                    	    {
                    	    	 XCExpression expr = Factory.getInstance().createXCExpression(c);
					    		 res.add(expr);
                    	    }
       				
           		
                    	}
                    	
				}
				else if(c instanceof IASTUnaryExpression)
				{
					int op = ((IASTUnaryExpression) c).getOperator();
			        IType type = c.getExpressionType();
				     
				     if(op == IASTUnaryExpression.op_tilde )
				     {
				    	 
				    	 if(verifyType(type))
				    	 {
				    		 XCExpression expr = Factory.getInstance().createXCExpression(c);
				    		 res.add(expr); 
				    	 }    	 						 
					 }
				     
				}
			}
				
				return PROCESS_CONTINUE;
			
		}
		
		
		private boolean verifyType(IType type)
		{
			if(type instanceof ITypedef)
			 {
				type = ((ITypedef) type).getType();
			 }
					
			 if(type instanceof IBasicType) 
		     { 
			    	 if(((IBasicType) type).isSigned())	
			    	 {	 
			    		 return true;
			    	 }	 
			 }

			 return false;
		}
		
		};
		
		v.shouldVisitExpressions = true;
		a.accept(v);
		
		return res;
	}

}
