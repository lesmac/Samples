import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

/** This is a plugin designed for ImageJ: (http://imagej.nih.gov/ij/download.html). This plugin filter modifies 
the sample Image_Inverter to create a checkerboard pattern of inversion.
*/ 

public class CheckerboardInverter implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}
 
	public void run(ImageProcessor ip) {
		Rectangle r = ip.getRoi();
		for (int y=r.y; y<(r.y+r.height); y++){
				for (int x=r.x; x<(r.x+r.width); x++){
					if ((Math.floor(x/10)%2==0)&&(Math.floor(y/10)%2==0)){
						ip.set(x, y, ~ip.get(x,y));
					}
					else if ((Math.floor(x/10)%2>0)&&(Math.floor(y/10)%2>0)){
						ip.set(x, y, ~ip.get(x,y));
					}
				}
		}
	
	}

}
