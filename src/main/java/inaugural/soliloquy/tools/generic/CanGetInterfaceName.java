package inaugural.soliloquy.tools.generic;

import soliloquy.specs.common.shared.SoliloquyClass;

public class CanGetInterfaceName {
    public String getProperTypeName(Object object) {
        return object instanceof SoliloquyClass ?
                ((SoliloquyClass) object).getInterfaceName() :
                object.getClass().getCanonicalName();
    }
}
