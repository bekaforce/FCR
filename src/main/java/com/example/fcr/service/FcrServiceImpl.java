package com.example.fcr.service;

import com.example.fcr.repo.FcrRepo;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class FcrServiceImpl implements FcrService {
    private FcrRepo fcrRepo;

    public FcrServiceImpl(FcrRepo fcrRepo) {
        this.fcrRepo = fcrRepo;
    }

    @Override
    public LinkedHashMap<String, Long> getAll(LocalDate start, LocalDate end) {
        List<String> allAnswers = fcrRepo.getAll(start, end);
        LinkedHashMap<String, Long> hashMap = new LinkedHashMap<>();
        Long[] all = getCount(allAnswers);
        hashMap.put("Абоненты получившие опрос", all[0]);
        hashMap.put("Абоненты ответившие на опрос", all[1]);
        hashMap.put("Абоненты не ответившие на опрос", all[2]);
        return hashMap;
    }

    @Override
    public LinkedHashMap<String, Long> getResult(LocalDate start, LocalDate end) {
        List<String> result = fcrRepo.getResult(start, end);
        Long[] longs = getAnswered(result);
        Long allAnswers = longs[0] + longs[1] + longs[2];
        Long positive = transformation(allAnswers, longs[0]);
        Long negative = transformation(allAnswers, longs[1]);
        Long neutral = transformation(allAnswers, longs[2]);
        LinkedHashMap<String, Long> hashMap = new LinkedHashMap<>();
        hashMap.put("Количество ответивших да", longs[0]);
        hashMap.put("Количество ответивших нет", longs[1]);
        hashMap.put("Нейтральные ответы", longs[2]);
        hashMap.put("Проценты Да", positive);
        hashMap.put("Проценты Нет", negative);
        hashMap.put("Проценты Нейтральных", neutral);
        return hashMap;
    }

     @Override
     public Long[] getAnswered(List<String> result){
        long positive = result.stream()
                 .filter(w -> w.equals("1"))
                 .count();
         long negative = result.stream()
                 .filter(w -> w.equals("2"))
                 .count();
         long neutral = result.stream()
                 .filter(w -> w.equals("3"))
                 .count();
         Long[] longs = {positive, negative, neutral};
         return longs;
     }

    @Override
    public Long[] getCount(List<String> allAnswers) {
        long received = allAnswers.size();
        long answered = allAnswers.stream()
                .filter(w -> w.equals("1"))
                .count();
        long notAnswered = allAnswers.stream()
                .filter(w -> w.equals("2"))
                .count();
        Long[] all = {received, answered, notAnswered};
        return all;
    }

    @Override
    public Long transformation(Long answers, Long input){
        Double result = (double) input / (double) answers * 100;
        return Math.round(result);
    }
}
