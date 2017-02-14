package datamanager;

import models.Car;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import static org.mockito.Mockito.*;

class DataManagerTest {
    @Test
    void serialize() throws IOException {
        Car car = new Car(100, "lada", "ABC");

        ObjectOutputStream oos  =  mock(ObjectOutputStream.class);
//        when(oos.writeObject(car)).then()
    }

    @Test
    void serialize1() {

    }

    @Test
    void deserialize() {

    }

    @Test
    void deserialize1() {

    }

    @Test
    void deserialize2() {

    }

}