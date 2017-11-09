/**
 * Author(s): Matthew Bachelder, Vaishnavi Bhosale, Richard Fisher
 *
 * ABS Mutator:
 *    Mutator to replace a variable by its negation, i.e. a  -a.
 *    Based on INVERT_NEGS mutator
 */
//TODO: Implement Me!!!
package org.pitest.mutationtest.engine.gregor.mutators.cs6367;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public enum ABSMutator implements MethodMutatorFactory {

  ABS_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
                              final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ABSMethodVisitor(this, methodInfo, context, methodVisitor);
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

class ABSMethodVisitor extends AbstractInsnMutator {

  private static final String                            MESSAGE   = "removed negation";
  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.INEG, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.DNEG, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.FNEG, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.LNEG, new InsnSubstitution(Opcodes.NOP, MESSAGE));
  }

  ABSMethodVisitor(final MethodMutatorFactory factory,
                          final MethodInfo methodInfo, final MutationContext context,
                          final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}
