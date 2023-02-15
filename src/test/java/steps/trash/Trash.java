package steps.trash;

import config.Const;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Trash {
    public static void restoreFileFromTrash(String fileName, String folderName) {
        given()
                .header("Authorization", Const.YANDEX_DISK_API_KEY)
                .baseUri(Const.BASE_URI)
                .when()
                .contentType(ContentType.JSON)
                .put("/v1/disk/trash/resources/restore?path=disk:/" + folderName +
                        "/" + fileName + "&url=" + Const.RANDOM_IMG)
                .then()
                .statusCode(202);
    }
}
