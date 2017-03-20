package com.oneself.cloud.provider.qrcode.util;

import java.awt.BasicStroke;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.Shape;  
import java.awt.geom.RoundRectangle2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.OutputStream;  
import java.util.Hashtable;  
import java.util.Random;  
import javax.imageio.ImageIO;
import org.springframework.util.StringUtils;
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel; 

/**
 * @author shiyajing
 * @E-mail shiyj@agree.com.cn
 * @version 2017年3月7日上午10:42:02
 */
public class QRCodeUtil {
	public static final String CHARSET = "UTF-8"; //字符集
	public static final String FORMAT = "JPG";//格式
    // 二维码尺寸  
	public static final int QRCODE_SIZE = 300;  
    
    // LOGO宽度  
    private static final int LOGO_WIDTH = 60;  
    // LOGO高度  
    private static final int LOGO_HEIGHT = 60;  
    
    /*前景色,默认黑色*/
    public static  int FORECOLOR = 0xFF000000;
	/*背景色，默认白色*/
    public static  int BACKCOLOR = 0xFFFFFFFF;
    
    /**
     * 绘制二维码 
     * @param content
     * @param logoPath
     * @param needCompress
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,  
                hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? FORECOLOR : BACKCOLOR);  
            }  
        }  
        if (logoPath == null || "".equals(logoPath)) {  
            return image;  
        }  
        // 插入图片  
        QRCodeUtil.insertImage(image, logoPath, needCompress);  
        return image;  
    }
    
    /**
     * 绘制二维码,自定义宽度和高度
     * @param content
     * @param logoPath
     * @param needCompress
     * @param qrWidth
     * @param qrHeight
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String logoPath, boolean needCompress,String qrWidth,String qrHeight,String characterSet,String onColor,String offColor) throws Exception {  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, StringUtils.isEmpty(characterSet)?CHARSET:characterSet);  
        hints.put(EncodeHintType.MARGIN, 1);  												
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,StringUtils.isEmpty(qrWidth)?QRCODE_SIZE:Integer.parseInt(qrWidth), StringUtils.isEmpty(qrHeight)?QRCODE_SIZE:Integer.parseInt(qrHeight),  
                hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();
        int backgroundColor=StringUtils.isEmpty(onColor)?FORECOLOR:toInt(onColor);
        int frontColor=StringUtils.isEmpty(onColor)?BACKCOLOR:toInt(offColor);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ?backgroundColor : frontColor);  
            }  
        }  
        if (logoPath == null || "".equals(logoPath)) {  
            return image;  
        }  
        // 插入图片  
        QRCodeUtil.insertImage(image, logoPath, needCompress);  
        return image;  
    }
    
    /** 
     * 插入LOGO 
     *  
     * @param source 
     *            二维码图片 
     * @param logoPath 
     *            LOGO图片地址 
     * @param needCompress 
     *            是否压缩 
     * @throws Exception 
     */  
    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {  
        File file = new File(logoPath);  
        if (!file.exists()) {  
            throw new Exception("logo file not found.");  
        }  
        Image src = ImageIO.read(new File(logoPath));  
        int width = src.getWidth(null);  
        int height = src.getHeight(null);  
        if (needCompress) { // 压缩LOGO  
            if (width > LOGO_WIDTH) {  
                width = LOGO_WIDTH;  
            }  
            if (height > LOGO_HEIGHT) {  
                height = LOGO_HEIGHT;  
            }  
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);  
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            src = image;  
        }  
        // 插入LOGO  
        Graphics2D graph = source.createGraphics();  
        int x = (QRCODE_SIZE - width) / 2;  
        int y = (QRCODE_SIZE - height) / 2;  
        graph.drawImage(src, x, y, width, height, null);  
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
        graph.setStroke(new BasicStroke(3f));  
        graph.draw(shape);  
        graph.dispose();  
    }  
    
    /** 
     * 生成二维码(内嵌LOGO) 
     * 二维码文件名随机，文件名可能会有重复 
     *  
     * @param content 
     *            内容 
     * @param logoPath 
     *            LOGO地址 
     * @param destPath 
     *            存放目录 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static String encode(String content, String logoPath, String destPath, boolean needCompress) throws Exception {  
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
        mkdirs(destPath);  
        String fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();  
        ImageIO.write(image, FORMAT, new File(destPath + "/" + fileName));  
        return fileName;  
    }  
    
    /** 
     * 生成二维码(内嵌LOGO) 
     * 调用者指定二维码文件名 
     *  
     * @param content 
     *            内容 
     * @param logoPath 
     *            LOGO地址 
     * @param destPath 
     *            存放目录 
     * @param fileName 
     *            二维码文件名 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static String encode(String content, String logoPath, String destPath, String fileName, boolean needCompress) throws Exception {  
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
        mkdirs(destPath);  
        fileName = fileName.substring(0, fileName.indexOf(".")>0?fileName.indexOf("."):fileName.length())   
                + "." + FORMAT.toLowerCase();  
        ImageIO.write(image, FORMAT, new File(destPath + "/" + fileName));  
        return fileName;  
    }  
  
    /** 
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir． 
     * (mkdir如果父目录不存在则会抛出异常) 
     * @param destPath 
     *            存放目录 
     */  
    public static void mkdirs(String destPath) {  
        File file = new File(destPath);  
        if (!file.exists() && !file.isDirectory()) {  
            file.mkdirs();  
        }  
    }  
    
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param logoPath 
     *            LOGO地址 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */  
    public static String encode(String content, String logoPath, String destPath) throws Exception {  
        return QRCodeUtil.encode(content, logoPath, destPath, false);  
    }  
    
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static String encode(String content, String destPath, boolean needCompress) throws Exception {  
        return QRCodeUtil.encode(content, null, destPath, needCompress);  
    }  
    
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */  
    public static String encode(String content, String destPath) throws Exception {  
        return QRCodeUtil.encode(content, null, destPath, false);  
    }  
    
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param logoPath 
     *            LOGO地址 
     * @param output 
     *            输出流 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static void encode(String content, String logoPath, OutputStream output, boolean needCompress)  
            throws Exception {  
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
        ImageIO.write(image, FORMAT, output);  
    }  
    
    /**
     * 生成二维码(内嵌LOGO),自定义宽带和高度
     * @param content 内容 
     * @param logoPath LOGO地址 
     * @param output 输出流 
     * @param needCompress 是否压缩LOGO 
     * @param qrWidth 宽度
     * @param qrHeight 高度
     * @throws Exception
     */
    public static void encode(String content, String logoPath, OutputStream output, boolean needCompress,String qrWidth,String qrHeight,String qrcodeformat,String characterSet,String onColor,String offColor)  
            throws Exception {  
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress,qrWidth,qrHeight,characterSet,onColor,offColor);  
        ImageIO.write(image,StringUtils.isEmpty(qrcodeformat)?FORMAT:qrcodeformat, output);  
    } 
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param output 
     *            输出流 
     * @throws Exception 
     */  
    public static void encode(String content, OutputStream output) throws Exception {  
        QRCodeUtil.encode(content, null, output, false);  
    } 
    
    /**
     * 生成二维码,自定义格式
     * @param content
     * @param output
     * @param qrWidth
     * @param qrHeight
     * @param qrcodeformat
     * @param characterSet
     * @throws Exception
     */
	public static void encode(String content, OutputStream output, String qrWidth, String qrHeight, String qrcodeformat,
			String characterSet,String onColor,String offColor) throws Exception {
		QRCodeUtil.encode(content, null, output, false, qrWidth, qrHeight, qrcodeformat, characterSet,onColor,offColor);
	}
	
	/**
	 * 颜色名称 RGB 转换16进制
	 * @param str
	 * @return
	 */
	public static int toInt(String str){
		String reg = "#[0-9A-Fa-f]{6}";
		if(!str.matches(reg)){
			return 0;
		}
		String newStr = str.replaceAll("#", "ff").toLowerCase();
		int value = (int)Long.parseLong(newStr,16);
		return value;
	}
    
    /*public static void main(String[] args) {
    	// String text = "http://www.baidu.com";  
    	 try {
    		//不含Logo  
             //QRCodeUtil.encode("shiyajing", null, "E:\\二维码", true);  
    		 QRCodeUtil.encode("shiyajing", "E:\\Administrator\\Pictures\\Camera Roll\\psb.jpg", "E:\\二维码", true);
		} catch (Exception e) {
			// TODO: handle exception
		}
         //不含Logo  
         //QRCodeUtil.encode(text, null, "e:\\", true);  
         //含Logo，不指定二维码图片名  
         //QRCodeUtil.encode(text, "e:\\csdn.jpg", "e:\\", true);  
         //含Logo，指定二维码图片名  
        // QRCodeUtil.encode(text, "e:\\csdn.jpg", "e:\\", "qrcode", true);  
	}*/
}
