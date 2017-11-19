/**
 * @Author(s): Matthew Bachelder, Vaishnavi Bhosale, Richard Fisher
 *
 * AOD Mutator:
 *    Mutator to replace an arithmetic operation by one of its operands
 *    Based on MATH mutator
 */
//TODO: Implement Me!!!
package org.pitest.mutationtest.engine.gregor.mutators.cs6367;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum AODMutator implements MethodMutatorFactory {

  AOD_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
                              final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new AODMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AODMethodVisitor extends AbstractInsnMutator {

  AODMethodVisitor(final MethodMutatorFactory factory,
                   final MethodInfo methodInfo, final MutationContext context,
                   final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();
  private static final String                            MESSAGE   = "Removed second operand (AOD)";

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from an addition formula (int)"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from an addition formula (double)"));
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from an addition formula (float)"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from an addition formula (long)"));

    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a subtraction formula (int)"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a subtraction formula (double)"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a subtraction formula (float)"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a subtraction formula (long)"));

    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a multiplication formula (int)"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a multiplication formula (double)"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a multiplication formula (float)"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a multiplication formula (long)"));

    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a division formula (int)"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a division formula (double)"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a division formula (float)"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a division formula (long)"));

    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a modulus formula (int)"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a modulus formula (double)"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a modulus formula (float)"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a modulus formula (long)"));


    /*
    // Ints
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.NOP, MESSAGE));

    // Longs
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.NOP, MESSAGE));

    // Floats
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.NOP, MESSAGE));

    // Doubles
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.NOP, MESSAGE));
    */
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}
