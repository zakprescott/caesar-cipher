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
        frame.setSize(1000, 575);
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
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.darkGray);
        panel.add(this.buildShiftLabel());
        panel.add(this.buildShiftControlPanel());
        panel.add(this.buildAToZLabel());
        return panel;
    }

    private JLabel buildShiftLabel() {
        JLabel shiftLabel = new JLabel("Shift");
        shiftLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        shiftLabel.setForeground(Color.white);
        return shiftLabel;
    }

    private JPanel buildShiftControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton minusButton = new JButton("-");
        minusButton.setFont(new Font("Monospaced", Font.BOLD, 24));

        JTextField shiftAmount = new JTextField("0");
        shiftAmount.setFont(new Font("Monospaced", Font.BOLD, 24));

        JButton plusButton = new JButton("+");
        plusButton.setFont(new Font("Monospaced", Font.BOLD, 24));

        panel.add(minusButton);
        panel.add(shiftAmount);
        panel.add(plusButton);
        panel.setBackground(Color.darkGray);
        return panel;
    }

    private JLabel buildAToZLabel() {
        JLabel aToZLabel = new JLabel("a " + RIGHT_ARROW + " a");
        aToZLabel.setFont(new Font("Monospaced", Font.ITALIC, 24));
        aToZLabel.setForeground(Color.white);
        return aToZLabel;
    }
}