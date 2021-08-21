package com.example.readwordspring.contract;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReadWordContract {
    @GetMapping(path = "api/readwordexample")
    void readWordExample();

    @GetMapping(path = "api/readword")
    void readWord(@RequestParam(name = "info_file", defaultValue = "") Byte[] bytesFiles);
}
