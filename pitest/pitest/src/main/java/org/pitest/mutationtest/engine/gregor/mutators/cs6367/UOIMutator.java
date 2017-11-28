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
  public void visitVarInsn(final int opcode, final int var) { //visits instructions with variables in the assigment

    if (opcode == Opcodes.ILOAD) { //	found load an int value from a local variable
      final MutationIdentifier newId = this.context.registerMutation(
              this.factory, "Added unary operator to variable ");

      if (this.context.shouldMutate(newId)) { //insert new instructions into bytecode
        this.mv.visitIntInsn(Opcodes.ILOAD, var); //load the int value from  variable

        Random ran = new Random(); //random choice between -1 and 1
        int c = ran.nextInt();

        if(c%2 == 0)
          this.mv.visitInsn(Opcodes.ICONST_1); //push integer value of 1. simulates ++
        else
          this.mv.visitInsn(Opcodes.ICONST_M1); //push integer value of -1. simulates --

        this.mv.visitInsn(Opcodes.IADD); //add -1 or 1 to the value stored in variable
        this.mv.visitVarInsn(Opcodes.ISTORE, var); //store the new value back into the variable
        super.visitVarInsn(opcode, var);

      } else {
        super.visitVarInsn(opcode, var);
      }
    } else if(opcode == Opcodes.DLOAD){  //	found load an double value from a local variable
      final MutationIdentifier newId = this.context.registerMutation(
              this.factory, "Added unary operator to variable ");

      if (this.context.shouldMutate(newId)) {
        this.mv.visitVarInsn(Opcodes.DLOAD, var);

        Random ran = new Random(); //random choice between -1 and 1
        int c = ran.nextInt();

        if(c%2 == 0)
          this.mv.visitLdcInsn(new Double("1.0")); //push double value of 1. simualtes ++
        else
          this.mv.visitLdcInsn(new Double("-1.0")); //push double value of -1. simulates --

        this.mv.visitInsn(Opcodes.DADD);  //add -1 or 1 to the value stored in variable
        this.mv.visitVarInsn(Opcodes.DSTORE, var);//store the new value back into the variable
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
  public void visitIincInsn(final int var, final int increment) { //visits all instructions with a unary operator

        final MutationIdentifier newIde = this.context.registerMutation(this.factory,
                "UOI Mutator: Removing the unary operator off of local variable");

        if (this.context.shouldMutate(newIde)) {
          this.mv.visitIincInsn(var, 0); //replaces unary increment/decrement amount with 0
        } else {
          this.mv.visitIincInsn(var, increment);
        }

  }

}