package com.pdi.image.manipulators;


import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.ImageIcon;

public class BrightnessManipulator {

	 RescaleOp rescale;
	
    public BufferedImage rescale(BufferedImage  bufferedImage, float offset) {

        rescale = new RescaleOp(2.0f,offset, null);
        bufferedImage=rescale.filter(bufferedImage,null);
        return bufferedImage;
    }
    
    public BufferedImage brighten(BufferedImage bufferedImage, float offset){
    	bufferedImage = rescale(bufferedImage, offset);
        return bufferedImage;        
   }
}
