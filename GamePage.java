package CovidScreeningGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePage implements ActionListener {

	private JFrame frame;      // หน้าต่างหลักของเกม
	private String playerName; // เก็บชื่อ Player ที่รับมาจากหน้า Direction
	private int score = 0;     // คะแนนที่ได้เริ่มต้น
	private int timeLeft = 30; // ตัวจับเวลาถอยหลังเริ่มต้นที่ 30 วินาที

	// แสดงชื่อผู้เล่น คะแนนปัจจุบัน เวลาที่เหลือ และสถานะอุณหภูมิ
	private JLabel welcomeLabel, scoreLabel, timerLabel, statusLabel;  
	// ปุ่มหยุดเกม Quarantine และ Release
	private JButton pauseButton, quarantineButton, releaseButton;    
	private Timer countdownTimer; // ตัวจับเวลานับถอยหลัง
	private Character currentCharacter; // ตัวละครที่สุ่มมา(เก็บอุณหภูมิ)
	// background แบบสุ่ม 
	private JLabel backgroundLabel;
	private String[] backgroundImages = {
			"src/images/game1_bg.png",
			"src/images/game2_bg.png",
			"src/images/game3_bg.png",
			"src/images/game4_bg.png",
	};

	/**
	 * Main Method สำหรับเทสต์แยกคลาส
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePage window = new GamePage("Tester");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Constructor ของ GamePage
	public GamePage(String name) {
		this.playerName = name;  // รับชื่อ Player มาจาก Direction
		initialize();            // เรียกใช้เมธอด initialize() เพื่อสร้าง UI
		spawnCharacter();        // สุ่มอุณหภูมิครั้งแรก
		startCountdown();        // เริ่มจับเวลาถอยหลัง
	}

	// Method จัดการ GUI บน GamePage
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Covid Screening Game");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);  // ใช้ layout แบบกำหนดตำแหน่งเอง

		// ป้ายแสดงชื่อ Player
		welcomeLabel = new JLabel("Hurry up " + playerName + "!");
		welcomeLabel.setFont(new Font("Century Gothic", Font.BOLD, 24));
		welcomeLabel.setBounds(20, 20, 400, 40);
		frame.getContentPane().add(welcomeLabel);

		// ป้ายแสดง Score
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		scoreLabel.setBounds(20, 70, 200, 30);
		frame.getContentPane().add(scoreLabel);

		// ป้ายแสดงเวลาที่เหลือ Time Left
		timerLabel = new JLabel("Time Left: " + timeLeft + "s");
		timerLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
		timerLabel.setBounds(620, 20, 170, 30);
		frame.getContentPane().add(timerLabel);

		// ปุ่มหยุดเกม Pause
		pauseButton = new JButton("Pause");
		pauseButton.setBackground(new Color(185, 179, 172));
		pauseButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
		pauseButton.setBounds(358, 20, 100, 40);
		pauseButton.addActionListener(this);
		frame.getContentPane().add(pauseButton);

		// ปุ่มกักตัว Quarantine
		quarantineButton = new JButton("Quarantine");
		quarantineButton.setBackground(new Color(253, 246, 236));
		quarantineButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
		quarantineButton.setBounds(60, 450, 200, 50);
		quarantineButton.addActionListener(this);
		frame.getContentPane().add(quarantineButton);

		// ปุ่มกลับบ้าน Release
		releaseButton = new JButton("Release");
		releaseButton.setBackground(new Color(253, 246, 236));
		releaseButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
		releaseButton.setBounds(540, 450, 200, 50);
		releaseButton.addActionListener(this);
		frame.getContentPane().add(releaseButton);

		// ป้ายแสดงค่าอุณหภูมิ Temperature
		statusLabel = new JLabel("", SwingConstants.CENTER);
		statusLabel.setFont(new Font("Franklin Gothic Book", Font.BOLD | Font.ITALIC, 24));
		statusLabel.setBounds(300, 450, 200, 30);
		frame.getContentPane().add(statusLabel);
		
		// พื้นหลัง
		backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 800, 600);
		backgroundLabel.setLayout(null);
		frame.getContentPane().add(backgroundLabel);
        
		frame.setVisible(true);
	}

	// สุ่มอุณหภูมิ Temperature (เรียกใช้ Method ที่สุ่มอุณหภูมิจาก Character)
	private void spawnCharacter() {
		currentCharacter = new Character();
		statusLabel.setText("Temp: " + String.format("%.1f", currentCharacter.getTemperature()) + " °C");
		// เปลี่ยน background แบบสุ่ม
		int randomIndex = (int) (Math.random() * backgroundImages.length);
		backgroundLabel.setIcon(new ImageIcon(backgroundImages[randomIndex]));
	}
	
	private void startCountdown() {
		countdownTimer = new Timer(1000, this); // this คือ GamePage ที่ implements ActionListener
		countdownTimer.start();
	}

	// จัดการ event เมื่อมีการกดปุ่ม
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == countdownTimer) {
			timeLeft--;
			timerLabel.setText("Time Left: " + timeLeft + "s");

			// เมื่อเวลาหมด ไปยัง GameOverPage
			if (timeLeft <= 0) {
				countdownTimer.stop();
				frame.dispose();  // ปิดหน้าจอปัจจุบัน
				new GameOverPage(playerName, score); // ไปหน้า GameOverPage พร้อมส่งชื่อและคะแนน
			}
		}

		else if (e.getSource() == pauseButton) {
			countdownTimer.stop();  // หยุดเวลาชั่วคราว
			int option = JOptionPane.showOptionDialog(  // สร้าง Dialog แบบมีตัวเลือก
				frame,
				"Continue playing or go back to Main Menu?", // ข้อความที่แสดงใน Dialog
				"Game Paused", // ชื่อหน้าต่าง Dialog
				JOptionPane.YES_NO_OPTION, // กำหนดเป็นแบบ Yes No
				JOptionPane.QUESTION_MESSAGE, // กำหนดไอคอน Dialog (?)
				null,
				new String[]{"Continue", "Main Menu"},  // 0, 1
				"Continue"
			);

			if (option == 1) {  // ถ้ากด Main Menu
				frame.dispose();  // ปิดหน้าจอปัจจุบัน
				new MainMenu();   // ไปหน้า MainMenu
			} else {  // ถ้ากด Continue
				countdownTimer.start(); // เริ่มนับถอยหลังต่อ
			}
		}

		else if (e.getSource() == quarantineButton || e.getSource() == releaseButton) {
			// ตรวจว่าผู้เล่นเลือกถูกหรือไม่
			boolean shouldQuarantine = currentCharacter.shouldBeQuarantined();
			boolean pressedQuarantine = (e.getSource() == quarantineButton);

			if ((shouldQuarantine && pressedQuarantine) || (!shouldQuarantine && !pressedQuarantine)) {
				// ตอบถูก: +1 คะแนน
				score++;
				scoreLabel.setText("Score: " + score);
			} else {
				// ตอบผิด: -3 วินาที
				timeLeft -= 3;
				if (timeLeft < 0) timeLeft = 0;
				timerLabel.setText("Time Left: " + timeLeft + "s");
			}

			// สุ่มอุณหภูมิและพื้นหลังใหม่ เมื่อมีการกดปุ่ม Quarantine หรือ Release
			spawnCharacter();
		}
	}
}
