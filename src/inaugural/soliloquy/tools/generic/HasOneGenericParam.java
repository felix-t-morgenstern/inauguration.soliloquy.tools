package inaugural.soliloquy.tools.generic;

public abstract class HasOneGenericParam<T> extends HasGenericParams
        implements soliloquy.specs.common.shared.HasOneGenericParam<T> {
    private String _parameterizedClassName;

    @Override
    public String getInterfaceName() {
        if (_parameterizedClassName == null) {
            _parameterizedClassName = getUnparameterizedInterfaceName() + "<"
                    + getProperTypeName(getArchetype()) + ">";
        }
        return _parameterizedClassName;
    }
}
