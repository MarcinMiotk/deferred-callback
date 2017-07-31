package deferred;

class DeferredFactoryImpl implements DeferredFactory {
    public Deferred factory() {
        return new DeferredImpl();
    }
}
