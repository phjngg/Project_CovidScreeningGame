package CovidScreeningGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu implements ActionListener {

    private JFrame frame;             // หน้าต่างหลักของเกม
    private JButton playButton;       // ปุ่มเริ่มเกม
    private JButton highScoreButton;  // ปุ่มดูคะแนนทั้งหมด
    private JLabel background;        // พื้นหลัง

	/**
	 * Main Method สำหรับเทสต์แยกคลาส
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainMenu window = new MainMenu();       // สร้าง object ของ MainMenu
                    window.frame.setVisible(true);          // แสดงหน้าต่าง
                } catch (Exception e) {
                    e.printStackTrace();                    // แสดง error ถ้าเกิดปัญหา
                }
            }
        });
    }

    // Constructor ของ MainMenu
    public MainMenu() {
        initialize();  // เรียกใช้เมธอด initialize() เพื่อสร้าง UI
    }

    // Method จัดการ GUI บน MainMenu
    private void initialize() {
        frame = new JFrame();                               
        frame.setTitle("Main Menu");
        frame.setBounds(100, 100, 800, 600);       			  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.getContentPane().setBackground(Color.WHITE);  
        frame.getContentPane().setLayout(null);       		   // ใช้ layout แบบกำหนดตำแหน่งเอง

        // ปุ่ม PLAY
        playButton = new JButton("PLAY");
        playButton.setBackground(new Color(185, 179, 172));
        playButton.setFont(new Font("Century Gothic", Font.BOLD, 24)); 
        playButton.setBounds(300, 250, 200, 50);                      
        playButton.addActionListener(this);    // เมื่อคลิกปุ่ม ระบบจะเรียกเมธอด actionPerformed()                
        frame.getContentPane().add(playButton);

        // ปุ่ม HIGH SCORE
        highScoreButton = new JButton("HIGH SCORE");
        highScoreButton.setBackground(new Color(185, 179, 172));
        highScoreButton.setFont(new Font("Century Gothic", Font.BOLD, 24));
        highScoreButton.setBounds(300, 320, 200, 50);
        highScoreButton.addActionListener(this);
        frame.getContentPane().add(highScoreButton);
        
        // พื้นหลัง
        background = new JLabel(new ImageIcon("src/images/menu_bg.png"));
        background.setBackground(new Color(253, 246, 236));
        background.setBounds(0, 0, 800, 600);
        frame.getContentPane().add(background);
        
        frame.setVisible(true); 
    }

    // จัดการ event เมื่อมีการกดปุ่ม
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            frame.dispose();           // ปิดหน้าจอปัจจุบัน
            new Direction();           // ไปหน้า Direction
        } else if (e.getSource() == highScoreButton) {
        	frame.dispose();           // ปิดหน้าจอปัจจุบัน
            new HighScorePage();       // ไปหน้า HighScorePage
        }
    }
}
