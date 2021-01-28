package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
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


/**
 * Rule12_9
 * Unary minus applied to an unsigned expression
 */

@RelationBuilder
public class UnsignedExpressionWithUnaryMinusGroup implements IRelationBuilder<XCExpression, XCCompUnit>{
	
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
				     int op = ((IASTUnaryExpression) c).getOperator();
				     IType type = c.getExpressionType();
				     type = getType(type);
							
					 if(type instanceof IBasicType) 
				     {
					    	 if(((IBasicType) type).isUnsigned() && op == IASTUnaryExpression.op_minus )
					    	 {	
					    		 XCExpression expr = Factory.getInstance().createXCExpression(c);
					    		 res.add(expr);
					    	 }					         
					 }
							
				 }
				
				return PROCESS_CONTINUE;
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