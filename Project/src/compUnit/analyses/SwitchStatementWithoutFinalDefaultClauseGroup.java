package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTCaseStatement;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDefaultStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTSwitchStatement;
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
 * Rule15_3
 */

@RelationBuilder
public class SwitchStatementWithoutFinalDefaultClauseGroup implements IRelationBuilder<XCStatement, XCCompUnit>{
	
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

				if(c instanceof  IASTSwitchStatement && c.isPartOfTranslationUnitFile()) 
				{   
					IASTStatement body = ((IASTSwitchStatement) c).getBody();
				    IASTNode children[] = body.getChildren();
					int ok = 0;
					
					for(int i = 0; i < children.length; i++)
					{
						if(children[i] instanceof IASTDefaultStatement)	
						{   ok = 1;
							for( int j = i+1; j < children.length; j++)
							{
								if(children[j] instanceof IASTCaseStatement) {
									ok = 0; 
									break;
								}
							}
						}
					}
					if(ok == 0)
					{   	
						XCStatement p = Factory.getInstance().createXCStatement(c);
						res.add(p);
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
	
