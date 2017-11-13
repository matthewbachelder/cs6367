/**
 * Author(s): Matthew Bachelder, Vaishnavi Bhosale, Richard Fisher
 *
 * OBBN Mutator:
 *    Mutator to replace the operator & by | and vice-versa.
 *    (based on MATH mutator)
 */
package org.pitest.mutationtest.engine.gregor.mutators.cs6367;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum OBBNMutator implements MethodMutatorFactory {

  OBBN_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
                              final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new OBBNMethodVisitor(this, methodInfo, context, methodVisitor);
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

class OBBNMethodVisitor extends AbstractInsnMutator {

  OBBNMethodVisitor(final MethodMutatorFactory factory,
                    final MethodInfo methodInfo, final MutationContext context,
                    final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    //Ints
    MUTATIONS.put(Opcodes.IOR, new InsnSubstitution(Opcodes.IAND,
            "Replaced bitwise OR with AND (OBBN)"));
    MUTATIONS.put(Opcodes.IAND, new InsnSubstitution(Opcodes.IOR,
            "Replaced bitwise AND with OR (OBBN)"));

    //Longs
    MUTATIONS.put(Opcodes.LOR, new InsnSubstitution(Opcodes.LAND,
            "Replaced bitwise OR with AND (OBBN)"));
    MUTATIONS.put(Opcodes.LAND, new InsnSubstitution(Opcodes.LOR,
            "Replaced bitwise AND with OR (OBBN)"));
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}
