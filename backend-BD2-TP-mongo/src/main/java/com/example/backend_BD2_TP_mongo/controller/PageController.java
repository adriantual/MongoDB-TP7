package com.example.backend_BD2_TP_mongo.controller;

import com.example.backend_BD2_TP_mongo.api.PageService;
import com.example.backend_BD2_TP_mongo.domain.dto.PageDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/{id}")
    public List<PageDTO> getPageById(@PathVariable String id) {
        //return pageService.findById(id);
        return Collections.singletonList(this.pageService.findById(id));
    }


    @PostMapping("/insert")
    public ResponseEntity<String> createPage2(@Valid @RequestBody PageDTO pageDTO) {
        // Procesa el DTO
        pageService.insertPage(pageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Page created");
    }

}