package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFieldDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IBasicType;
import org.eclipse.cdt.core.dom.ast.IBinding;
import org.eclipse.cdt.core.dom.ast.IType;
import org.eclipse.cdt.core.dom.ast.ITypedef;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTTypedefNameSpecifier;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCDeclaration;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule6_4
 * bit-field declared without appropriate type
 */

@RelationBuilder
public class BitFieldDeclaredWithoutAppropriateTypeGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
	
	@Override
	public Group<XCDeclaration> buildGroup(XCCompUnit arg0) {
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		Group<XCDeclaration> res = new Group<>();
		
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e) 
		{	
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {		
			
			
			public int visit(IASTDeclarator c) {
				
				if(c instanceof IASTFieldDeclarator && c.isPartOfTranslationUnitFile() )
				{
					IASTNode p = c.getParent();
					IASTSimpleDeclSpecifier f = null;
					int ok = 0, k1 = 0, k2 = 0;
					if(p instanceof IASTSimpleDeclaration) {
				
						IASTDeclSpecifier decl = ((IASTSimpleDeclaration) p).getDeclSpecifier();
						if(decl instanceof IASTSimpleDeclSpecifier)
						{
							f = (IASTSimpleDeclSpecifier)decl;
					
							if(f.isSigned() || f.isUnsigned())
							{
								k1 = 1;
							}
							int type = f.getType();
							if(type == IASTSimpleDeclSpecifier.t_int || type == IASTSimpleDeclSpecifier.t_unspecified)
							{
								k2 = 1;
							}
					
							if(k1==1 && k2==1)
							{
								ok = 1;
								
							}
						}
						else
							if(decl instanceof CASTTypedefNameSpecifier )
							{   
								IBinding bindings[] = ((CASTTypedefNameSpecifier) decl).findBindings(((CASTTypedefNameSpecifier) decl).getName(),false);
								
								if(bindings.length > 0)
								{
									IBinding bind = bindings[0];
									
									if(bind instanceof ITypedef)
									{
										IType type = ((ITypedef) bind).getType();
										if(type instanceof IBasicType) 
										{   
											IBasicType basicType = (IBasicType)type;
											if(basicType.isUnsigned() || basicType.isSigned())
											{
												if(!(basicType.isLong() || basicType.isShort() || basicType.isLongLong()))
												{				
													if(basicType.getKind() == IBasicType.Kind.eInt)
													{
														ok = 1;
													}
												}
											}
										}	
									}
								}
							}
							
						if(ok==0)
						{
							XCDeclaration d=Factory.getInstance().createXCDeclaration(c);
							res.add(d);
						}
			
					}
				}
				return PROCESS_CONTINUE;
			}
		};
		
		v.shouldVisitDeclarators = true;
		a.accept(v);
		
		return res;
	
	}
}