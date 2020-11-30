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
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Canvas;

public class screen {

	private JFrame frmChess;
	private final JLabel lblNewLabel = new JLabel("");
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		buttonGroup.add(btnNewButton_1);
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame(Mode.PvsAI);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("CHESS");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 71));

		JButton btnNewButton = new JButton("AI vs AI");
		buttonGroup.add(btnNewButton);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame(Mode.AIvsAI);
			}
		});

		JButton btnNewButton_2 = new JButton("Player vs Player");
		buttonGroup.add(btnNewButton_2);
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame(Mode.PvsP);
			}
		});

		JButton btnNewButton_3 = new JButton("Settings");
		buttonGroup.add(btnNewButton_3);
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setFont(new Font("Arial Black", Font.PLAIN, 11));
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel.setIcon(new ImageIcon(screen.class.getResource("/Logo/background1.jpg")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(148)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
					.addGap(151))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(214)
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(210))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(214)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(210))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(214)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(210))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(214)
					.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(210))
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 625, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 81, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
					.addGap(39))
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 413, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		Canvas canvas = new Canvas();
		frmChess.getContentPane().add(canvas, BorderLayout.NORTH);
	}

	private void loadGame(Mode mode) {
		GameEngine game = new GameEngine("Player vs Player", 600,600, mode);
		game.start();
		frmChess.dispose();
	}
}
