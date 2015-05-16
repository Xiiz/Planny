package view;

import controller.PlannyController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;

/**
 * Splash Screen de l'application pour initialiser la base de données et les
 * objets
 *
 * @author Yassine Doghri
 */
public class PlannySplash extends JFrame {

    private static JProgressBar pbar;
    Thread t = null;

    public PlannySplash(PlannyController controller) throws IOException {
        super("Planny");
        setSize(600, 400);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setLocationRelativeTo(null);
        toFront();

        BufferedImage myImage = ImageIO.read(new File("src/view/components/images/splash-bg.jpg"));
        setContentPane(new ImagePanel(myImage));

        JLabel copyrightText = new JLabel("© 2015 Planny - Amine Bouazizi | Yassine Doghri | Pierre Lasalmonie");
        JLabel versionText = new JLabel("Version BETA");

        copyrightText.setForeground(Color.decode("#555555"));
        versionText.setForeground(Color.decode("#555555"));

        setLayout(new BorderLayout());

        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());

        add(footer, BorderLayout.SOUTH);

        JPanel copyrightPanel = new JPanel();
        copyrightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel versionPanel = new JPanel();
        versionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        footer.add(copyrightPanel, BorderLayout.WEST);
        footer.add(versionPanel, BorderLayout.EAST);

        copyrightPanel.add(copyrightText);
        versionPanel.add(versionText);

        footer.setBackground(new Color(0, 0, 0, 0));
        copyrightPanel.setBackground(new Color(0, 0, 0, 0));
        versionPanel.setBackground(new Color(0, 0, 0, 0));

        pbar = new JProgressBar();
        pbar.setMinimum(0);
        pbar.setMaximum(100);
        pbar.setStringPainted(true);
        pbar.setForeground(Color.decode("#EB1C2F"));
        pbar.setBackground(Color.decode("#D7CCC8"));
        pbar.setBorderPainted(false);
        pbar.setStringPainted(false);

        JPanel progressPanel = new JPanel();

        progressPanel.setLayout(null);
        progressPanel.setBackground(new Color(0, 0, 0, 0));

        add(progressPanel, BorderLayout.CENTER);

        progressPanel.add(pbar);

        pbar.setPreferredSize(new Dimension(300, 8));
        pbar.setBounds(150, 290, 300, 8);

        t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    pbar.setValue(i);
                    try {
                        t.sleep(15);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PlannySplash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                controller.startPlanningFrame();
                dispose();
            }
        };
        t.start();
    }

}

class ImagePanel extends JComponent {

    private final Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
