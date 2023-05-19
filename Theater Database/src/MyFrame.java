import java.awt.Color;
import javax.swing.*;

public class MyFrame extends JFrame{
	//data fields for width and height
	private int W, H;
	/**constructor : with default screen/window size (800x500)
	* @author Martzel Baste */
	public MyFrame() {
	super(); //calls superclass JFrame
	H=800; W=500;
	setWindowSize(W, H);
	}
	/**parameterized constructor : user may set based on preference
	* @author Martzel Baste
	* @param width - size of the width
	* @param height - size of the length */
	public MyFrame(int width, int height) {
	super();
	setWindowSize(width, height);
	}
	/**parameterized constructor : user may set based on preference
	* @author Martzel Baste 
	* @param title - caption of the frame or window
	* @param width - size of the width
	* @param height - size of the length */
	public MyFrame(String title,int width, int height) {
	super(title);
	setWindowSize(width, height);
	}
	/**parameterized constructor : user may set based on preference
	* @author Martzel Baste
	* @param title - caption of the frame or window
	* @param width - size of the width
	* @param height - size of the length
	* @param visible - setting frame to show when true */
	public MyFrame(String title,int width, int height, boolean visible) {
	super(title);
	setWindowSize(width, height);
	setVisible(visible);
	}
	/**parameterized constructor : user may set based on preference
	* @author Martzel Baste
	* @param width - size of the width
	* @param height - size of the length */
	public void setWindowSize(int width, int height) {
	H=height; W=width;
	setSize(width, height);
	}
	/**parameterized constructor : user may set based on preference
	* @author Martzel Baste 
	* @param title - caption of the frame or window
	* @param width - size of the width
	* @param height - size of the length */
	public void setMyFrame(String title, int width, int height){
	setTitle(title);
	setWindowSize(width, height);
	}
	/**parameterized constructor : user may set based on preference
	* @author Martzel Baste 
	* @param title - caption of the frame or window
	* @param width - size of the width
	* @param height - size of the length
	* @param visible - setting frame to show when true
	*/
	public void setMyFrame(String title, int width, int height, boolean visible){
	setMyFrame(title, width, height);
	setVisible(visible);
	}
	/**parameterized constructor : user may set based on preference
	* @author Martzel Baste 
	* @param title - caption of the frame or window
	* @param width - size of the width
	* @param height - size of the length
	* @param visible - setting frame to show when true
	* @close_operation - 0 to 3 values
	*/
	public void setMyFrame(String title, int width, int height, boolean visible, int close_operation){
	setMyFrame(title, width, height, visible);
	setDefaultCloseOperation(close_operation);
	}
	/**parameterized constructor : user may set based on pref
	* @param width - size of the width
	* @param height - size of the length
	* @param visible - setting frame to show when true
	* @close_operation - 0 to 3 values
	* @resize - prevent frame from resizing if false
	*/
	public void setMyFrame(String title, int width, int height, boolean visible, int close_operation, boolean resize){
	setMyFrame(title, width, height, visible, close_operation);
	setResizable(resize);
	}
	/**@author Martzel Baste
	* @param red - 0 to 255
	* @param greeen 0 to 255
	* @param blue 0 to 255
	* @param opacity 0 to 100
	*/
	public void setBackgroundColor(int red, int green, int blue, int opacity) {
	getContentPane().setBackground(new Color(red,green,blue,opacity));
	}
	/**@author Martzel Baste
	* @param file - location of the image file
	* @return JPanel - returns the method as a JPanel
	*/
	public JPanel setBackgroundImage(String file) {
	JPanel panelBG = new JPanel();
	JLabel img = new JLabel(new ImageIcon(file));//set image to Jlabel
	panelBG.add(img); //add label to panelBG
	return panelBG;	
	}
	
}//end of class


	