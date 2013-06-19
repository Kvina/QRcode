package com.google.zxing.client.j2se;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class Decoder {
	
	public static void main(String[] args) {

		File file = new File("D://qrcodeImage.png");

		BufferedImage bufferedImage = null;

		try {

			bufferedImage = ImageIO.read(file);

		} catch (IOException e) {

			e.printStackTrace();

		}

		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);

		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();

		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

		Result result = null;

		try {

			result = new MultiFormatReader().decode(bitmap, hints);

		} catch (NotFoundException e) {

			e.printStackTrace();

		}

		System.out.println(result.toString());

	}

}
