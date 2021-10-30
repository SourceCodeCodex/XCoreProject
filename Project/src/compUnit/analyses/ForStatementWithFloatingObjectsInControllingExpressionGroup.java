package compUnit.analyses;


import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IBasicType;
import org.eclipse.cdt.core.dom.ast.IType;
import org.eclipse.cdt.core.dom.ast.ITypedef;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCStatement;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/**
 * Rule 13.4 (required): The controlling expression of a for statement shall not contain
 * any objects of floating type.
 */

@RelationBuilder
public class ForStatementWithFloatingObjectsInControllingExpressionGroup implements IRelationBuilder<XCStatement, XCCompUnit>{
	
	@Override
	public Group<XCStatement> buildGroup(XCCompUnit arg0) {
	
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		ASTVisitor v;
		Group<XCStatement> res = new Group<>();
		
		try 
		{
			m = arg0.getUnderlyingObject();
			a = m.getAST();	
		}
		catch(CoreException e)
		{
			e.printStackTrace();
		}
		
		v = new ASTVisitor() {
	      
			public int visit(IASTStatement d) {

				if(d.isPartOfTranslationUnitFile() && d instanceof  IASTForStatement) 
				{
				 	visitConditionExpression(d);		      
				}
				
				return PROCESS_CONTINUE;
			}
			
			private void visitConditionExpression(IASTStatement d)
			{
				IASTExpression control = ((IASTForStatement) d).getConditionExpression();
				if(control != null)
				{
					ASTVisitor r = new ASTVisitor() {
					
						public int visit(IASTExpression c)
						{	
				
							IType type = c.getExpressionType();
							type = getType(type);		
							
							if(type instanceof IBasicType) 
							{
								IBasicType.Kind kind = ((IBasicType) type).getKind();
								if(kind == IBasicType.Kind.eFloat || kind == IBasicType.Kind.eDouble)
								{	
									XCStatement k = Factory.getInstance().createXCStatement(d);
									res.add(k);
									return PROCESS_ABORT;
								}					         
							}
						
							return PROCESS_CONTINUE;
						}			
					};
					r.shouldVisitExpressions = true;
					control.accept(r);		
				}
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
		
		v.shouldVisitStatements = true;
		a.accept(v);
	
		return res;
 
	}
}
	
	
	