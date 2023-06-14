package org.com.springframework.json.controllers;

import org.com.springframework.json.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class JsonController {
    @Autowired
    private JsonService jsonService;

    @PostMapping("json")
    public ResponseEntity process() throws IOException {
        String jsonString = "[{\"id\":\"1\",\"type\":{\"code\":\"1\",\"name\":\"Physical Address\"},\"addressLineDetail\":{\"line1\":\"Address 1\",\"line2\":\"Line 2\"},\"provinceOrState\":{\"code\":\"5\",\"name\":\"Eastern Cape\"},\"cityOrTown\":\"City 1\",\"country\":{\"code\":\"ZA\",\"name\":\"South Africa\"},\"postalCode\":\"1234\",\"lastUpdated\":\"2015-06-21T00:00:00.000Z\"}" +
                ",{\"id\":\"2\",\"type\":{\"code\":\"2\",\"name\":\"Postal Address\"},\"cityOrTown\":\"City 2\",\"country\":{\"code\":\"LB\",\"name\":\"Lebanon\"},\"postalCode\":\"23456\",\"lastUpdated\":\"2017-06-21T00:00:00.000Z\"}," +
                "{\"id\":\"3\",\"type\":{\"code\":\"5\",\"name\":\"Business Address\"},\"addressLineDetail\":{\"line1\":\"Address 3\",\"line2\":\"\"},\"cityOrTown\":\"City 3\",\"country\":{\"code\":\"ZA\",\"name\":\"South Africa\"},\"postalCode\":\"3456\",\"lastUpdated\":\"2018-06-13T00:00:00.000Z\"}]";

        String processedPerson = jsonService.processJson(jsonString);
        return ResponseEntity.ok(processedPerson);
    }
}
