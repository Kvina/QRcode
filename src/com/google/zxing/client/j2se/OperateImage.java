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
 * @author ������
 * �������� 2013-4-13
 */
public class OperateImage{

    public OperateImage() {
		super();
	}


    /**
     * ������ ---- ʵ�����ǻ�һ�������ɫ��Բ
     * ---- ��ָ��������Ϊ���Ļ�һ��С�뾶��Բ�Σ����������ɫ���䵱��
     * @param srcImagePath	 ԴͼƬ��ɫ
     * @param x		���x����
     * @param y		���y����
     * @param width	���Ŀ��
     * @param height	���ĸ߶�
     * @param ovalColor	�����ɫ
     * @param imageFormat	д��ͼƬ��ʽ
     * @param toPath	д��·��
     * @throws IOException
     */
    public void drawPoint(String srcImagePath,int x,int y,int width,int height,Color ovalColor,String imageFormat,String toPath) throws IOException{
    	FileOutputStream fos = null;
		try {
			//��ȡԴͼƬ
			BufferedImage image = ImageIO.read(new File(srcImagePath));
			//����xy���������������
			Graphics2D g2d = image.createGraphics();
			g2d.setColor(ovalColor);
			//���һ����Բ��
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
     * ��һ�飨�������---- ʵ�����ǻ�һ�飨����������ɫ��Բ
     * ---- ��ָ��������Ϊ���Ļ�һ��С�뾶��Բ�Σ����������ɫ���䵱��
     * @param srcImagePath	ԭͼƬ·��
     * @param pointList	���б�
     * @param width	���
     * @param height		�߶�
     * @param ovalColor �����ɫ
     * @param imageFormat	д��ͼƬ��ɫ
     * @param toPath	д��·��
     * @throws IOException
     */
    public void drawPoints(ByteMatrix matrix ,int multiple,int width,int height,Color ovalColor,String imageFormat,String path) throws IOException{
    	FileOutputStream fos = null;
    	try {
    		//��ȡԴͼƬ
    		BufferedImage image = ImageIO.read(new File(path));
    		//����xy���������������
    		Graphics2D g2d = image.createGraphics();
    		g2d.setColor(ovalColor);
    		//���һ����Բ��

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


    
  