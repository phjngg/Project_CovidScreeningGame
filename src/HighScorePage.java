package CovidScreeningGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class HighScorePage implements ActionListener {

    private JFrame frame;               // หน้าต่างหลักของเกม
    private JTextArea scoreArea;        // พื้นที่แสดง All Scores
    private JButton backButton;         // ปุ่ม Back
    private JLabel titleLabel;          // ป้ายแสดงหัวข้อ
    private JScrollPane scrollPane;     // เลื่อนดูคะแนน
    private ScoreManager scoreManager;  // ตัวโหลดคะแนน

    /**
     * Main method สำหรับทดสอบแยก
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HighScorePage window = new HighScorePage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor ของ HighScorePage
    public HighScorePage() {
        scoreManager = new ScoreManager();  // สร้าง Object ของ ScoreManager เพื่อใช้โหลดคะแนน
        initialize();                       // เรียกเมธอด initialize เพื่อสร้าง UI
        loadAllScores();                    // โหลดคะแนนมาแสดงบนหน้าจอ
    }

    // Method จัดการ GUI บน HighScorePage
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("High Scores");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(253, 246, 236));
        frame.getContentPane().setLayout(null);

        // ป้ายแสดงหัวข้อ All Player Scores
        titleLabel = new JLabel("All Player Scores", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Algerian", Font.BOLD, 36));
        titleLabel.setBounds(150, 30, 500, 50);
        frame.getContentPane().add(titleLabel);

        // กล่องแสดง All Scores
        scoreArea = new JTextArea();
        scoreArea.setBackground(new Color(255, 255, 255));
        scoreArea.setFont(new Font("Century", Font.BOLD, 30));
        scoreArea.setEditable(false);  // ไม่ให้แก้ไขคะแนน

        // คะแนนใส่ใน ScrollPane ให้เลื่อนได้
        scrollPane = new JScrollPane(scoreArea);
        scrollPane.setBounds(200, 100, 400, 350);
        frame.getContentPane().add(scrollPane);

        // ปุ่ม Back
        backButton = new JButton("Back");
        backButton.setBackground(new Color(185, 179, 172));
        backButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        backButton.setBounds(640, 20, 100, 40);
        backButton.addActionListener(this);   
        frame.getContentPane().add(backButton);

        frame.setVisible(true); 
    }

    // โหลดคะแนนทั้งหมดจาก ScoreManager และแสดงผล
    private void loadAllScores() {
        List<String> scores = scoreManager.getAllScoresSorted();  // ดึงคะแนนทั้งหมด แบบเรียงแล้ว
        StringBuilder sb = new StringBuilder();                   // ใช้ StringBuilder ต่อข้อความ

        // วนลูปแสดงแต่ละคะแนนใน sb
        for (String line : scores) {
            sb.append(line).append("\n"); // ขึ้นบรรทัดใหม่
        }
        // ใส่ข้อความลงไปใน scoreArea
        scoreArea.setText(sb.toString()); 
   }

    // จัดการ event เมื่อมีการกดปุ่ม	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			frame.dispose();         // ปิดหน้าจอ HighScorePage
			new MainMenu();          // กลับไปที่หน้า MainMenu
		}
	}
}
