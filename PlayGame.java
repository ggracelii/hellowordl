import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayGame extends JFrame implements ActionListener
{
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	public static boolean play;
	public static boolean single;
	public static PlayGame playGame;
	private ImageIcon icon;
	private JLabel label;
	private JPanel panel;
	private JButton singleButton;
	private JButton multiButton;
	private Wordl game;
	private static int hm = 0;


  
	public PlayGame()
	{
		super("Hello Wordl");
		setResizable(true);
		JFrame frame = new JFrame("Play Hello Wordl");

		play = false;
		setSize(WIDTH, HEIGHT);

		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		singleButton = new JButton("Single Player");
		singleButton.setBounds(125, 350, 150, 30);
		singleButton.setBackground(Color.GREEN);
		singleButton.setForeground(Color.BLACK);
		singleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				play = true;
				single = true;
			}
		});
		panel.add(singleButton);

		multiButton = new JButton("Multi Player");
		multiButton.setBounds(425, 350, 150, 30);
		multiButton.setBackground(Color.GREEN);
		multiButton.setForeground(Color.BLACK);
		multiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				play = true;
				single = false;
			}
		});
		panel.add(multiButton);

		icon = new ImageIcon("welcome.jpg");
		label = new JLabel(icon, SwingConstants.CENTER);
		label.setIcon(icon);
		panel.add(label);

		frame.getContentPane().add(panel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		frame.setVisible(true);

		while(play == false)
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
			}
		}

		if(single)
		{
			frame.getContentPane().removeAll();
			frame.getContentPane().invalidate();
			frame.setSize(WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBackground(Color.BLACK);
			
			panel = null;
			icon = null;
			label = null;
			panel = new JPanel();
			icon = new ImageIcon("single.jpg");
			label = new JLabel(icon, SwingConstants.CENTER);
			panel.setLayout(new BorderLayout());
			label.setIcon(icon);
			panel.add(label);
			
			frame.getContentPane().add(panel);
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
			frame.setVisible(true);

			try
			{
				Thread.sleep(5000);
			}
			catch (InterruptedException e)
			{
			}

			game = new SinglePlayer();
			hm++;
		}
		else
		{
			frame.getContentPane().removeAll();
			frame.getContentPane().invalidate();
			frame.setSize(WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBackground(Color.BLACK);
			
			panel = null;
			icon = null;
			label = null;
			panel = new JPanel();
			icon = new ImageIcon("multi.jpg");
			label = new JLabel(icon, SwingConstants.CENTER);
			panel.setLayout(new BorderLayout());
			panel.add(label);
			
			frame.getContentPane().add(panel);
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
			frame.setVisible(true);

			Scanner input = new Scanner(System.in);
			String s = input.nextLine();
			while(s.length() < 5 || s.length() > 7)
			{
				System.out.println("Enter a new key between 5-7 letters:");
				s = input.nextLine();
			}
			
			game = new MultiPlayer(s);
		}
		init();
	}	

	public static void main(String[] args)
	{
		playGame = new PlayGame();
	}

	public void init()
	{
		if(single && hm != 0)
		{
			((SinglePlayer)game).resetKey();
		}
		else if(!single && hm != 0)
		{
			Scanner input = new Scanner(System.in);
			String s = input.nextLine();
			while(s.length() < 5 || s.length() > 7)
			{
				System.out.println("Enter a new key between 5-7 letters:");
				s = input.nextLine();
			}
			
			game = new MultiPlayer(s);
		}
	
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);
		((Component)game).setFocusable(true);
		
		this.getContentPane().add(game);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
	}
}