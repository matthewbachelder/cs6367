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
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum UOIMutator implements MethodMutatorFactory {

  UOI_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
                              final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new UOIMethodVisitor(this, methodInfo, context, methodVisitor);
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

class UOIMethodVisitor extends AbstractInsnMutator {

  UOIMethodVisitor(final MethodMutatorFactory factory,
                   final MethodInfo methodInfo, final MutationContext context,
                   final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB,
            "Replaced integer addition with subtraction"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD,
            "Replaced integer subtraction with addition"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV,
            "Replaced integer multiplication with division"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL,
            "Replaced integer division with multiplication"));
    MUTATIONS.put(Opcodes.IOR, new InsnSubstitution(Opcodes.IAND,
            "Replaced bitwise OR with AND"));
    MUTATIONS.put(Opcodes.IAND, new InsnSubstitution(Opcodes.IOR,
            "Replaced bitwise AND with OR"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL,
            "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.IXOR, new InsnSubstitution(Opcodes.IAND,
            "Replaced XOR with AND"));
    MUTATIONS.put(Opcodes.ISHL, new InsnSubstitution(Opcodes.ISHR,
            "Replaced Shift Left with Shift Right"));
    MUTATIONS.put(Opcodes.ISHR, new InsnSubstitution(Opcodes.ISHL,
            "Replaced Shift Right with Shift Left"));
    MUTATIONS.put(Opcodes.IUSHR, new InsnSubstitution(Opcodes.ISHL,
            "Replaced Unsigned Shift Right with Shift Left"));

    // longs

    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB,
            "Replaced long addition with subtraction"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD,
            "Replaced long subtraction with addition"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV,
            "Replaced long multiplication with division"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL,
            "Replaced long division with multiplication"));
    MUTATIONS.put(Opcodes.LOR, new InsnSubstitution(Opcodes.LAND,
            "Replaced bitwise OR with AND"));
    MUTATIONS.put(Opcodes.LAND, new InsnSubstitution(Opcodes.LOR,
            "Replaced bitwise AND with OR"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL,
            "Replaced long modulus with multiplication"));
    MUTATIONS.put(Opcodes.LXOR, new InsnSubstitution(Opcodes.LAND,
            "Replaced XOR with AND"));
    MUTATIONS.put(Opcodes.LSHL, new InsnSubstitution(Opcodes.LSHR,
            "Replaced Shift Left with Shift Right"));
    MUTATIONS.put(Opcodes.LSHR, new InsnSubstitution(Opcodes.LSHL,
            "Replaced Shift Right with Shift Left"));
    MUTATIONS.put(Opcodes.LUSHR, new InsnSubstitution(Opcodes.LSHL,
            "Replaced Unsigned Shift Right with Shift Left"));

    // floats
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB,
            "Replaced float addition with subtraction"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD,
            "Replaced float subtraction with addition"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV,
            "Replaced float multiplication with division"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL,
            "Replaced float division with multiplication"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL,
            "Replaced float modulus with multiplication"));

    // doubles
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB,
            "Replaced double addition with subtraction"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD,
            "Replaced double subtraction with addition"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV,
            "Replaced double multiplication with division"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL,
            "Replaced double division with multiplication"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL,
            "Replaced double modulus with multiplication"));

  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}
