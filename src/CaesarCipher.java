import javax.swing.*;
import java.awt.*;

public class CaesarCipher {

    private JPanel background;
    public static final String LEFT_ARROW = "\u2190";
    public static final String RIGHT_ARROW = "\u2192";

    public static void main(String[] args) {
        new CaesarCipher().buildGUI();
    }

    public void buildGUI() {
        JFrame frame = new JFrame("Caesar Cipher");
        frame.getContentPane().add(BorderLayout.CENTER, this.buildBackground());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);
    }

    private JPanel buildBackground() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.add(BorderLayout.WEST, this.buildTextArea("Plaintext"));
        panel.add(BorderLayout.CENTER, this.buildEncodeDecodePanel());
        panel.add(BorderLayout.EAST, this.buildTextArea("Ciphertext"));
        panel.add(BorderLayout.SOUTH, this.buildShiftPanel());

        return panel;
    }

    private JTextArea buildTextArea(String text) {
        JTextArea textArea = new JTextArea(text, 20, 35);
        textArea.setFont(new Font("Monospaced", Font.ITALIC, 18));
        textArea.setForeground(Color.lightGray);
        return textArea;
    }

    private JPanel buildEncodeDecodePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.darkGray);

        JButton encodeButton = new JButton("Encode " + RIGHT_ARROW);
        JButton decodeButton = new JButton(LEFT_ARROW + " Decode");

        encodeButton.setFont(new Font("Monopaced", Font.BOLD, 16));
        decodeButton.setFont(new Font("Monopaced", Font.BOLD, 16));

        panel.add(encodeButton);
        panel.add(decodeButton);

        return panel;
    }

    private JPanel buildShiftPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));

        JButton minusButton = new JButton("-");
        JTextField shift = new JTextField("0");
        JButton plusButton = new JButton("+");

        subPanel.add(minusButton);
        subPanel.add(shift);
        subPanel.add(plusButton);

        subPanel.setBackground(Color.darkGray);

        mainPanel.add(BorderLayout.NORTH, new JLabel("Shift"));
        mainPanel.add(BorderLayout.CENTER, subPanel);
        mainPanel.add(BorderLayout.SOUTH, new JLabel("a " + RIGHT_ARROW + "a"));

        return mainPanel;
    }
}