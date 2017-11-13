/**
 * Author(s): Matthew Bachelder, Vaishnavi Bhosale, Richard Fisher
 *
 * ROR Mutator:
 *    Mutator to replace the relational operators with another one.
 *    It applies every replacement, e.g., <  ≥, or >  ≤
 *    (based on NEGATE_CONDITIONALS mutator)
 */
package org.pitest.mutationtest.engine.gregor.mutators.cs6367;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

import java.util.HashMap;
import java.util.Map;

public enum RORMutator implements MethodMutatorFactory {

  ROR_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
                              final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new RORMethodVisitor(this, context, methodVisitor);
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

class RORMethodVisitor extends AbstractJumpMutator {

  private static final String                     DESCRIPTION = "Relational operator replacement mutation (ROR)";
  private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

  static {
    // Compares a single value to 0
    MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFNE, DESCRIPTION));
    MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFEQ, DESCRIPTION));
    MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT, DESCRIPTION));
    MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLT, DESCRIPTION));
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, DESCRIPTION));
    MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGE, DESCRIPTION));

    // Compares two values
    MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPEQ, DESCRIPTION));
    MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPNE, DESCRIPTION));
    MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGT, DESCRIPTION));
    MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLT, DESCRIPTION));
    MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLE, DESCRIPTION));
    MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGE, DESCRIPTION));

    // Compares two object references and if they refer to the same object, then they are equal
    MUTATIONS.put(Opcodes.IF_ACMPEQ, new Substitution(Opcodes.IF_ACMPNE, DESCRIPTION));
    MUTATIONS.put(Opcodes.IF_ACMPNE, new Substitution(Opcodes.IF_ACMPEQ, DESCRIPTION));
  }

  RORMethodVisitor(final MethodMutatorFactory factory,
                   final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }

}
