package features.stock;

import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate.Test;
import org.junit.jupiter.api.Assertions;

class StockRunner {

    @Test
    void test() {
    	var karate = Runner.path("classpath:features/stock/").parallel(2);
        Assertions.assertEquals(0, karate.getFailCount(), karate.getErrorMessages());
    }
}
