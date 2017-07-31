package deferred;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DeferredReactor {


    private final List<Deferred> list = new ArrayList<>();


    public DeferredReactor(Deferred... events) {
        for(Deferred event :events) {
            list.add(event);
        }
    }

    static DeferredReactor when(Deferred... events) {
        return new DeferredReactor(events);
    }

    void done(DoneFunction1 function) {
        // TODO
    }

    void done(DoneFunction2 function) {
        // TODO
    }

    void done(DoneFunction3 function) {
        // register to call function when
        // all deffered are resolved
        final AtomicInteger resolved = new AtomicInteger(0);    // TODO: bug - multiple resolve calls
        list.forEach(v-> {
            // TOOD: Liskov Substitution principle
            ((DeferredImpl)v).subscrieResolving(()->{
                System.out.println("resolved: "+list);
                int count =resolved.incrementAndGet();
                if(count==3) {     // TODO: 3
                    function.accept(
                            ((DeferredImpl)list.get(0)).get(),
                            ((DeferredImpl)list.get(1)).get(),
                            ((DeferredImpl)list.get(2)).get()
                    );
                }
            });
        });
    }

    @FunctionalInterface
    public interface DoneFunction1 {
        void accept(DeferredVariable variable1);
    }

    @FunctionalInterface
    public interface DoneFunction2 {
        void accept(DeferredVariable variable1, DeferredVariable variable2);
    }

    @FunctionalInterface
    public interface DoneFunction3 {
        void accept(DeferredVariable variable1, DeferredVariable variable2, DeferredVariable variable3);
    }
}
