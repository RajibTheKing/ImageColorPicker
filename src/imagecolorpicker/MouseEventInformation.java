/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagecolorpicker;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author RajibTheKing
 */


public class MouseEventInformation  implements MouseListener 
{

    public MouseEventInformation(ImageColorPicker imageColorPicker) 
    {
        System.out.println("Inside MouseEventInformation Constructor");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Point p = e.getLocationOnScreen();

        System.out.println("mouseClicked: " + p.x + "," + p.y);
        
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        Point p = e.getLocationOnScreen();

        System.out.println("mousePressed: " + p.x + "," + p.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        Point p = e.getLocationOnScreen();

        System.out.println("mouseReleased: " + p.x + "," + p.y);
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
