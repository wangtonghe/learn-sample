package common;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author wangtonghe
 * @date 2017/4/17 22:18
 */
public class TestFile {

    @Test
    public void test() throws IOException{
        File file = new File("test.txt");
        if(!file.exists()){
            boolean flag =  file.createNewFile();
            System.out.println(flag);
        }
    }
}
