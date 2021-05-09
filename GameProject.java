// GAME Project 
// Arjun Shrivastava
// Period 5 
import java.awt.*;  import java.awt.event.*;
import javax.swing.*; import javax.swing.event.*;
import java.lang.Math;
import javax.imageio.ImageIO;


public class GameProject extends JFrame
// This class makes the JFrame, sets the size,location, and adds content. 
{
	public GameProject()// Constructor sets all parameters for JFrame 
	{
		 super("Arjun"); 
		 JFrame frame = new JFrame();
		 frame.setSize(1120, 540);
		 setDefaultCloseOperation(DISPOSE_ON_CLOSE);   // allows to close file
		 setLocation(100, 100); // sets the location
		 
		 frame.setResizable(false);
		 Mandalorion pan = new Mandalorion();
		 //Character Mand = new Character();
		 //frame.setContentPane(pan);
		 //JPanel pan = new JPanel(); 
		 //JPanel pan = new JPanel(); 
		 //frame.add(pan);
		 frame.getContentPane().add(pan);
		
	
		 //pan.addComponentToPane(pan.getContentPane());
		 //Mand.addChar(frame.getContentPane());
		
		 frame.setVisible(true);
		/*super ("Name");
		JFrame Frame = new JFrame();
		setSize(600, 600);   // frame size 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);   // allows to close file
		setLocation(200,100); // sets the location
		setResizable(true); // sets to be resizable 
	
		Mandalorion pan = new Mandalorion();   // Panel object
		Frame.setContentPane(pan); 
		pan.addComponentToPane(Frame.getContentPane());
		 
		Frame.setVisible(true); */
		
		

	}
	public static void main(String [] args) 
	{
		GameProject arj = new GameProject(); // Main calls the constructor that sets the frame's properties. 
	}
	
}

class Mandalorion extends JPanel implements MouseListener, KeyListener, MouseMotionListener, ActionListener 
/*This class draw everything on the panel. It draws the buttons on choose your character 
, droids, baby Yoda, blasts and the home page*/
{
	/* These are all field variables */
	JPanel cards, cards2; 
	JPanel charPage; 
    JPanel character;
	JButton b1 = new JButton("Play");
	JButton b2 = new JButton("Choose your Character");
	JButton b3 = new JButton("Choose your Planet");
	JButton b4 = new JButton("Instructions");
	JButton b5 = new JButton("Leaderboard");
	JButton b6 = new JButton("Star Wars Trivia");
	
	JButton gorgu; 
	JButton luke; 
	JButton mando; 
	JButton bo; 
	JButton ahsoka; 
	JButton cara; 
	
	boolean ifHome = true; 
	boolean ifGame = false; 
	boolean ifRight = false; 
	boolean ifUp = false;
	boolean flightning = false; 
	
	int droidX = 850;
	int droidY = 293;
	

	int blastX = 710;
	int blastY = 340; 
	
	
	boolean restart2 = false; 
	int upTracker = 0;
	int ge = (int)(Math.random()* 255); 
	int r = (int)(Math.random()* 255); 
    int b = (int)(Math.random()*255); 
    Timer tDroid; 
    Timer tBlast;
    Timer blastDelay;
    Timer yodaM; 
    Timer yodaD;
    Timer score; 
    Timer forceLightning; 
	Timer kaboom; 
	Timer mover; 
    boolean shoot = false; 
    boolean hit = false; 
    boolean yodaExplode = false; 
   // boolean droidExplode = false; 
    
	Rectangle blast;
	int yodaX = 120;
	int yodaY = 270;
	int life = 5; 
	int barrX = 0;
    int barrY = 0;
   
	int[][] blastCorr = new int[10][2]; 
	int[][] droidCorr = new int[10][2];  
	
	int blastNum = 0; 
	
	Image explode; 
	long currentScore = 0;
	boolean stop = false; 
	boolean stop2 = false;
	int coins = 500;
	int lightCounter = 0; 
	int lightY = 10; 
	
	boolean forceLightning3 = false; 
	boolean forceLightningClicked = false; 
	boolean[] explosion = new boolean[6]; 
	
	int changeBag = 0;
	String[] bagName = new String[4]; 
	
	
	boolean[] dExplode = new boolean[5]; 
	JButton pause;
	
	Image battleDroid ;
	Image showLightning;
	JPanel imageAdd; 
	Image bagImg;
	int dReturn = 0;
	long speed = 0;
	boolean crush = false; 
	boolean crush2 = false; 
	int crushCounter = 0; 
	public Mandalorion() // Constructor initializes the coordinates for the driods and blasts. It also adds listeners and timers.
	{
		addComponentToPane();
		if(ifGame == false)
		{
			stop = false; 
		}
		if(ifGame == true)
		{
			stop = true; 
		}
		bagName[0] = "coruscant.jpeg";
		bagName[1] = "c2.jpeg";
		bagName[2] = "c3.jpeg";
		bagName[3] = "c4.png";
		dExplode[0] = false;  
		dExplode[1] = false;
		dExplode[2] = false;
		dExplode[3] = false;
		dExplode[4] = false; 
		blastCorr[0][0] = blastX; 
		blastCorr[0][1] = blastY; 
		
		droidCorr[0][0] = droidX; 
		droidCorr[0][1] = droidY; 
		droidCorr[1][0] = 950; 
		droidCorr[1][1] = 293; 
		droidCorr[2][0] = 1050; 
		droidCorr[2][1] = 293; 
		droidCorr[3][0] = 1150; 
		droidCorr[3][1] = 293; 
		droidCorr[4][0] = 1250; 
		droidCorr[4][1] = 293; 
		
		blastCorr[1][0] = droidCorr[1][0] - 140; 
		blastCorr[1][1] = 340; 
		blastCorr[2][0] = droidCorr[2][0] - 140; 
		blastCorr[2][1] = 340; 
		blastCorr[3][0] = droidCorr[3][0] - 140; 
		blastCorr[3][1] = 340; 
		blastCorr[4][0] = droidCorr[4][0] - 140; 
		blastCorr[4][1] = 340; 

		setFocusable(true);
		requestFocus();
		DroidMover dMove = new DroidMover();
		
		BlastMover bMove = new BlastMover();
		
		//BlastDelay delay = new BlastDelay();
		
		yodaMove right = new yodaMove();
		
		yodaDown down = new yodaDown();
		timePass time = new timePass(); 
 
		DroidCrush crusher = new DroidCrush(); 
		//bagMover move = new bagMover();
		
		//setBackground(Color.BLUE); // Set Bag color 
		addMouseListener(this); 
		addKeyListener(this); 
		//Panels arj = new Panels(); 
		//arj.addActionListener();
		addMouseMotionListener(this); // the second way to add listeners 
		
		tDroid = new Timer(75, dMove);
        tDroid.start();
        
        tBlast = new Timer(75, bMove);
        tBlast.start();
        
       // blastDelay = new Timer(100, delay);
       // blastDelay.start();
        
        yodaM = new Timer(100, right);
        yodaM.start();
        
        yodaD = new Timer(2000, down);
        yodaD.start();
        
        score = new Timer(10, time); 
        score.start();
        
        Timer destroy = new Timer(100, crusher);
        destroy.start();
       // mover = new Timer(50, move); 
       // move.start(); 
        
       
        
	}
	public void paintComponent(Graphics g) // This method draws the front image, babyYoda, droids, force powers, blasts, and calls decideHit(); 
	{
		
		super.paintComponent(g); 
		//System.out.println(ifGame + " game");
		bagImg = new ImageIcon("mandalorian.jpeg").getImage();
		Image gameBag = new ImageIcon(bagName[changeBag]).getImage();
		battleDroid = new ImageIcon("droid2.png").getImage();
		Image Yoda = new ImageIcon("bYoda.png").getImage();
		explode = new ImageIcon("explosion.gif").getImage();
		showLightning = new ImageIcon("lightning10.png").getImage();
		//System.out.println(ifHome + "lll");
		
		if(ifHome)
		{
           g.drawImage(bagImg, 0, 0,  null); 
		}
		if(ifGame)
		{
			//g.drawImage(forceField, 0, 0, null);
		    barrX = yodaX - 100;	
            barrY = yodaY + 60;
			g.drawImage(gameBag, 0, 0,  null); 
			g.setColor(Color.black); 
			g.fillRect(10, 440, 1120, 100);
			
			
			
						
			if(dExplode[0] == false)
			{
				
				g.drawImage(battleDroid, droidCorr[0][0], droidCorr[0][1], -100, 150, null);
			}
			if(dExplode[0] == true)
			{
				drawExplode(g, 0); 
			}
			
			if(dExplode[1] == false)
			{
				
				g.drawImage(battleDroid, droidCorr[1][0], droidCorr[1][1], -100, 150, null);
			}
			if(dExplode[1] == true)
			{
				drawExplode(g, 1); 
			}
					
					
			if(dExplode[2] == false)
			{
				g.drawImage(battleDroid, droidCorr[2][0], droidCorr[2][1], -100, 150, null);
			}
			if(dExplode[2] == true)
			{
				drawExplode(g, 2); 
			}
					
					
			if(dExplode[3] == false)
			{
				g.drawImage(battleDroid, droidCorr[3][0], droidCorr[3][1], -100, 150, null);
			}
			if(dExplode[3] == true)
			{
				drawExplode(g, 3); 
			}
			
			
			if(dExplode[4] == false)
			{
				g.drawImage(battleDroid, droidCorr[4][0], droidCorr[4][1], -100, 150, null);
			}
			if(dExplode[4] == true)
			{
				drawExplode(g, 4); 
			}
								
				
			/*g.drawImage(battleDroid, droidCorr[1][0], droidCorr[1][1], -100, 150, null);
			g.drawImage(battleDroid, droidCorr[2][0], droidCorr[2][1], -100, 150, null);
			g.drawImage(battleDroid, droidCorr[3][0], droidCorr[3][1], -100, 150, null);
			g.drawImage(battleDroid, droidCorr[4][0], droidCorr[4][1], -100, 150, null);*/
			
			
			
			g.setColor(Color.BLUE);
			
			if(dExplode[0] == false && blastCorr[0][0] > 10)
			{
				g.drawRect(blastCorr[0][0], blastCorr[0][1], 75, 2);
			}
			if(dExplode[1] == false && blastCorr[1][0] > 10)
			{
				g.drawRect(blastCorr[1][0], blastCorr[1][1], 75, 2);
			}
			if(dExplode[2] == false && blastCorr[2][0] > 10)
			{
			    g.drawRect(blastCorr[2][0], blastCorr[2][1], 75, 2);
			}
			if(dExplode[3] == false && blastCorr[3][0] > 10)
			{
			
			    g.drawRect(blastCorr[3][0], blastCorr[3][1], 75, 2);
			}
			if(dExplode[4] == false && blastCorr[4][0] > 10)
			{
				g.drawRect(blastCorr[4][0], blastCorr[4][1], 75, 2);
			}
		    
		    if((dExplode[0] == true && dExplode[1] == true && dExplode[2] == true && dExplode[3] == true && dExplode[4] == true) || yodaX > 1024)
		    {
				//restart2 = true; 
				//stop = true; 
				/*dExplode[0] = false;  
				dExplode[1] = false;
				dExplode[2] = false;
				dExplode[3] = false;
				dExplode[4] = false; 
				blastCorr[0][0] = blastX; 
				blastCorr[0][1] = blastY; 
				
				droidCorr[0][0] = droidX; 
				droidCorr[0][1] = droidY; 
				droidCorr[1][0] = 950; 
				droidCorr[1][1] = 293; 
				droidCorr[2][0] = 1050; 
				droidCorr[2][1] = 293; 
				droidCorr[3][0] = 1150; 
				droidCorr[3][1] = 293; 
				droidCorr[4][0] = 1250; 
				droidCorr[4][1] = 293; 
				
				blastCorr[1][0] = droidCorr[1][0] - 140; 
				blastCorr[1][1] = 340; 
				blastCorr[2][0] = droidCorr[2][0] - 140; 
				blastCorr[2][1] = 340; 
				blastCorr[3][0] = droidCorr[3][0] - 140; 
				blastCorr[3][1] = 340; 
				blastCorr[4][0] = droidCorr[4][0] - 140; 
				blastCorr[4][1] = 340; */
				
				dExplode[0] = false;  
				dExplode[1] = false;
				dExplode[2] = false;
				dExplode[3] = false;
				dExplode[4] = false; 
				blastCorr[0][0] = 710; 
				blastCorr[0][1] = 340; 
				
				droidCorr[0][0] = 1250;
				droidCorr[0][1] = 293; 
				droidCorr[1][0] = 1350; 
				droidCorr[1][1] = 293; 
				droidCorr[2][0] = 1450; 
				droidCorr[2][1] = 293; 
				droidCorr[3][0] = 1550; 
				droidCorr[3][1] = 293; 
				droidCorr[4][0] = 1650; 
				droidCorr[4][1] = 293; 
				
				blastCorr[1][0] = droidCorr[1][0] - 140; 
				blastCorr[1][1] = 340; 
				blastCorr[2][0] = droidCorr[2][0] - 140; 
				blastCorr[2][1] = 340; 
				blastCorr[3][0] = droidCorr[3][0] - 140; 
				blastCorr[3][1] = 340; 
				blastCorr[4][0] = droidCorr[4][0] - 140; 
				blastCorr[4][1] = 340; 
			}
		    System.out.println(crush + " " + crush2);
			//System.out.println(currentScore);
            Font plainFont = new Font("Serif", Font.BOLD, 24);
            g.setFont(plainFont);
            g.setColor(Color.black);
            g.drawString("Score: " + currentScore, 10, 30); 
			g.drawString("Coins: " + coins, 130, 30); 
			
			if(forceLightning3)
			{
				drawLightning(g); 
			
				
			}
				/*System.out.println(false);
				g.drawImage(showLightning, droidCorr[0][0], lightY, -75, 600, null);
				dExplode[0] = true;
				shoot = true;
				if(dExplode[0] == true)
				{
					 g.drawImage(showLightning, droidCorr[1][0], lightY, -75, 600, null);
				     dExplode[1] = true;
				     forceLightning3 = false;
				     shoot = true;
			
				}
				if(dExplode[1] == true)
				{
					 g.drawImage(showLightning, droidCorr[2][0], lightY, -75, 600, null);
			
				     dExplode[2] = true;
				     forceLightning3 = false;
				     shoot = true;
				}
				if(dExplode[2] == true)
				{
					 g.drawImage(showLightning, droidCorr[3][0], lightY, -75, 600, null);
				     dExplode[3] = true;
				     forceLightning3 = false;
				     shoot = true;
				}
				if(dExplode[3] == true)
				{
					 g.drawImage(showLightning, droidCorr[4][0], lightY, -75, 600, null);
					 dExplode[4] = true;
				     forceLightning3 = false;
				     shoot = true;
				}*/
				
			
					
					
				
				//dExplode[lightCounter] = true;
			
				//shoot = true; 
				//repaint();
			
			
			
			//g.drawRect(yodaX -100, yodaY + 60, 75, 120);
			g.setColor(Color.blue);
			//g.drawRect(barrX, barrY, 50, 120);
			//System.out.println(yodaX + " " + yodaY);
			if(yodaExplode == false)
			{
			
				g.drawImage(Yoda, yodaX, yodaY, -150, 200, null);
			}
			if(yodaExplode == true)
			{
				System.out.println(true);
				explosion[5] = true;
				if(explosion[5] == true)
				g.drawImage(explode, yodaX, yodaY, -150, 200, null);
				restart2 = true;
				addComponentToPane();
				stop = true; 
				
		
			}
			//g.setColor(Color.YELLOW);
			//g.drawImage(forceField, yodaX + 200, yodaY - 100, -600, 400,null);
			//g.drawRect(yodaX + 200, yodaY - 100, 600, 10);
			//System.out.println("ddd" + (blastY - Y));
            //System.out.println(barrX + " " + barrY);
			decideHit();
			if(yodaX > 1024)
			{
				yodaX = 100;
				yodaY = 260;
				changeBag++;
				if(changeBag > 3)
				{
					changeBag = 0; 
				}
			}
		  }
		  //System.out.println(stop + "stop");
			
			
       }
	public void drawExplode(Graphics g, int index) // Draws the explode gif after anything dies
	{
		//System.out.println(true);
		explosion[index] = true;
		if(explosion[index] == true)
		g.drawImage(explode, droidCorr[index][0], droidCorr[index][1] - 50, -150, 200, null);
	}
		
		
	public void drawLightning(Graphics g) // Draws the lightning graphics 
	{
		for(int i = 0; i < 1120; i++)
		{
			if(i == droidCorr[0][0] && dExplode[0] == false)
			{
				g.drawImage(showLightning, droidCorr[0][0], lightY, -75, 600, null);
				forceLightning3 = false;
				dExplode[0] = true;
				break; 
			}
		    else if(i == droidCorr[1][0] && dExplode[1] == false)
			{
				g.drawImage(showLightning, droidCorr[1][0], lightY, -75, 600, null);
				forceLightning3 = false;
				dExplode[1] = true;
				break; 
			}
		    else if(i == droidCorr[2][0] && dExplode[2] == false)
			{
				g.drawImage(showLightning, droidCorr[2][0], lightY, -75, 600, null);
				forceLightning3 = false;
				dExplode[2] = true;
				break; 
			}
		    else if(i == droidCorr[3][0] && dExplode[3] == false)
			{
				g.drawImage(showLightning, droidCorr[3][0], lightY, -75, 600, null);
				forceLightning3 = false;
				dExplode[3] = true;
				break; 
			}
			else if(i == droidCorr[4][0] && dExplode[4] == false)
			{
				g.drawImage(showLightning, droidCorr[4][0], lightY, -75, 600, null);
				forceLightning3 = false;
				dExplode[4] = true;
				break; 
				
			}    
		}
	}
		
		//System.out.println(ifHome + "lll");
		
		//g.drawRect(10, 10, 100, 100);
	
	public void decideHit() // This method deduces if a blast has hitten baby yoda. 
	{
			//System.out.println(Math.abs(yodaX - 100 - blastX));
			//System.out.println(((Math.abs(yodaY + 60 - blastY) <=120)));
		
			/*if((Math.abs(barrX - blastCorr[i][0])) <= 75 && (Math.abs(barrY - blastCorr[i][1]) <= 120))
			{
					 
					  
			}*/
			//System.out.println((Math.abs(barrY - blastCorr[0][1]) <= 60));
			//System.out.println(barrY);
		 			
			if((Math.abs(barrX - blastCorr[0][0])) <= 50 && (Math.abs(barrY - blastCorr[0][1]) <= 60)) 
			{
				//System.out.println(1); 
				hit = true;
				blastNum = 1; 
				ifHit();
			}
			if((Math.abs(barrX - blastCorr[1][0])) <= 50 && (Math.abs(barrY - blastCorr[1][1]) <= 60)) {
				//System.out.println(2); 
				hit = true;
				blastNum = 2; 
				ifHit();
			}
			if((Math.abs(barrX - blastCorr[2][0])) <= 50 && (Math.abs(barrY - blastCorr[2][1]) <= 60)) {
				//System.out.println(3); 
				hit = true;
				blastNum = 3; 
				ifHit();
			
			}
			if((Math.abs(barrX - blastCorr[3][0])) <= 50 && (Math.abs(barrY - blastCorr[3][1]) <= 60)) {
				//System.out.println(4);
				hit = true;
				blastNum = 4; 
				ifHit();
			
			}
			if((Math.abs(barrX - blastCorr[4][0])) <= 50 && (Math.abs(barrY - blastCorr[4][1]) <= 60)) {
				//System.out.println(5); 
				hit = true;
				blastNum = 5; 
				ifHit();
			
		   
			}
		   
		  
			//System.out.println(barrX + " 1 " + blastCorr[0][0] + " " + Math.abs(barrX-43));
			//System.out.println(barrX + " 2 " + blastCorr[1][0]);
			//System.out.println(barrX + " 3 " + blastCorr[2][0]);
			//System.out.println(barrX + " 4 " + blastCorr[3][0]);
			//System.out.println(barrX + " 5 " + blastCorr[4][0]);
	
					  
					
			 //System.out.println((Math.abs(yodaX - blastX) < 75) && ((Math.abs(yodaY - blastY) < 120)));

			   
	}
	
	public void ifHit() /* If baby yoda ever gets hit, this method is called.
	 It reduces the life by 1 and has the if statement to end the program if all 5 lives are lost */
	{

		 life -=1;
		 //System.out.println(life + "life");
		 if (life == 0) 
		 {
			 System.out.println(life + "lifeee");
			 yodaExplode = true; 
		
		 }
		 //System.out.println((Math.abs(barrY - blastCorr[1][1])));
		 //System.out.println((Math.abs(barrY - blastCorr[2][1])));
		 //System.out.println((Math.abs(barrY - blastCorr[3][1])));
         //System.out.println((Math.abs(barrY - blastCorr[4][1])));

	
	 
	 }
	 
	
		

	public void addComponentToPane() /* This 
	method sets the layout for the game. Play, choose character, pause/resume game, choosing force 
	powers, and going back to the home page */
	{
		Character star = new Character();
		cards = new JPanel(new CardLayout());
		cards2 = new JPanel(new CardLayout());
		//ImageIcon bag = new ImageIcon(getClass().getResource("mandalorian.jpeg"));
		JPanel homeAdd = new JPanel(); 
		//JButton bagImage = new JButton(bag); 
		JPanel home = new JPanel();
		JPanel play = new JPanel();
		JPanel playAdd = new JPanel();
		

	    //homeAdd.setLayout(new FlowLayout(1, 1, 1));
		home.setLayout(new GridLayout(7 ,7));
		//bagImage.setLayout(new FlowLayout(-5, -10, -10));
		home.add(b1);
		home.add(b2);
		home.add(b3);
		home.add(b4);
		home.add(b5);
		home.add(b6);
		//homeAdd.setLayout(new BorderLayout()); 
		
		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		//b1.setBorderPainted(false);
		
		b2.setOpaque(false);
		b2.setContentAreaFilled(false);
		//b2.setBorderPainted(false);
		
		b3.setOpaque(false);
		b3.setContentAreaFilled(false);
		//b3.setBorderPainted(false);
		
		b4.setOpaque(false);
		b4.setContentAreaFilled(false);
		//b4.setBorderPainted(false);
		
		b5.setOpaque(false);
		b5.setContentAreaFilled(false);
		//b5.setBorderPainted(false);
		
		b6.setOpaque(false);
		b6.setContentAreaFilled(false);
		//b6.setBorderPainted(false);
		//bagImage.add(b1);
		//bagImage.add(b2);
		//bagImage.add(b3);
		//bagImage.add(b4);
		//bagImage.add(b5);
		//homeAdd.add(home, BorderLayout.WEST); 
	
		//bagImage.setPreferredSize(new Dimension(0 ,0));
		
		/*b1.setPreferredSize(new Dimension(w1, h1));
		b2.setPreferredSize(new Dimension(w1, h1));
		b3.setPreferredSize(new Dimension(w1, h1));
		b4.setPreferredSize(new Dimension(w1, h1));
		b5.setPreferredSize(new Dimension(w1, h1));
		b6.setPreferredSize(new Dimension(w1, h1));*/
		
		/*b1.setLocation(100, 100);
		b1.setLocation(100, 150);
		b1.setLocation(100, 200);
		b1.setLocation(100, 250);
		b1.setLocation(100, 300);
		b1.setLocation(100, 350);*/
		//home.add(bagImage);
		//cards.add(home); 
		
		//bagImage.setOpaque( false );
		homeAdd.add(home); 
		//homeAdd.add(home, BorderLayout.WEST);
		cards.add(homeAdd, "1");
		
		
		b1.addActionListener(this);
        b2.addActionListener(this);
        
	    charPage = new JPanel(); 
	    character = new JPanel();
		ImageIcon babyYoda = new ImageIcon(getClass().getResource("gorgu.jpeg"));
		gorgu = new JButton("", babyYoda);
		//gorgu.setPreferredSize( new Dimension(50, 50));
			
			
		ImageIcon skywalker = new ImageIcon(getClass().getResource("luke.jpeg"));
		ImageIcon din = new ImageIcon(getClass().getResource("mando.jpeg"));
		ImageIcon katan = new ImageIcon(getClass().getResource("Bo.jpeg"));
		ImageIcon tano = new ImageIcon(getClass().getResource("tano.jpeg"));
		ImageIcon dune = new ImageIcon(getClass().getResource("dune.jpeg"));
		//ImageIcon light = new ImageIcon(getClass().getResource("lightning.jpeg"));
		

			
		luke = new JButton("", skywalker);
		mando = new JButton("", din);
		bo = new JButton("", katan);
		ahsoka = new JButton("", tano);
		cara = new JButton("", dune);
			
		
			
		//character.setLayout(new GridLayout(10, 1));
		
		character.add(gorgu); 
		character.add(luke);
		character.add(mando);
		character.add(bo);
		character.add(ahsoka);
		character.add(cara);
		//charPage.setLayout(new BorderLayout());
			
	    int width = 50; 
		int height = 50;
		gorgu.setPreferredSize(new Dimension(width, height)); 
		luke.setPreferredSize(new Dimension(width, height)); 
		mando.setPreferredSize(new Dimension(width, height)); 
		bo.setPreferredSize(new Dimension(width, height)); 
		ahsoka.setPreferredSize(new Dimension(width, height)); 
		cara.setPreferredSize(new Dimension(width, height)); 
		
		charPage.add(character);

		//cards.add(star); 
		cards.add(play, "play");
		cards.add(charPage, "char");

		//åhomeAdd.setBackground(new Color(255, 255, 255, 255));
		
		JPanel abilities = new JPanel();
		JPanel playButtons = new JPanel(); 
	    cards.add(abilities, "3"); 
		playButtons.setLayout(new GridLayout(7,7)); 
		
		play.setLayout(new BorderLayout());
		JButton returnHome = new JButton("Home");
		returnHome.addActionListener(this);
		
		
		
		play.add(playButtons, BorderLayout.WEST); 
		JButton power = new JButton("Force Powers");
		JButton restart = new JButton("Restart"); 
		pause = new JButton("Pause Game");
		pause.addActionListener(this); 
		power.addActionListener(this);
		playButtons.add(returnHome); 
		
		
		
			
			
			
		playButtons.add(power); 
		playButtons.add(pause);

	
		JPanel powerButtons = new JPanel();
		JButton Quit = new JButton("Quit");
		powerButtons.setLayout(new GridLayout(7, 1)); 
		abilities.setLayout(new BorderLayout()); 
		abilities.add(powerButtons);
		JButton Lightning2 = new JButton("Force Lightning 500 coins"); 
		JButton ForceDeflect = new JButton("Force deflect 100 coins"); 
		JButton Speed = new JButton("Force Speed 100 coins"); 
		JButton ForceCrush = new JButton("Force Crush 100 coins"); 
		JButton Choke = new JButton("Force Choke  300 coins "); 
		JButton Shield = new JButton("Force Shield 500 coins"); 
		
		powerButtons.add(Lightning2); 
		powerButtons.add(ForceDeflect); 
		powerButtons.add(Speed); 
		powerButtons.add(ForceCrush); 
		powerButtons.add(Choke); 
		powerButtons.add(Shield);
		playButtons.add(Quit); 
		//playButtons.add(restart); 

 
		Lightning2.addActionListener(this);
		restart.addActionListener(this);
		Quit.addActionListener(this);
		ForceCrush.addActionListener(this);
		add(cards);
		//repaint();
		
		
	}
	
	
	
class Character
{
	public void addChar(Container pane)
	{

		
		
		

	}
		
		
		
}
	
	public void mousePressed(MouseEvent e) {} // If mouse is pressed - Not doing anything at the moment 
	
	public void mouseReleased(MouseEvent e) {} // If mouse click is released -  Not doing anything at the moment
	
	public void mouseClicked(MouseEvent e) {} //If mouse is clicked - Not doing anything at the moment 
	
	public void mouseEntered(MouseEvent e) {}  // If the mouse enters the panel - Not doing anything at the moment 
	
	public void mouseExited(MouseEvent e) {} // If the mouse exits the panel - Not doing anything at the moment 
	
	
	public void mouseDragged(MouseEvent e) {} // If you are draggin anything -  Not doing anything at the moment 
	
	
	public void mouseMoved(MouseEvent e) {} // Checks if mouse is moving -  Not doing anything at the moment 
	
	
	
	public void keyPressed(KeyEvent e) /* This method dectects if the right or up key has been pressed. 
	It controlls the boolean that gives permission to move baby Yoda around*/
	{
		grabFocus();

		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			System.out.println(true + "Ddd");
			ifRight = true; 
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			System.out.println(true + "asa");
			ifUp = true; 
		}
		
    }
	
	
	public void keyReleased( KeyEvent evt ) {} // Not doing anything at the moment 
	
	
	public void keyTyped( KeyEvent evt ) /* This method is checking if the f has been pressed while the lightinng 
	power has been activated */
	{
		 char ch = evt.getKeyChar();
		 if(ch == 'f' && forceLightningClicked == true)
		 {
			// System.out.println(true);
			 forceLightning3 = true; 
			 
			
			 
		 }
		 if(ch == 'c')
		 {
			 crush2 = true; 
			 System.out.println(true+ "hi");
		 }
			 
			 
		
		
		
		
	} 
	
	
	public void actionPerformed(ActionEvent evt)
	/* This changes the to different cards depending upon the button pressed on the home page. 
	 Right now it can operate between home page, play, and chose character
	 In the play page, it can allow you to choose force powers, pause/resume game, and return to home page*/
	{
		CardLayout cl = (CardLayout)(cards.getLayout());
		//CardLayout c2 = (CardLayout)(cards2.getLayout());
		
		
		  if (evt.getActionCommand().equals("Choose your Character"))
		  {
			 cl.show(cards, "char"); 
			 System.out.println(true + " Chracter page");
			 ifGame = false;
			 ifHome = false;
			 repaint();
			 //System.out.println(ifHome + "ddd");
		  }
		  
		  if (evt.getActionCommand().equals("Play"))
		  {
			 cl.show(cards, "play");
			 System.out.println(true + "ddd");
			 ifHome = false;
			 ifGame = true; 
			 repaint();
			 //System.out.println(ifHome + "ddd");
			 
		  }
		  if (evt.getActionCommand().equals("Home"))
		  {
			 //System.out.println("hi");
			 
		     cl.show(cards, "1");

			 ifHome = true;
		     ifGame = false; 
		     repaint();
		  }
		  
		  if (evt.getActionCommand().equals("Force Powers") && stop == false)
		  {
			 //System.out.println("hi");
		     cl.show(cards, "3");
			 stop2 = !(stop2); 
			 ifHome = false;
		     ifGame = false; 
		     repaint();
		     
		  }
		  
		  if (evt.getActionCommand().equals("Force Lightning 500 coins"))
		     {
				 //System.out.println("hi");
				 cl.show(cards, "play");
				 stop2 = !(stop2); 
				 ifHome = false;
				 ifGame = true; 
				 coins = coins - 500; 
				 forceLightningClicked = true; 
				 repaint();
		     }
		  if(evt.getActionCommand().equals("Pause Game") && stop == false)
		   {
				 System.out.println("hi");
				 cl.show(cards, "play");
		
				 ifHome = false;
				 ifGame = true; 
				 pause.setText("Resume");
				 stop2 = !(stop2); 
				 repaint();
		    }
		  if(evt.getActionCommand().equals("Resume"))
		  {
				 System.out.println("hi");
				 cl.show(cards, "play");
				 pause.setText("Pause Game");
				 ifHome = false;
				 ifGame = true; 
				 stop2 = !(stop2);  
				 repaint();
		   }
		       
		  /*if(evt.getActionCommand().equals("Restart"))
		   {
			     //restart2 = false;
				 System.out.println("hi0000");
				// cl.show(cards, "play");
				 yodaExplode = false;
				 ifHome = false;
				 ifGame = true; 
				 //pause.setText("Resume");
				 //stop = !(stop); \dExplode[0] = false;  
				 dExplode[1] = false;
				 dExplode[2] = false;
				 dExplode[3] = false;
				 dExplode[4] = false; 
				 blastCorr[0][0] = blastX; 
				 blastCorr[0][1] = blastY; 
				
				 droidCorr[0][0] = droidX; 
				 droidCorr[0][1] = droidY; 
				 droidCorr[1][0] = 950; 
				 droidCorr[1][1] = 293; 
				 droidCorr[2][0] = 1050; 
				 droidCorr[2][1] = 293; 
				 droidCorr[3][0] = 1150; 
				 droidCorr[3][1] = 293; 
				 droidCorr[4][0] = 1250; 
				 droidCorr[4][1] = 293; 
				 
				 blastCorr[1][0] = droidCorr[1][0] - 140; 
				 blastCorr[1][1] = 340; 
				 blastCorr[2][0] = droidCorr[2][0] - 140; 
				 blastCorr[2][1] = 340; 
				 blastCorr[3][0] = droidCorr[3][0] - 140; 
				 blastCorr[3][1] = 340; 
				 blastCorr[4][0] = droidCorr[4][0] - 140; 
				 blastCorr[4][1] = 340; 
				 explosion[5] = false;
				
				 
				 stop = false; 
				 //repaint();
		    }*/
		    if(evt.getActionCommand().equals("Quit"))
		    {
				System.exit(0); 
			}
			if(evt.getActionCommand().equals("Force Crush 100 coins"))
		    {
				System.out.println("luke");
				crush = true;
				cl.show(cards, "play");
				ifGame = true;
				ifHome = false; 
				stop2 = !(stop2); 
				
				coins = coins - 500; 
				
				repaint();
			}
			  
			  
			
		
	}
	
	class DroidMover implements ActionListener // This class contains the timer that moves the droid 
	{
		public void actionPerformed(ActionEvent e) // Moves the droid 
		{
			if(!(stop) && !(ifHome) && !(stop2))
			{
				droidCorr[0][0] -= 1;  
				droidCorr[1][0] -= 1;  
				droidCorr[2][0] -= 1;  
				droidCorr[3][0] -= 1;  
				droidCorr[4][0] -= 1;  
			
				repaint();
			}
			repaint();
		
		}
		
	}
	
	class BlastMover implements ActionListener// This class contains the timer that helps move the blasts the droid shoot
	{
		public void actionPerformed(ActionEvent e) 
		/* While this method also moves the droids by calling changeLoc(), it also calls reshoot() if baby yoda has been hit */
		{
			if(!(stop) && !(stop2) && ifGame && !(ifHome))
			{
			
			
					changeLoc(); 
					if(blastCorr[0][0] < 0 && dExplode[0] == false)
					{
						 blastCorr[0][0] = droidCorr[0][0]  - 140; 
						 changeLoc();
					}
					
					if(blastCorr[1][0] < 0 && dExplode[1] == false) 
					{
						blastCorr[1][0]  = droidCorr[1][0]  - 140; 
						changeLoc();
					}
					
					if(blastCorr[2][0] < 0 && dExplode[2] == false) 
					{
						blastCorr[2][0]  = droidCorr[2][0]  - 140; 
						changeLoc();
					}
					
					if(blastCorr[3][0] < 0 && dExplode[3] == false)
					{
						
						blastCorr[3][0]  = droidCorr[3][0]  - 140; 
						changeLoc();
					}
					if(blastCorr[4][0] < 0 && dExplode[4] == false)
					{
						
						blastCorr[4][0]  = droidCorr[4][0]  - 140; 
						changeLoc();
					}
					
					
					/*for(int i = 0; i < 5; i++)
					{
				
						if((Math.abs(barrX - blastCorr[i][0])) <= 75 && (Math.abs(barrY - blastCorr[i][1]) <= 120))
						{
								
							blastCorr[i][0] = droidCorr[i][0]  - 140; 
							blastCorr[i][0] -= 45;
							repaint();
							//System.out.println(1);
							
							 
							 
						}
						else if(hit == false && blastCorr[i][0]  < 100)
						{
						 
							//System.out.println(2);	
							blastCorr[i][0] = droidCorr[i][0] - 140; 
							//hit = false; 
							blastCorr[i][0] -= 45; 
							repaint();
					
						
						}
						else
						{
							//System.out.println(3);
							blastCorr[i][0] -= 45; 
							if(blastCorr[i][0]  < 100) blastCorr[i][0]  = droidCorr[i][0]  - 140; 
							repaint();
						}
					}*/
					
					//System.out.println(barrX + " 1 " + blastCorr[0][0] + " " + Math.abs(barrX-43));
					if(hit == true)
					{
						hit = false; 
						reshoot();
						
						
					}
				}
			
			repaint();
			
			
		}
		
		public void changeLoc() // Constantly moves the blasts unless the they come in contact with babyYoda
		
		{
			if(ifGame && !(stop) && !(stop2))
			{
				if(speed < 100)
				{
					speed = currentScore/50;
				}
				else 
				{
					speed = 100;
				}
					
				if(dExplode[0] == false)
				blastCorr[0][0] -= speed;
				
				if(dExplode[1] == false)
				blastCorr[1][0] -= speed;
				
				if(dExplode[2] == false)
				blastCorr[2][0] -= speed;
				
				
				if(dExplode[3] == false)
				blastCorr[3][0] -= speed;
				
				
				if(dExplode[4] == false)
				blastCorr[4][0] -= speed;
				
				if(dExplode[0] == true)
				{
					while(blastCorr[0][0] > -100)
					{
						blastCorr[0][0] -= speed;
					
							
						
					}
				}
				
				if(dExplode[1] == true)
				{
					blastCorr[1][0] = -100;
					while(blastCorr[1][0] > -100)
					{
						blastCorr[1][0] -= speed;
					
							
						
					}
				}
				
				if(dExplode[2] == true)
				{
				   blastCorr[2][0] = -100;
				   while(blastCorr[2][0] > -100)
					{
						blastCorr[2][0] -= speed;
					
							
						
					}
				}
				
				
				if(dExplode[3] == true)
				{
					blastCorr[3][0] = -100;
					while(blastCorr[3][0] > -100)
					{
						blastCorr[3][0] -= speed;
					
							
						
					}
				}
				
				
				if(dExplode[4] == true)
				{
					blastCorr[4][0] = -100;
					while(blastCorr[4][0] > -100)
					{
						blastCorr[4][0] -= speed;
					
							
						
					}
				}
				
				
				repaint();
			}
			
		}
	    public void reshoot() // Called when a blast hits baby yoda. 
	    // This method moves the blast back to the droid, essentialy reusing it 
		{
			//System.out.println(true);
			blastCorr[blastNum - 1][0]  = droidCorr[blastNum - 1][0]  - 140; 
		    changeLoc();
			repaint();
			
		}

	}
	
	/*class BlastDelay implements ActionListener
    {
		public void actionPerformed(ActionEvent e) 
		{
			
			shoot = !(shoot);
			
		}
		
		
		
	}*/
	
	class yodaMove implements ActionListener // This class has the timer that moves baby Yoda up or sideways 
    {
		public void actionPerformed(ActionEvent e) 
		/* If the right key or up is key pressed, this methid moves Yoda 
		 up or down. */
		{
			if(!(stop) && !(stop2))
		    {
				
				
					//System.out.println(1);
				if(ifGame == true)
				yodaX += 7; 
				ifRight = false; 
				repaint();
			
				if(ifUp == true)
				{
					//System.out.println(1);
					
					yodaY -= 350; 
					upTracker++;
					ifUp = false;
					//System.out.println(ifUp + " " + upTracker);
					repaint();
					 /*System.out.println((Math.abs(barrY - blastCorr[0][1])) + " 1");
				System.out.println((Math.abs(barrX - blastCorr[1][0])) + " 2");
				System.out.println((Math.abs(barrX - blastCorr[2][0])) + " 3");
				System.out.println((Math.abs(barrX - blastCorr[3][0])) + " 4");
				System.out.println((Math.abs(barrX - blastCorr[4][0])) + " 5");*/
				}
			}
			
			
		}
		
		
		
	}
	
	class yodaDown implements ActionListener // This class has the timer that acts as gravity 
    {
		public void actionPerformed(ActionEvent e) 
		/* This method acts like gravity and pulls baby Yods
		 back towards the ground when he jumps up and also 
		 translates him forward */
		 {
			if(!stop && !(stop2))
			{
				if(ifUp == false && upTracker > 0)
				{
			
					
					yodaY += 350; 
					yodaX += 100;
					repaint();	
					upTracker = 0;
				
				
				}
			}
			//if(yodaExplode)
			//{
				//System.exit(0);
			//}
		}
		
		
		
	}
	
	class timePass implements ActionListener /* This timer updates the score, 
	which is based on how much time you have survived */
	{
		public void actionPerformed(ActionEvent e) // This listener updates the score 
		{
			if(ifGame && !(stop))
			currentScore += 1; 
			
		}
		
	}
	
	class DroidCrush implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			//System.out.println(crush +  "  " + crush2);
			if(crush && crush2)
			{
				droidCorr[crushCounter][1] -= 400;
				crushCounter++;
				if(droidCorr[crushCounter][1] <= 193)
				dExplode[crushCounter] = true; 
			}
			
			//crushCounter++; 

		}
	}

}
	
	

	

		



