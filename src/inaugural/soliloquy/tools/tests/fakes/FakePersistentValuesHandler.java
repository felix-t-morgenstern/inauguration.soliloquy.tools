package inaugural.soliloquy.tools.tests.fakes;

import soliloquy.specs.common.infrastructure.List;
import soliloquy.specs.common.persistence.*;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class FakePersistentValuesHandler implements PersistentValuesHandler {
    public ArrayList<String> Inputs = new ArrayList<>();
    public LinkedBlockingQueue<Object> Outputs = new LinkedBlockingQueue<>();

    @Override
    public void addPersistentValueTypeHandler(PersistentValueTypeHandler<?> persistentValueTypeHandler) throws IllegalArgumentException {

    }

    @Override
    public boolean removePersistentValueTypeHandler(String s) {
        return false;
    }

    @Override
    public <T> PersistentValueTypeHandler<T> getPersistentValueTypeHandler(String s) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public Object generateArchetype(String s) throws IllegalArgumentException {
        Inputs.add(s);
        return Outputs.poll();
    }

    @Override
    public List<String> persistentValueTypesHandled() {
        return null;
    }

    @Override
    public void registerPersistentListHandler(PersistentListHandler persistentListHandler) {

    }

    @Override
    public void registerPersistentMapHandler(PersistentMapHandler persistentMapHandler) {

    }

    @Override
    public void registerPersistentPairHandler(PersistentPairHandler persistentPairHandler) {

    }

    @Override
    public void registerPersistentRegistryHandler(PersistentRegistryHandler persistentRegistryHandler) {

    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}
