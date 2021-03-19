package inaugural.soliloquy.tools.generic;

import inaugural.soliloquy.tools.Check;

public abstract class HasGenericParams extends CanGetInterfaceName
        implements soliloquy.specs.common.shared.HasGenericParams {
    protected abstract String getUnparameterizedInterfaceName();

    protected <T> T archetypeCheck(T archetype, String paramName) {
        return Check.archetypeAndArchetypesOfArchetypeAreNotNull(paramName,
                Check.ifNull(archetype, paramName));
    }
}
