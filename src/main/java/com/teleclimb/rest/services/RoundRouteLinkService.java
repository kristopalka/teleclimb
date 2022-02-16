package com.teleclimb.rest.services;

import com.teleclimb.rest.repositories.RoundRouteLinkRepository;
import org.springframework.stereotype.Service;

@Service
public record RoundRouteLinkService(RoundRouteLinkRepository linkRepo) {
}
