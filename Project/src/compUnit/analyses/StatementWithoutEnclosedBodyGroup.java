package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDoStatement;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
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
 *  group of statements (switch, while, do � while or for) without enclosed body
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
			m = (ITranslationUnit)arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e)
		{
			e.printStackTrace();
		}
		
		v = new ASTVisitor() {
	
			public int visit(IASTStatement c) {

				if(c instanceof  IASTForStatement || c instanceof  IASTDoStatement || c instanceof  IASTWhileStatement) 
				{
					IASTNode s[] = c.getChildren();
					int ok = 0;
					for(IASTNode p:s)
					{
						if(p instanceof IASTCompoundStatement)	
						{
							ok = 1; break;
						}
					}
					if(ok == 0)
					{	XCStatement p = Factory.getInstance().createXCStatement(c);
						res.add(p);
					}
				}
			else
				if(c instanceof  IASTSwitchStatement)
				{
					int index = c.getRawSignature().indexOf("case");
					index--;
					if(c.getRawSignature().charAt(index) != '{')
					{
						XCStatement p = Factory.getInstance().createXCStatement(c);
						res.add(p);
					}
				}
		
				return 0;
			}
	

	};
	v.shouldVisitStatements = true;
	a.accept(v);
	return res;
 
	}
}
	
	

