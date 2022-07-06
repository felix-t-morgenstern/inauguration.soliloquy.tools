package inaugural.soliloquy.tools.tests.fakes;

// NB: This fake implementation is required to test AbstractTypeHandler.equals, since Mockito does
//    not permit object::hashCode to be mocked
public class FakeObjectWithArbitraryHashCode {
    public int HashCode;

    @Override
    public int hashCode() {
        return HashCode;
    }
}
