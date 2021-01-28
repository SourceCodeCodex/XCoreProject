package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTBinaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
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

/**
 * Rule 13.3 (required): Floating-point expressions shall not be tested for equality or
 * inequality.
 */

@RelationBuilder
public class FloatingPointExpressionsTestedForEqualityInequalityGroup implements IRelationBuilder<XCExpression, XCCompUnit>{
	
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
				
				if(c instanceof IASTBinaryExpression && c.isPartOfTranslationUnitFile())
				{ 
				        boolean k1 = false, k2 = false;
                    	int eq = IASTBinaryExpression.op_equals;
                    	int noteq = IASTBinaryExpression.op_notequals;
                    	int ge = IASTBinaryExpression.op_greaterEqual;
      				  	int gt = IASTBinaryExpression.op_greaterThan;
      				  	int le = IASTBinaryExpression.op_lessEqual;
      				    int lt = IASTBinaryExpression.op_lessThan;
                    	int op = ((IASTBinaryExpression) c).getOperator();

                    	if(op == eq || op == noteq || op == ge || op == gt || op == le || op == lt )
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
				
				return PROCESS_CONTINUE;
			}
		
		private boolean verifyType(IType type)
		{
			type = getType(type);
    	    
    	    if(type instanceof IBasicType) 
		    {
    	    	IBasicType.Kind kind = ((IBasicType) type).getKind();	
    	    	if(kind == IBasicType.Kind.eDouble || kind == IBasicType.Kind.eFloat)
    	    	{
    	    		return true;
    	    	}
		    }
    	    return false;
		}
		
		private IType getType(IType type)
		{
			if(type instanceof ITypedef)
			{
				type = ((ITypedef) type).getType();
				return getType(type);
			}
			else
				return type;
			
		}
		
	};
		
		v.shouldVisitExpressions = true;
		a.accept(v);
		
		return res;
	}
}