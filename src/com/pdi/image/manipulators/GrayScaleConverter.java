package com.pdi.image.manipulators;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GrayScaleConverter {
	int width;
	int height;

	public BufferedImage ToGrayScale(BufferedImage image) {

		try {

			width = image.getWidth();
			height = image.getHeight();

			for(int i=0; i<height; i++){

				for(int j=0; j<width; j++){

					Color c = new Color(image.getRGB(j, i));
					int red = (int)(c.getRed() * 0.299);
					int green = (int)(c.getGreen() * 0.587);
					int blue = (int)(c.getBlue() *0.114);
					
					Color newColor = new Color(red+green+blue, red+green+blue,red+green+blue);

					image.setRGB(j,i,newColor.getRGB());
				}
			}
			System.out.println(image);
			File ouptut = new File("grayscale.jpg");
			
			ImageIO.write(image, "jpg", ouptut);
			
			return image;
		} catch (Exception e) {
			
		}
		return null;
	}


}
