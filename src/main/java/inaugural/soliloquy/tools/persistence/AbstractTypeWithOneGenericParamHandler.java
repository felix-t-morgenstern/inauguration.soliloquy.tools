package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.persistence.TypeWithOneGenericParamHandler;
import soliloquy.specs.common.shared.HasOneGenericParam;

import java.util.function.Function;

@SuppressWarnings("rawtypes")
public abstract class AbstractTypeWithOneGenericParamHandler<T extends HasOneGenericParam>
        extends AbstractTypeWithGenericParamsHandler<T>
        implements TypeWithOneGenericParamHandler<T> {
    private final Function<Object, T> ARCHETYPE_FACTORY;

    protected AbstractTypeWithOneGenericParamHandler(
            T archetype,
            PersistentValuesHandler persistentValuesHandler,
            Function<Object, T> archetypeFactory) {
        super(archetype, persistentValuesHandler);
        ARCHETYPE_FACTORY = Check.ifNull(archetypeFactory, "archetypeFactory");
    }

    @Override
    public T generateArchetype(String innerType) throws IllegalArgumentException {
        return ARCHETYPE_FACTORY.apply(PERSISTENT_VALUES_HANDLER.generateArchetype(
                Check.ifNullOrEmpty(innerType, "innerType")));
    }
}
