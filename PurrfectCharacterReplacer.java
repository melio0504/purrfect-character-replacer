import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.net.URL;

public class PurrfectCharacterReplacer {
    private static final Color CAT_ORANGE = new Color(255, 153, 51);
    private static final Color CAT_GRAY = new Color(200, 200, 200);
    private static final Color CAT_PINK = new Color(255, 153, 204);
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Purr-fect Character Replacer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        
        try {
            frame.setIconImage(ImageIO.read(new URL("https://cdn-icons-png.flaticon.com/512/616/616408.png")));
        } catch (Exception e) {
            System.out.println("Couldn't load cat icon");
        }
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(CAT_ORANGE);
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("Purr-fect Character Replacer");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        try {
            ImageIcon catPawIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/512/1864/1864514.png"));
            catPawIcon = new ImageIcon(catPawIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
            titleLabel.setIcon(catPawIcon);
            titleLabel.setIconTextGap(15);
        } catch (Exception e) {
            System.out.println("Couldn't load cat paw icon");
        }
        
        headerPanel.add(titleLabel);
        frame.add(headerPanel, BorderLayout.NORTH);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(CAT_GRAY);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel inputLabel = new JLabel("Enter your text here:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(inputLabel, gbc);
        
        JTextArea inputTextArea = new JTextArea(8, 40);
        inputTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setBorder(new LineBorder(CAT_ORANGE, 2));
        gbc.gridy = 1;
        mainPanel.add(inputScrollPane, gbc);
        
        JPanel replacePanel = new JPanel(new GridLayout(2, 2, 15, 15));
        replacePanel.setBackground(CAT_GRAY);
        replacePanel.setBorder(new CompoundBorder(
            new TitledBorder(new LineBorder(CAT_ORANGE, 2), "Character Replacement", 
                             TitledBorder.CENTER, TitledBorder.TOP,
                             new Font("Arial", Font.BOLD, 16), CAT_ORANGE),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel replaceLabel = new JLabel("Character to replace:");
        replaceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        replacePanel.add(replaceLabel);
        
        JTextField replaceField = new JTextField(5);
        replaceField.setFont(new Font("Arial", Font.PLAIN, 14));
        replaceField.setHorizontalAlignment(JTextField.CENTER);
        replacePanel.add(replaceField);
        
        JLabel withLabel = new JLabel("Replace with:");
        withLabel.setFont(new Font("Arial", Font.BOLD, 14));
        replacePanel.add(withLabel);
        
        JTextField withField = new JTextField(5);
        withField.setFont(new Font("Arial", Font.PLAIN, 14));
        withField.setHorizontalAlignment(JTextField.CENTER);
        replacePanel.add(withField);
        
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(replacePanel, gbc);
        
        JButton replaceButton = new JButton("Replace Characters!");
        styleCatButton(replaceButton);
        
        try {
            ImageIcon catIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/512/8277/8277564.png"));
            catIcon = new ImageIcon(catIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            replaceButton.setIcon(catIcon);
        } catch (Exception e) {
            System.out.println("Couldn't load cat button icon");
        }
        
        gbc.gridy = 3;
        mainPanel.add(replaceButton, gbc);
        
        JLabel outputLabel = new JLabel("Result:");
        outputLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 4;
        mainPanel.add(outputLabel, gbc);
        
        JTextArea outputTextArea = new JTextArea(8, 40);
        outputTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setBorder(new LineBorder(CAT_ORANGE, 2));
        gbc.gridy = 5;
        mainPanel.add(outputScrollPane, gbc);
        
        frame.add(mainPanel, BorderLayout.CENTER);
        
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(CAT_PINK);
        footerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel footerLabel = new JLabel("Meow! Happy text editing!");
        footerLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);
        
        frame.add(footerPanel, BorderLayout.SOUTH);
        
        replaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputTextArea.getText();
                String replaceChar = replaceField.getText();
                String withChar = withField.getText();
                
                if (replaceChar.length() != 1 || withChar.length() != 1) {
                    JOptionPane.showMessageDialog(frame, 
                        "Please enter exactly one character in each field!", 
                        "Meow! Error!", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String result = text.replace(replaceChar.charAt(0), withChar.charAt(0));
                outputTextArea.setText(result);
                
                String[] catMessages = {
                    "Purr-fect replacement!",
                    "Meow! Job done!",
                    "Characters replaced successfully! *purr*",
                    "Text transformed! *licks paw*"
                };
                
                Random rand = new Random();
                String message = catMessages[rand.nextInt(catMessages.length)];
                
                try {
                    ImageIcon happyCat = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/512/3468/3468370.png"));
                    happyCat = new ImageIcon(happyCat.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                    JOptionPane.showMessageDialog(frame, message, "Success!", JOptionPane.INFORMATION_MESSAGE, happyCat);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, message);
                }
                
                replaceButton.setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            }
        });
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private static void styleCatButton(JButton button) {
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        button.setBackground(CAT_ORANGE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
            new LineBorder(Color.WHITE, 2),
            new EmptyBorder(10, 20, 10, 20)
        ));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(CAT_ORANGE.darker());
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(CAT_ORANGE);
            }
        });
    }
}