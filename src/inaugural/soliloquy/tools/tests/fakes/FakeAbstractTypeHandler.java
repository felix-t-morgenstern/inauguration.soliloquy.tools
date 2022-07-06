package inaugural.soliloquy.tools.tests.fakes;

import inaugural.soliloquy.tools.persistence.AbstractTypeHandler;

// NB: This fake implementation is required since Mockito does not permit object::hashCode to be
//     mocked
public class FakeAbstractTypeHandler<T> extends AbstractTypeHandler<T> {
    public int HashCode;

    public FakeAbstractTypeHandler(T archetype) {
        super(archetype);
    }

    @Override
    public T read(String s) throws IllegalArgumentException {
        return null;
    }

    @Override
    public String write(T t) {
        return null;
    }

    @Override
    public int hashCode() {
        return HashCode;
    }
}
