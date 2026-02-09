package com.zezu.labseq.api.error;

public record ApiError(String code, String message, Long n, Long maxN) {}

