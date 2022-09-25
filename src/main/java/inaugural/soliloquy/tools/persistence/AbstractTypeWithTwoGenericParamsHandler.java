package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.persistence.TypeWithTwoGenericParamsHandler;
import soliloquy.specs.common.shared.HasTwoGenericParams;

import java.util.function.Function;

@SuppressWarnings("rawtypes")
public abstract class AbstractTypeWithTwoGenericParamsHandler<T extends HasTwoGenericParams>
        extends AbstractTypeWithGenericParamsHandler<T>
        implements TypeWithTwoGenericParamsHandler<T> {
    private final Function<Object, Function<Object, T>> ARCHETYPE_FACTORY;

    protected AbstractTypeWithTwoGenericParamsHandler(T archetype,
                                                      PersistentValuesHandler
                                                              persistentValuesHandler,
                                                      Function<Object, Function<Object, T>>
                                                              archetypeFactory) {
        super(archetype, persistentValuesHandler);
        ARCHETYPE_FACTORY = Check.ifNull(archetypeFactory, "archetypeFactory");
    }

    @Override
    public T generateArchetype(String innerType1, String innerType2)
            throws IllegalArgumentException {
        return ARCHETYPE_FACTORY
                .apply(PERSISTENT_VALUES_HANDLER
                        .generateArchetype(Check.ifNullOrEmpty(innerType1, "innerType1")))
                .apply(PERSISTENT_VALUES_HANDLER
                        .generateArchetype(Check.ifNullOrEmpty(innerType2, "innerType2")));
    }
}