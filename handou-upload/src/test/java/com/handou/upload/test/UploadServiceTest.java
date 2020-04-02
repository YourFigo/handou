package com.handou.upload.test;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author Figo
 * @Date 2020/4/2 13:15
 */
public class UploadServiceTest {

    @Test
    public void testFile() throws IOException {
        System.out.println(new File("").getCanonicalPath() + "\\upload\\image");
    }
}
