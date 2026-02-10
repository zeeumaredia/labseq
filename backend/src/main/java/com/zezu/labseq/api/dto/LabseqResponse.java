package com.zezu.labseq.api.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
    name = "LabseqResponse",
    description = "Response containing the Labseq sequence value for a given index"
)
public record LabseqResponse(@Schema(
        description = "Sequence index",
        example = "10"
    ) long n,
    @Schema(
        description = "Labseq value at index n (arbitrary precision integer)",
        example = "3"
    ) String value) 
    { }
