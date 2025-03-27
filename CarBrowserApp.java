import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Car {
    String brand, model, color, engineType, transmission;
    int year, mileage, price, horsepower, maxSpeed;

    public Car(String brand, String model, int year, int mileage, int price, String color, String engineType, int horsepower, String transmission, int maxSpeed) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.color = color;
        this.engineType = engineType;
        this.horsepower = horsepower;
        this.transmission = transmission;
        this.maxSpeed = maxSpeed;
    }
}

public class CarBrowserApp {
    private List<Car> cars = new ArrayList<>();
    private int index = 0;
    private JLabel carLabel;
    
    public CarBrowserApp() {
        JFrame frame = new JFrame("Autólista Böngésző");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        carLabel = new JLabel("", SwingConstants.CENTER);
        frame.add(carLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("Előző");
        JButton nextButton = new JButton("Következő");
        JButton addButton = new JButton("Új autó hozzáadása");
        
        prevButton.addActionListener(e -> showPreviousCar());
        nextButton.addActionListener(e -> showNextCar());
        addButton.addActionListener(e -> addCar());
        
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(addButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        loadSampleData();
        updateDisplay();

        frame.setVisible(true);
    }

    private void loadSampleData() {
        cars.add(new Car("Toyota", "Corolla", 2018, 150000, 3500000, "Fehér", "Benzin", 120, "Manuális", 220));
        cars.add(new Car("Ford", "Focus", 2017, 120000, 3300000, "Fekete", "Dízel", 150, "Automata", 200));
        cars.add(new Car("Audi", "A3", 2020, 80000, 4900000, "Zöld", "Hibrid", 180, "Félautomata", 240));
        cars.add(new Car("Skoda", "Octavia", 2019, 180000, 4200000, "Ezüst", "Dízel", 160, "Manuális", 200));
        cars.add(new Car("BMW", "320d", 2015, 180000, 4200000, "Ezüst", "Dízel", 200, "Automata", 230));
    }

    private void updateDisplay() {
        if (index >= 0 && index < cars.size()) {
            Car car = cars.get(index);
            carLabel.setText("Márka: " + car.brand + ", Modell: " + car.model + ", Évjárat: " + car.year + ", Ár: " + car.price + " HUF");
        }
    }

    private void showPreviousCar() {
        if (index > 0) {
            index--;
            updateDisplay();
        }
    }

    private void showNextCar() {
        if (index < cars.size() - 1) {
            index++;
            updateDisplay();
        }
    }

    private void addCar() {
        JTextField brandField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField mileageField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField engineTypeField = new JTextField();
        JTextField horsepowerField = new JTextField();
        JTextField transmissionField = new JTextField();
        JTextField maxSpeedField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.add(new JLabel("Márka:")); panel.add(brandField);
        panel.add(new JLabel("Modell:")); panel.add(modelField);
        panel.add(new JLabel("Évjárat:")); panel.add(yearField);
        panel.add(new JLabel("Futott km:")); panel.add(mileageField);
        panel.add(new JLabel("Ár:")); panel.add(priceField);
        panel.add(new JLabel("Szín:")); panel.add(colorField);
        panel.add(new JLabel("Motor típus:")); panel.add(engineTypeField);
        panel.add(new JLabel("Lóerő:")); panel.add(horsepowerField);
        panel.add(new JLabel("Sebességváltó:")); panel.add(transmissionField);
        panel.add(new JLabel("Max sebesség:")); panel.add(maxSpeedField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Új autó hozzáadása", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            cars.add(new Car(
                brandField.getText(), modelField.getText(), Integer.parseInt(yearField.getText()),
                Integer.parseInt(mileageField.getText()), Integer.parseInt(priceField.getText()),
                colorField.getText(), engineTypeField.getText(), Integer.parseInt(horsepowerField.getText()),
                transmissionField.getText(), Integer.parseInt(maxSpeedField.getText())
            ));
            JOptionPane.showMessageDialog(null, "Autó hozzáadva!");
        }
    }

    public static void main(String[] args) {
        new CarBrowserApp();
    }
        
}
