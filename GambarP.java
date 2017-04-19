import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class GambarP extends JPanel{

	private BufferedImage image;

	JMenuBar menuBar;

	public GambarP()
	{
		this.image = null;
	
		setFocusable(true);

    	setLayout(null);
		setOpaque(true);

	}

	/** Sets the main Image **/
	public void setImage(BufferedImage image){
	
		this.image = image;
		repaint();
	}
	
	/** returns the image 
	 * @return The image shown by this panel
	 */
	public BufferedImage getImage(){
		return image;
	}

	public double getScaleFactor(int iMasterSize, int iTargetSize) {

		double dScale = 1;
		if (iMasterSize > iTargetSize) {

			dScale = (double) iTargetSize / (double) iMasterSize;

		} else {

			dScale = (double) iTargetSize / (double) iMasterSize;

		}

		return dScale;

	}

	public double getScaleFactorToFit(Dimension original, Dimension toFit) {

		double dScale = 1d;

		if (original != null && toFit != null) {

			double dScaleWidth = getScaleFactor(original.width, toFit.width);
			double dScaleHeight = getScaleFactor(original.height, toFit.height);

			dScale = Math.min(dScaleHeight, dScaleWidth);

		}

		return dScale;

	}
	
	/** Overwritten paint event for drawing **/
	public void paintComponent(Graphics g) {
		/*g.setColor(new Color(50,50,50));
		g.fillRect(0,0,getSize().width,getSize().height);	
			
		if(image != null){

			int center_x = getSize().width/2 - image.getWidth() /2;
			int center_y = getSize().height/2 - image.getHeight() /2;

			if(center_x < 10){ center_x = 10;}
			if(center_y < 10){ center_y = 10;}
		
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}*/
		try {
		
			super.paintComponent(g);

			double scaleFactor = Math.min(1d, getScaleFactorToFit(new Dimension(image.getWidth(), image.getHeight()), getSize()));

			int scaleWidth = (int) Math.round(image.getWidth() * scaleFactor);
			int scaleHeight = (int) Math.round(image.getHeight() * scaleFactor);

			Image scaled = image.getScaledInstance(scaleWidth, scaleHeight, BufferedImage.SCALE_SMOOTH);

			int width = getWidth() - 1;
			int height = getHeight() - 1;

			int x = (width - scaled.getWidth(this)) / 2;
			int y = (height - scaled.getHeight(this)) / 2;

			g.drawImage(scaled, x, y, this);
			
		} catch (Exception e) {
			
		}
	}

}
