package compUnit.analyses;


import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDoStatement;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTNodeLocation;
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
 * Rule14_8
 *  group of statements (switch, while, do … while or for) without enclosed body
 */

@RelationBuilder
public class StatementWithoutEnclosedBodyGroup implements IRelationBuilder<XCStatement, XCCompUnit>{
	
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
					int ok = 1;
					if(c instanceof  IASTForStatement )
					{
				        IASTStatement st = ((IASTForStatement) c).getBody();
				        
						if(!(st instanceof IASTCompoundStatement))	
						{	
							ok = 0;
						}
					}
					else
					     if(c instanceof  IASTDoStatement )
							{
						        IASTStatement st = ((IASTDoStatement) c).getBody();
						        
								if(!(st instanceof IASTCompoundStatement))	
								{
									ok = 0;
								}
							}
					   else
						   if(c instanceof  IASTWhileStatement)
							{
						        IASTStatement st = ((IASTWhileStatement) c).getBody();
						        
								if(!(st instanceof IASTCompoundStatement))	
								{
									ok = 0;
								}
							}
						   
					
						   else
							   if(c instanceof  IASTSwitchStatement)
							   {   
								   IASTNode child = ((IASTSwitchStatement) c).getBody();
								   IASTNodeLocation l[] = child.getNodeLocations();
								   int offsetCompS = l[0].getNodeOffset();
								   int offset;
								   
								   if(child instanceof IASTCompoundStatement)
								   {
									   IASTStatement a[] = ((IASTCompoundStatement) child).getStatements();
								       offset = a[0].getNodeLocations()[0].getNodeOffset();
								   
								       if(offsetCompS == offset)
								       {
								    	   ok = 0;
								       }
								   }
								   else
								   {
									   ok = 0;
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
	
	


