package com.example.fcr.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;



public interface FcrService {
    LinkedHashMap<String, Long> getAll(LocalDate start, LocalDate end);
    LinkedHashMap<String, Long> getResult(LocalDate start, LocalDate end);
    Long[] getAnswered(List<String> result);
    Long[] getCount(List<String> allAnswers);
    Long transformation(Long answers, Long input);
}
