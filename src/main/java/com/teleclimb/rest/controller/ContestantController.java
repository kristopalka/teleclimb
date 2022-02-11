package com.teleclimb.rest.controller;

import com.teleclimb.rest.repository.ContestantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContestantController {
    private final ContestantRepository repo;
}
