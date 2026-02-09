package com.zezu.labseq.exception;

public class NumberTooLargeException extends RuntimeException{

    private final long n;
    private final long maxN;

    public NumberTooLargeException(long n, long maxN) {
        super("Input number is too large for this in-memory implementation");
        this.n = n;
        this.maxN = maxN;
    }

    public long getN() {
        return n;
    }

    public long getMaxN() {
        return maxN;
    }
}
