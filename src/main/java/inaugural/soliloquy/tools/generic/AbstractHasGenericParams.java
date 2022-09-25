package inaugural.soliloquy.tools.generic;

import inaugural.soliloquy.tools.Check;

public abstract class AbstractHasGenericParams extends CanGetInterfaceName {
    protected <T> T archetypeCheck(T archetype, String paramName) {
        return Check.archetypeAndArchetypesOfArchetypeAreNotNull(paramName,
                Check.ifNull(archetype, paramName));
    }

    protected abstract String getUnparameterizedInterfaceName();
}
