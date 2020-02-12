import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarCipher {

    public static final String LEFT_ARROW = "\u2190";
    public static final String RIGHT_ARROW = "\u2192";

    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    JButton minusButton;
    JButton plusButton;
    JButton encodeButton;
    JButton decodeButton;
    JTextField shiftTextField;
    JTextArea plainTextArea;
    JTextArea cipherTextArea;
    JLabel aToZLabel;

    public static void main(String[] args) {
        new CaesarCipher().buildGUI();
    }

    public void buildGUI() {
        JFrame frame = new JFrame("Caesar Cipher");
        frame.getContentPane().add(BorderLayout.CENTER, this.buildBackgroundPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 525);
        frame.setVisible(true);
    }

    private JPanel buildBackgroundPanel() {
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.darkGray);
        plainTextArea = this.buildTextArea("Plaintext");
        cipherTextArea = this.buildTextArea("Ciphertext");
        backgroundPanel.add(BorderLayout.WEST, plainTextArea);
        backgroundPanel.add(BorderLayout.CENTER, this.buildEncodeDecodePanel());
        backgroundPanel.add(BorderLayout.EAST, cipherTextArea);
        backgroundPanel.add(BorderLayout.SOUTH, this.buildShiftPanel());
        return backgroundPanel;
    }

    private JTextArea buildTextArea(String text) {
        JTextArea textArea = new JTextArea(text, 20, 35);
        textArea.setFont(new Font("Monospaced", Font.ITALIC, 18));
        textArea.setForeground(Color.lightGray);
        return textArea;
    }

    private JPanel buildEncodeDecodePanel() {
        JPanel encodeDecodePanel = new JPanel();
        encodeDecodePanel.setLayout(new BoxLayout(encodeDecodePanel, BoxLayout.Y_AXIS));
        encodeDecodePanel.setBackground(Color.darkGray);

        encodeButton = new JButton("Encode " + RIGHT_ARROW);
        decodeButton = new JButton(LEFT_ARROW + " Decode");

        encodeButton.setFont(new Font("Monopaced", Font.BOLD, 16));
        decodeButton.setFont(new Font("Monopaced", Font.BOLD, 16));

        encodeButton.addActionListener(new EncodeButtonListener());
        decodeButton.addActionListener(new DecodeButtonListener());

        encodeDecodePanel.add(encodeButton);
        encodeDecodePanel.add(decodeButton);

        return encodeDecodePanel;
    }

    private JPanel buildShiftPanel() {
        JPanel shiftPanel = new JPanel();
        shiftPanel.setBackground(Color.darkGray);
        shiftPanel.add(BorderLayout.NORTH, this.buildShiftLabel());
        shiftPanel.add(BorderLayout.CENTER, this.buildShiftControlPanel());
        shiftPanel.add(BorderLayout.SOUTH, this.buildAToZLabel());
        return shiftPanel;
    }

    private JLabel buildShiftLabel() {
        JLabel shiftLabel = new JLabel("Shift");
        shiftLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        shiftLabel.setForeground(Color.white);
        return shiftLabel;
    }

    private JPanel buildShiftControlPanel() {
        JPanel shiftControlPanel = new JPanel();
        shiftControlPanel.setLayout(new BoxLayout(shiftControlPanel, BoxLayout.X_AXIS));

        minusButton = new JButton("-");
        minusButton.setFont(new Font("Monospaced", Font.BOLD, 24));
        minusButton.addActionListener(new MinusButtonListener());

        shiftTextField = new JTextField("0", 2);
        shiftTextField.setFont(new Font("Monospaced", Font.BOLD, 24));

        plusButton = new JButton("+");
        plusButton.setFont(new Font("Monospaced", Font.BOLD, 24));
        plusButton.addActionListener(new PlusButtonListener());

        shiftControlPanel.add(minusButton);
        shiftControlPanel.add(shiftTextField);
        shiftControlPanel.add(plusButton);
        shiftControlPanel.setBackground(Color.darkGray);

        return shiftControlPanel;
    }

    private JLabel buildAToZLabel() {
        aToZLabel = new JLabel("a " + RIGHT_ARROW + " a");
        aToZLabel.setFont(new Font("Monospaced", Font.ITALIC, 24));
        aToZLabel.setForeground(Color.white);
        return aToZLabel;
    }

    class MinusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int shiftAmount = Integer.parseInt(shiftTextField.getText());

            if (shiftAmount > 0) {
                shiftTextField.setText(String.valueOf(--shiftAmount));
                if (shiftAmount >= 0) {
                    aToZLabel.setText("a " + RIGHT_ARROW + " " + alphabet.charAt(shiftAmount));
                } else {
                    aToZLabel.setText("a " + RIGHT_ARROW + " " + alphabet.charAt(26 + shiftAmount));
                }
            }
        }
    }

    class PlusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int shiftAmount = Integer.parseInt(shiftTextField.getText());

            if (shiftAmount < 25) {
                shiftTextField.setText(String.valueOf(++shiftAmount));
                if (shiftAmount >= 0) {
                    aToZLabel.setText("a " + RIGHT_ARROW + " " + alphabet.charAt(shiftAmount));
                } else {
                    aToZLabel.setText("a " + RIGHT_ARROW + " " + alphabet.charAt(26 + shiftAmount));
                }
            }
        }
    }

    class EncodeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            cipherTextArea.setText("Message has been encoded!");
        }
    }

    class DecodeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            plainTextArea.setText("Message has been decoded!");
        }
    }
}