import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author DAA
 *
 */
public class Form extends JFrame {
	
	int i, j;
	int days[];
	int months[];
	JLabel header, name, surname, birthday_day, birthday_month, sex;
	JTextField name_field, surname_field, birthday_day_field, birthday_month_field;
	JRadioButton radio_male, radio_female, radio_divers;
	JButton button_generieren;
	boolean isError = false;

	public Form() {
		
		super("Title: Umfrage");
		super.setBounds(200, 200, 300, 550);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = super.getContentPane();
		container.setLayout(new GridLayout(14, 1, 5, 5));
		getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 40, 20));
		
		header = new JLabel("Generieren Sie ein Fantasyname");
		
		name = new JLabel("Geben Sie Ihren Vorname ein:");
		name_field = new JTextField(3);
		
		surname = new JLabel("Geben Sie Ihren Nachname ein:");
		surname_field = new JTextField(3);
		
		birthday_day = new JLabel("Geben Sie Ihr Geburtstag ein:");
		birthday_day_field = new JTextField(2);
		
		birthday_month = new JLabel("Geben Sie Ihr Geburtsmonat ein:");
		birthday_month_field = new JTextField(2);
		
		sex = new JLabel("Geben Sie Ihr Geschlecht ein:");
		radio_male = new JRadioButton("Männlich", true);
		radio_female = new JRadioButton("Weiblich");
		radio_divers = new JRadioButton("Divers");

		ButtonGroup radioGroup = new ButtonGroup();
		
		button_generieren = new JButton("Generiegen");
		
		container.add(header);
		container.add(name);
		container.add(name_field);
		container.add(surname);
		container.add(surname_field);
		container.add(birthday_day);
		container.add(birthday_day_field);
		container.add(birthday_month);
		container.add(birthday_month_field);
		container.add(sex);

		radioGroup.add(radio_male);
		radioGroup.add(radio_female);
		radioGroup.add(radio_divers);
		container.add(radio_male);
		container.add(radio_female);
		container.add(radio_divers);
		
		container.add(button_generieren);
		
		button_generieren.addActionListener(new SendEvent());
	}
	
	class SendEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String your_name, your_surname, is_you_male, is_you_female, is_you_divers, fantasyname = "", fantasy_surname = "";
			char name_field_array[], surname_field_array[];
			int your_birthday_day, your_birthday_month;
			
			your_name = name_field.getText();
			your_surname = surname_field.getText();
			your_birthday_day = Integer.parseInt (birthday_day_field.getText());
			your_birthday_month = Integer.parseInt (birthday_month_field.getText());
			is_you_male = radio_male.getText();
			is_you_female = radio_female.getText();
			is_you_divers = radio_divers.getText();
			
			System.out.println(your_name);
			System.out.println(your_surname);
			System.out.println(your_birthday_day);
			System.out.println(your_birthday_month);
			System.out.println(is_you_male);
			System.out.println(is_you_female);
			System.out.println(is_you_divers);
			
			name_field_array = your_name.toCharArray();
			surname_field_array = your_surname.toCharArray();
			
			System.out.println(name_field_array);
			
			if (your_birthday_day <= 15) {
				if (name_field_array.length >= 4) {
					fantasyname = fantasyname + name_field_array[1] + name_field_array[2] + name_field_array[3];
				} else {
					fantasyname = fantasyname + name_field_array[1] + name_field_array[2] + "e";
				}
				
				if (surname_field_array.length >= 4) {
					fantasyname = fantasyname + surname_field_array[1] + surname_field_array[2] + surname_field_array[3];
				} else {
					fantasyname = fantasyname + surname_field_array[1] + surname_field_array[2] + "o";
				}
				
			} else {
				if (surname_field_array.length >= 4) {
					fantasyname = fantasyname + surname_field_array[1] + surname_field_array[2] + surname_field_array[3];
				} else {
					fantasyname = fantasyname + surname_field_array[1] + surname_field_array[2] + "o";
				}
				
				if (name_field_array.length >= 4) {
					fantasyname = fantasyname + name_field_array[1] + name_field_array[2] + name_field_array[3];
				} else {
					fantasyname = fantasyname + name_field_array[1] + name_field_array[2] + "e";
				}
			}
			
			fantasyname = fantasyname.substring(0,1).toUpperCase() + fantasyname.substring(1);
			
			if (your_birthday_month == 12 || your_birthday_month <= 2) {
				fantasy_surname = "Winter";
			} else if (your_birthday_month <= 5) {
				fantasy_surname = "Frühling";
			} else if (your_birthday_month <= 8) {
				fantasy_surname = "Sommer";
			} else {
				fantasy_surname = "Herbst";
			}
			
			if (radio_male.isSelected()) {
				fantasy_surname = fantasy_surname + "borg";
			} else if (radio_female.isSelected()) {
				fantasy_surname = fantasy_surname + "iga";
			} else {
				fantasy_surname = fantasy_surname + "arg";
			}
			
			System.out.println(fantasyname + " " + fantasy_surname);
			
			// Fehler bearbeiten
			
			if (your_birthday_day < 1 || your_birthday_day > 12) {
				isError = true;
				JOptionPane.showMessageDialog(null, "Ein Tag soll zwischen 1 und 31 sein.", "Fehler", JOptionPane.PLAIN_MESSAGE);
				birthday_day_field.setText("");
			} else {
				isError = false;
			}
			
			if (your_birthday_month < 1 || your_birthday_month > 12) {
				isError = true;
				JOptionPane.showMessageDialog(null, "Ein Monat soll zwischen 1 und 12 sein.", "Fehler", JOptionPane.PLAIN_MESSAGE);
				birthday_month_field.setText("");
			} else {
				isError = false;
			}
			
			if (name_field_array.length < 3) {
				isError = true;
				JOptionPane.showMessageDialog(null, "Ein Vorname ist zu kurz.", "Fehler", JOptionPane.PLAIN_MESSAGE);
				name_field.setText("");
			} else {
				isError = false;
			}
			
			if (surname_field_array.length < 3) {
				isError = true;
				JOptionPane.showMessageDialog(null, "Ein Nachname ist zu kurz.", "Fehler", JOptionPane.PLAIN_MESSAGE);
				surname_field.setText("");
			} else {
				isError = false;
			}
			
			for (i = 0; i < name_field_array.length; i++) {
				//if (name_field_array[i] instanceof char) {
					//isError = true;
				//};
			}
			
			System.out.println(isError);
			
			if (!isError) {
				JOptionPane.showMessageDialog(null, "" + fantasyname + " " + fantasy_surname, "Ihr Fantasiename", JOptionPane.PLAIN_MESSAGE);
			}

			
		}
		
		
	}
	
}
