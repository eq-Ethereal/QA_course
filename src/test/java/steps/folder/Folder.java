package steps.folder;


import config.Config;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Folder {

    public static String createFolder(String name) {
        return given()
                .header("Authorization", Config.YANDEX_DISK_API_KEY)
                .baseUri(Config.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .put("/v1/disk/resources?path=" + name)
                .then().log().all()
                .statusCode(201)
                //.extract().body().as(CreateFolder.class);
                .extract().response().jsonPath().get("href");
    }
    public static void deleteFolder(String folderName) {
        given()
                .header("Authorization", Config.YANDEX_DISK_API_KEY)
                .baseUri(Config.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .delete("/v1/disk/resources?path=disk:/" + folderName)
                .then().log().all()
                .statusCode(204);
    }

    public static int getFolderStatusCode(String folderName) {
        return given()
                .header("Authorization", Config.YANDEX_DISK_API_KEY)
                .baseUri(Config.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .get("/v1/disk/resources?path=disk:/" + folderName)
                .then().log().all()
                .extract().statusCode();
    }

}
