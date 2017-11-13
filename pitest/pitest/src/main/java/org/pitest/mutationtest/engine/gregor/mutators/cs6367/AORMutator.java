/**
 * Author(s): Matthew Bachelder, Vaishnavi Bhosale, Richard Fisher
 *
 * AOR Mutator:
 *    Mutator to replace an arithmetic operation with a different one
 *    (based on MATH mutator)
 */
package org.pitest.mutationtest.engine.gregor.mutators.cs6367;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum AORMutator implements MethodMutatorFactory {

  AOR_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
                              final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new AORMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AORMethodVisitor extends AbstractInsnMutator {

  AORMethodVisitor(final MethodMutatorFactory factory,
                   final MethodInfo methodInfo, final MutationContext context,
                   final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    //Ints
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB,
            "Replaced integer addition with subtraction (AOR)"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD,
            "Replaced integer subtraction with addition (AOR)"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV,
            "Replaced integer multiplication with division (AOR)"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL,
            "Replaced integer division with multiplication (AOR)"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL,
            "Replaced integer modulus with multiplication (AOR)"));

    //Longs
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB,
            "Replaced long addition with subtraction (AOR)"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD,
            "Replaced long subtraction with addition (AOR)"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV,
            "Replaced long multiplication with division (AOR)"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL,
            "Replaced long division with multiplication (AOR)"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL,
            "Replaced long modulus with multiplication (AOR)"));

    // Floats
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB,
            "Replaced float addition with subtraction (AOR)"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD,
            "Replaced float subtraction with addition (AOR)"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV,
            "Replaced float multiplication with division (AOR)"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL,
            "Replaced float division with multiplication (AOR)"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL,
            "Replaced float modulus with multiplication (AOR)"));

    // Doubles
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB,
            "Replaced double addition with subtraction (AOR)"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD,
            "Replaced double subtraction with addition (AOR)"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV,
            "Replaced double multiplication with division (AOR)"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL,
            "Replaced double division with multiplication (AOR)"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL,
            "Replaced double modulus with multiplication (AOR)"));

  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}
