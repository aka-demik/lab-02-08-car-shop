package carshop;

import carshop.CarNotFoundException;
import carshop.Store;
import models.Car;
import models.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class StoreTest {

//    static Store store;
//
//    @BeforeAll
//    static void initStore() {
//        store = new Store();
//        Assertions.assertNotNull(store);
//    }

    @Test
    void createCar() {
        Store store = new Store();
        Assertions.assertNotNull(store.getFreeCars());

        store.createCar(150000, "kia Rio", "ABC");

        Assertions.assertEquals(1, store.getFreeCars().size());

        store.getFreeCars().stream().forEach((car) -> {
            Assertions.assertEquals(150000, car.getPrice());
            Assertions.assertEquals("kia Rio", car.getModel());
            Assertions.assertEquals("ABC", car.getRegNum());
        });
    }

    @Test
    void save() {

    }

    @Test
    void recover() {

    }

    @Test
    void getFirstOrder() {
        Store store = new Store();
        Assertions.assertNotNull(store.getFreeCars());

    }

    @Test
    void sellCar() throws Throwable {
        Store store = new Store();

        Executable sell = () ->
                store.sellCar("kia Rio", "Jhon", "Konor", "123");

        Assertions.assertThrows(CarNotFoundException.class, sell);

        store.createCar(150000, "kia Rio", "ABC");
        sell.execute();

        Car car = new Car(150000, "kia Rio", "ABC");
        Order order = new Order(car, 160000, 12, (short) 13);
        Assertions.assertEquals(0, store.getFreeCars().size());
        Assertions.assertEquals(1, store.getOrders().stream().filter((o) -> {
            return order.getCar().getModel().equals("kia Rio") &&
                    order.getCar().getRegNum().equals("ABC") &&
                    order.getCar().getPrice() == 150000 &&
                    order.getSum() == 160000;
        }).count());
        Assertions.assertEquals(1, store.getContractList().values().stream().filter((client) -> {
            return client.getLastName().equals("Konor")
                    && client.getFirstName().equals("Jhon")
                    && client.getPhoneNumber().equals("123");
        }).count());
    }

    @Test
    void getContractList() {
        Store store = new Store();
        Assertions.assertNotNull(store.getContractList());
        Assertions.assertEquals(0, store.getContractList().size());
    }

    @Test
    void getOrders() {

    }

    @Test
    void getFreeCars() {

    }

}