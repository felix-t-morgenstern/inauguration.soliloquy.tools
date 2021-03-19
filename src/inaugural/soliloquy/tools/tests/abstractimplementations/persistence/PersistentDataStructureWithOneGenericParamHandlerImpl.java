package inaugural.soliloquy.tools.tests.abstractimplementations.persistence;

import inaugural.soliloquy.tools.persistence.PersistentDataStructureWithOneGenericParamHandler;

public class PersistentDataStructureWithOneGenericParamHandlerImpl<T>
        extends PersistentDataStructureWithOneGenericParamHandler<T> {
    @Override
    public T read(String s) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String write(T t) {
        throw new UnsupportedOperationException();
    }

    public String getInnerType(String valueType, Class<T> dataStructureClass) {
        return super.getInnerType(valueType, dataStructureClass);
    }
}
