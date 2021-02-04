package com.lightning.portal;

import com.lightning.portal.bean.Folder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;

@SpringBootTest
class PortalApplicationTests {

    @Test
    void contextLoads() {
        Folder folder = new Folder();
        System.out.println("folder.getFolderId() = " + folder);

    }

}
