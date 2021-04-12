package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;

public abstract class PersistentDataStructureWithOneGenericParamHandler<T>
        extends PersistentDataStructureWithGenericParams<T>
        implements PersistentValueTypeHandler<T> {
    protected PersistentDataStructureWithOneGenericParamHandler() {
        super();
    }

    // NB: dataStructureClass is passed in here, since getArchetype is an unsupported operation
    // TODO: Consider refactoring PersistentTypeHandler to not contain getArchetype
    @SuppressWarnings("ConstantConditions")
    protected String getInnerType(String valueType, Class<T> dataStructureClass) {
        Check.ifNullOrEmpty(valueType, "valueType");
        Check.ifNull(dataStructureClass, "dataStructureClass");

        StackTraceElement callingMethod = Thread.currentThread().getStackTrace()[0];
        String className = callingMethod.getClassName();

        int openingCaret = valueType.indexOf("<");
        if (openingCaret < 0) {
            throw new IllegalArgumentException(className +
                    ".getInnerType: no opening caret found");
        }
        int closingCaret = valueType.lastIndexOf(">");
        if (closingCaret < 0) {
            throw new IllegalArgumentException(className +
                    ".getInnerType: no closing caret found");
        }
        if (!valueType.substring(0, openingCaret).equals(dataStructureClass.getCanonicalName())) {
            throw new IllegalArgumentException(
                    className +
                            ".generateArchetype: valueType is not a String representation of a" +
                            dataStructureClass.getName());
        }
        return valueType.substring(openingCaret + 1, closingCaret);
    }
}
