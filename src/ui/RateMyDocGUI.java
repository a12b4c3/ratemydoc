package ui;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RateMyDocGUI extends JFrame {
    public JPanel mainPanel;
    private JLabel authorReview;
    private JLabel searchReview;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel reviewAptTypeLabel;
    private JLabel docEmailLabel;
    private JLabel leaveReviewLabel;
    private JComboBox aptTypeDropDown;
    private JTextField doctorEmailAddress;
    private JLabel doctorNameLabel;
    private JLabel doctorLocationLabel;
    private JLabel doctorSpecLabel;
    private JTextField doctorSpecField;
    private JLabel doctorHospitalLabel;
    private JLabel starFilterLabel;
    private JComboBox starFilter;
    private JLabel resultsCountLabel;
    private JButton submitReviewButton;
    private JButton performQuery;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField doctorNameField;
    private JTextField doctorLocationField;
    private JTextField doctorHospitalField;
    private JTextArea reviewTextField;
    private JTextField adminUsername;
    private JTextField adminPasswordTextField;
    private JTextField reviewIDToDeleteTextField;
    private JButton deleteReviewButton;
    private JButton hideReviewButton;
    private JLabel StarRatingLabel;
    private JComboBox starRatingBox;
    private JScrollPane responseTextPanel;
    private JButton resetWriteButton;
    private JButton resetQueryButton;
    private JLabel writeReviewResponse;

    public RateMyDocGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        reviewTextField.setLineWrap(true);

        resetQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctorNameField.setText("");
                doctorLocationField.setText("");
                doctorSpecField.setText("");
                doctorHospitalField.setText("");
                starFilter.setSelectedIndex(0);
            }
        });

        resetWriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
                aptTypeDropDown.setSelectedIndex(0);
                doctorEmailAddress.setText("");
                writeReviewResponse.setText("");
                reviewTextField.setText("");
                starRatingBox.setSelectedIndex(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new RateMyDocGUI("RateMyDoc CANADA");
        frame.setMinimumSize(new Dimension(1000, 600));
        frame.setVisible(true);
    }


}
