package com.example.springboot.service;

import com.example.springboot.dto.VocabListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DbService {
    List<VocabListDTO> getAllData();
}
