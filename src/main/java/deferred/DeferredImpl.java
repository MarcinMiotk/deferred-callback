package deferred;

class DeferredImpl implements Deferred {

    Runnable callback;
    DeferredVariable variable;

    @Override
    public void resolve(DeferredVariable variable) {
        this.variable =variable;
        this.callback.run();
    }

    void subscrieResolving(Runnable resolved) {
        this.callback = resolved;
    }

    DeferredVariable get() {
        return this.variable;
    }
}
