package steps.folder;


import config.Const;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Folder {

    public static void createFolder(String name) {
        given()
                .header("Authorization", Const.YANDEX_DISK_API_KEY)
                .baseUri(Const.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .put("/v1/disk/resources?path=disk:/" + name)
                .then()
                .statusCode(201)
                //.extract().body().as(CreateFolder.class);
                .extract().response().jsonPath().get("href");
    }

    public static void deleteFolder(String folderName) {
        given()
                .header("Authorization", Const.YANDEX_DISK_API_KEY)
                .baseUri(Const.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .delete("/v1/disk/resources?path=disk:/" + folderName)
                .then()
                .statusCode(204);
    }

    public static int getFolderStatusCode(String folderName) {
        return given()
                .header("Authorization", Const.YANDEX_DISK_API_KEY)
                .baseUri(Const.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .get("/v1/disk/resources?path=disk:/" + folderName)
                .then()
                .extract().statusCode();
    }

}
