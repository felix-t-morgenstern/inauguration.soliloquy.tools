package inaugural.soliloquy.tools.tests.fakes;

import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.persistence.TypeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class FakePersistentValuesHandler implements PersistentValuesHandler {
    public ArrayList<String> Inputs = new ArrayList<>();
    public LinkedBlockingQueue<Object> Outputs = new LinkedBlockingQueue<>();


    @Override
    public <T> T generateArchetype(String s) throws IllegalArgumentException {
        Inputs.add(s);
        //noinspection unchecked
        return (T)Outputs.poll();
    }

    @Override
    public String getInterfaceName() {
        return null;
    }

    @Override
    public void addTypeHandler(TypeHandler<?> typeHandler) throws IllegalArgumentException {

    }

    @Override
    public boolean removeTypeHandler(String s) {
        return false;
    }

    @Override
    public <T> TypeHandler<T> getTypeHandler(String s) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<String> typesHandled() {
        return null;
    }
}
