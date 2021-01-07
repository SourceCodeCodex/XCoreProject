package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTIfStatement;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCStatement;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule 14.9 (required): An if (expression) construct shall be followed by a compound statement. 
 * The else keyword shall be followed by either a compound statement, or another if statement.
 */

@RelationBuilder
public class IfWithoutCompoundStatementGroup implements IRelationBuilder<XCStatement, XCCompUnit>{
	
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
	
			public int visit(IASTStatement c) {

				if(c.isPartOfTranslationUnitFile()) {
					int k1 = 1,k2 = 1;
					if(c instanceof  IASTIfStatement )
					{ IASTStatement thenClause = ((IASTIfStatement) c).getThenClause();
					  IASTStatement elseClause = ((IASTIfStatement) c).getElseClause();
					  
					  if(!(thenClause instanceof IASTCompoundStatement))
					  {
						  k1 = 0;
					  }
					  
					  if(elseClause != null)
					  {
						  if(!(elseClause instanceof IASTCompoundStatement) && !(elseClause instanceof IASTIfStatement))
						  {
							  k2 = 0;
						  }
					  }
					  if(k1 == 0 || k2 == 0)
					  {
						  XCStatement p = Factory.getInstance().createXCStatement(c);
						  res.add(p);
						  
					  }
					
					}
				}
				        
				return PROCESS_CONTINUE;
			}
	

	
		};
		v.shouldVisitStatements = true;
		a.accept(v);
		return res;
 
	}
}
	
	