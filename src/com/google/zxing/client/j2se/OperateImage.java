package com.google.zxing.client.j2se;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.ByteMatrix;
/**
 * @author 杨敏钰
 * 创建日期 2013-4-13
 */
public class OperateImage{

    public OperateImage() {
		super();
	}


    /**
     * 画单点 ---- 实际上是画一个填充颜色的圆
     * ---- 以指定点坐标为中心画一个小半径的圆形，并填充其颜色来充当点
     * @param srcImagePath	 源图片颜色
     * @param x		点的x坐标
     * @param y		点的y坐标
     * @param width	填充的宽度
     * @param height	填充的高度
     * @param ovalColor	填充颜色
     * @param imageFormat	写入图片格式
     * @param toPath	写入路径
     * @throws IOException
     */
    public void drawPoint(String srcImagePath,int x,int y,int width,int height,Color ovalColor,String imageFormat,String toPath) throws IOException{
    	FileOutputStream fos = null;
		try {
			//获取源图片
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			//根据xy点坐标绘制连接线
			Graphics2D g2d = image.createGraphics();
			g2d.setColor(ovalColor);
			//填充一个椭圆形
			g2d.fillOval(x, y, width, height);
			fos = new FileOutputStream(toPath);
			ImageIO.write(image, imageFormat, fos);	
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
    			fos.close();
    		}
		}
    }
    
    /**
     * 画一组（多个）点---- 实际上是画一组（多个）填充颜色的圆
     * ---- 以指定点坐标为中心画一个小半径的圆形，并填充其颜色来充当点
     * @param srcImagePath	原图片路径
     * @param pointList	点列表
     * @param width	宽度
     * @param height		高度
     * @param ovalColor 填充颜色
     * @param imageFormat	写入图片颜色
     * @param toPath	写入路径
     * @throws IOException
     */
    public void drawPoints(ByteMatrix matrix ,int multiple,int width,int height,Color ovalColor,String imageFormat,String path) throws IOException{
    	FileOutputStream fos = null;
    	try {
    		//获取源图片
    		BufferedImage image = ImageIO.read(new File(path));
    		//根据xy点坐标绘制连接线
    		Graphics2D g2d = image.createGraphics();
    		g2d.setColor(ovalColor);
    		//填充一个椭圆形

    		if(matrix != null){
    			
    			
    			for (int x = 0; x < matrix.getWidth(); x++) {
    		       for (int y = 0; y < matrix.getHeight(); y++) {
    		    	if( matrix.get(x, y) == 1 && !(x<8&&y<8) && !(x<8&&y>height-8) && !(x>width-8&&y<8)){
    		    	   g2d.fillOval(x*multiple, y*multiple, multiple, multiple);
    		    	}
    		       }
    		     }
  		
    		}
    		fos = new FileOutputStream(path);
    		ImageIO.write(image, imageFormat, fos);	
    	} catch (IOException e) {
    		e.printStackTrace();
    	}finally{
    		if(fos!=null){
    			fos.close();
    		}
    	}
    }
}


    
  