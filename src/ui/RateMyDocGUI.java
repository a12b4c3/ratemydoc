package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

import database.DatabaseConnectionHandler;
import delegates.*;


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
    private JTextField adminUsernameTextField;
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
    private JLabel adminServerResponse;
    private JTextField editReviewField;
    private JLabel aptDateLabel;
    private JTextField aptDateField;
    private JLabel reviewCharCount;

    private boolean adminUsernameHasValue = false;
    private boolean adminPasswordHasValue = false;
    private boolean adminReviewIdHasValue = false;
    private boolean editReviewIdHasValue = false;
    private boolean aptDateHasValue = false;
    private boolean docEmailHasValue = false;

    private RMDDelegate delegate;

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
                writeReviewResponse.setText("Resp: ");
                reviewTextField.setText("");
                starRatingBox.setSelectedIndex(0);
                aptDateField.setText("YYYY-MM-DD");
                aptDateHasValue = false;
            }
        });

        adminUsernameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (!adminUsernameHasValue) {
                    adminUsernameTextField.setText("");
                }
            }
        });

        adminUsernameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (adminUsernameTextField.getText().equals("")) {
                    adminUsernameHasValue = false;
                    adminUsernameTextField.setText("Admin Username");
                } else {
                    adminUsernameHasValue = true;
                }
            }
        });

        adminPasswordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (!adminPasswordHasValue) {
                    adminPasswordTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (adminPasswordTextField.getText().equals("")) {
                    adminPasswordHasValue = false;
                    adminPasswordTextField.setText("Admin Password");
                } else {
                    adminPasswordHasValue = true;
                }
            }
        });
        reviewIDToDeleteTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (!adminReviewIdHasValue) {
                    reviewIDToDeleteTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (reviewIDToDeleteTextField.getText().equals("")) {
                    adminReviewIdHasValue = false;
                    reviewIDToDeleteTextField.setText("Review to Delete");
                } else {
                    adminReviewIdHasValue = true;
                }
            }
        });
        editReviewField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!editReviewIdHasValue) {
                    editReviewField.setText("");
                }
                super.focusGained(e);

            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (editReviewField.getText().equals("")) {
                    editReviewIdHasValue = false;
                    editReviewField.setText("Enter valid rID to update review.");
                } else {
                    editReviewIdHasValue = true;
                }
            }
        });
        reviewTextField.addFocusListener(new FocusAdapter() {
        });

        aptDateField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (!aptDateHasValue) {
                    aptDateField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (aptDateField.getText().equals("")) {
                    aptDateHasValue = false;
                    aptDateField.setText("YYYY-MM-DD");
                } else {
                    aptDateHasValue = true;
                }
            }
        });

        reviewTextField.getDocument().addDocumentListener(new DocumentListener() {
            int MAX_REVIEW_CHAR_LENGTH = 250;
            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                int charCount = reviewTextField.getText().length();
                reviewCharCount.setText("Character count: "+ charCount + " /" + MAX_REVIEW_CHAR_LENGTH);
                if (charCount > MAX_REVIEW_CHAR_LENGTH) {
                    reviewCharCount.setText("Too many characters! " + MAX_REVIEW_CHAR_LENGTH + " max!");
                }
            }
        });

        doctorEmailAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        //start the database
        new DatabaseConnectionHandler("ora_limen6", "a59418160");

        JFrame frame = new RateMyDocGUI("RateMyDoc CANADA");
        frame.setMinimumSize(new Dimension(1000, 600));
        frame.setVisible(true);
    }

    private void submitReviewHandler() {
        writeReviewResponse.setText("Resp: ");
        String usr = usernameField.getText().trim();
        String pwd = passwordField.getText().trim();
        String atype = Objects.requireNonNull(aptTypeDropDown.getSelectedItem()).toString().trim();
        String adate = aptDateField.getText().trim();
        if (!Utils.isValidDate(adate)) {
            System.out.println(adate);
            aptDateField.setText("Invalid Date, use format YYYY-MM-DD");
            aptDateHasValue = false;
            return;
        }
        String demail = doctorEmailAddress.getText().trim();
        if (!Utils.isValidEmail(demail)) {
            doctorEmailAddress.setText("Invalid Email, please set Email.");
        }
        String rtext = reviewTextField.getText().trim();
        int drating = starRatingBox.getSelectedIndex();
        String ridedit = editReviewField.getText().trim();
        WrittenReview review = new WrittenReview();

        if (!editReviewIdHasValue && Utils.hasNoEmptyStrings(new String[] {usr, pwd, atype, adate, adate, demail, rtext}) && drating != 0) { // submit case
        review.setReviewerUsername(usr);
        review.setReviewerPassword(pwd);
        review.setAppointmentType(atype);
        review.setAppointmentDate(adate);
        review.setDoctorEmailAddress(demail);
        review.setReviewText(rtext);
        review.setReviewRating(drating);
        // todo submit to delegate

        } else if (editReviewIdHasValue && Utils.hasNoEmptyStrings(new String[] {usr, pwd, rtext}) && drating != 0) { // edit case
            review.setReviewerUsername(usr);
            review.setReviewerPassword(pwd);
            review.setReviewRating(drating);
            // todo submit to delegate
        }
    }

    private void submitQueryHandler() {
        String docname = doctorNameField.getText();
        String docloc = doctorLocationField.getText();
        String docspec = doctorSpecField.getText();
        String dochos = doctorHospitalField.getText();
        int docrating = starFilter.getSelectedIndex();
        // todo submit to delegate
    }

    private void submitEditHandler() {

    }


}
