package com.pdi.image.manipulators;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class NegativeManipulator {

	int width;
	int height;

	private BufferedImage resetImageChannels(BufferedImage image) {

		try {

			width = image.getWidth();
			height = image.getHeight();

			for(int i=0; i<height; i++){

				for(int j=0; j<width; j++){

					Color c = new Color(image.getRGB(j, i));


					int red = (int)(c.getRed());
					int green = (int)(c.getGreen());
					int blue = (int)(c.getBlue());

					Color newColor = new Color(255-red, 255-green,255-blue);

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


	public BufferedImage convertToNegative(BufferedImage image) {
		return resetImageChannels(image);
	}
}
