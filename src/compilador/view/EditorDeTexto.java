/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.view;

import compilador.controllers.EditorController;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

public class EditorDeTexto extends javax.swing.JFrame {
    ActionListener listener = new EditorController(this);
    
    public EditorDeTexto() {
        initComponents();
    }

    public void adicionarTabela(TabelaView tabela){
        adicionarComponente(tabela, java.awt.BorderLayout.EAST);
    }
    
    public void adicionarConsole(Console console){
        adicionarComponente(console, java.awt.BorderLayout.SOUTH);
    }
    
    private void adicionarComponente(Component componente, String posicao){
        removerComponente(componente);
        getContentPane().add(componente, posicao);    
        revalidate();
    }
    
    private void removerComponente(Component component){
        for(Component c : getContentPane().getComponents()){
            if(c.getClass().equals(component.getClass()))
                getContentPane().remove(c);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAreaDeTexto = new javax.swing.JScrollPane();
        areaDeTexto = new javax.swing.JTextArea();
        barraDeFerramentas = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        barraDeMenus = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuItemNovo = new javax.swing.JMenuItem();
        menuItemAbrir = new javax.swing.JMenuItem();
        menuItemSalvar = new javax.swing.JMenuItem();
        menuItemSalvarComo = new javax.swing.JMenuItem();
        menuItemFechar = new javax.swing.JMenuItem();
        menuExecutar = new javax.swing.JMenu();
        menuItemCompilar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(908, 522));
        getContentPane().setLayout(new java.awt.BorderLayout());

        areaDeTexto.setColumns(20);
        areaDeTexto.setLineWrap(true);
        areaDeTexto.setRows(5);
        areaDeTexto.setTabSize(4);
        areaDeTexto.setWrapStyleWord(true);
        panelAreaDeTexto.setViewportView(areaDeTexto);

        getContentPane().add(panelAreaDeTexto, java.awt.BorderLayout.CENTER);

        barraDeFerramentas.setRollover(true);

        jSeparator1.setToolTipText("");
        barraDeFerramentas.add(jSeparator1);

        getContentPane().add(barraDeFerramentas, java.awt.BorderLayout.PAGE_START);

        menuArquivo.setText("Arquivo");

        menuItemNovo.setText("Novo");
        menuItemNovo.addActionListener(listener);
        menuArquivo.add(menuItemNovo);

        menuItemAbrir.setText("Abrir");
        menuItemAbrir.addActionListener(listener);
        menuArquivo.add(menuItemAbrir);

        menuItemSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSalvar.setText("Salvar");
        menuItemSalvar.addActionListener(listener);
        menuArquivo.add(menuItemSalvar);

        menuItemSalvarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemSalvarComo.setText("Salvar como");
        menuItemSalvarComo.addActionListener(listener);
        menuArquivo.add(menuItemSalvarComo);

        menuItemFechar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menuItemFechar.setText("Sair");
        menuItemFechar.addActionListener(listener);
        menuArquivo.add(menuItemFechar);

        barraDeMenus.add(menuArquivo);

        menuExecutar.setText("Executar");

        menuItemCompilar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuItemCompilar.setText("Compilar");
        menuItemCompilar.addActionListener(listener);
        menuItemCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCompilarActionPerformed(evt);
            }
        });
        menuExecutar.add(menuItemCompilar);

        barraDeMenus.add(menuExecutar);

        setJMenuBar(barraDeMenus);

        setSize(new java.awt.Dimension(1053, 561));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCompilarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemCompilarActionPerformed

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorDeTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EditorDeTexto().setVisible(true);
        });
    }

    public String getTexto(){
        return areaDeTexto.getText().toUpperCase();
    }

    public void setTexto(String texto){
        areaDeTexto.setText(texto);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaDeTexto;
    private javax.swing.JToolBar barraDeFerramentas;
    private javax.swing.JMenuBar barraDeMenus;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenu menuExecutar;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JMenuItem menuItemCompilar;
    private javax.swing.JMenuItem menuItemFechar;
    private javax.swing.JMenuItem menuItemNovo;
    private javax.swing.JMenuItem menuItemSalvar;
    private javax.swing.JMenuItem menuItemSalvarComo;
    private javax.swing.JScrollPane panelAreaDeTexto;
    // End of variables declaration//GEN-END:variables
}
