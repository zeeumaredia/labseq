package com.zezu.labseq.exception;

public class InvalidNumberException extends RuntimeException {

    private final long n;

    public InvalidNumberException(long n) {
        super("Input number must be a non-negative Integer.");
        this.n = n;
    }

    public long getN() {
        return n;
    }
}
