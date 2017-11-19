/**
 * Author(s): Matthew Bachelder, Vaishnavi Bhosale, Richard Fisher
 *
 * UOI Mutator:
 *    Mutator to replace a variable with a unary operator or removes an
 *    instance of an unary operator. a  a++ and a++  a
 *    Based on MATH mutator but this probably needs to be changed
 */
//TODO: Implement Me!!!  Make sure the MATH mutator is the best starting point.
package org.pitest.mutationtest.engine.gregor.mutators.cs6367;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

import java.util.Random;

public enum UOIMutator implements MethodMutatorFactory {

  UOI__MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
                              final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new UOIMutatorMethodVisitor(this, context, methodVisitor);
  }

  @Override
  public String getGloballyUniqueId() {
    return this.getClass().getName();
  }

  @Override
  public String getName() {
    return name();
  }
}

// This operator works exclusively on function local variables, not object variables
class UOIMutatorMethodVisitor extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  UOIMutatorMethodVisitor(final MethodMutatorFactory factory,
                                final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }






  @Override
  public void visitVarInsn(final int opcode, final int var) {

    if (opcode == Opcodes.ILOAD) {
      final MutationIdentifier newId = this.context.registerMutation(
              this.factory, "Added increment to variable ");

      if (this.context.shouldMutate(newId)) {
        this.mv.visitIntInsn(Opcodes.ILOAD, var);
        this.mv.visitIntInsn(Opcodes.BIPUSH, Opcodes.ICONST_1);
        this.mv.visitInsn(Opcodes.IADD);
        this.mv.visitVarInsn(Opcodes.ISTORE, var);
        super.visitVarInsn(opcode, var);

      } else {
        super.visitVarInsn(opcode, var);
      }
    } else if(opcode == Opcodes.DLOAD){
      final MutationIdentifier newId = this.context.registerMutation(
              this.factory, "Added increment to variable ");

      if (this.context.shouldMutate(newId)) {
        this.mv.visitVarInsn(Opcodes.DLOAD, var);
        this.mv.visitLdcInsn(new Double("1.0"));
        this.mv.visitInsn(Opcodes.DADD);
        this.mv.visitVarInsn(Opcodes.DSTORE, var);
        super.visitVarInsn(opcode, var);

      } else {
        super.visitVarInsn(opcode, var);
      }

    }



    else {
      super.visitVarInsn(opcode, var);
    }


  }






  @Override
  public void visitIincInsn(final int var, final int increment) {




        final MutationIdentifier newIde = this.context.registerMutation(this.factory,
                "UOI Mutator: Removed unary operator of local variable");

        if (this.context.shouldMutate(newIde)) {

          this.mv.visitIincInsn(var, 0);

        } else {
          this.mv.visitIincInsn(var, increment);
        }





  }

}