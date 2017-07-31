package deferred;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static deferred.DeferredReactor.when;
import static deferred.DeferredVariableString.of;
import static org.junit.Assert.*;

public class DeferredTest {


    DeferredFactory factory;

    @Before
    public void up() {
        factory = new DeferredFactoryImpl();
    }


    @Test
    public void usage() throws InterruptedException {
        Deferred d1 = factory.factory();
        Deferred d2 = factory.factory();
        Deferred d3 = factory.factory();


        Semaphore semaphore = new Semaphore(0);
        when(d1, d2, d3).done((v1, v2, v3) -> {
            System.out.println("DONE: v1="+v1+", v2="+v2+", v3="+v3);
            semaphore.release();
        });

        d1.resolve(of("Marcin"));
        d1.resolve(of("Karol"));
        d1.resolve(of("Agata"));

        boolean acquired = semaphore.tryAcquire(5, TimeUnit.SECONDS);
        assertTrue(acquired);
    }

}