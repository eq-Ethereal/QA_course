package api.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.file.File;
import steps.folder.Folder;
import steps.trash.Trash;

import java.util.Random;


public class YandexAPITests {
    private static String folderName;
    private static String fileName;

    @BeforeEach
    public void setup() {
        folderName = generateRandomString(5);
        fileName = generateRandomString(10);
    }

    @Test
    public void createAndDeleteFolder() {
        Folder.createFolder(folderName);
        Folder.deleteFolder(folderName);
        Assertions.assertEquals(400, Folder.getFolderStatusCode(folderName));
    }

    @Test
    public void createFolderAndFileDeleteThem() throws InterruptedException {
        Folder.createFolder(folderName);
        File.addFileToFolder(fileName, folderName);
        Thread.sleep(300);//awaitility dll
        File.deleteFile(folderName, fileName);
        Assertions.assertEquals(400, File.getFileStatusCode(folderName,fileName));
        Folder.deleteFolder(folderName);
        Assertions.assertEquals(404, Folder.getFolderStatusCode(folderName));
    }
    @Test
    public void createFolderAndFileMoveToTrashAndRestoreThenDeleteThem() throws InterruptedException {
        Folder.createFolder(folderName);
        File.addFileToFolder(fileName, folderName);
        Thread.sleep(300);
        File.deleteFile(folderName, fileName);
        Trash.restoreFileFromTrash(fileName,folderName);
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
