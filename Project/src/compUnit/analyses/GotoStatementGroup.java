package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTGotoStatement;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCGotoStatement;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Goto statement group
 */

@RelationBuilder
public class GotoStatementGroup implements IRelationBuilder<XCGotoStatement, XCCompUnit>{
	
	@Override
	public Group<XCGotoStatement> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCGotoStatement> res = new Group<>();
		
		try {
			m = (ITranslationUnit)arg0.getUnderlyingObject();
			a = m.getAST();
		} catch(CoreException e) {
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {			
			public int visit(IASTStatement c) {
				if(c instanceof IASTGotoStatement) {
			        XCGotoStatement p=Factory.getInstance().createXCGotoStatement(c);
					res.add(p);
				}
				return PROCESS_CONTINUE;
			}
		};
		v.shouldVisitStatements = true;
		a.accept(v);
		
		return res;
	}
}
	