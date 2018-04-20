package com.sys.core.util.image;


import com.sys.core.util.filestream.FileUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.log4j.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/*试试启动jvm时加上 -Djava.awt.headless=true

找到解决的办法了
1.System.setProperty("java.awt.headless", "true");
2.启动tomcat加上 startup.sh -Djava.awt.headless=true */
public class ImageUtil {
// ------------------------------ FIELDS ------------------------------

    private static final Logger logger = Logger.getLogger(ImageUtil.class);
    private static Component component = new Canvas();
    // ".pcx","tga",".tif"这三种格式目前还不支持；
    // 这些定义的格式经过我测试过是可以支持的。
    private static String[] imageFormatArray = new String[]{".jpg", ".jpeg", ".gif", ".png", ".bmp"};

// --------------------------- main() method ---------------------------


    public static void main(String[] args) throws Exception {
    	
    	String targetImg="e:/1.jpg";
//    	File delFile=new File(targetImg);
//    	delFile.delete();
//    	
//    	if(delFile.exists()==false){
//    		
//        	FileUtil.copyFile("E:/网赚/正品运动代理/尚佳/水印资料/use/大背景白色644-448.png", "e:/1.jpg");
//        	
//    	}
//    	
//    	int x=getWidth(targetImg);
//    	
//    	int y=getHeight(targetImg);
//      ImageUtil imageUtil = new ImageUtil();
//      imageUtil.listFormat();
//      imageUtil.pressText("￥322", targetImg, "仿宋体", Font.PLAIN, Color.orange , 12, x-65,y-17);
//      imageUtil.pressText("￥522", targetImg, "华文细黑", Font.PLAIN, Color.darkGray , 12, x-65,y-25);
    	
    	String rightTopImgStr="E:/网赚/正品运动代理/尚佳/水印资料/use/正品大.png";
    	
    	int x=getWidth(targetImg);
    	
    	int y=getHeight(targetImg);
    	
    	ImageUtil imageUtil = new ImageUtil();   
    	  
        imageUtil.listFormat();
    	
    	int pressX=imageUtil.getWidth(rightTopImgStr);
    	int pressY=imageUtil.getHeight(rightTopImgStr);
    	
//    	imageUtil.createZoomSizeImage("e:/2.jpg","e:/2.jpg",0.8);
//        
//        
//        imageUtil.pressImage("e:/2.jpg", targetImg, (x-pressX)/2, (y-pressY)/2);
    	
//    	imageUtil.listFormat();
//    	imageUtil. pressImage(rightTopImgStr, targetImg,x-pressX,0);
        
        
    	
      
    }
    
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) throws IOException   
    {   
        int type = source.getType();   
        BufferedImage target = null;   
        double sx = (double) targetW / source.getWidth();   
        double sy = (double) targetH / source.getHeight();   
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放   
        // 则将下面的if else语句注释即可   
        if (sx > sy)   
        {   
            sx = sy;   
            targetW = (int) (sx * source.getWidth());   
        }   
        else  
        {   
            sy = sx;   
            targetH = (int) (sy * source.getHeight());   
        }   
        if (type == BufferedImage.TYPE_CUSTOM)   
        { // handmade   
            ColorModel cm = source.getColorModel();   
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,   
                    targetH);   
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();   
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);   
        }   
        else  
        {   
            //固定宽高，宽高一定要比原图片大   
            //target = new BufferedImage(targetW, targetH, type);   
            target = new BufferedImage(240, 172, type);   
        }   
           
        Graphics2D g = target.createGraphics();   
           
        //写入背景   
        g.drawImage(ImageIO.read(new File("E:/网赚/正品运动代理/尚佳/水印资料/use/白色背景图.png")), 0, 0, null);   
           
        // smoother than exlax:   
        g.setRenderingHint(RenderingHints.KEY_RENDERING,   
                RenderingHints.VALUE_RENDER_QUALITY);   
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));   
        g.dispose();   
        return target;   
    }  

    /**
     * 查看图像I/O库所支持的图像格式有哪些格式
     */
    public void listFormat() {
        String readerMIMETypes[] = ImageIO.getReaderMIMETypes();
        String writerMIMETypes[] = ImageIO.getWriterMIMETypes();
        if (logger.isInfoEnabled()) {
            logger.info("ReaderMIMETypes:" + Arrays.asList(readerMIMETypes));
            logger.info("WriterMIMETypes:" + Arrays.asList(writerMIMETypes));
        }
    }

    /**
     * 将目录下的所有图像进行放大缩小
     *
     * @param strDir    图像的目录
     * @param zoomRatio 放大缩小的倍率
     * @param rebuild   是否重新创建，即已经存在的图像是否覆盖重建
     * @throws Exception Exception
     */
    public void zoom(String strDir, double zoomRatio, boolean rebuild) throws Exception {
        File fileDir = new File(strDir);
        if (!fileDir.exists()) {
            logger.warn("Not exist:" + strDir);
            return;
        }
        String dirTarget = strDir + "/Zoom" + zoomRatio;
        File fileTarget = new File(dirTarget);
        if (!fileTarget.exists()) {
            fileTarget.mkdir();
        }
        File[] files = fileDir.listFiles();
        StringBuilder stringBuilder;
        for (File file : files) {
            String fileFullName = file.getCanonicalPath();
            String fileShortName = file.getName();
            if (!new File(fileFullName).isDirectory())// 排除二级目录，如果想就再递归一次，这里省略
            {
                if (isZoomAble(fileShortName)) {
                    if (logger.isInfoEnabled()) {
                        logger.info("Begin Zoom:" + fileFullName);
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(dirTarget).append("/").append(fileShortName);
                    if (!new File(stringBuilder.toString()).exists() || rebuild) {
                        try {
                            createZoomSizeImage(fileFullName, stringBuilder.toString(), zoomRatio);
                        }
                        catch (Exception e) {
                            logger.error("createZoomSizeImage Error:" + fileFullName, e);
                        }
                    }
                    if (logger.isInfoEnabled()) {
                        logger.info("End Zoom:" + fileFullName);
                    }
                } else {
                    logger.warn("Can't Zoom:" + fileFullName);
                }
            }
        }
    }

    /**
     * 校验图像文件的格式是否可以进行缩放
     *
     * @param fileName fileName
     * @return boolean iszoom able
     */
    public boolean isZoomAble(String fileName) {
        boolean result = false;
        for (String imageFormat : imageFormatArray) {
            if (fileName.toLowerCase().lastIndexOf(imageFormat) == (fileName.length() - imageFormat.length())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 按比例进行放大缩小图像，zoomRatio = 1为原大，zoomRatio > 1为放大，zoomRatio < 1 为缩小
     *
     * @param fileName       source file name
     * @param fileNameTarget target file name
     * @param zoomRatio      zoom ratio
     * @throws Exception exception
     */
    public void createZoomSizeImage(String fileName, String fileNameTarget, double zoomRatio) throws Exception {
        Image image = ImageIO.read(new File(fileName));
        int width = new Double(image.getWidth(null) * zoomRatio).intValue();
        int height = new Double(image.getHeight(null) * zoomRatio).intValue();
        AreaAveragingScaleFilter areaAveragingScaleFilter = new AreaAveragingScaleFilter(width, height);
        FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(), areaAveragingScaleFilter);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = bufferedImage.createGraphics();
        graphics.drawImage(component.createImage(filteredImageSource), 0, 0, null);
        ImageIO.write(bufferedImage, "JPEG", new File(fileNameTarget));
    }

    public void cropImage(String fileName, String fileNameTarget, int x, int y, int w, int h) throws Exception {
        Image sourceImage = ImageIO.read(new File(fileName));
        
        ImageFilter cropFilter = new CropImageFilter(x, y, w, h);//四个参数分别为图像起点坐标和宽高，即CropImageFilter(int x,int y,int width,int height)，详细情况请参考API
        FilteredImageSource filteredImageSource = new FilteredImageSource(sourceImage.getSource(), cropFilter);
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = bufferedImage.createGraphics();
        graphics.drawImage(component.createImage(filteredImageSource), 0, 0, null);
        ImageIO.write(bufferedImage, "JPEG", new File(fileNameTarget));
    }

    public static String getPressImgPath() {
        return "/template/data/util/shuiyin.gif";
    }

    /**
     * 把图片印刷到图片上
     *
     * @param pressImg -- 水印文件
     * @param targetImg -- 目标文件
     * @param x         -- 偏移量x
     * @param y         -- 偏移量y
     */
    public static void pressImage(String pressImg, String targetImg, int x, int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            // 水印文件
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
//            g.drawImage(src_biao, wideth - wideth_biao - x, height - height_biao - y, wideth_biao,
//                    height_biao, null);
            
	          g.drawImage(src_biao, x,  y, wideth_biao,
	          height_biao, null);
            // /
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印文字水印图片
     *
     * @param pressText --文字
     * @param targetImg -- 目标图片
     * @param fontName -- 字体名
     * @param fontStyle -- 字体样式
     * @param color     -- 字体颜色
     * @param fontSize -- 字体大小
     * @param x         -- 偏移量x
     * @param y         -- 偏移量y
     */

    public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, String color, int fontSize, int x, int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.createGraphics();
            graphics.drawImage(src, 0, 0, wideth, height, null);
            // String s="www.qhd.com.cn";
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4), 16);

            graphics.setColor(new Color(r, g, b));
            graphics.setFont(new Font(fontName, fontStyle, fontSize));


            graphics.drawString(pressText, wideth - fontSize - x, height - fontSize / 2 - y);
            graphics.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    
    public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, Color color, int fontSize, int x, int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.createGraphics();
            graphics.drawImage(src, 0, 0, wideth, height, null);
            // String s="www.qhd.com.cn";
//            int r = Integer.parseInt(color.substring(0, 2), 16);
//            int g = Integer.parseInt(color.substring(2, 4), 16);
//            int b = Integer.parseInt(color.substring(4), 16);
//
            graphics.setColor(color);
            graphics.setFont(new Font(fontName, fontStyle, fontSize));


            graphics.drawString(pressText, wideth - fontSize - x, height - fontSize / 2 - y);
            graphics.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 读取GIF文件，并进行缩放，存放于BufferedImage数组中
     *
     * @param inputFileName source image file
     * @param zoomRatio     zoom ratio > 1 zoom in; < 1 zoom out;
     * @return BufferedImage Array
     * @throws IOException IOException
     */
    public BufferedImage[] readGifFile(String inputFileName, double zoomRatio) throws IOException {
        Iterator imageReaders = ImageIO.getImageReadersBySuffix("GIF");
        if (!imageReaders.hasNext()) {
            throw new IOException("no ImageReaders for GIF");
        }
        ImageReader imageReader = (ImageReader) imageReaders.next();
        File file = new File(inputFileName);
        if (!file.exists()) {
            throw new IOException("no file: " + file.getName());
        }
        imageReader.setInput(ImageIO.createImageInputStream(file));
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        for (int i = 0; true; ++i) {
            try {
                Image image = imageReader.read(i);
                int width = new Double(image.getWidth(null) * zoomRatio).intValue();
                int height = new Double(image.getHeight(null) * zoomRatio).intValue();
                if (width > 0 && height > 0) {
                    AreaAveragingScaleFilter areaAveragingScaleFilter = new AreaAveragingScaleFilter(width, height);
                    FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(), areaAveragingScaleFilter);
                    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                    Graphics graphics = bufferedImage.createGraphics();
                    graphics.drawImage(component.createImage(filteredImageSource), 0, 0, null);
                    images.add(bufferedImage);
                }
            }
            catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return images.toArray(new BufferedImage[images.size()]);
    }

    /**
     * 根据BufferedImage数组的数据，写入到GIF文件中去
     *
     * @param images         source images to put into GIF
     * @param outputFileName target file name
     * @throws java.io.IOException IOException
     */
    public void writeGifFile(BufferedImage[] images, String outputFileName) throws IOException {
        Iterator imageWriters = ImageIO.getImageWritersBySuffix("GIF");
        if (!imageWriters.hasNext()) {
            throw new IOException("no ImageWriters for GIF");
        }
        ImageWriter imageWriter = (ImageWriter) imageWriters.next();
        File file = new File(outputFileName);
        file.delete();
        imageWriter.setOutput(ImageIO.createImageOutputStream(file));
        if (imageWriter.canWriteSequence()) {
            if (logger.isInfoEnabled()) {
                logger.info("Using writeToSequence for format GIF");
            }
            imageWriter.prepareWriteSequence(null);
            for (BufferedImage image : images) {
                imageWriter.writeToSequence(new IIOImage(image, null, null), null);
            }
            imageWriter.endWriteSequence();
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("cross fingers for format GIF");
            }
            for (BufferedImage image : images) {
                imageWriter.write(image);
            }
        }
    }

    public static int getWidth(String fileName) {
        int width=0;
        Image image;
        try {
            image = ImageIO.read(new File(fileName));
            width = image.getWidth(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return width;
    }

    public static int getHeight(String fileName) {
        int height=0;
        Image image;
        try {
            image = ImageIO.read(new File(fileName));
            height = image.getHeight(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return height;
    }
}