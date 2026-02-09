package com.zezu.labseq.service;

import com.zezu.labseq.domain.LabseqCache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigInteger;

@ApplicationScoped
public class LabseqService {

    @Inject
    LabseqCache cache;

    public BigInteger valueAt(long n) {
        return cache.get(n);
    }
}
