package com.hussain.safesubmit.services;

import com.google.gson.Gson;
import com.hussain.safesubmit.rdb.entities.Entry;
import com.hussain.safesubmit.rdb.repositories.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntryPointServiceImpl<T> implements EntryPointService<T> {

    private final EntryRepository entryRepository;
    private final ExitPointService<T> exitPointService;

    @Override
    public String passData(T data, String dataType) {
        Entry entry = Entry.builder()
                .id(UUID.randomUUID().toString())
                .data(new Gson().toJson(data))
                .dataType(dataType)
                .addedAt(new Date())
                .lastRetryDate(null)
                .retryCount(0)
                .isSent(false)
                .errorMessage(null)
                .build();

        entry = entryRepository.save(entry);

        exitPointService.sendDataToTarget(entry.getId(), data);

        return entry.getId();
    }

}
