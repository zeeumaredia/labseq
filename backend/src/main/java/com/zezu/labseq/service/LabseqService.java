package com.zezu.labseq.service;

import com.zezu.labseq.domain.LabseqCache;
import com.zezu.labseq.exception.InvalidNumberException;
import com.zezu.labseq.exception.NumberTooLargeException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigInteger;

@ApplicationScoped
public class LabseqService {

    @Inject
    LabseqCache cache;

    public static final long MAX_N = 200_000;

    public BigInteger valueAt(long n) {
        if (n < 0) throw new InvalidNumberException(n);
        if (n > MAX_N) throw new NumberTooLargeException(n, MAX_N);
        return cache.get(n);
    }
}
