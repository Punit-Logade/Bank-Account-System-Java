package bankAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BankGUI extends JFrame implements ActionListener {

    private JPanel btnPanel1, btnPanel2, inputPanel;
    private JTextField input;
    private JLabel displayLabel;
    private RoundedButton submit;
    private ArrayList<JButton> buttonsList = new ArrayList<>();
    private boolean isDarkMode = false;

    private BankLogic logic = new BankLogic();
    private String currentAction = "";

    private String defaultMessage = "Welcome to Banking System";

    private String[] leftButtons = {
            "Dark Mode",
            "Change Pin",
            "Check Balance",
            "Withdraw"
    };

    private String[] rightButtons = {
            "Hindi",
            "Create New Account",
            "Deposit",
            "Get Ac Details"
    };

    public BankGUI() {

        setTitle("Banking System");
        setLayout(new BorderLayout(20, 20));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnPanel1 = new JPanel(new GridLayout(4, 1, 12, 12));
        for (String text : leftButtons) {
            JButton btn = new RoundedButton(text);
            btn.addActionListener(this);
            buttonsList.add(btn);
            btnPanel1.add(btn);
        }

        inputPanel = new JPanel(new GridLayout(4, 1, 20, 20));

        displayLabel = new JLabel(defaultMessage, JLabel.CENTER);
        displayLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        input = new JTextField();
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setFont(new Font("Segoe UI", Font.BOLD, 20));

        submit = new RoundedButton("Submit");
        submit.setFont(new Font("Segoe UI", Font.BOLD, 18));
        submit.setPreferredSize(new Dimension(180, 50));
        submit.addActionListener(this);

        inputPanel.add(displayLabel);
        inputPanel.add(input);
        inputPanel.add(submit);

        btnPanel2 = new JPanel(new GridLayout(4, 1, 12, 12));
        for (String text : rightButtons) {
            JButton btn = new RoundedButton(text);
            btn.addActionListener(this);
            buttonsList.add(btn);
            btnPanel2.add(btn);
        }

        add(btnPanel1, BorderLayout.WEST);
        add(inputPanel, BorderLayout.CENTER);
        add(btnPanel2, BorderLayout.EAST);

        defaultTheme();

        setSize(850, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String key = e.getActionCommand();

        if (key.equals("Dark Mode")) {
            if (!isDarkMode) DarkTheme();
            else defaultTheme();
            return;
        }

        if (!key.equals("Submit")) {
            currentAction = key;
            showInstructions(key);
            input.setText("");
            return;
        }

        handleSubmit();
    }

    private void showInstructions(String action) {

        switch (action) {

            case "Create New Account":
                displayLabel.setText("Enter: AccountNo, Name, PIN");
                break;

            case "Deposit":
                displayLabel.setText("Enter: AccountNo, Amount");
                break;

            case "Withdraw":
                displayLabel.setText("Enter: AccountNo, PIN, Amount");
                break;

            case "Check Balance":
                displayLabel.setText("Enter: AccountNo, PIN");
                break;

            case "Change Pin":
                displayLabel.setText("Enter: AccountNo, OldPIN, NewPIN");
                break;

            case "Get Ac Details":
                displayLabel.setText("Enter: AccountNo");
                break;

            case "Hindi":
                displayLabel.setText("Hindi Comming Soon");
                break;

            default:
                displayLabel.setText("Select an operation.");
        }
    }

    private void handleSubmit() {

        if (currentAction.isEmpty()) {
            showTemporaryMessage("Nothing to Submit, Select an operation first.", 8);
            return;
        }

        try {

            String[] data = input.getText().split(",");
            String result = "";

            switch (currentAction) {

                case "Create New Account":
                    if (data.length != 3) throw new Exception();
                    result = logic.createAccount(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim());
                    break;

                case "Deposit":
                    if (data.length != 2) throw new Exception();
                    result = logic.deposit(
                            data[0].trim(),
                            Double.parseDouble(data[1].trim()));
                    break;

                case "Withdraw":
                    if (data.length != 3) throw new Exception();
                    result = logic.withdraw(
                            data[0].trim(),
                            data[1].trim(),
                            Double.parseDouble(data[2].trim()));
                    break;

                case "Check Balance":
                    if (data.length != 2) throw new Exception();
                    result = logic.checkBalance(
                            data[0].trim(),
                            data[1].trim());
                    break;

                case "Change Pin":
                    if (data.length != 3) throw new Exception();
                    result = logic.changePin(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim());
                    break;

                case "Get Ac Details":
                    if (data.length != 1) throw new Exception();
                    result = logic.getAccountDetails(data[0].trim());
                    break;
            }

            showTemporaryMessage(result, 15);

        } catch (Exception ex) {
            showTemporaryMessage("Invalid Input Format.", 6);
        }

        input.setText(""); // auto clear input
    }

    /* -------- TEMP MESSAGE RESET AFTER N SEC -------- */

    private void showTemporaryMessage(String message, int seconds) {

        displayLabel.setText(message);

        Timer timer = new Timer((seconds*1000), e -> {
            displayLabel.setText(defaultMessage);
        });

        timer.setRepeats(false);
        timer.start();
    }

    /* ---------------- LIGHT THEME ---------------- */

    public void defaultTheme() {

        btnPanel1.setBackground(new Color(242, 244, 248));
        btnPanel2.setBackground(new Color(242, 244, 248));
        inputPanel.setBackground(new Color(250, 251, 253));

        displayLabel.setForeground(new Color(30, 30, 35));
        input.setBackground(Color.WHITE);
        input.setForeground(new Color(30, 30, 35));

        for (JButton b : buttonsList) {
            b.setBackground(new Color(225, 230, 240));
            b.setForeground(new Color(45, 55, 72));
        }

        submit.setBackground(new Color(80, 120, 200));
        submit.setForeground(Color.WHITE);

        isDarkMode = false;
    }

    /* ---------------- DARK THEME ---------------- */

    public void DarkTheme() {
    
        btnPanel1.setBackground(new Color(28, 30, 34));
        btnPanel2.setBackground(new Color(28, 30, 34));
        inputPanel.setBackground(new Color(35, 37, 42));
    
        displayLabel.setForeground(new Color(220, 225, 235));
    
        // INPUT FIELD
        input.setBackground(new Color(40, 44, 52)); // deeper dark
        input.setForeground(new Color(180, 210, 255)); // soft bluish white
        input.setCaretColor(new Color(200, 220, 255)); // cursor color
        input.setSelectionColor(new Color(70, 90, 140)); // selection highlight
        input.setSelectedTextColor(Color.WHITE);
    
        for (JButton b : buttonsList) {
            b.setBackground(new Color(55, 58, 65));
            b.setForeground(new Color(255, 190, 140));
        }
    
        submit.setBackground(new Color(90, 140, 255));
        submit.setForeground(Color.WHITE);
    
        isDarkMode = true;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankGUI());
    }

}
