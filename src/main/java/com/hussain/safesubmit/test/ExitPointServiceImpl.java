package com.hussain.safesubmit.test;

import com.hussain.safesubmit.services.ExitPointService;
import com.hussain.safesubmit.services.OnCompleteListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExitPointServiceImpl implements ExitPointService<Student> {

    private RestTemplate restTemplate = new RestTemplate();
    private final OnCompleteListener onCompleteListener;

    @Override
    public void sendDataToTarget(String id, Student data) {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("https://hussain-test.free.beeceptor.com", data, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Response: {}", response.getBody());
                onCompleteListener.onSuccessListener(id);
            } else {
                log.error("Error while sending object...");
                onCompleteListener.onFailedListener(id, "Error by hussain!");
            }
        } catch (Exception e) {
            log.error("Error while sending object...");
            onCompleteListener.onFailedListener(id, "Error by hussain22!");
        }
    }

}
