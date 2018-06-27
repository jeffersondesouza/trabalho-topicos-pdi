package com.pdi.image.manipulators;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageManipulatorsFacade {

	private GrayScaleConverter grayScaleConverter = new GrayScaleConverter();
	private BrightnessManipulator brightnessManipulator = new BrightnessManipulator();
	private RGBManipulator rgbManipulator = new RGBManipulator();
	private NegativeManipulator negativeManipulator = new NegativeManipulator();
	
	public BufferedImage ToGrayScale(BufferedImage image){
		return grayScaleConverter.ToGrayScale(image);
	}
	
	public BufferedImage brighten(BufferedImage bufferedImage, float offset){
		return  brightnessManipulator.brighten(bufferedImage, offset);
	}
	
	public BufferedImage convertToR(BufferedImage image){
		return rgbManipulator.convertToR(image);
	}
	
	public BufferedImage convertToG(BufferedImage image){
		return rgbManipulator.convertToG(image);
	}
	
	public BufferedImage convertToB(BufferedImage image){
		return rgbManipulator.convertToB(image);
	}
	
	public BufferedImage convertToNegative(BufferedImage image){
		return negativeManipulator.convertToNegative(image);
	}
	
	
}
