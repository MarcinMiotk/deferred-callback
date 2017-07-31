package deferred;

class DeferredVariableString implements DeferredVariable{

    final String variable;

    public DeferredVariableString(String variable) {
        this.variable = variable;
    }


    static DeferredVariable of(String variable) {
        return new DeferredVariableString(variable);
    }

    @Override
    public String toString() {
        return variable.toString();
    }
}
