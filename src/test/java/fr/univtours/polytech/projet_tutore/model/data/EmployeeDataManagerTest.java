package fr.univtours.polytech.projet_tutore.model.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeDataManagerTest {
    private DataManager dataManager;

    @BeforeEach
    void setUp() {
        EmployeeDataManager dataManager = new EmployeeDataManager();
    }

    @Test
    void testEmployeeDataManager() {
        Assertions.assertNotNull(dataManager.getFilePath());
        System.out.println(dataManager.getFilePath());
    }

    @Test
    void testInitialize() {
        dataManager.setFilePath(null);
        Assertions.assertNull(dataManager.getFilePath());

        dataManager.initialize();
        Assertions.assertNotNull(dataManager.getFilePath());

        System.out.println(dataManager.getFilePath());
    }

    @Test
    void testParse() {

    }

    @Test
    void testSerialize() {

    }
}
