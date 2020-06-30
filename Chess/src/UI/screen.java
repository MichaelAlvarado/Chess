package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Main.GameEngine;
import Main.launch;
import States.GameState.Mode;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;

public class screen {

	private JFrame frmChess;
	private final JLabel lblNewLabel = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					screen window = new screen();
					window.frmChess.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChess = new JFrame();
		frmChess.setIconImage(Toolkit.getDefaultToolkit().getImage(screen.class.getResource("/Logo/Logo.jpg")));
		frmChess.setTitle("Chess");
		frmChess.setBounds(100, 100, 641, 452);
		frmChess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChess.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frmChess.getContentPane().add(panel);

		JButton btnNewButton_1 = new JButton("Player vs AI");
		btnNewButton_1.setBackground(new Color(204, 204, 204));
		btnNewButton_1.setBounds(211, 167, 201, 61);
		btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame(Mode.PvsAI);
			}
		});
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("CHESS by Michael Alvarado");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(148, 43, 326, 61);
		panel.add(lblNewLabel_1);
		panel.add(btnNewButton_1);

		JButton btnNewButton = new JButton("AI vs AI");
		btnNewButton.setBackground(new Color(204, 204, 204));
		btnNewButton.setBounds(211, 239, 201, 55);
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame(Mode.AIvsAI);
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Player vs Player");
		btnNewButton_2.setBackground(new Color(204, 204, 204));
		btnNewButton_2.setBounds(211, 103, 201, 53);
		btnNewButton_2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame(Mode.PvsP);
			}
		});
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Settings");
		btnNewButton_3.setBackground(new Color(204, 204, 204));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(211, 305, 201, 56);
		btnNewButton_3.setFont(new Font("Arial Black", Font.PLAIN, 11));
		panel.add(btnNewButton_3);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-27, -12, 671, 441);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel.setIcon(new ImageIcon(screen.class.getResource("/Logo/background1.jpg")));
		panel.add(lblNewLabel);
	}

	private void loadGame(Mode mode) {
		GameEngine game = new GameEngine("Player vs Player", 1000,1000, mode);
		game.start();
		frmChess.dispose();
	}
}
