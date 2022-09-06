package inaugural.soliloquy.tools.persistence;

import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.common.shared.SoliloquyClass;

import static inaugural.soliloquy.tools.generic.Archetypes.generateSimpleArchetype;

public abstract class AbstractSoliloquyTypeHandler<T extends SoliloquyClass>
        extends AbstractTypeHandler<T>
        implements TypeHandler<T> {
    public AbstractSoliloquyTypeHandler(Class<T> type) {
        super(generateSimpleArchetype(type));
    }
}
