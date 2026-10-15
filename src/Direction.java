package CovidScreeningGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Direction implements ActionListener {

    private JFrame frame; 						  // หน้าต่างหลักของเกม
    private JTextField nameField; 				  // ช่องกรอกชื่อผู้เล่น
    private JButton quarantineButton, backButton; // ปุ่ม Quarantine และปุ่ม Back
    private JTextArea advice;                     // คำแนะนำเกี่ยวกับเกม
    private JLabel warning;                       // คำเตือนเกี่ยวกับเกม

	/**
	 * Main Method สำหรับเทสต์แยกคลาส
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Direction window = new Direction();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor ของ Direction
    public Direction() {
        initialize();  // เรียกใช้เมธอด initialize() เพื่อสร้าง UI
    }

    // Method จัดการ GUI บน Direction
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Direction");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(253, 246, 236));
        frame.getContentPane().setLayout(null);  // ใช้ layout แบบกำหนดตำแหน่งเอง

        // ชื่อหัวข้อ: COVID Screening Game
        JLabel chooseTitle = new JLabel("Covid Screening Game", SwingConstants.CENTER);
        chooseTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        chooseTitle.setBounds(150, 30, 500, 50);
        frame.getContentPane().add(chooseTitle);

        // ป้ายให้กรอกชื่อ Player
        JLabel nameLabel = new JLabel("To Be Screened By:");
        nameLabel.setFont(new Font("Century Gothic", Font.BOLD, 19));
        nameLabel.setBounds(300, 90, 200, 30);
        frame.getContentPane().add(nameLabel);

        // ช่องกรอกชื่อ Player
        nameField = new JTextField();
        nameField.setFont(new Font("Century Gothic", Font.BOLD, 14));
        nameField.setBounds(250, 130, 300, 30);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        // ปุ่มเริ่มเกม Quarantine
        quarantineButton = new JButton("Quarantine");
        quarantineButton.setBackground(new Color(185, 179, 172));
        quarantineButton.setFont(new Font("Algerian", Font.BOLD, 20));
        quarantineButton.setBounds(300, 430, 200, 65);
        quarantineButton.addActionListener(this);
        frame.getContentPane().add(quarantineButton);

        // ปุ่มย้อนกลับไปหน้า MainMenu
        backButton = new JButton("BACK");
        backButton.setBackground(new Color(185, 179, 172));
        backButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
        backButton.setBounds(620, 20, 100, 40);
        backButton.addActionListener(this);
        frame.getContentPane().add(backButton);

        // คำแนะนำเกี่ยวกับเกม
        advice = new JTextArea();
        advice.setBackground(new Color(253, 246, 236));
        advice.setFont(new Font("Century Gothic", Font.BOLD, 19));
        advice.setText(
                "Hey! Listen up.\n" +
                "You have 30 seconds to screen as many people as possible \n" +
                "from high-risk COVID-19 zones. \n\n" +
                "Anyone with a temperature of 37.5°C or higher could be infected.");
        advice.setBounds(85, 187, 616, 150);
        advice.setEditable(false); // ไม่ให้พิมพ์เพิ่ม
        frame.getContentPane().add(advice);

        // คำเตือนเกี่ยวกับเกม
        warning = new JLabel("Quarantine them before it's too late!");
        warning.setFont(new Font("Algerian", Font.BOLD, 29));
        warning.setBounds(85, 340, 616, 40);
        frame.getContentPane().add(warning);
        
        frame.setVisible(true);
    }

    // จัดการ event เมื่อมีการกดปุ่ม
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim(); // ดึงชื่อที่ Player กรอก และตัดช่องว่างหัว-ท้ายออก

        if (e.getSource() == backButton) {
            frame.dispose(); 	  // ปิดหน้าจอปัจจุบัน
            new MainMenu();   	  // ไปหน้า MainMenu
            return;
        }

        if (name.isEmpty()) {  // ถ้าไม่ได้กรอกชื่อให้แจ้งเตือน
            JOptionPane.showMessageDialog(frame,
                    "Please enter your name before proceeding.",  // ข้อความที่แสดงใน Dialog
                    "Missing Name",   // ชื่อหน้าต่าง Dialog
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (e.getSource() == quarantineButton) {
            frame.dispose();      // ปิดหน้าจอปัจจุบัน
            new GamePage(name);   // ไปหน้า GamePage พร้อมส่งชื่อ Player ไป
        }
    }
}
