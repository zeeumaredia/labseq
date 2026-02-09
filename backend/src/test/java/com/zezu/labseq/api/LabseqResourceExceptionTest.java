package com.zezu.labseq.api;

import com.zezu.labseq.service.LabseqService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class LabseqResourceExceptionTest {

    @Test
    void negativeN_returns400_withErrorPayload() {
        given()
                .when().get("/labseq/-1")
                .then()
                .statusCode(400)
                .contentType("application/json")
                .body("code", is("INVALID_NUMBER"))
                .body("message", not(isEmptyOrNullString()))
                .body("n", is(-1));
    }

    @Test
    void tooLargeN_returns413_withErrorPayload() {
        long max = LabseqService.MAX_N;
        long tooLarge = max + 1;

        given()
                .when().get("/labseq/" + tooLarge)
                .then()
                .statusCode(413)
                .contentType("application/json")
                .body("code", is("NUMBER_TOO_LARGE"))
                .body("message", not(isEmptyOrNullString()))
                .body("n", is((int) tooLarge))
                .body("maxN", is((int) max));
    }
}
