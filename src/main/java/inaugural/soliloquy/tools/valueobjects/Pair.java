package inaugural.soliloquy.tools.valueobjects;

import inaugural.soliloquy.tools.Check;

import java.util.Objects;

public class Pair<T1, T2> implements soliloquy.specs.common.valueobjects.Pair<T1, T2> {
    private final T1 ITEM_1;
    private final T2 ITEM_2;

    private final T1 ARCHETYPE_1;
    private final T2 ARCHETYPE_2;

    private Pair(T1 item1, T2 item2) {
        this(item1, item2, item1, item2);
    }

    private Pair(T1 item1, T2 item2, T1 archetype1, T2 archetype2) {
        if (item1 == null && archetype1 == null) {
            throw new IllegalArgumentException(
                    "Pair: item1 cannot be null if archetype1 is not provided");
        }
        if (item2 == null && archetype2 == null) {
            throw new IllegalArgumentException(
                    "Pair: item2 cannot be null if archetype2 is not provided");
        }

        ITEM_1 = item1;
        ITEM_2 = item2;
        ARCHETYPE_1 = Check.ifNull(archetype1, "archetype1");
        ARCHETYPE_2 = Check.ifNull(archetype2, "archetype2");
    }

    public static <Type1, Type2> soliloquy.specs.common.valueobjects.Pair<Type1, Type2> pairOf(
            Type1 item1, Type2 item2) {
        return new Pair<>(item1, item2);
    }

    public static <Type1, Type2> soliloquy.specs.common.valueobjects.Pair<Type1, Type2> pairOf(
            Type1 item1, Type2 item2, Type1 archetype1, Type2 archetype2) {
        return new Pair<>(item1, item2, archetype1, archetype2);
    }

    /**
     * @return The first item
     */
    public T1 item1() {
        return ITEM_1;
    }

    /**
     * @return The second item
     */
    public T2 item2() {
        return ITEM_2;
    }

    @Override
    public Pair<T1, T2> makeClone() {
        return new Pair<>(ITEM_1, ITEM_2, ARCHETYPE_1, ARCHETYPE_2);
    }

    @Override
    public T1 firstArchetype() throws IllegalStateException {
        return ARCHETYPE_1;
    }

    @Override
    public T2 secondArchetype() throws IllegalStateException {
        return ARCHETYPE_2;
    }

    @Override
    public String getInterfaceName() {
        return soliloquy.specs.common.valueobjects.Pair.class.getCanonicalName();
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object obj) {
        //noinspection rawtypes
        if (!(obj instanceof soliloquy.specs.common.valueobjects.Pair pair)) {
            return false;
        }

        if (ITEM_1 == null) {
            if (pair.item1() != null) {
                return false;
            }
        }
        else {
            if (!ITEM_1.equals(pair.item1())) {
                return false;
            }
        }

        if (ITEM_2 == null) {
            if (pair.item2() != null) {
                return false;
            }
        }
        else {
            if (!ITEM_2.equals(pair.item2())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ITEM_1, ITEM_2);
    }
}
