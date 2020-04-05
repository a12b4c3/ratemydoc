package ui;

import javax.management.Query;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.LinkedList;
import java.util.Objects;

import database.DatabaseConnectionHandler;
import delegates.*;


public class RateMyDocGUI extends JFrame {
    private final delegates.RMDDelegate delegate;
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
    private JLabel doctorIdentifierLabel;
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
    private JButton monitorButton;
    private JLabel StarRatingLabel;
    private JComboBox starRatingBox;
    private JButton resetWriteButton;
    private JButton resetQueryButton;
    private JLabel writeReviewResponse;
    private JTextField editReviewField;
    private JLabel aptDateLabel;
    private JTextField aptDateField;
    private JLabel reviewCharCount;
    private JTextArea responseTextArea;
    private JComboBox docIdentifierBox;
    private JButton divisionQuery;
    private JButton countQuery;

    private boolean adminUsernameHasValue = false;
    private boolean adminPasswordHasValue = false;
    private boolean adminReviewIdHasValue = false;
    private boolean editReviewIdHasValue = false;
    private boolean aptDateHasValue = false;
    private boolean docEmailHasValue = false;

    public RateMyDocGUI(RMDDelegate delegate) {
        super("RateMyDoc CANADA");
        this.delegate = delegate;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        reviewTextField.setLineWrap(true);
        responseTextArea.setLineWrap(true);
        responseTextArea.setEditable(false);

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

        submitReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitReviewHandler();
            }
        });


        performQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                submitQueryHandler();
            }
        });

        deleteReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteReviewHandler();
            }
        });

        monitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO
            }
        });

        countQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                countQueryHandler();
            }
        });

        divisionQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                divisionQueryHandler();
            }
        });
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
            return;
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

        writeReviewResponse.setText("rID: " + delegate.insertReview(review));

        } else if (editReviewIdHasValue && Utils.hasNoEmptyStrings(new String[] {usr, pwd, rtext}) && drating != 0) { // edit case
            review.setReviewerUsername(usr);
            review.setReviewerPassword(pwd);
            review.setReviewText(rtext);
            review.setReviewRating(drating);
            review.setUpdateReviewId(ridedit);


            delegate.editReview(review);
        }
    }

    private void submitQueryHandler() {
        QueryReview query = new QueryReview();
        String docname = doctorNameField.getText();
        String docspec = doctorSpecField.getText();
        String dochos = doctorHospitalField.getText();
        int docrating = starFilter.getSelectedIndex();
        int docidentifier = docIdentifierBox.getSelectedIndex();
        query.setDoctorName(docname);
        query.setDocIdentifier(docidentifier);
        query.setDoctorSpecialization(docspec);
        query.setDoctorHospital(dochos);
        query.setOnlyDoctorsAboveRating(docrating);

        LinkedList<String> results = delegate.showReview(query);
        resultsCountLabel.setText(Utils.getCountText(results));
        responseTextArea.setText(Utils.concatLLString(results));
    }

    private void deleteReviewHandler(){
        String adminuser = adminUsernameTextField.getText();
        String adminpass = adminPasswordTextField.getText();
        String idToDelete = reviewIDToDeleteTextField.getText();
        WrittenReview wr = new WrittenReview();
        wr.setReviewerUsername(adminuser);
        wr.setReviewerPassword(adminpass);
        wr.setUpdateReviewId(idToDelete);
        delegate.deleteReview(wr);
    }

    private void countQueryHandler() {
        responseTextArea.setText("Total number of reviews across all doctors: " + delegate.countReviews());
    }

    private void divisionQueryHandler() {
        QueryReview query = new QueryReview();
        int docidentifier = docIdentifierBox.getSelectedIndex();
        query.setDocIdentifier(docidentifier);

        LinkedList<String> results = delegate.showMostReviewed(query);
        resultsCountLabel.setText(Utils.getCountText(results));
        responseTextArea.setText(Utils.concatLLString(results));
    }

    public static void main(String[] args) {
        Frame frame = new RateMyDocGUI(null);
        frame.setMinimumSize(new Dimension(1000, 600));
        frame.setVisible(true);
    }

}
