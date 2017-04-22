package tr.edu.medipol.odev2.g2.beyzaodeyen;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

/** ozkans Degerlendirme

Oguzhan Yalcin ile benzer

Ekran: 20/20
Ogrenci Islemleri: 15/15
Polimorphism: 15/15
Dosyadan Okuma: 20/20
Dosyaya Yazma:  20/20
Genel Program: 10/10
Bonus:25/25
--------------------------
SONUC: 125/125
 */
public class swing {

	private List<Ogrenci> ogrenciList = new ArrayList<>();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					swing window = new swing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void refreshList(JList lb) {
		final DefaultListModel<Ogrenci> model = new DefaultListModel();
		for (Ogrenci ogr : ogrenciList) {
			model.addElement(ogr);
		}

		lb.setModel(model);
	}

	/**
	 * Create the application.
	 */
	public swing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 821, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea tb_name = new JTextArea();
		tb_name.setBounds(184, 11, 178, 28);
		frame.getContentPane().add(tb_name);

		JTextArea tb_type = new JTextArea();
		tb_type.setBounds(184, 72, 178, 28);
		frame.getContentPane().add(tb_type);

		JList listbox = new JList();
		listbox.setBounds(10, 164, 352, 161);
		frame.getContentPane().add(listbox);

		JButton btn_save = new JButton("Kaydet");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String content = "";

				try (PrintWriter out = new PrintWriter("filename.txt")) {
					for (Ogrenci ogr : ogrenciList) {
						out.println(ogr.getName() + "-" + ogr.getType() + "-" + ogr.getDepartment());
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		btn_save.setBounds(372, 268, 134, 23);
		frame.getContentPane().add(btn_save);

		JButton btn_remove = new JButton("Ogrenci Sil");
		btn_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ogrenci selectedStudent = (Ogrenci) listbox.getSelectedValue();
				ogrenciList.remove(selectedStudent);
				refreshList(listbox);
			}
		});
		btn_remove.setBounds(372, 234, 134, 23);
		frame.getContentPane().add(btn_remove);

		JButton btn_import = new JButton("Dosya Ac");
		btn_import.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ogrenciList.clear();

				try (BufferedReader br = new BufferedReader(new FileReader(new File("filename.txt")))) {
					for (String line; (line = br.readLine()) != null;) {
						String[] splitted = line.split("-");
						ogrenciList.add(new Ogrenci(splitted[0], splitted[1], splitted[2]));
					}
					// line is not visible here.
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshList(listbox);
			}
		});
		btn_import.setBounds(372, 302, 134, 23);
		frame.getContentPane().add(btn_import);

		JTextArea tb_department = new JTextArea();
		tb_department.setBounds(184, 125, 178, 28);
		frame.getContentPane().add(tb_department);

		JButton btn_add = new JButton("Ogrenci Ekle");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ogrenci newOgrenci = new Ogrenci(tb_name.getText(), tb_type.getText(), tb_department.getText());
				ogrenciList.add(newOgrenci);

				refreshList(listbox);

			}
		});
		btn_add.setBounds(372, 130, 134, 23);
		frame.getContentPane().add(btn_add);

		JButton btn_reset = new JButton("Sifirla");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ogrenciList.clear();
				refreshList(listbox);
			}
		});
		btn_reset.setBounds(372, 200, 134, 23);
		frame.getContentPane().add(btn_reset);
		
		JLabel lblOgrenciEkle = new JLabel("Ogrenci Ad Soyad Giriniz :");
		lblOgrenciEkle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOgrenciEkle.setBounds(29, 25, 145, 14);
		frame.getContentPane().add(lblOgrenciEkle);
		
		JLabel lblOgrenciTipiniGrnz = new JLabel("Ogrenci Tip Giriniz :");
		lblOgrenciTipiniGrnz.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOgrenciTipiniGrnz.setBounds(29, 72, 164, 28);
		frame.getContentPane().add(lblOgrenciTipiniGrnz);
		
		JLabel lblNewLabel = new JLabel("Ogrenci Bolum Giriniz :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(32, 139, 142, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}