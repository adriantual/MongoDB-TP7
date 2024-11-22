package com.example.backend_BD2_TP_mongo.services;

import com.example.backend_BD2_TP_mongo.api.PageService;
import com.example.backend_BD2_TP_mongo.domain.Page;
import com.example.backend_BD2_TP_mongo.domain.dto.PageDTO;
import com.example.backend_BD2_TP_mongo.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service

public class PageServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;


    @Override
    public PageDTO findById(String id) {
        return PageDTO.fromDocument(this.pageRepository.findById(id)); }

    @Override
    public void insertPage(PageDTO pageDTO) {
        Page page = convertToEntity(pageDTO);
        pageRepository.save(page);
    }



    private Page convertToEntity(PageDTO dto) {
        Page page = new Page();
        page.setTitle(dto.getTitle());
        page.setText(dto.getText());
        page.setAuthor(dto.getAuthor());
        page.setDate(new Date());
        return page;





    }
}
