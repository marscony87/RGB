import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RGBApplication extends JFrame implements ActionListener {


    private JLabel redLabel, greenLabel, blueLabel;
    private JTextField redTextField, greenTextField, blueTextField;
    private JRadioButton blinkRadioButton, randomRadioButton;
    private JButton startButton;
    private Timer timer;
    private JPanel panel;

    public RGBApplication() {

        setTitle("RGB app");


        setSize(400, 300);
        setLocationRelativeTo(null);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        redLabel = new JLabel("Red:");
        greenLabel = new JLabel("Green:");
        blueLabel = new JLabel("Blue:");


        redTextField = new JTextField("", 3);
        greenTextField = new JTextField("", 3);
        blueTextField = new JTextField("", 3);


        blinkRadioButton = new JRadioButton("Blink");
        randomRadioButton = new JRadioButton("Random");

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(135, 25));

        startButton.addActionListener(this);


        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(blinkRadioButton);
        radioGroup.add(randomRadioButton);


        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(redLabel, gbc);
        gbc.gridx++;
        panel.add(redTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(greenLabel, gbc);
        gbc.gridx++;
        panel.add(greenTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(blueLabel, gbc);
        gbc.gridx++;
        panel.add(blueTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(blinkRadioButton, gbc);
        gbc.gridx++;
        panel.add(randomRadioButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(startButton, gbc);


        panel.setBackground(new Color(196, 171, 199));
        add(panel);


        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            try {

                int red = Integer.parseInt(redTextField.getText());
                int green = Integer.parseInt(greenTextField.getText());
                int blue = Integer.parseInt(blueTextField.getText());

                if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {

                    throw new NumberFormatException();
                }


                if (blinkRadioButton.isSelected()) {

                    if (timer != null && timer.isRunning()) {
                        timer.stop();
                    }

                    timer = new Timer(5000, new ActionListener() {
                        boolean isWhite = false;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (isWhite) {
                                panel.setBackground(new Color(red, green, blue));
                                isWhite = false;
                            } else {
                                panel.setBackground(Color.WHITE);
                                isWhite = true;
                            }
                        }
                    });

                    timer.start();
                }

                else if (randomRadioButton.isSelected()) {

                    if (timer != null && timer.isRunning()) {
                        timer.stop();
                    }
                    timer = new Timer(5000, new ActionListener() {
                        boolean isColor = false;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (isColor) {
                                panel.setBackground(new Color(red, green, blue));
                                isColor = false;
                            } else {

                                Color randomColor = new Color((int) (Math.random() * 0x1000000));
                                panel.setBackground(randomColor);
                                isColor = true;
                            }
                        }
                    });

                    timer.start();
                }
            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this, "Unesite validne brojeve od 0 do 255.", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
