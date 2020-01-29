package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class InMemoryTimeEntryRepository implements  TimeEntryRepository {
    private Map<Long, TimeEntry> map = new HashMap<>();
    long counter=0;

    public TimeEntry create(TimeEntry timeEntry) {


        TimeEntry timeEntry1 = new TimeEntry(++counter, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        map.put(counter, timeEntry1);
        System.out.println(timeEntry1.toString());
        return timeEntry1;
    }

    public TimeEntry find(long id) {

        return map.getOrDefault(id, null);
    }


    public List<TimeEntry> list() {

        return new ArrayList<>(map.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(map.get(id) == null) {
            return null;
        }
        TimeEntry timeEntry1 = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        map.put(id, timeEntry1);
        return map.get(id);
    }

    public void delete(long id) {
        map.remove(id);
    }
}
