package util;

import junit.framework.TestCase;

import java.io.File;

public class ImageUtilTest extends TestCase {
    public void testLoadImage() {
        String srcDir = System.getProperty("user.dir") + "/src";
        File dir = new File(srcDir);

        if (dir.isDirectory()) {
            String[] files = dir.list();
            if (files != null) {
                System.out.println("Conteúdo do diretório src:");
                for (String file : files) {
                    System.out.println(file);
                }
            } else {
                System.out.println("O diretório está vazio ou não pode ser listado.");
            }
        } else {
            System.out.println("Não é um diretório.");
        }
        assertNull(ImageUtil.create("images/bogusFilename.gif"));
        System.out.println();
        assertNotNull(ImageUtil.create("images/courses.gif"));
    }
}
