/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagecolorpicker;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author RajibTheKing
 */
public class FirstPage extends javax.swing.JFrame {

    /**
     * Creates new form FirstPage
     */
    public FirstPage() 
    {
        System.out.println("Inside FirstPage");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        filePathLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logOutputArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Select File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("StartProcessing");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        filePathLabel.setText("Selected File Path: ");

        logOutputArea.setColumns(20);
        logOutputArea.setRows(5);
        jScrollPane1.setViewportView(logOutputArea);

        jLabel1.setText("Log Output ");

        jButton3.setText("Clear Log");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(filePathLabel)
                                    .addGap(16, 16, 16)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jButton3)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filePathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser(); 
            int returnVal = fc.showOpenDialog(this);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                ImageColorPicker.g_file = fc.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println("File: " + ImageColorPicker.g_file.getName() + ", FilePath = " + ImageColorPicker.g_file.getAbsolutePath());
                filePathLabel.setText("Selected file path: " + ImageColorPicker.g_file.getAbsolutePath());
            } 
            else 
            {
                System.out.println("Open command cancelled by user.");
            }
            System.out.println(returnVal);
            
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        try
        {
            String path = ImageColorPicker.g_file.getAbsolutePath();
            String extension = "";

            int i = path.lastIndexOf('.');
            if (i > 0) {
                extension = path.substring(i+1);
            }
            extension = extension.toLowerCase();
            
            if(extension.compareTo("jpg")==0 || extension.compareTo("png")==0 || extension.compareTo("bmp")==0 || extension.compareTo("jpeg")==0)
            {
                new ColorPicky(ImageColorPicker.g_file.getAbsolutePath());
            }
            else
            {
                throw new FileNotFoundException();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getCause());
            JOptionPane.showMessageDialog(null, "..Asholee duitaa Karone Eita hotee pareee\n1. Hoy.. apni Thik moto file ta select koren nai\n2. Na hoy, Apni vul Image file select korechen\n\nPlease Select a valid Image file");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        logOutputArea.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel filePathLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea logOutputArea;
    // End of variables declaration//GEN-END:variables
}
