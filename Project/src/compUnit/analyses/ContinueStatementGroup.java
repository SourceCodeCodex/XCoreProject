package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTContinueStatement;
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
 * Rule14_5
 * continue statement group
 */

@RelationBuilder
public class ContinueStatementGroup implements IRelationBuilder<XCStatement, XCCompUnit>{
	
	@Override
	public Group<XCStatement> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCStatement> res = new Group<>();
		
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		} catch(CoreException e) {
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {			
			public int visit(IASTStatement c) {
				if(c instanceof IASTContinueStatement) {
					if(c.isPartOfTranslationUnitFile())
					{
						XCStatement p=Factory.getInstance().createXCStatement(c);
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
	
	

