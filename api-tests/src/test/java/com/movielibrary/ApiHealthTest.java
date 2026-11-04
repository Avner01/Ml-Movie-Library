package com.movielibrary;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiHealthTest {

    /*
     * בדיקת API ראשונה.
     *
     * GET = בקשה לקבלת מידע מהשרת.
     * HTTP 200 = הבקשה הצליחה.
     * TestNG = הכלי שמריץ את הבדיקה.
     * REST Assured = הספרייה ששולחת את הבקשה
     * ובודקת את התשובה.
     *
     * כרגע אנחנו בודקים שרת ציבורי לצורך לימוד.
     * בהמשך נחבר בדיקות ל-API של סרטים.
     */

    @Test
    public void shouldReturnSuccessfulResponse() {

        given()
            .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
}
