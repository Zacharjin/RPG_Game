/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import mapeditor.FileManager.Block;

/**
 *
 * @author Piotr
 */
public class MainWindow extends javax.swing.JFrame {

    //Main file manager

    private FileManager m = new FileManager();
    //Variables
    private boolean dragged = false;
    private int layer = 0, selected = 0, mouseX = 0, mouseY = 0, mapScrollX = 0, mapScrollY = 0, menuBarY = 0;
    private Block menu[][];
    //indicator
    private Rectangle rect;

    public void displayMap() {
        int num = 0, windowH = jPanel2.getHeight() / m.imageW, windowW = jPanel2.getWidth() / m.imageW;
        //jPanel1.setBackground(Color.);
        for (int layerT = layer; layerT >= 0; layerT--) {
            System.out.println("Layer:" + layerT);
            for (int i = 0; i < m.map[0][i].length - mapScrollY - 1 && i <= windowH; i++) {
                if (layerT == 1) {
                    JLabel rect = new JLabel();
                    rect.setSize(jPanel2.getWidth(), jPanel2.getHeight());
                    rect.setOpaque(true);
                    rect.setBackground(new Color(10, 10, 10, 5));
                    jPanel2.add(rect);
                }
                for (int j = 0; j < m.map[0].length - mapScrollX - 1 && j <= windowW; j++) {
                    if (m.map[layerT][j + mapScrollX][i + mapScrollY].ID != 0) {
                        System.out.print(m.map[layerT][j + mapScrollX][i + mapScrollY] + ",");
                        JLabel jLabel = new JLabel(new ImageIcon(m.map[layerT][j + mapScrollX][i + mapScrollY].sprite));
                        jLabel.setSize(m.imageW, m.imageW);
                        jLabel.setLocation(j * m.imageW, i * m.imageW);
                        jPanel2.add(jLabel);
                        num++;
                    }
                }
                System.out.println();
            }

        }
    }

    public void displayBlocks() {
        int num = 0, line = 0;
        for (int i = 0; i < menu[0].length - menuBarY; i++) {
            System.out.println(i);
            for (int j = 0; j < 4; j++) {
                JLabel jLabel;
                jLabel = new JLabel(new ImageIcon(menu[j][i + menuBarY].sprite));
                jLabel.setSize(m.imageW, m.imageW);
                jLabel.setLocation(j * m.imageW, i * m.imageW);
                jPanel1.add(jLabel);
                num++;
            }
        }
    }

    public MainWindow() {
        m.loadGraph("res/world.png");
        menu = m.menu;
        initComponents();
        //jScrollPane1.
        displayMap();
        jPanel1.setSize(m.imageW * 4, jPanel1.getHeight());
        displayBlocks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollBar2 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollBar3 = new javax.swing.JScrollBar();
        jComboBox3 = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1, 1));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizeMainWindow(evt);
            }
        });

        jScrollBar1.setMaximum(32);
        jScrollBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.N_RESIZE_CURSOR));
        jScrollBar1.setOpaque(false);
        jScrollBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                handleAdjust(evt);
            }
        });

        jScrollBar2.setMaximum(32);
        jScrollBar2.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        jScrollBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollBar2.setOpaque(false);
        jScrollBar2.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                handleAdjust(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel1.setPreferredSize(new java.awt.Dimension(64, 6));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setName("MainView"); // NOI18N
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickOnPane2(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mousePress(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                MainWindow.this.mouseReleased(evt);
            }
        });
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                drawRect(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollBar3.setMaximum(60);
        jScrollBar3.setOpaque(false);
        jScrollBar3.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollMenu(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Map", "Items" }));
        jComboBox3.setDoubleBuffered(true);
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Open");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Exit");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(jScrollBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                            .addComponent(jScrollBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    void repaintView() {
        jPanel2.removeAll();
        displayMap();
        jPanel2.repaint();
    }

    void repaintMenu() {
        jPanel1.removeAll();
        displayBlocks();
        jPanel1.repaint();
    }

    private void handleAdjust(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_handleAdjust
        mapScrollX = jScrollBar2.getValue();
        mapScrollY = jScrollBar1.getValue();
        repaintView();
    }//GEN-LAST:event_handleAdjust

    private void ClickOnPane2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClickOnPane2
        int x = evt.getX() / m.imageW + jScrollBar2.getValue();
        int y = evt.getY() / m.imageW + jScrollBar1.getValue();
        m.map[layer][x][y] = m.block[selected];
        repaintView();
    }//GEN-LAST:event_ClickOnPane2

    private void select(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select
        int scrollBarY = jScrollBar3.getValue();
        int x = evt.getX() / m.imageW, y = (evt.getY()) / m.imageW;
        selected = x + y * 4 + scrollBarY * 4 + layer * 126;
    }//GEN-LAST:event_select

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser chooser = new JFileChooser();
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            m.save(chooser.getSelectedFile() + ".txt");
        }
        repaintView();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JFileChooser chooser = new JFileChooser();
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            jPanel2.removeAll();
            m.openMap(chooser.getSelectedFile().getPath());
        }
        repaintView();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mousePress(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mousePress
        System.out.println(layer);
        mouseX = evt.getX() / m.imageW + jScrollBar2.getValue();
        mouseY = evt.getY() / m.imageW + jScrollBar1.getValue();
        dragged = true;
        rect = new Rectangle();
        rect.x = evt.getX();
        rect.y = evt.getY();
        repaintView();
    }//GEN-LAST:event_mousePress

    private void mouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseReleased
        int t_mouseX = evt.getX() / m.imageW + jScrollBar2.getValue();
        int t_mouseY = evt.getY() / m.imageW + jScrollBar1.getValue();
        dragged = false;
        for (int i = mouseY; i <= t_mouseY; i++) {
            for (int j = mouseX; j <= t_mouseX; j++) {
                m.map[layer][j][i] = m.block[selected];
            }
        }
        repaintView();
    }//GEN-LAST:event_mouseReleased

    private void scrollMenu(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollMenu
        menuBarY = jScrollBar3.getValue();
        repaintMenu();
    }//GEN-LAST:event_scrollMenu

    private void resizeMainWindow(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_resizeMainWindow
        repaintView();
    }//GEN-LAST:event_resizeMainWindow

    private void drawRect(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawRect
        if (dragged) {
            rect.height = evt.getY() - rect.y;
            rect.width = evt.getX() - rect.x;
            Graphics2D g2d = (Graphics2D) jPanel2.getGraphics();
            g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }//GEN-LAST:event_drawRect

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        layer = jComboBox3.getSelectedIndex();
        m.inintMenu(layer);
        repaintView();
        repaintMenu();
    }//GEN-LAST:event_jComboBox3ActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollBar jScrollBar2;
    private javax.swing.JScrollBar jScrollBar3;
    // End of variables declaration//GEN-END:variables
}
