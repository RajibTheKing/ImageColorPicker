/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagecolorpicker;

/**
 *
 * @author RajibTheKing
 */
import java.io.File;
import javax.swing.*;



public class ImageColorPicker extends JFrame
{

    /**
     * @param args the command line arguments
     */
    
    public static JFrame g_mainFrame;
    public static File g_file;
    public static String logOutput;
    public static void main(String[] args) 
    {
        // TODO code application logic here
        System.out.println("Hello world");
        logOutput = "";
        
        //ColorPicky m_colorPicky = new ColorPicky("hell");
        g_mainFrame = new FirstPage();
        g_mainFrame.setVisible(true);
        
    }
    
    
}
