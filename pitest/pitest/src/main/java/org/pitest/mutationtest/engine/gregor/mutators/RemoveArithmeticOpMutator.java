/**
 * Author(s): Matthew Bachelder, Vaishnavi Bhosale, Richard Fisher
 * 
 * 
 */
package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

public enum RemoveArithmeticOpMutator implements MethodMutatorFactory {
	REMOVE_ARITHMETIC_OP_MUTATOR;
	  @Override
	  public MethodVisitor create(final MutationContext context,
	      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
	    return new RemoveArithmeticOpMethodVisitor(this, context, methodVisitor);
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

class RemoveArithmeticOpMethodVisitor extends AbstractJumpMutator {

  private static final String                     DESCRIPTION = "inverted comparator operator";
  private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

  static {
	  //TODO: Change IADD, FADD, DADD, LADD,ISUB, FSUB, DSUB, LSUB, IMUL, FMUL, DMUL, LMUL, IDIV, FDIV, DDIV, LDIV.  Any others???  
    MUTATIONS.put(Opcodes.IADD, new Substitution(Opcodes.ISUB, DESCRIPTION));
    MUTATIONS.put(Opcodes.FADD, new Substitution(Opcodes.FSUB, DESCRIPTION));
    MUTATIONS.put(Opcodes.DADD, new Substitution(Opcodes.DSUB, DESCRIPTION));
    MUTATIONS.put(Opcodes.LADD, new Substitution(Opcodes.LSUB, DESCRIPTION));
    MUTATIONS.put(Opcodes.ISUB, new Substitution(Opcodes.IADD, DESCRIPTION));
    MUTATIONS.put(Opcodes.FSUB, new Substitution(Opcodes.FADD, DESCRIPTION));
    MUTATIONS.put(Opcodes.DSUB, new Substitution(Opcodes.DADD, DESCRIPTION));
    MUTATIONS.put(Opcodes.LSUB, new Substitution(Opcodes.LADD, DESCRIPTION));
    MUTATIONS.put(Opcodes.IMUL, new Substitution(Opcodes.IDIV, DESCRIPTION));
    MUTATIONS.put(Opcodes.FMUL, new Substitution(Opcodes.FDIV, DESCRIPTION));
    MUTATIONS.put(Opcodes.DMUL, new Substitution(Opcodes.DDIV, DESCRIPTION));
    MUTATIONS.put(Opcodes.LMUL, new Substitution(Opcodes.LDIV, DESCRIPTION));
    MUTATIONS.put(Opcodes.IDIV, new Substitution(Opcodes.IMUL, DESCRIPTION));
    MUTATIONS.put(Opcodes.FDIV, new Substitution(Opcodes.FMUL, DESCRIPTION));
    MUTATIONS.put(Opcodes.DDIV, new Substitution(Opcodes.DMUL, DESCRIPTION));
    MUTATIONS.put(Opcodes.LDIV, new Substitution(Opcodes.LMUL, DESCRIPTION));
  }

  RemoveArithmeticOpMethodVisitor(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }

}
