package swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.regex.Pattern;

public class Prac1 extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private JPanel sideMenu;
    private boolean scientificMode = false;
    private boolean isMenuVisible = false;
    
    // Enhanced colors for better visibility
    private final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private final Color OPERATOR_COLOR = new Color(255, 140, 0);
    private final Color NUMBER_COLOR = new Color(255, 255, 255);
    private final Color FUNCTION_COLOR = new Color(100, 149, 237);
    private final Color MENU_COLOR = new Color(50, 50, 50);
    private final Color EQUALS_COLOR = new Color(46, 204, 113);
    private final Color CLEAR_COLOR = new Color(255, 99, 71);
    
    // Pattern for checking invalid operator sequences
    private final Pattern INVALID_OPERATOR_PATTERN = Pattern.compile("([+\\-*/^]{2,})");
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
                Prac1 frame = new Prac1();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Prac1() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setTitle("Scientific Calculator");
        setLayout(new BorderLayout());
        setResizable(true);
        getContentPane().setBackground(BACKGROUND_COLOR);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBackground(BACKGROUND_COLOR);
        JButton menuButton = createHamburgerButton();
        topPanel.add(menuButton, BorderLayout.WEST);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Segoe UI", Font.BOLD, 32));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        textField.setBackground(Color.WHITE);
        topPanel.add(textField, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(BACKGROUND_COLOR);
        updateButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        createSideMenu();

        add(mainPanel, BorderLayout.CENTER);
        add(sideMenu, BorderLayout.WEST);
        sideMenu.setVisible(false);
    }

    private JButton createHamburgerButton() {
        JButton menuButton = new JButton("☰");
        menuButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        menuButton.setFocusPainted(false);
        menuButton.setBackground(BACKGROUND_COLOR);
        menuButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        menuButton.addActionListener(e -> toggleMenu());
        return menuButton;
    }

    private void createSideMenu() {
        sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBackground(MENU_COLOR);
        sideMenu.setPreferredSize(new Dimension(200, getHeight()));
        sideMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel menuTitle = new JLabel("Calculator Mode");
        menuTitle.setForeground(Color.WHITE);
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideMenu.add(menuTitle);
        sideMenu.add(Box.createVerticalStrut(20));

        String[] modes = {"Standard", "Scientific"};
        ButtonGroup group = new ButtonGroup();

        for (String mode : modes) {
            JRadioButton radioButton = new JRadioButton(mode);
            radioButton.setForeground(Color.WHITE);
            radioButton.setBackground(MENU_COLOR);
            radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            radioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            radioButton.addActionListener(e -> {
                scientificMode = mode.equals("Scientific");
                updateButtonPanel();
                toggleMenu();
            });
            group.add(radioButton);
            sideMenu.add(radioButton);
            sideMenu.add(Box.createVerticalStrut(10));
        }
        
        ((JRadioButton)sideMenu.getComponent(2)).setSelected(true);
    }

    private void toggleMenu() {
        isMenuVisible = !isMenuVisible;
        sideMenu.setVisible(isMenuVisible);
        revalidate();
    }

    private void updateButtonPanel() {
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(scientificMode ? 7 : 5, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons;
        if (scientificMode) {
            buttons = new String[]{
                "sin", "cos", "tan", "C",
                "log", "ln", "^", "÷",
                "√", "(", ")", "×",
                "7", "8", "9", "−",
                "4", "5", "6", "+",
                "1", "2", "3", "=",
                "0", ".", "π", "⌫"
            };
        } else {
            buttons = new String[]{
                "C", "⌫", "(", ")",
                "7", "8", "9", "÷",
                "4", "5", "6", "×",
                "1", "2", "3", "−",
                "0", ".", "=", "+"
            };
        }

        for (String text : buttons) {
            JButton button = createStyledButton(text);
            buttonPanel.add(button);
        }

        revalidate();
        repaint();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        if (text.matches("[0-9.π]")) {
            styleButton(button, NUMBER_COLOR, Color.BLACK);
        } else if (text.matches("[+−×÷^()]")) {
            styleButton(button, OPERATOR_COLOR, Color.BLACK);
        } else if (text.equals("=")) {
            styleButton(button, EQUALS_COLOR, Color.BLACK);
        } else if (text.equals("C")) {
            styleButton(button, CLEAR_COLOR, Color.BLACK);
        } else {
            styleButton(button, FUNCTION_COLOR, Color.BLACK);
        }

        button.addActionListener(e -> handleButtonClick(text));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(button.getBackground().brighter());
            }
        });

        return button;
    }

    private void styleButton(JButton button, Color backgroundColor, Color textColor) {
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setOpaque(true);
        button.setBorderPainted(true);
    }

    private void handleButtonClick(String command) {
        String currentText = textField.getText();
        
        switch (command) {
            case "C":
                textField.setText("");
                break;
            case "⌫":
                if (!currentText.isEmpty()) {
                    textField.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            case "=":
                if (!currentText.isEmpty()) {
                    calculateAndDisplayResult();
                }
                break;
            case "π":
                textField.setText(currentText + String.valueOf(Math.PI));
                break;
            case "sin":
            case "cos":
            case "tan":
            case "log":
            case "ln":
            case "√":
            case "^":
                handleScientificFunction(command, currentText);
                break;
            default:
                // Check for invalid operator sequences before adding new operator
                if (isOperator(command) && !currentText.isEmpty()) {
                    String newText = currentText + command;
                    if (INVALID_OPERATOR_PATTERN.matcher(newText).find()) {
                        JOptionPane.showMessageDialog(this, 
                            "Invalid operator sequence", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                textField.setText(currentText + command);
        }
    }

    private boolean isOperator(String s) {
        return s.matches("[+−×÷^()]");
    }

    private void handleScientificFunction(String function, String currentText) {
        try {
            double input = currentText.isEmpty() ? 0 : evaluateExpression(currentText);
            double result = switch (function) {
                case "sin" -> Math.sin(Math.toRadians(input));
                case "cos" -> Math.cos(Math.toRadians(input));
                case "tan" -> Math.tan(Math.toRadians(input));
                case "log" -> Math.log10(input);
                case "ln" -> Math.log(input);
                case "√" -> Math.sqrt(input);
                case "^" -> Math.pow(input,input);              
                default -> input;
            };
            textField.setText(String.format("%.8f", result));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid input for " + function, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateAndDisplayResult() {
        try {
            String expression = textField.getText()
                .replace("×", "*")
                .replace("÷", "/")
                .replace("−", "-")
                .replace(" ", "");  // Remove whitespace
            
            double result = evaluateExpression(expression);
            
            // Format result: show as integer if possible, otherwise with decimal places
            String formattedResult;
            if (result == (long) result) {
                formattedResult = String.format("%d", (long) result);
            } else {
                formattedResult = String.format("%.8f", result);
            }
            textField.setText(formattedResult);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid expression", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            textField.setText("");
        }
    }

    private double evaluateExpression(String expression) {
        try {
            return evaluatePostfix(infixToPostfix(expression));
        } catch (Exception e) {
            throw new ArithmeticException("Invalid expression");
        }
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    private String infixToPostfix(String expression) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operators = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            // Handle parentheses
            if (ch == '(') {
                operators.push(ch);
            }
            else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop());
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                }
            }
            
            // Handle numbers (including decimal numbers)
            else if (Character.isDigit(ch) || ch == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && 
                       (Character.isDigit(expression.charAt(i)) || 
                        expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--;
                postfix.append(num).append(' ');
            }
            
            // Handle operators
            else if ("+-*/^".indexOf(ch) != -1) {
                while (!operators.isEmpty() && 
                       precedence(operators.peek()) >= precedence(ch)) {
                    postfix.append(operators.pop());
                }
                operators.push(ch);
            }
        }
        
        // Pop remaining operators
        while (!operators.isEmpty()) {
            postfix.append(operators.pop());
        }
        
        return postfix.toString();
    }

    private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            
            // Skip spaces
            if (ch == ' ') continue;
            
            // Handle numbers
            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder num = new StringBuilder();
                while (i < postfix.length() && 
                       (Character.isDigit(postfix.charAt(i)) || 
                        postfix.charAt(i) == '.')) {
                    num.append(postfix.charAt(i));
                    i++;
                }
                i--;
                stack.push(Double.parseDouble(num.toString()));
            }
            
            // Handle operators
            else if ("+-*/^".indexOf(ch) != -1) {
                if (stack.size() < 2) {
                    throw new ArithmeticException("Invalid expression");
                }
                
                double b = stack.pop();
                double a = stack.pop();
                
                switch (ch) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a-b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        if (b == 0) throw new ArithmeticException("Division by zero");
                        stack.push(a / b);
                        break;
                    case '^':
                        stack.push(Math.pow(a, b));
                        break;
                }
            }
        }
        
        if (stack.size() != 1) {
            throw new ArithmeticException("Invalid expression");
        }
        
        return stack.pop();
    }
}