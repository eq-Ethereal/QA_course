package api.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import steps.folder.Folder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class YandexAPITests {
    private static String name;
    private static int code;
    @BeforeAll
    public static void setup(){
        name = generateRandomString(5);
    }

    @Test
    public void createAndDeleteFolder() {
        Folder.createFolder(name);
        Folder.deleteFolder(name);
        code = Folder.getFolderStatusCode(name);
        Assertions.assertEquals(code,404);
    }
    private static String generateRandomString(int length) {

        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
