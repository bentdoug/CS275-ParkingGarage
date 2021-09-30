package parkinggarage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

class GUI extends JPanel {

    public static boolean checkCode(String code) {
        boolean isValid = false;
        if (!code.equals("booty")) {
            if (code.length() == 4) {
                for (int i = 0; i < code.length(); i++) {
                    if (code.charAt(i) == '0' || code.charAt(i) == '1' || code.charAt(i) == '2' || code.charAt(i) == '3' || code.charAt(i) == '4' || code.charAt(i) == '5' || code.charAt(i) == '6' || code.charAt(i) == '7' || code.charAt(i) == '8' || code.charAt(i) == '9') {
                        isValid = true;
                    } else {
                        isValid = false;
                        break;
                    }
                }
            } else {
                isValid = false;
            }
        } else {
            isValid = true;
        }
        return isValid;
    }
    public static double getAmountDue(String code){
        double amountDue = 9;
        if(code.equals("booty")){
            amountDue=420.69;
            return amountDue;
        }else{
            return amountDue;
        }
    }

    public static void main(String args[]) throws Exception {

        //Getting dimensions for full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        //Creating the window that pops up
        JFrame frame = new JFrame("Parking Garage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        Color background = new Color(2, 0, 102);
        //Creating the panel at top and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JPanel box = new JPanel();
        box.setBackground(background);
        JLabel label = new JLabel("Enter Parking Code");
        label.setFont(label.getFont().deriveFont(64.0f));
        label.setForeground(Color.WHITE);
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        tf.setPreferredSize(new Dimension(200, 70));
        tf.setFont(tf.getFont().deriveFont(64.0f));
        JButton send = new JButton("Send");
        JButton reset = new JButton("Clear");
        reset.setBounds(50, 100, 95, 30);
        JLabel InvalidCode = new JLabel("");
        InvalidCode.setForeground(Color.RED);
        InvalidCode.setFont(label.getFont().deriveFont(64.0f));
           double amountDue = 9.0;
        JLabel AcceptedCode = new JLabel("");
        AcceptedCode.setForeground(Color.GREEN);
        AcceptedCode.setVisible(false);
        InvalidCode.setVisible(false);
        AcceptedCode.setFont(label.getFont().deriveFont(64.0f));
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("");
                AcceptedCode.setVisible(false);
                InvalidCode.setVisible(false);
            }
        });
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String textFieldValue = tf.getText();
                    if (checkCode(textFieldValue)) {
                       double amountDue = getAmountDue(textFieldValue);
                    AcceptedCode.setText("Code accepted Amount Due:  $" + amountDue);
                    AcceptedCode.setVisible(true);
                    InvalidCode.setVisible(false);
                    String code = textFieldValue;
                    
                } else {
                    InvalidCode.setText("Invalid Code. Try again");    
                    AcceptedCode.setVisible(false);
                    InvalidCode.setVisible(true);
                }
            }
        });

        send.setFont(tf.getFont().deriveFont(64.0f));
        reset.setFont(tf.getFont().deriveFont(64.0f));
        box.add(label); // Components Added using Flow Layout
        box.add(tf);
        box.add(send);
        box.add(reset);
        box.add(AcceptedCode);
        box.add(InvalidCode);
        box.setSize(width / 2, height / 2);
        panel.add(box);
        //Adding Components to the frame.
        frame.add(box, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
