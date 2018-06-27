package com.pdi.image.manipulators;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class RGBManipulator {

	int width;
	int height;


	private BufferedImage resetImageChannels(BufferedImage image, int redValue, int greenValue, int blueValue) {

		try {

			width = image.getWidth();
			height = image.getHeight();

			for(int i=0; i<height; i++){

				for(int j=0; j<width; j++){

					Color c = new Color(image.getRGB(j, i));
					int red = (int)(c.getRed()*redValue);
					int green = (int)(c.getGreen()*greenValue);
					int blue = (int)(c.getBlue()*blueValue);

					Color newColor = new Color(red, green,blue);

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

	public BufferedImage convertToB(BufferedImage image) {
		return resetImageChannels(image, 1, 0, 0);
	}

	public BufferedImage convertToG(BufferedImage image) {
		return resetImageChannels(image, 0, 1, 0);
	}

	public BufferedImage convertToR(BufferedImage image) {
		return resetImageChannels(image, 0, 0, 1);
	}
}
