package com.hussain.safesubmit.rdb.repositories;

import com.hussain.safesubmit.rdb.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, String> {

    List<Entry> findAllByIsSentTrue();

    List<Entry> findAllByIsSentFalse();

    void deleteAllByIdIn(List<String> ids);

}
