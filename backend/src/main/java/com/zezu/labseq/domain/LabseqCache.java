package com.zezu.labseq.domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@ApplicationScoped
public class LabseqCache {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // Prefix cache: values.get(i) == l(i)
    private final List<BigInteger> values = new ArrayList<>(1024);

    public LabseqCache() {
        // l(0)=0, l(1)=1, l(2)=0, l(3)=1
        values.add(BigInteger.ZERO);
        values.add(BigInteger.ONE);
        values.add(BigInteger.ZERO);
        values.add(BigInteger.ONE);
    }

    /** Returns l(n), computing missing prefix if needed. */
    public BigInteger get(long n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        int target = safeIndex(n);

        // Fast path: already computed
        lock.readLock().lock();
        try {
            if (target < values.size()) {
                return values.get(target);
            }
        } finally {
            lock.readLock().unlock();
        }

        // Compute missing prefix under write lock
        lock.writeLock().lock();
        try {
            while (values.size() <= target) {
                int i = values.size();
                BigInteger next = values.get(i - 4).add(values.get(i - 3));
                values.add(next);
            }
            return values.get(target);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private static int safeIndex(long n) {
        if (n > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "n is too large for this in-memory implementation: " + n
            );
        }
        return (int) n;
    }
}
