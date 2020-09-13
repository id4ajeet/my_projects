
package com.ajeet.impactt.api;

import com.ajeet.impactt.service.dto.PhoneBookDto;
import com.ajeet.impactt.service.ifc.IPhoneBookRepoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@RestController
@RequestMapping("/phonebook")
public class PhoneBookController {

    private final IPhoneBookRepoService phoneBookService;

    @Autowired
    public PhoneBookController(IPhoneBookRepoService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PhoneBookDto> get() {
        return phoneBookService.findAll();
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PhoneBookDto get(@PathVariable long id) {
        return phoneBookService.find(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public String post(@RequestBody PhoneBookDto request) {
        boolean save = phoneBookService.save(request);
        return save ? "SUCCESS" : "FAILED";
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public String put(@RequestBody PhoneBookDto request) {
        boolean save = phoneBookService.save(request);
        return save ? "SUCCESS" : "FAILED";
    }

    @PutMapping(path = "/all", consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public String putAll(@RequestBody List<PhoneBookDto> request) {
        boolean save = phoneBookService.saveAll(request);
        return save ? "SUCCESS" : "FAILED";
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        phoneBookService.delete(id);
    }
}
