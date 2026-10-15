package CovidScreeningGame;

import java.io.*;
import java.util.*;

public class ScoreManager {

    // ชื่อไฟล์ที่ใช้จัดเก็บคะแนน
    private static final String FILE_NAME = "scores.txt";

    // บันทึกคะแนน Player ลงในไฟล์
    public void saveScore(String name, int score) {
        // โหลดคะแนนทั้งหมดจากไฟล์มาไว้ใน Map
        Map<String, Integer> scoreMap = loadAllScores();

        // ถ้ายังไม่มีชื่อผู้เล่นนี้ หรือได้คะแนนมากกว่าเดิม จะบันทึกคะแนนลงใน Map
        if (!scoreMap.containsKey(name) || score > scoreMap.get(name)) {
            scoreMap.put(name, score);
        }

        // เขียนข้อมูลทั้งหมดลงไฟล์ใหม่ (ทับไฟล์เดิม)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue()); // ชื่อ, คะแนน
                writer.newLine(); // ขึ้นบรรทัดใหม่สำหรับแต่ละ Player
            }
        } catch (IOException e) {
            e.printStackTrace(); // ถ้าเขียนไฟล์ล้มเหลว แสดง Stack Trace และตำแหน่ง error
        }
    }

    // อ่านคะแนนทั้งหมดจากไฟล์
    private Map<String, Integer> loadAllScores() {
        Map<String, Integer> scoreMap = new HashMap<>(); // สร้าง HashMap เก็บข้อมูล key = ชื่อ, value = คะแนน

        File file = new File(FILE_NAME);
        if (!file.exists()) return scoreMap; // ตรวจสอบว่ามีไฟล์ไหม ถ้าไม่มีคืน Map เปล่า

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // อ่านแต่ละบรรทัด แล้วแปลงเป็น (ชื่อ, คะแนน)
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim(); // ชื่อ
                    int score = Integer.parseInt(parts[1].trim()); // คะแนน
                    scoreMap.put(name, score); // ใส่ลง scoreMap
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return scoreMap; // คืน Map ที่โหลดมา (ชื่อ, คะแนน)
    }

    // คืนค่าที่เก็บชื่อและคะแนน เรียงคะแนนจาก มากไปน้อย
    public List<String> getAllScoresSorted() {
        Map<String, Integer> scoreMap = loadAllScores();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(scoreMap.entrySet()); // แปลง Map ไปเป็น List เพื่อเอาไป sort

        // เรียงคะแนน มากไปน้อย
        entryList.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        // สร้าง List<String> เก็บผลลัพธ์ในรูปแบบข้อความ
        List<String> result = new ArrayList<>();

        // วนลูปคะแนนที่เรียงแล้ว แล้วแปลงเป็นข้อความ "Player got [0] points"
        for (Map.Entry<String, Integer> entry : entryList) {
            result.add(entry.getKey() + " got [ " + entry.getValue() + " ] points ");
        }

        return result;
    }
}
