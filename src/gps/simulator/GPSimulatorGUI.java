package gps.simulator;

import gps.simulator.tools.MessageConsole;
import gps.simulator.modele.Nomadic;
import gps.simulator.modele.StandardGPS;
import gps.simulator.modele.Teltonica;
import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/*
 * JAVA GPS Simulator
 * ISO Raid Project
 * 
 * From Perl oPhone GPS Simulator
 * Author: Alexis DUQUE - alexisd61@gmail.com - 2013
 *
 */
import javax.swing.*;

public class GPSimulatorGUI extends javax.swing.JFrame {

    /**
     * Creates new form GPSimulatorFrame
     */
    public GPSimulatorGUI() {
        initComponents();
        initConsole();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        fileChooser = new javax.swing.JFileChooser();
        gpsModel = new javax.swing.JComboBox();
        buttonStart = new javax.swing.JButton();
        buttonStop = new javax.swing.JButton();
        serverIP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonQuit = new javax.swing.JButton();
        serverPort = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        fileText = new javax.swing.JTextField();
        sendPeriod = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextPane();

        jFormattedTextField2.setText("jFormattedTextField2");

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new OnlyTextFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GPS Simulator - ISO Raid Project 2014");
        setResizable(false);

        gpsModel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nomadic" }));
        gpsModel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gpsModelItemStateChanged(evt);
            }
        });

        buttonStart.setText("Start");
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        buttonStop.setText("Stop");
        buttonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStopActionPerformed(evt);
            }
        });

        serverIP.setText("localhost");
        serverIP.setToolTipText("");
        serverIP.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                serverIPInputMethodTextChanged(evt);
            }
        });

        jLabel1.setText("GPS model");

        jLabel2.setText("Server IP");

        jLabel3.setText("Server Port");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("GPS Simulator - ISO Raid Project");

        jLabel5.setText("Period (sec)");

        buttonQuit.setText("Exit");
        buttonQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQuitActionPerformed(evt);
            }
        });

        serverPort.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#####"))));
        serverPort.setText("2947");
        serverPort.setToolTipText("");
        serverPort.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                serverPortInputMethodTextChanged(evt);
            }
        });

        jLabel6.setText("Positions");

        fileText.setEditable(false);
        fileText.setText("jeu_essai_positions.txt");

        sendPeriod.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
        sendPeriod.setText("5");
        sendPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendPeriodActionPerformed(evt);
            }
        });
        sendPeriod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sendPeriodKeyPressed(evt);
            }
        });

        console.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(console);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonStart, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(gpsModel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(serverIP, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(sendPeriod, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(serverPort))
                                .addGap(18, 18, 18)
                                .addComponent(buttonQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fileText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gpsModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(serverIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(serverPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fileText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(sendPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonStop)
                            .addComponent(buttonQuit)
                            .addComponent(buttonStart))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQuitActionPerformed

        System.exit(0);
    }//GEN-LAST:event_buttonQuitActionPerformed

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStartActionPerformed
        // Start Simulator
        //buttonStart.setEnabled(false);
        //buttonStop.setEnabled(true);
        
        final String ip = serverIP.getText();
        final String fileT = fileText.getText();
        final int port = Integer.parseInt(serverPort.getText());
        final int per = Integer.parseInt(sendPeriod.getText());
        Random rand = new Random(); 
        int imei = 0000000003;// + rand.nextInt(1000);

            switch (gpsModel.getSelectedIndex()) {
                case 0:
                    gpsTracker = new Nomadic(ip, port, per, imei, fileT, this);
                    break;
                case 1:
                    gpsTracker = new Teltonica(ip, port, per, imei, fileT, this);
                    break;
            }
        
        stopGPS = false;
        (new Thread(gpsTracker)).start();

    }//GEN-LAST:event_buttonStartActionPerformed

    private void buttonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStopActionPerformed
        // TODO add your handling code here:
        //buttonStart.setEnabled(true);
        //buttonStop.setEnabled(false);
        try {
            gpsTracker.stopGPS();
            gpsTracker = null;
            System.err.println("Stop Send");
        } catch (Exception e) {
            System.err.println("No GPS Started");
        }
    }//GEN-LAST:event_buttonStopActionPerformed

    private void serverPortInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_serverPortInputMethodTextChanged

    }//GEN-LAST:event_serverPortInputMethodTextChanged

    private void serverIPInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_serverIPInputMethodTextChanged

    }//GEN-LAST:event_serverIPInputMethodTextChanged

    private void gpsModelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gpsModelItemStateChanged

    }//GEN-LAST:event_gpsModelItemStateChanged

    private void sendPeriodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sendPeriodKeyPressed

    }//GEN-LAST:event_sendPeriodKeyPressed

    private void sendPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendPeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendPeriodActionPerformed

    private void initConsole() {
        MessageConsole mc = new MessageConsole(console);
        mc.redirectOut();
        mc.redirectErr(Color.RED, null);
        mc.setMessageLines(12);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GPSimulatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GPSimulatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GPSimulatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GPSimulatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GPSimulatorGUI().setVisible(true);
            }
        });
    }

    class OnlyTextFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".txt" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".txt");
        }

        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "Text documents (*.txt)";
        }
    }
    
    public boolean stopGPS;
    private StandardGPS gpsTracker;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonQuit;
    private javax.swing.JButton buttonStart;
    private javax.swing.JButton buttonStop;
    private javax.swing.JTextPane console;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTextField fileText;
    private javax.swing.JComboBox gpsModel;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField sendPeriod;
    private javax.swing.JTextField serverIP;
    private javax.swing.JFormattedTextField serverPort;
    // End of variables declaration//GEN-END:variables
}
