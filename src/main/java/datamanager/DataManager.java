package datamanager;

import carshop.Store;
import models.Car;
import models.Client;
import models.Order;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    private static Logger logger = Logger.getLogger(Store.class);

    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");

    }

    public static void serialize(Collection<? extends Serializable> list, String fileName) {
        logger.debug("serialize list to " + fileName);
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Serializable serializable : list) {
                    oos.writeObject(serializable);
                }
            }
        } catch (IOException e) {
            logger.error("serialize err: " + fileName, e);
        }
    }

    public static void serialize(Map<? extends Serializable, ? extends Serializable> map,
                                 String fileName) {
        logger.debug("serialize map to " + fileName);
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(map);
            }
        } catch (IOException e) {
            logger.error("serialize err: " + fileName, e);
        }
    }

    public static void deserialize(String fileName, HashMap<Order, Client> collection) {
        logger.debug("deserialize map from " + fileName);
        try (FileInputStream fis = new FileInputStream(fileName)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                HashMap<Order, Client> tmp = (HashMap<Order, Client>) ois.readObject();
                collection.putAll(tmp);
            }
        } catch (ClassNotFoundException | IOException e) {
            logger.error("deserialize err: " + fileName, e);
        }
    }

    public static void deserialize(String fileName, List<Car> collection) {
        logger.debug("deserialize List<Car> from " + fileName);
        try (FileInputStream fis = new FileInputStream(fileName)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (ois.available() > 0) {
                    Car serializable = (Car) ois.readObject();
                    collection.add(serializable);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("deserialize err: " + fileName, e);
        }
    }

    public static void deserialize(String fileName, Collection<Client> collection) {
        logger.debug("deserialize Collection<Client> from " + fileName);
        try (FileInputStream fis = new FileInputStream(fileName)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (ois.available() > 0) {
                    Client serializable = (Client) ois.readObject();
                    collection.add(serializable);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("deserialize err: " + fileName, e);
        }
    }
}
