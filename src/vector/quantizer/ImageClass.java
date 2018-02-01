/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vector.quantizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author amral
 */
public class ImageClass {
    public static int[][] readImage(String path){
		
		
		BufferedImage img;
		try {
			img = ImageIO.read(new File(path));
		
		int hieght=img.getHeight();
		int width=img.getWidth();
		
		int[][] imagePixels=new int[hieght][width];
		for(int x=0;x<width;x++){
			for(int y=0;y<hieght;y++){
				
				int pixel=img.getRGB(x, y);
				
				int red=(pixel  & 0x00ff0000) >> 16;
				int grean=(pixel  & 0x0000ff00) >> 8;
				int blue=pixel  & 0x000000ff;
				int alpha=(pixel & 0xff000000) >> 24;
				imagePixels[y][x]=red;
			}
		}
		
		return imagePixels;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	public static void writeImage(int[][] imagePixels,String outPath){
		
		BufferedImage image = new BufferedImage(imagePixels.length, imagePixels[0].length, BufferedImage.TYPE_INT_RGB);
	    for (int y = 0; y < imagePixels.length; y++) {
	        for (int x = 0; x < imagePixels[0].length; x++) {
	             int value= 0xff000000 | (imagePixels[y][x]<<16) | (imagePixels[y][x]<<8) | (imagePixels[y][x]);
	             image.setRGB(y, x, value);

	        }
	    }

	    File ImageFile = new File(outPath);
	    try {
	        ImageIO.write(image, "jpg", ImageFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

		
	}
}