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
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.lang.Math.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ColorPicky {

    final String m_ColorPicky_FilePath;

    public ColorPicky(String filaPath) {
        System.out.println(filaPath);
        m_ColorPicky_FilePath = filaPath;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("ImageColorPicker (IPV Video Team)");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                frame.add(new TestPane(m_ColorPicky_FilePath));

                //frame.add(new OutputLog());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public class TestPane extends JPanel {

        private BufferedImage img;
        private JLabel label;

        private JLabel tag1;
        private JLabel tag2;
        private JLabel gap;

        private JPanel fields;
        private JTextField red;
        private JTextField green;
        private JTextField blue;

        private JTextField Y;
        private JTextField U;
        private JTextField V;

        private String m_FilePath;
        private String m_FileName;

        public TestPane(String filePath) {
            m_FilePath = filePath;

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            label = new JLabel();
            try {
                File tempFile = new File(filePath);
                m_FileName = tempFile.getName();
                img = ImageIO.read(tempFile);
                int originalHeight = img.getHeight();
                int originalwidth = img.getWidth();

                if (originalHeight > 768 || originalwidth > 1024) {
                    int newHeight = 0, newWidth = 0;
                    float aspectRatio = (float) originalwidth / (float) originalHeight;

                    if (originalwidth > originalHeight) {
                        newWidth = 1024;
                        newHeight = (int) ((float) newWidth / aspectRatio);
                    } else {
                        newHeight = 768;
                        newWidth = (int) ((float) newHeight * aspectRatio);
                    }

                    Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
                    img = toBufferedImage(scaledImage);

                }
                
                //img = ConvertWithThreshold(img);

                label.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            add(label, gbc);

            fields = new JPanel();
            fields.setBorder(new EmptyBorder(5, 5, 5, 5));
            red = new JTextField(3);
            green = new JTextField(3);
            blue = new JTextField(3);

            Y = new JTextField(3);
            U = new JTextField(3);
            V = new JTextField(3);

            tag1 = new JLabel("<html><strong><font color='red'>R G B: </font></strong></html>");

            fields.add(tag1);
            fields.add(red);
            fields.add(green);
            fields.add(blue);

            gap = new JLabel("    ");
            fields.add(gap);

            tag2 = new JLabel("<html><strong><font color='red'>Y U V: </font></strong></html>");
            fields.add(tag2);
            fields.add(Y);
            fields.add(U);
            fields.add(V);

            add(fields, gbc);
            label.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    int packedInt = img.getRGB(e.getX(), e.getY());
                    Color color = new Color(packedInt, true);
                    String sMsg = UpdateFiledColor(color);

                    Point p = e.getLocationOnScreen();
                    System.out.println("Dragged Pixel:" + p.x + "," + p.y);
                    //JOptionPane.showMessageDialog(null, "<html>RGB = " + rgbText + "<br/>YUV = " + yuvText + "<br/></html>");
                    UpdateOutput(sMsg);
                }

                @Override
                public void mouseMoved(MouseEvent e) {

                    int packedInt = img.getRGB(e.getX(), e.getY());
                    Color color = new Color(packedInt, true);
                    String sMsg = UpdateFiledColor(color);
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

            label.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int packedInt = img.getRGB(e.getX(), e.getY());
                    Color color = new Color(packedInt, true);
                    String sMsg = UpdateFiledColor(color);

                    Point p = e.getLocationOnScreen();
                    System.out.println("Clicked Pixel:" + p.x + "," + p.y + "ImagePixel = (x,y)" + e.getX() + ", " + e.getY());
                    //JOptionPane.showMessageDialog(null, "<html>RGB = " + rgbText + "<br/>YUV = " + yuvText + "<br/></html>");
                    UpdateOutput(sMsg);
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

        }

        private String UpdateFiledColor(Color color) // Method for TestPane
        {
            fields.setBackground(color);

            red.setText(Integer.toString(color.getRed()));
            green.setText(Integer.toString(color.getGreen()));
            blue.setText(Integer.toString(color.getBlue()));

            float y, u, v;
            int r, g, b;
            r = color.getRed();
            g = color.getGreen();
            b = color.getBlue();

            y = (float) (r * .299000 + g * .587000 + b * .114000);
            u = (float) (r * -.168736 + g * -.331264 + b * .500000 + 128);
            v = (float) (r * .500000 + g * -.418688 + b * -.081312 + 128);

            y = (float) Math.floor(y);
            u = (float) Math.floor(u);
            v = (float) Math.floor(v);

            Y.setText(String.format("%03.0f", y));
            U.setText(String.format("%03.0f", u));
            V.setText(String.format("%03.0f", v));

            String rgbText = String.format("%03d", r) + ", " + String.format("%03d", g) + ", " + String.format("%03d", b);

            String yuvText = String.format("%03.0f", y) + ", " + String.format("%03.0f", u) + ", " + String.format("%03.0f", v);

            //String newText = String.format("%03.0f", y) + " " + String.format("%03.0f", u) + " " + String.format("%03.0f", v);
             String newText = String.format("%03d", r) + " " + String.format("%03d", g) + " " + String.format("%03d", b) + " " + String.format("%03.0f", y) + " " + String.format("%03.0f", u) + " " + String.format("%03.0f", v);

            //return "RGB = "+rgbText+" <---> YUV = "+yuvText;
            return newText;
        }

        private void UpdateOutput(String sMsg) {
            String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
            System.out.println("Inside Update");

             //sMsg =  timeStamp + ": " + sMsg;
             //sMsg += " --> ("+m_FileName+")";
             ImageColorPicker.g_iCounter++;
             sMsg = ImageColorPicker.g_iCounter + " " + sMsg;
            String sNow = FirstPage.logOutputArea.getText();

            if (sNow == "") {
                sNow = sMsg;
            } else {
                sNow = sNow + "\n" + sMsg;
            }

            FirstPage.logOutputArea.setText(sNow);
        }

        private BufferedImage ConvertWithThreshold(BufferedImage img) {

            System.out.println(img.getHeight() + ", " + img.getWidth());

            System.out.println(img.getMinX() + ", " + img.getMinY());

            int iHeight = img.getHeight();
            int iWidth = img.getWidth();

            for (int i = 0; i < iHeight; i++) {
                for (int j = 0; j < iWidth; j++) {

                    int packedInt = img.getRGB(j, i);
                    Color color = new Color(packedInt, true);

                    float y, u, v;
                    int r, g, b;

                    r = color.getRed();
                    g = color.getGreen();
                    b = color.getBlue();

                    y = (float) (r * .299000 + g * .587000 + b * .114000);
                    u = (float) (r * -.168736 + g * -.331264 + b * .500000 + 128);
                    v = (float) (r * .500000 + g * -.418688 + b * -.081312 + 128);

                    y = (float) Math.floor(y);
                    u = (float) Math.floor(u);
                    v = (float) Math.floor(v);

                    
                    //String yuvText = String.format("%03.0f", y) + ", " + String.format("%03.0f", u) + ", " + String.format("%03.0f", v);
                    //System.out.println("Here yuv = " + yuvText);
                    
                    if ( (u>=95 && u<=125) && (v>=135 && v<=175) ) 
                    {
                        //img.setRGB(j, i, Color.RED.getRGB());
                    }
                    else
                    {
                        img.setRGB(j, i, Color.RED.getRGB());
                    }
                     
                    /*if (r>=125 && r>g && g>b) 
                    {
                        img.setRGB(j, i, Color.RED.getRGB());
                    }*/
                }
            }

            return img;
        }

    }
}
