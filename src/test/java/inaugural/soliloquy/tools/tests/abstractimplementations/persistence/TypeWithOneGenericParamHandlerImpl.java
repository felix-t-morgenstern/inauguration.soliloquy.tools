package inaugural.soliloquy.tools.tests.abstractimplementations.persistence;

import inaugural.soliloquy.tools.persistence.AbstractTypeWithOneGenericParamHandler;
import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.shared.HasOneGenericParam;

import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class TypeWithOneGenericParamHandlerImpl<T extends HasOneGenericParam>
        extends AbstractTypeWithOneGenericParamHandler<T> {
    public TypeWithOneGenericParamHandlerImpl(
            T archetype,
            PersistentValuesHandler persistentValuesHandler,
            Function<Object, T> archetypeFactory) {
        super(archetype, persistentValuesHandler, archetypeFactory);
    }

    @Override
    public T read(String s) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String write(T t) {
        throw new UnsupportedOperationException();
    }
}
