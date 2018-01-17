package oose;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;

public class Controller {

	private Vehicle vehicle;
	private String[] vehicles = { "Boat", "Ship", "Truck", "Motorcycle", "Bus", "Car", "Tram", "Train"};
	private Simulator simulationPane;
	private JLabel speedlabel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JComboBox<String> combobox;
	private JFrame frame;

	@SuppressWarnings("unused")
	private boolean accelerate, decelerate, cruise,stop;
	int currentvelocity = 1;
	int maximumvelocity = 300;

	public static void main(String args[]) {
		new Controller();
	}
	
	public Controller() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame = new JFrame("Vehicle Control System");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());

				combobox = new JComboBox<String>(vehicles);
				combobox.setSelectedIndex(6);
				combobox.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						int selectedIndex = combobox.getSelectedIndex();
						String vehicleName = vehicles[selectedIndex];
						initialiseVehicle(vehicleName);							
					}
				});

				speedlabel = new JLabel("          ");
				
				configStart();
				configAccelerate();
				configDecelerate();
				configCruise();
				configStop();
				
				JToolBar toolBar =new JToolBar();
				toolBar.setRollover(true);

				toolBar.add(combobox);
				toolBar.add(speedlabel);
				toolBar.add(button1);
				toolBar.add(button2);
				toolBar.add(button3);
				toolBar.add(button4);
				toolBar.add(button5);

				frame.add(toolBar,BorderLayout.NORTH);
				frame.setPreferredSize(new Dimension(800,200));
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

		});
	}
	
	private void configStart() {
		button1 = new JButton("start");
		button1.setBackground(Color.red);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(vehicle == null) {
					int selectedIndex = combobox.getSelectedIndex();
					String vehicleName = vehicles[selectedIndex];
					initialiseVehicle(vehicleName);
					speedlabel.setText(vehicle.printSpeed());
				}
				if(simulationPane !=null) {
					frame.remove(simulationPane);
				}
				accelerate = false;
				decelerate = false;
				cruise = false;
				stop = false;
				
				accelerate = false;
				decelerate = false;
				cruise = false;
				stop = false;

				simulationPane = new Simulator();
				frame.add(simulationPane,BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
			}

		});
	}

	private void configAccelerate() {
		button2 = new JButton("accelerate");
		button2.setBackground(Color.red);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accelerate = true;
				decelerate = false;
				cruise = false;
				stop = false;

				Thread thread = new Thread(){
					public void run(){
						try {
							while(accelerate) {
								Thread.sleep(2 * 1000);

								if(currentvelocity<=maximumvelocity) {
									currentvelocity = currentvelocity +1;
									vehicle.setCurrentSpeed(currentvelocity);
									speedlabel.setText(vehicle.printSpeed());
									simulationPane.updateTimer();
								}									    
							}
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						} 
					}
				};

				thread.start();
			}				    	
		});

	}
	
	private void configDecelerate() {
		button3 = new JButton("cruise");
		button3.setBackground(Color.red);
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accelerate = false;
				decelerate = false;
				cruise = true;
				stop = false;

			}				    	
		});
	}
	private void configCruise() {
		button4 = new JButton("decelerate");
		button4.setBackground(Color.red);
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accelerate = false;
				decelerate = true;
				cruise = false;
				stop = false;
				
				Thread thread = new Thread(){
					public void run(){
						try {
							while(decelerate) {
								Thread.sleep(2 * 1000);

								if(currentvelocity >1) {
									currentvelocity = currentvelocity -1;
									vehicle.setCurrentSpeed(currentvelocity);
									speedlabel.setText(vehicle.printSpeed());
									simulationPane.updateTimer();
								}									    
							}
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						} 
					}
				};

				thread.start();
			}				    	
		});
	}
	
	private void configStop() {
		button5 = new JButton("stop");
		button5.setBackground(Color.red);
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accelerate = false;
				decelerate = false;
				cruise = false;
				stop = true;
				
				currentvelocity = 1;
				vehicle.setCurrentSpeed(currentvelocity);
				speedlabel.setText(vehicle.printSpeed());
				simulationPane.updateTimer();
			}				    	
		});
	}
	
	private void initialiseVehicle(String vehicleName) {
		if(vehicleName.equals("Boat")) {
			vehicle = new Boat("Apollo ");
		}
		else if(vehicleName.equals("Ship")) {
			vehicle = new Ship("Cruizz");
		}
		else if(vehicleName.equals("Truck")) {
			vehicle = new Truck("Ford F-650");
		}
		else if(vehicleName.equals("Motorcycle")) {
			vehicle = new Motorcycle("Suzuki");
		}
		else if(vehicleName.equals("Bus")) {
			vehicle = new Bus("Aero");
		}
		else if(vehicleName.equals("Car")) {
			vehicle = new Car("BMW");
		}
		else if(vehicleName.equals("Tram")) {
			vehicle = new Tram("EdinburghTram");
		}
		else if(vehicleName.equals("Train")) {
			vehicle = new Train("Virgin",4);
		}	 	
	}


	public class Simulator extends JPanel {
		private static final long serialVersionUID = 1L;
		private BufferedImage bimg;
		private int xPos = 0;
		private int direction = 1;
		private File file; 
		private Timer timer;
		public Simulator() {
			setDisplayObject();
			try {	
				bimg = ImageIO.read(file);
				timer = new Timer(maximumvelocity/currentvelocity, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						xPos += direction;
						if (xPos + bimg.getWidth() > getWidth()) {
							xPos = 0;
							direction *= -1;

						} else if (xPos < 0) { 
							xPos = 0;
							direction *= -1;
						}
						repaint();
					}

				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		public void updateTimer() {
			timer.setDelay(maximumvelocity/currentvelocity);
		}

		private void setDisplayObject() {
			if(vehicle instanceof Boat) {
				file = new File(System.getProperty("user.dir")+"/img/boat.png");
			}
			else if(vehicle instanceof Ship) {
				file = new File(System.getProperty("user.dir")+"/img/ship.png");
			}
			else if(vehicle instanceof Truck) {
				file = new File(System.getProperty("user.dir")+"/img/truck.png");
			}
			else if(vehicle instanceof Motorcycle) {
				file = new File(System.getProperty("user.dir")+"/img/motorcycle.png");
			}
			else if(vehicle instanceof Bus) {
				file = new File(System.getProperty("user.dir")+"/img/bus.png");
			}
			else if(vehicle instanceof Car) {
				file = new File(System.getProperty("user.dir")+"/img/car.png");
			}
			else if(vehicle instanceof Tram) {
				file = new File(System.getProperty("user.dir")+"/img/tram.png");
			}
			else if(vehicle instanceof Train) {
				file = new File(System.getProperty("user.dir")+"/img/train.png");
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return bimg == null ? super.getPreferredSize() : new Dimension(bimg.getWidth() * 4, bimg.getHeight());
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			int y = getHeight() - bimg.getHeight();
			g.drawImage(bimg, xPos, y, this);

		}

	}

}
