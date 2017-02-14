import carshop.CarNotFoundException;
import carshop.Store;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    public static void main(String[] args) throws CarNotFoundException {
        Store store = new Store();
        logger.trace("Store created");
        store.createCar(500000, "kia-rio", "B146AA");
        store.createCar(1500000, "kia-sorento", "B146BB");
        store.sellCar("kia-rio", "John", "Konnor", "+79876543210");

        store.save();
        store.recover();
    }
}
