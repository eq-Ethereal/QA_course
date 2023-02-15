package steps.file;

import config.Const;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class File {
    public static void addFileToFolder(String fileName, String folderName) {
        given()
                .header("Authorization", Const.YANDEX_DISK_API_KEY)
                .baseUri(Const.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .post("/v1/disk/resources/upload?path=" + folderName + "/" + fileName + "&url=" + Const.RANDOM_IMG)
                .then()
                .statusCode(202);
    }

    public static void deleteFile(String folderName, String fileName) {
         given()
                .header("Authorization", Const.YANDEX_DISK_API_KEY)
                .baseUri(Const.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .delete("/v1/disk/resources?path=disk:/" + folderName + "/" + fileName)
                .then().log().all()
                .statusCode(204);
    }

    public static int getFileStatusCode(String folderName,String fileName) {
        return given()
                .header("Authorization", Const.YANDEX_DISK_API_KEY)
                .baseUri(Const.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .get("/v1/disk/resources?path=disk:/" + folderName + "/" + fileName)
                .then()
                .extract().statusCode();
    }
}
