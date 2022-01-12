package com.hussain.safesubmit.schedulers;

import com.google.gson.Gson;
import com.hussain.safesubmit.rdb.entities.Entry;
import com.hussain.safesubmit.rdb.repositories.EntryRepository;
import com.hussain.safesubmit.services.ExitPointService;
import com.hussain.safesubmit.test.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ResendEntriesScheduler {

    private final EntryRepository entryRepository;
    private final ExitPointService exitPointService;

    @Scheduled(fixedDelayString = "5000000")
    public void resendEntries() {
        log.info("Start resending entries...");

        List<Entry> entryList = entryRepository.findAllByIsSentFalse();
        entryList.forEach(entry -> {
            entry.setLastRetryDate(new Date());
            entry.setRetryCount(entry.getRetryCount() + 1);
            entryRepository.save(entry);
            try {
                Object object = new Gson().fromJson(entry.getData(), Class.forName(entry.getDataType()));
                exitPointService.sendDataToTarget(entry.getId(), object);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}
