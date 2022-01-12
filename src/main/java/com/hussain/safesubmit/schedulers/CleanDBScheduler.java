package com.hussain.safesubmit.schedulers;

import com.hussain.safesubmit.rdb.entities.Entry;
import com.hussain.safesubmit.rdb.repositories.EntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class CleanDBScheduler {

    private final EntryRepository entryRepository;

    //@Scheduled(fixedDelayString = "${scheduler.clean-db-delay}")
    public void cleanDB() {
      log.info("Start cleaning DB...");

      List<Entry> entryList = entryRepository.findAllByIsSentTrue();
      entryRepository.deleteAllByIdIn(entryList
              .stream()
              .map(Entry::getId)
              .collect(Collectors.toList())
      );

      log.info("{} items were deleted.", entryList.size());
    }

}
