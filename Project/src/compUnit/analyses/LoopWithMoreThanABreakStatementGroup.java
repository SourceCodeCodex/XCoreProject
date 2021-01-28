package compUnit.analyses;


import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTBreakStatement;
import org.eclipse.cdt.core.dom.ast.IASTDoStatement;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTSwitchStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IASTWhileStatement;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCStatement;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 14.6 (required): For any iteration statement there shall be at most one break
 * statement used for loop termination.
 */

@RelationBuilder
public class LoopWithMoreThanABreakStatementGroup implements IRelationBuilder<XCStatement, XCCompUnit>{
	
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

				if(d.isPartOfTranslationUnitFile()) 
				{
					visitLoop(d);
				}
				return PROCESS_CONTINUE;
			}
			
			
			private void visitLoop(IASTStatement d)
			{
				IASTStatement body = getBody(d);
				if(body != null)
				{
					
					ASTVisitor visitor = new ASTVisitor() {
						private int i=0;
						
						public int visit(IASTStatement c)
						{
							if(c instanceof IASTForStatement || c instanceof  IASTDoStatement || c instanceof  IASTWhileStatement || c instanceof IASTSwitchStatement)
							{
								return PROCESS_SKIP;
							}	
							
							if(c instanceof IASTBreakStatement)
							{	
								i++;
								if(i>=2)
								{ 
									XCStatement k = Factory.getInstance().createXCStatement(c);
									res.add(k);
									
								}
							}
						
							return PROCESS_CONTINUE;
						}			
					};
					visitor.shouldVisitStatements = true;
					body.accept(visitor);	
						      
				}
				
			}
			
			private IASTStatement getBody(IASTStatement s)
			{
				IASTStatement body = null;
				if(s instanceof IASTForStatement)
					body = ((IASTForStatement) s).getBody();
				else if(s instanceof IASTDoStatement)
					body = ((IASTDoStatement) s).getBody();
				else if(s instanceof IASTWhileStatement)
					body = ((IASTWhileStatement) s).getBody();
				
				return body;
				
			}
			
		};
		
		v.shouldVisitStatements = true;
		a.accept(v);
	
		return res;
 
	}
}
	
	


