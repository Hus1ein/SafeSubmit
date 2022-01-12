package com.hussain.safesubmit.rdb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

    @Id
    private String id;
    private String data;
    private String dataType;
    private Date addedAt;
    private Date lastRetryDate;
    private int retryCount;
    private boolean isSent;
    private String errorMessage;

}
