package inaugural.soliloquy.tools.tests.abstractimplementations.persistence;

import inaugural.soliloquy.tools.persistence.AbstractTypeWithTwoGenericParamsHandler;
import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.shared.HasTwoGenericParams;

import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class TypeWithTwoGenericParamsHandlerImpl<T extends HasTwoGenericParams>
        extends AbstractTypeWithTwoGenericParamsHandler<T> {
    public TypeWithTwoGenericParamsHandlerImpl(T archetype,
                                               PersistentValuesHandler persistentValuesHandler,
                                               Function<Object, Function<Object, T>>
                                                       archetypeFactory) {
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
