/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagecolorpicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author RajibTheKing
 */

public class FileChooser 
{

  private File file;

  public FileChooser() 
  {
    final JFrame frame = new JFrame("Hello");

    JButton btnFile = new JButton("Select Excel File");
    
    btnFile.addActionListener(new ActionListener() 
    {
        //Handle open button action.
        public void actionPerformed(ActionEvent e) 
        {
            
            
        }
    });

    frame.getContentPane().add(btnFile);
    frame.setSize(100, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  
}