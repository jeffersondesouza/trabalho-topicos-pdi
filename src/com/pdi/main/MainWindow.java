package com.pdi.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.pdi.image.manipulators.ImageManipulatorsFacade;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	ImageManipulatorsFacade imageManipulator = new ImageManipulatorsFacade();

	BufferedImage manipulatedImge;
	String originalImagPath = null;

	private JFrame frame;
	/**
	 * @wbp.nonvisual location=-21,-23
	 */
	private final JFileChooser fileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblOriginalImage = new JLabel("");
		lblOriginalImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblOriginalImage.setBounds(300, 100, 300, 400);
		lblOriginalImage.setText("Escolha uma Imagem");

		JLabel lblManipulatedImg = new JLabel("");
		lblManipulatedImg.setBounds(650, 100, 300, 400);

		JButton btnProcurarImagem = new JButton("Procurar Imagem");
		btnProcurarImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click");

				CreateImage(lblOriginalImage, lblManipulatedImg);
			}
		});

		btnProcurarImagem.setBounds(360, 50, 165, 38);
		frame.getContentPane().add(btnProcurarImagem);

		frame.getContentPane().add(lblOriginalImage);
		frame.getContentPane().add(lblManipulatedImg);

		JButton saveButton = new JButton("Salvar Imagem");
		saveButton.setBounds(717, 50, 165, 38);
		frame.getContentPane().add(saveButton);

		JButton btnGrayScale = new JButton("Escala de Cinza");
		btnGrayScale.setBounds(50, 100, 153, 25);
		frame.getContentPane().add(btnGrayScale);

		JButton btnChangeBrightness = new JButton("Alterar Brilho");
		btnChangeBrightness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangeBrightness.setBounds(50, 135, 153, 25);
		frame.getContentPane().add(btnChangeBrightness);

		JButton btnBandaR = new JButton("Banda R");
		btnBandaR.setBounds(50, 170, 153, 25);
		frame.getContentPane().add(btnBandaR);

		JButton btnBandaG = new JButton("Banda G");
		btnBandaG.setBounds(50, 205, 153, 25);
		frame.getContentPane().add(btnBandaG);

		JButton btnBandaB = new JButton("Banda B");
		btnBandaB.setBounds(50, 240, 153, 25);
		frame.getContentPane().add(btnBandaB);

		JButton btnNegativo = new JButton("Negativo");
		btnNegativo.setBounds(50, 275, 153, 25);
		frame.getContentPane().add(btnNegativo);

		JLabel lblImagemOriginal = new JLabel("Imagem Original");
		lblImagemOriginal.setBounds(370, 100, 155, 15);
		frame.getContentPane().add(lblImagemOriginal);

		JLabel lblImagemManipulada = new JLabel("Imagem Manipulada");
		lblImagemManipulada.setBounds(717, 100, 155, 15);
		frame.getContentPane().add(lblImagemManipulada);
		
		saveButtonHandleClick(saveButton, lblManipulatedImg);
		btnGrayScaleHandleClick(btnGrayScale, lblManipulatedImg);
		btnChangeBrightnessHandleClick(btnChangeBrightness, lblManipulatedImg);
		btnBandaRHandleClick(btnBandaB, lblManipulatedImg);
		btnBandaGHandleClick(btnBandaG, lblManipulatedImg);
		btnBandaBHandleClick(btnBandaR, lblManipulatedImg);
		btnNegativoHandleClick(btnNegativo, lblManipulatedImg);
		
		JLabel lblMarcosJffersonFerreira = new JLabel("Marcos Jéfferson Ferreira de Souza");
		lblMarcosJffersonFerreira.setBounds(50, 41, 387, 15);
		frame.getContentPane().add(lblMarcosJffersonFerreira);
	}

	void CreateImage (JLabel originalImage, JLabel manipulatedImg){

		BufferedImage img = null;

		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			originalImagPath = selectedFile.getAbsolutePath();

			try {
				img = ImageIO.read(new File(selectedFile.getAbsolutePath()));
				manipulatedImge = ImageIO.read(new File(selectedFile.getAbsolutePath()));

				originalImage.setIcon(new ImageIcon(img));
				manipulatedImg.setIcon(new ImageIcon(img));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void saveImageOnDisk(){
		if(hasImageToManipulate()) {
			int returnValue = fileChooser.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {

				File selectedFile = fileChooser.getSelectedFile();

				try {

					ImageIO.write(manipulatedImge, "PNG", selectedFile);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Convetions Methods
	 */

	void convertToGrayScale(JLabel imageLabel, String imagPath){
		if(hasImageToManipulate()) {
			try {
				manipulatedImge = imageManipulator.ToGrayScale(ImageIO.read(new File(imagPath)));
				imageLabel.setIcon(new ImageIcon(manipulatedImge));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void convertToChannelR(JLabel imageLabel, String imagPath){
		if(hasImageToManipulate()) {
			try {
				manipulatedImge = imageManipulator.convertToR(ImageIO.read(new File(imagPath)));
				imageLabel.setIcon(new ImageIcon(manipulatedImge));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	}

	void convertToChannelG(JLabel imageLabel, String imagPath){
		if(hasImageToManipulate()) {
			try {
				manipulatedImge = imageManipulator.convertToG(ImageIO.read(new File(imagPath)));
				imageLabel.setIcon(new ImageIcon(manipulatedImge));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void convertToChannelB(JLabel imageLabel, String imagPath){
		if(hasImageToManipulate()) {
			try {
				manipulatedImge = imageManipulator.convertToB(ImageIO.read(new File(imagPath)));
				imageLabel.setIcon(new ImageIcon(manipulatedImge));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	}

	void ConvertToBrightenScale(JLabel imageLabel, String imagPath){
		if(hasImageToManipulate()) {
			try {
				manipulatedImge = imageManipulator.brighten(ImageIO.read(new File(imagPath)),0);
				imageLabel.setIcon(new ImageIcon(manipulatedImge));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void convertToNegative(JLabel imageLabel, String imagPath){
		if(hasImageToManipulate()) {

			try {
				manipulatedImge = imageManipulator.convertToNegative(ImageIO.read(new File(imagPath)));
				imageLabel.setIcon(new ImageIcon(manipulatedImge));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void btnGrayScaleHandleClick(JButton button, JLabel imgLabel){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				convertToGrayScale(imgLabel, originalImagPath);
			}
		});
	}

	void saveButtonHandleClick(JButton button, JLabel imgLabel){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveImageOnDisk();
			}
		});
	}

	void btnChangeBrightnessHandleClick(JButton button, JLabel imgLabel){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConvertToBrightenScale(imgLabel, originalImagPath);
			}
		});
	}

	void btnBandaRHandleClick(JButton button, JLabel imgLabel){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				convertToChannelR(imgLabel, originalImagPath);
			}
		});
	}

	void btnBandaGHandleClick(JButton button, JLabel imgLabel){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				convertToChannelG(imgLabel, originalImagPath);
			}
		});
	}

	void btnBandaBHandleClick(JButton button, JLabel imgLabel){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				convertToChannelB(imgLabel, originalImagPath);
			}
		});
	}

	void btnNegativoHandleClick(JButton button, JLabel imgLabel){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				convertToNegative(imgLabel, originalImagPath);
			}
		});
	}

	boolean hasImageToManipulate() {
		return originalImagPath != null;
	}
}
