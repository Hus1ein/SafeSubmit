package com.hussain.safesubmit.test;

import com.google.gson.Gson;
import com.hussain.safesubmit.rdb.entities.Entry;
import com.hussain.safesubmit.rdb.repositories.EntryRepository;
import com.hussain.safesubmit.services.EntryPointService;
import com.hussain.safesubmit.test.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(path = "/test")
@RequiredArgsConstructor
public class TestController {

   private final EntryPointService<Student> entryPointService;

    @GetMapping(path = "")
    public String test() {

        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName("Hussain Abdelilah");
        student.setEmail("hussain@hotmail.com");

        entryPointService.passData(student, "com.hussain.safesubmit.test.Student");

        return "Hi!";
    }

}
