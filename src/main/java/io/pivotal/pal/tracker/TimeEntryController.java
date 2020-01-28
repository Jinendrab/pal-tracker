package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }
    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        System.out.println(timeEntryToCreate.toString());
        return ResponseEntity.status(201).body(timeEntryRepository.create(timeEntryToCreate));

    }
    @GetMapping("/{TIME_ENTRY_ID}")
    public ResponseEntity<TimeEntry> read(@PathVariable(name="TIME_ENTRY_ID", required=true) long  timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if (timeEntry != null) return ResponseEntity.status(200).body(timeEntry);
        else return ResponseEntity.status(404).body(null);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.status(200).body(timeEntryRepository.list());
    }

    @PutMapping("/{TIME_ENTRY_ID}")
    public ResponseEntity update(@PathVariable(name="TIME_ENTRY_ID", required=true) long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId,expected);
        if (timeEntry != null) return ResponseEntity.status(200).body(timeEntry);
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{TIME_ENTRY_ID}")
    public ResponseEntity delete(@PathVariable(name="TIME_ENTRY_ID", required=true) long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.status(204).body(null);
    }
}
