package vn.ohana.controllers.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ohana.utility.UtilityService;
import vn.ohana.utility.dto.CreateUtilityParam;
import vn.ohana.utility.dto.UpdateUtilityParam;

@RestController
@RequestMapping("api/utilities")
@CrossOrigin("*")
public class UtilityAPI {
    @Autowired
    UtilityService utilityService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return new ResponseEntity<>( utilityService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{utilityId}")
    public ResponseEntity<?> findAll(@PathVariable int utilityId) {
        return new ResponseEntity<>(utilityService.getById(utilityId), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<?> createNew(@RequestBody CreateUtilityParam params) {
        return new ResponseEntity<>(utilityService.save(params), HttpStatus.OK);
    }
    @PatchMapping("/{utilityId}")
    public ResponseEntity<?> update(@PathVariable int utilityId,@RequestBody UpdateUtilityParam params) {
        return new ResponseEntity<>(utilityService.update(params), HttpStatus.OK);
    }
}
