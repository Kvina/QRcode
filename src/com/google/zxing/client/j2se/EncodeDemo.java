package com.google.zxing.client.j2se;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import sun.net.www.content.image.png;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

public class EncodeDemo {
   private static final int BLACK = 0xff000000;
   private static final int WHITE = 0xFFFFFFFF;
 /**
  * @param args
  */
 public static void main(String[] args) {
	 EncodeDemo encodeDemo=new EncodeDemo();
	 encodeDemo.encode();
	 encodeDemo.decode();
 }
 //����
 /**
  * 123
  * �ڱ���ʱ��Ҫ��com.google.zxing.qrcode.encoder.Encoder.java�е�
  *  static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";�޸�ΪUTF-8���������ı�����������
  */
 public void encode(){
  try { 
   //String str = "�������������Ա��У����䣺25�����᣺�й�����";// ��ά������ 
   String str = "�ҵ�һ�������õĳ��������������㡣";// ��ά������
   String path = "D://qrcodeImage.png"; 
   String toPath = "C://qrcodeImage.png"; 

   BitMatrix bitMatrix; 
   bitMatrix= new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 350, 350);
 //  QRCodeWriter qrCodeWriter=new QRCodeWriter();
//   qrCodeWriter.drawPoints(bitMatrix, 4, Color.RED, "png" , path);
   
     File file = new File(path); 
     writeToFile(bitMatrix, "png", file); 
  } catch (Exception e) { 
   e.printStackTrace(); 
  }
 }
 public static void writeToFile(BitMatrix matrix, String format, File file)
 throws IOException {
BufferedImage image = toBufferedImage(matrix);
ImageIO.write(image, format, file);
}
 public static BufferedImage toBufferedImage(BitMatrix matrix) {
     int width = matrix.getWidth();
     int height = matrix.getHeight();
     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
     for (int x = 0; x < width; x++) {
       for (int y = 0; y < height; y++) {
         image.setRGB(x, y, matrix.get(x, y) ? BLACK:WHITE);
       
       }
     }
     return image;
   }
 //����
 public void decode(){
  try{
   Reader reader = new MultiFormatReader(); 
   String imgPath = "D://qrcodeImage.png"; 
   File file = new File(imgPath); 
   BufferedImage image; 
   try { 
    image = ImageIO.read(file); 
    if (image == null) { 
    System.out.println("Could not decode image"); 
    } 
    LuminanceSource source = new BufferedImageLuminanceSource(image); 
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source)); 
    Result result; 
    Hashtable hints= new Hashtable(); 
    hints.put(DecodeHintType.CHARACTER_SET, "utf-8"); 
    //�������ñ��뷽ʽΪ��utf-8��
    result = new MultiFormatReader().decode(bitmap,hints); 
    String resultStr = result.getText(); 
    System.out.println("���������ݣ�"+resultStr);

   } catch (IOException ioe) { 
    System.out.println(ioe.toString()); 
   } catch (ReaderException re) { 
    System.out.println(re.toString()); 
   }

  }catch(Exception ex){
   System.out.println(ex.toString());
  }
 }

}