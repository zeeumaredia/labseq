package com.zezu.labseq.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class LabseqResourceTest {

    @Test
    void baseCases() {
        given().when().get("/labseq/0").then().statusCode(200).body("value", is("0"));
        given().when().get("/labseq/1").then().statusCode(200).body("value", is("1"));
        given().when().get("/labseq/2").then().statusCode(200).body("value", is("0"));
        given().when().get("/labseq/3").then().statusCode(200).body("value", is("1"));
    }

    @Test
    void negativeIsBadRequest() {
        given().when().get("/labseq/-1").then().statusCode(400);
    }

    @Test
    void tooLargeIsRejected() {
        given().when().get("/labseq/200001").then().statusCode(413);
    }
}
