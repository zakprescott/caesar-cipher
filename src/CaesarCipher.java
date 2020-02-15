import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class CaesarCipher {

    public static final String LEFT_ARROW = "\u2190";
    public static final String RIGHT_ARROW = "\u2192";

    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    JButton minusButton;
    JButton plusButton;
    JButton encodeButton;
    JButton decodeButton;
    JLabel shiftAmountLabel;
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
        plainTextArea.addMouseListener(new PlainTextAreaListener());

        cipherTextArea = this.buildTextArea("Ciphertext");
        cipherTextArea.addMouseListener(new CipherTextAreaListener());

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
        textArea.setEditable(false);
        return textArea;
    }

    private JPanel buildEncodeDecodePanel() {
        JPanel encodeDecodePanel = new JPanel();
        encodeDecodePanel.setLayout(new BoxLayout(encodeDecodePanel, BoxLayout.Y_AXIS));
        encodeDecodePanel.setBackground(Color.darkGray);

        encodeButton = this.buildButton("Encode " + RIGHT_ARROW);
        decodeButton = this.buildButton(LEFT_ARROW + " Decode");

        encodeButton.addActionListener(new EncodeButtonListener());
        decodeButton.addActionListener(new DecodeButtonListener());

        encodeDecodePanel.add(encodeButton);
        encodeDecodePanel.add(decodeButton);

        return encodeDecodePanel;
    }

    private JButton buildButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.BOLD, 24));
        return button;
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

        minusButton = this.buildButton("-");
        minusButton.addActionListener(new MinusButtonListener());

        shiftAmountLabel = new JLabel(" 0 ");
        shiftAmountLabel.setForeground(Color.white);
        shiftAmountLabel.setFont(new Font("Monospaced", Font.BOLD, 24));

        plusButton = this.buildButton("+");
        plusButton.addActionListener(new PlusButtonListener());

        shiftControlPanel.add(minusButton);
        shiftControlPanel.add(shiftAmountLabel);
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

    class PlainTextAreaListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            if (plainTextArea.getText().equals("Plaintext")) {
                plainTextArea.setText("");
                plainTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
                plainTextArea.setForeground(Color.black);
                plainTextArea.setEditable(true);
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
    }

    class CipherTextAreaListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            if (cipherTextArea.getText().equals("Ciphertext")) {
                cipherTextArea.setText("");
                cipherTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
                cipherTextArea.setForeground(Color.black);
                cipherTextArea.setEditable(true);
            }
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
    }

    class MinusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int shiftAmount = Integer.parseInt(shiftAmountLabel.getText().trim());

            if (shiftAmount > 0) {
                shiftAmountLabel.setText(" " + String.valueOf(--shiftAmount) + " ");

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
            int shiftAmount = Integer.parseInt(shiftAmountLabel.getText().trim());

            if (shiftAmount < 25) {
                shiftAmountLabel.setText(" " + String.valueOf(++shiftAmount) + " ");

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
            String encodedText = this.encodeText(plainTextArea.getText());
            cipherTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
            cipherTextArea.setForeground(Color.black);
            cipherTextArea.setEditable(true);
            cipherTextArea.setText(encodedText);
        }

        private String encodeText(String plainText) {
            List<Character> encodedText = new ArrayList<Character>();
            int shiftAmount = Integer.parseInt(shiftAmountLabel.getText().trim());

            for (int i = 0; i < plainText.length(); i++) {
                char plainChar = plainText.charAt(i);

                if (Character.isLetter(plainChar)) {
                    int plainIndex = alphabet.indexOf(plainChar);
                    int encodedIndex = plainIndex + shiftAmount;

                    if (encodedIndex < 26) {
                        encodedText.add(alphabet.charAt(encodedIndex));
                    } else {
                        encodedIndex = encodedIndex - 26;
                        encodedText.add(alphabet.charAt(encodedIndex));
                    }
                } else {
                    encodedText.add(plainChar);
                }
            }

            StringBuilder encodedTextStringBuilder = new StringBuilder(encodedText.size());
            for (Character ch : encodedText) {
                encodedTextStringBuilder.append(ch);
            }

            return encodedTextStringBuilder.toString();
        }
    }

    class DecodeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String decodedText = this.decodeText(cipherTextArea.getText());
            plainTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
            plainTextArea.setForeground(Color.black);
            plainTextArea.setText(decodedText);
        }

        private String decodeText(String encodedText) {
            List<Character> plainText = new ArrayList<Character>();

            int shiftAmount = Integer.parseInt(shiftAmountLabel.getText().trim());

            for (int i = 0; i < encodedText.length(); i++) {
                char encodedChar = encodedText.charAt(i);

                if (Character.isLetter(encodedChar)) {
                    int encodedIndex = alphabet.indexOf(encodedChar);
                    int plainIndex = encodedIndex - shiftAmount;

                    if (plainIndex >= 0) {
                        plainText.add(alphabet.charAt(plainIndex));
                    } else {
                        plainIndex = plainIndex + 26;
                        plainText.add(alphabet.charAt(plainIndex));
                    }
                } else {
                    plainText.add(encodedChar);
                }
            }

            StringBuilder plainTextStringBuilder = new StringBuilder(plainText.size());
            for (Character ch : plainText) {
                plainTextStringBuilder.append(ch);
            }

            return plainTextStringBuilder.toString();
        }
    }
}