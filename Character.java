package CovidScreeningGame;

public class Character {

    // อุณหภูมิร่างกายของตัวละคร
    private double temperature;

    // Constructor เมื่อสร้าง Character ใหม่ จะสุ่มอุณหภูมิ
    public Character() {
        // สุ่มอุณหภูมิในช่วง 36.0°C - 39.0°C 
        this.temperature = 36 + Math.random() * 3;
    }

    // Method เช็คว่าควรกักตัวไหม
    public boolean shouldBeQuarantined() {
        return temperature >= 37.5; // ควร
    }

    // Method return ค่าอุณหภูมิ
    public double getTemperature() {
        return temperature;
    }

}
