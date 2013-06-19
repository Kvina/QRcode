package com.google.zxing.client.j2se;


import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class Encoder {

	public static void main(String[] args) {

		//String contents = "今天，我们来简单聊聊google开源项目——ZXing（二维条码编解码）";
		String contents = "123木头人";
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();

		hints.put(EncodeHintType.CHARACTER_SET, "GBK");

		BitMatrix matrix = null;

		try {

			matrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, 300, 300, hints);

		} catch (WriterException e) {

			e.printStackTrace();

		}

		File file = new File("D://qrcodeImage.png");

		try {

			MatrixToImageWriter.writeToFile(matrix, "png", file);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
