package fr.univtours.polytech.projet_tutore.model.data;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ClockingTimeDataManagerTest {
    private ClockingTimeDataManager clockingTimeDataManager;

    @BeforeEach
    void setUp(){
        clockingTimeDataManager= new ClockingTimeDataManager();
    }

    @Test
    void testClockingTimeDataManager() {
        Assertions.assertNotNull(clockingTimeDataManager.getFilePath());
    }

    @Test
    void testInitialize() {
        clockingTimeDataManager.setFilePath(null);
        Assertions.assertNull(clockingTimeDataManager.getFilePath());

        clockingTimeDataManager.initialize();
        Assertions.assertNotNull(clockingTimeDataManager.getFilePath());
    }


    @Test
    void testSerializeClockingTime() {
        ArrayList<ClockingTime> theList =new ArrayList<ClockingTime>();

        Employee employee1= new Employee("Jean","Truc");
        Date date1 = new Date();
        Time time1 = new Time();
        ClockingTime clockingTime1 = new ClockingTime(employee1,date1,time1);
        theList.add(clockingTime1);

        Employee employee2=new Employee("Jean","mi");
        Date date2 = new Date();
        Time time2 = new Time();
        ClockingTime clockingTime2 = new ClockingTime(employee1,date1,time1);
        theList.add(clockingTime2);

        ArrayList<ClockingTime> theNewList = new ArrayList<ClockingTime>();

        try{
            String path = (new File(clockingTimeDataManager.getFilePath())).getParentFile() + File.separator + "ClockingTimeTest.txt";
            clockingTimeDataManager.setFilePath(path);
            clockingTimeDataManager.serializeClockingTime(theList);
            theNewList.addAll(clockingTimeDataManager.parseClockingTime());

            Assertions.assertEquals(clockingTime1.toString(),theNewList.get(0).toString());
            Assertions.assertEquals(clockingTime2.toString(),theNewList.get(1).toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testSerializeClockingTime_emptyFile(){
        ArrayList<ClockingTime> theNewList = new ArrayList<ClockingTime>();

        String path = (new File(clockingTimeDataManager.getFilePath())).getParentFile() + File.separator + "emptyFileTest.txt";
        clockingTimeDataManager.setFilePath(path);
        try{
            theNewList.addAll(clockingTimeDataManager.parseClockingTime());
            Assertions.assertEquals(new ArrayList<ClockingTime>(1),theNewList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testSerializeClockingTime_ThrowsExceptions(){
        clockingTimeDataManager.setFilePath("");

        Assertions.assertThrows(FileNotFoundException.class,()->clockingTimeDataManager.parseClockingTime());
    }
}
