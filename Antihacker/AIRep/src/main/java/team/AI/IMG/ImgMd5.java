package team.AI.IMG;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class ImgMd5 {

public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        String path="";
        String path2="";

        FileInputStream fis= new FileInputStream(path);
        String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
        IOUtils.closeQuietly(fis);
        System.out.println("p1_MD5:"+md5);

        FileInputStream fis2= new FileInputStream(path2);
        String md52 = DigestUtils.md5Hex(IOUtils.toByteArray(fis2));
        IOUtils.closeQuietly(fis2);
        System.out.println("p2_MD5:"+md52);

    }
}
