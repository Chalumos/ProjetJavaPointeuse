package fr.univtours.polytech.projet_tutore.model.data_manager;

import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class ClockingTimeDataManagerTest {
    private ClockingTimeDataManager clockingTimeDataManager;

    @BeforeEach
    void setUp(){
        clockingTimeDataManager= new ClockingTimeDataManager();
    }

    @Test
    void testSerialize() {
        ArrayList<ClockingTime> clockingTimeList = Stub.getClockingTimeList();
        ArrayList<ClockingTime> newClockingTimeList = new ArrayList<>();

        String path = (new File(clockingTimeDataManager.getFilePath())).getParentFile().getParentFile().getPath();
        path += File.separator + "data_test" + File.separator + "ClockingTime.txt";

        try {
            clockingTimeDataManager.setFilePath(path);
            clockingTimeDataManager.serialize(clockingTimeList);
            newClockingTimeList.addAll(clockingTimeDataManager.parse());

            for (int i = 0; i < clockingTimeList.size(); i++) {
                ClockingTime clockingTime = clockingTimeList.get(i);
                ClockingTime newClockingTime = newClockingTimeList.get(i);

                Assertions.assertEquals(clockingTime.toString(), newClockingTime.toString());
            }
        }
        catch(Exception e){
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
