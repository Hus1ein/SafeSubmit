package com.hussain.safesubmit.services;

import com.hussain.safesubmit.rdb.entities.Entry;
import com.hussain.safesubmit.rdb.repositories.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnCompleteListenerImpl implements OnCompleteListener{

    private final EntryRepository entryRepository;

    @Override
    public void onSuccessListener(String id) {
        Entry entry = entryRepository.findById(id).orElse(null);
        if (entry == null) {
            return;
        }

        entry.setSent(true);
        entryRepository.save(entry);
    }

    @Override
    public void onFailedListener(String id, String errorMessage) {
        Entry entry = entryRepository.findById(id).orElse(null);
        if (entry == null) {
            return;
        }

        entry.setSent(false);
        entry.setErrorMessage(errorMessage);
        entryRepository.save(entry);
    }

}
