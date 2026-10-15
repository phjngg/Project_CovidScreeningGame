package CovidScreeningGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverPage implements ActionListener {

	private JFrame frame;         // หน้าต่างหลักของเกม
	private String playerName;    // ชื่อ Player
	private int score;            // คะแนนที่ Player ทำได้
	private JLabel gameOverLabel, messageLabel, resultLabel;
	private JButton homeButton, tryAgainButton; 
	private ScoreManager scoreManager;

	/**
	 * Main Method สำหรับเทสต์แยกคลาส
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOverPage window = new GameOverPage("Tester", 0);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor ของ GameOverPage 
	public GameOverPage(String playerName, int score) {
		this.playerName = playerName; // รับชื่อ Player
		this.score = score;           // รับคะแนนที่ Player ทำได้
		initialize();                 // เรียกใช้เมธอด initialize() เพื่อสร้าง UI
	}

	// Method จัดการ GUI บน GameOverPage
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Game Over");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(253, 246, 236));
		frame.getContentPane().setLayout(null);  // ใช้ layout แบบกำหนดตำแหน่งเอง

		// ป้ายแสดงหัวข้อ GAME OVER
		gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
		gameOverLabel.setFont(new Font("Algerian", Font.BOLD, 52));
		gameOverLabel.setForeground(Color.RED);
		gameOverLabel.setBounds(150, 50, 500, 60);
		frame.getContentPane().add(gameOverLabel);

		// ป้ายแสดง Message
		messageLabel = new JLabel("Not Bad " + playerName + "!", SwingConstants.CENTER);
		messageLabel.setFont(new Font("Algerian", Font.PLAIN, 44));
		messageLabel.setForeground(Color.BLACK);
		messageLabel.setBounds(150, 140, 500, 50);
		frame.getContentPane().add(messageLabel);

		// ป้ายแสดง Score ที่ Player ทำได้
		resultLabel = new JLabel("You quarantined a total of: " + score, SwingConstants.CENTER);
		resultLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
		resultLabel.setForeground(Color.BLACK);
		resultLabel.setBounds(100, 240, 600, 50);
		frame.getContentPane().add(resultLabel);

		// บันทึกคะแนนลงไฟล์ จากคลาส ScoreManager
		scoreManager = new ScoreManager();
		scoreManager.saveScore(playerName, score);

		// ปุ่มไปหน้าหลัก Home
		homeButton = new JButton("Home");
		homeButton.setBackground(new Color(185, 179, 172));
		homeButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
		homeButton.setBounds(450, 400, 150, 50);
		homeButton.addActionListener(this);
		frame.getContentPane().add(homeButton);

		// ปุ่มเล่นอีกครั้ง Try Again
		tryAgainButton = new JButton("Try Again");
		tryAgainButton.setBackground(new Color(185, 179, 172));
		tryAgainButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
		tryAgainButton.setBounds(200, 400, 150, 50);
		tryAgainButton.addActionListener(this);
		frame.getContentPane().add(tryAgainButton);

		frame.setVisible(true);
	}


	// จัดการ event เมื่อมีการกดปุ่ม	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tryAgainButton) {
			frame.dispose();     // ปิดหน้าต่างนี้
			new Direction();     // กลับไปกรอกชื่อใหม่
		} else if (e.getSource() == homeButton) {
			frame.dispose();     // ปิดหน้าต่างนี้
			new MainMenu();      // กลับไปหน้า Main Menu
		}
	}
}
