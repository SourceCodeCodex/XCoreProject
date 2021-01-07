package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
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
 * Rule 8.2 (required): Whenever an object or function is declared or defined, its type
 * shall be explicitly stated.
 */

	@RelationBuilder
	public class DeclarationAndDefinitionWithUnspecifiedTypeGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
		
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
					
					if( c.isPartOfTranslationUnitFile()) 				
					{   
						IASTSimpleDeclSpecifier f = null;
						IASTDeclSpecifier decl = getDeclSpecifier(c.getParent());
						boolean ok = false;

								
						if(decl instanceof IASTSimpleDeclSpecifier)
					    {
						   f = (IASTSimpleDeclSpecifier)decl;
						   int type = f.getType();
						  System.out.println("IASTSimpleDeclSpecifier"+ type);		
						   if( type == IASTSimpleDeclSpecifier.t_unspecified)
						   {
								ok = true;
						   }
									
						}
						else
							if(decl instanceof CASTTypedefNameSpecifier )
							{   
								IBinding bindings[] = ((CASTTypedefNameSpecifier) decl).findBindings(((CASTTypedefNameSpecifier) decl).getName(),false);
								if(bindings.length > 0)
								{
									IBinding bind = bindings[0];
									ok = verifyType(bind);
								}
							
							}
								
							if(ok == true)
							{ 
								XCDeclaration d=Factory.getInstance().createXCDeclaration(c);
								res.add(d);
							}
							
						
					}
						
					return PROCESS_CONTINUE;
				}
				
				private IASTDeclSpecifier getDeclSpecifier(IASTNode p)
				{
					IASTDeclSpecifier decl = null;
					if(p instanceof IASTSimpleDeclaration)
					{
						decl = ((IASTSimpleDeclaration) p).getDeclSpecifier();
					}
					else if(p instanceof IASTFunctionDefinition)
					{
                        decl = ((IASTFunctionDefinition) p).getDeclSpecifier();
					}
					
					return decl;
				}
				
				private boolean verifyType(IBinding bind)
				{
					if(bind instanceof ITypedef)
					{
						IType type = ((ITypedef) bind).getType();
						if(type instanceof IBasicType) 
						{   
							IBasicType basicType = (IBasicType)type;
							System.out.println("type: " +basicType);
							if(basicType.getKind() == IBasicType.Kind.eUnspecified)
							{ 
								return true;
							}																																													
						}						
					}					
					return false;
				}	
			};
			
			v.	shouldVisitDeclarators = true;
			a.accept(v);
			
			return res;
		
		}
	}
