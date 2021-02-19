package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.Check;

public abstract class PersistentDataStructureWithOneGenericParamHandler<T>
        extends PersistentTypeHandler<T> {
    @Override
    public String getInterfaceName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getArchetype() {
        // NB: The PersistentTypeHandler for this data structure should be selected by the
        // PersistentValuesHandler through specific, manually-defined String pattern recognition,
        // rather than via getArchetype.
        return null;
    }

    protected String getInnerType(String valueType, Class<T> dataStructureClass,
                                  String className) {
        Check.ifNullOrEmpty(valueType, "valueType");

        int openingCaret = valueType.indexOf("<");
        int closingCaret = valueType.lastIndexOf(">");
        if (!valueType.substring(0, openingCaret).equals(dataStructureClass.getCanonicalName())) {
            throw new IllegalArgumentException(
                    className +
                            ".generateArchetype: valueType is not a String representation of a" +
                            dataStructureClass.getName());
        }
        return valueType.substring(openingCaret + 1, closingCaret);
    }
}
