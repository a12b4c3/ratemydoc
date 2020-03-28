package ui;

import javax.swing.*;
import java.awt.*;

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
    private JTextField textField1;
    private JLabel doctorNameLabel;
    private JLabel doctorLocationLabel;
    private JLabel doctorSpecLabel;
    private JTextField doctorSpecialization;
    private JLabel doctorHospitalLabel;
    private JLabel starFilterLabel;
    private JComboBox starFilter;
    private JLabel resultsCountLabel;
    private JButton submitReviewButton;
    private JButton performQuery;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextArea textArea1;
    private JTextField adminUsername;
    private JTextField adminPasswordTextField;
    private JTextField reviewIDToDeleteTextField;
    private JButton button1;

    public RateMyDocGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new RateMyDocGUI("RateMyDoc CANADA");
        frame.setMinimumSize(new Dimension(1000, 600));
        frame.setVisible(true);
    }

}
