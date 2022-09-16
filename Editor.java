package application;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Taskbar;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


/**
 * @author Nicode3141
 */


public class Editor extends JFrame{

    private JTextArea textArea = new JTextArea(40 , 120);
    private JFileChooser fc = new JFileChooser();



    public Editor(){


        java.net.URL url = ClassLoader.getSystemResource("com/application/assets/icon/editor-icon-5.jpg");

        // Create an image instance from the image that you want to use as icon for your app.


        JFrame frame;
        frame =  new JFrame();
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nicolas\\Documents\\java\\assets\\icon\\editor-icon-5.jpg");
        frame.setIconImage(icon);
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
        fc.setFileFilter(txtFilter);

        add(scrollPane);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        menuBar.add(file);
        menuBar.add(help);

        file.add(Open);
        file.add(Save);
        file.addSeparator();
        file.add(Exit);

        help.add(Feedback);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        String filepath = "assets/icon/editor-icon-5.jpg";
        File img = new File(filepath);
        BufferedImage bImage;
        try {
            bImage = ImageIO.read(img);
//set icon on JFrame menu bar, as in Windows system
            frame.setIconImage(bImage);
//set icon on system tray, as in Mac OS X system
            final Taskbar taskbar = Taskbar.getTaskbar();
            taskbar.setIconImage(bImage);
        } catch (IOException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Action Open = new AbstractAction("Open File") {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(fc.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
                openFile(fc.getSelectedFile().getAbsolutePath());
            }

        }
    };

    Action Save = new AbstractAction("Save File") {
        @Override
        public void actionPerformed(ActionEvent e) {

            saveFile();

        }
    };

    Action Exit = new AbstractAction("Exit") {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0) ;

        }
    };

    public void openFile(String fileName) {
        FileReader fr  = null;

        try{
            fr = new FileReader(fileName);
            textArea.read(fr,null);
            fr.close();
            setTitle(fileName);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void  saveFile (){

        if (fc. showSaveDialog(null) == JFileChooser.APPROVE_OPTION){

            FileWriter fw = null;

            try {
                fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
                textArea.write(fw);
                fw.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    Action Feedback = new AbstractAction("Feedback") {
        @Override
        public void actionPerformed(ActionEvent e) {

            String url_open ="HTTPS://fakeappleweb.nicode3141.repl.co";
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
            } catch (IOException ex) {
                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };
    public static void main(String[] args){
          new Editor();
    }
}
