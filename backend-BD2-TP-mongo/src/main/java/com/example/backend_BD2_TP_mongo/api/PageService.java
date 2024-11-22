package com.example.backend_BD2_TP_mongo.api;

import com.example.backend_BD2_TP_mongo.domain.Page;
import com.example.backend_BD2_TP_mongo.domain.dto.PageDTO;

import java.util.List;

public interface PageService {
    PageDTO findById(String id);

    void insertPage(PageDTO page);
}