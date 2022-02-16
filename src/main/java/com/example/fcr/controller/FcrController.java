package com.example.fcr.controller;


import com.example.fcr.repo.FcrRepo;
import com.example.fcr.service.FcrServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Tag(name = "Fcr Controller", description = "Результаты опроса")
public class FcrController {
    private FcrRepo fcrRepo;
    private FcrServiceImpl fcrService;

    public FcrController(FcrRepo fcrRepo, FcrServiceImpl fcrService) {
        this.fcrRepo = fcrRepo;
        this.fcrService = fcrService;
    }

    @GetMapping("/all/{start}/{end}")
    @Operation(
            summary = "Итоговый результат",
            description = "Количество получивших и ответивших"
    )
    public Map<String, Long> all(@PathVariable(name = "start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                 @PathVariable(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        LinkedHashMap<String, Long> map = fcrService.getAll(start, end);
        return map;
    }

    @GetMapping("/all/result/{start}/{end}")
    @Operation(
            summary = "Итоговый результат",
            description = "Количество ответивших да и нет"
    )
    public Map<String, Long> result(@PathVariable(name = "start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                 @PathVariable(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        LinkedHashMap<String, Long> map = fcrService.getResult(start, end);
        return map;
    }
}
