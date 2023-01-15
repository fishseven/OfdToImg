package com.example.ofdtest2;

import org.ofdrw.converter.FontLoader;
import org.ofdrw.converter.ImageMaker;
import org.ofdrw.reader.OFDReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ofd转图片
 * input 源文件（ofd后缀文件）
 * output 生成文件（jpg后缀文件）
 * **/

public class OfdToImg {
    /***返回生成图片地址全路径集合***/
    public static List<String> ofdToImg(Img img)throws IOException{
        setFont();
        return toImg(img);
    }

    /**设置字体*/
    private static void setFont(){

        //为不规范的字体名创建映射
        FontLoader.getInstance()
                .addAliasMapping("小标宋体", "方正小标宋简体")
                .addAliasMapping("KaiTi_GB2312", "楷体")
                .addAliasMapping("楷体", "KaiTi")

                .addSimilarFontReplaceRegexMapping(".*Kai.*", "楷体")
                .addSimilarFontReplaceRegexMapping(".*Kai.*", "楷体")
                .addSimilarFontReplaceRegexMapping(".*MinionPro.*", "SimSun")
                .addSimilarFontReplaceRegexMapping(".*SimSun.*", "SimSun")
                .addSimilarFontReplaceRegexMapping(".*Song.*", "宋体")
                .addSimilarFontReplaceRegexMapping(".*MinionPro.*", "SimSun");

        FontLoader.getInstance().scanFontDir(new File("src/main/resources/fonts"));
        FontLoader.setSimilarFontReplace(true);
    }
    /**ofd转图片*/
    private static List<String> toImg(Img img) throws IOException {
//        log.info("ofd转图片开始: {}", JSON.toJSONString(img));
        List<String> list=new ArrayList<>();
        Files.createDirectories(Paths.get(img.getOutPutPath()));
        Path src = Paths.get(img.getInPut());

        try (OFDReader reader = new OFDReader(src);) {
            ImageMaker imageMaker = new ImageMaker(reader, 15);
            imageMaker.config.setDrawBoundary(false);
            for (int i = 0; i < imageMaker.pageSize(); i++) {
                BufferedImage image = imageMaker.makePage(i);
                Path dist = Paths.get(img.getOutPutPath(), img.getOutPutName()+i + "."+img.getType());
                ImageIO.write(image, img.getType().toUpperCase(), dist.toFile());
                list.add(i, img.getOutPutPath() + img.getOutPutName() + i +"."+img.getType());
            }
//            log.info("ofd转图片成功{}", JSON.toJSONString(list));
        }catch (Exception e){
//            log.error("ofd转图片失败{}", JSON.toJSONString(img));
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        String in="C:\\Users\\M\\Desktop\\032001900111_8888888.ofd";
        String outPath="C:\\Users\\M\\Desktop";
        String outName="ofd_"+System.currentTimeMillis()+"_";
        Img img = new Img();
        img.setInPut(in);
        img.setOutPutName(outName);
        img.setOutPutPath(outPath);
        img.setType("png");
        List<String> list=ofdToImg(img);
        System.out.println(list);
    }
}