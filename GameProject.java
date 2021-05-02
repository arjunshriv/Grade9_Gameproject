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
		 setLocation(0, 0); // sets the location
		 
		 frame.setResizable(false);
		 Mandalorion pan = new Mandalorion();
		 //frame.setContentPane(pan);
		 //JPanel pan = new JPanel(); 
		 //frame.add(pan);
		 frame.getContentPane().add(pan);
		
	
		 pan.addComponentToPane(frame.getContentPane());

		
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
		GameProject arj = new GameProject(); // Main calls the constructor that sets it's properties. 
	}
	
}

class Mandalorion extends JPanel implements MouseListener, KeyListener, MouseMotionListener, ActionListener 
/*This class draw everything on the panel. It draws the buttons on choose your character 
, droids, baby Yoda, blasts and the home page*/
{
	/* These are all field variables */
	JPanel cards, cards2; 
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
	int coins = 500;
	int lightCounter = 0; 
	int lightY = 10; 
	
	boolean forceLightning3 = false; 
	boolean forceLightningClicked = false; 
	boolean[] explosion = new boolean[6]; 
	
	
	boolean[] dExplode = new boolean[5]; 
	JButton pause;
	
	Image Arjun, battleDroid ;
	Image showLightning;
	JPanel imageAdd; 
	
	public Mandalorion() // Constructor initializes the coordinates for the driods and blasts. It also adds listeners and timers.
	{
		if(ifGame == false)
		{
			stop = false; 
		}
		if(ifGame == true)
		{
			stop = true; 
		}
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
        
        yodaM = new Timer(1000, right);
        yodaM.start();
        
        yodaD = new Timer(2000, down);
        yodaD.start();
        
        score = new Timer(10, time); 
        score.start();
        
       
        
	}
	public void paintComponent(Graphics g) // This method draws the front image, babyYoda, droids, force powers, blasts, and calls decideHit(); 
	{
		
		super.paintComponent(g); 
		//System.out.println(ifGame);
		Arjun = new ImageIcon("mandalorian.jpeg").getImage();
		Image gameBag = new ImageIcon("coruscant.jpeg").getImage();
		battleDroid = new ImageIcon("droid2.png").getImage();
		Image Yoda = new ImageIcon("bYoda.png").getImage();
		explode = new ImageIcon("explosion.gif").getImage();
		showLightning = new ImageIcon("lightning10.png").getImage();
		//System.out.println(ifHome + "lll");
		
		if(ifHome)
		{
			g.drawImage(Arjun, 0, 0, 1120, 540, null); 
		}
		if(ifGame)
		{
		
		    barrX = yodaX - 100;
            barrY = yodaY + 60;
			g.drawImage(gameBag, 0, 0, 1120, 540, null); 
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
			
			
			
			Color col2 = new Color(r, ge, b);
			g.setColor(col2);
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
		    
		    if(dExplode[0] == true && dExplode[1] == true && dExplode[2] == true && dExplode[3] == true && dExplode[4] == true)
		    {
				stop = true; 
			}
		    
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
				explosion[5] = true;
				if(explosion[5] == true)
				g.drawImage(explode, yodaX, yodaY, -150, 200, null);
				stop = true; 
		
			}
			//System.out.println("ddd" + (blastY - Y));
            //System.out.println(barrX + " " + barrY);
			decideHit();
			
		  }
			
			
       }
	public void drawExplode(Graphics g, int index)
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
			 yodaExplode = true; 
		
		 }
		 //System.out.println((Math.abs(barrY - blastCorr[1][1])));
		 //System.out.println((Math.abs(barrY - blastCorr[2][1])));
		 //System.out.println((Math.abs(barrY - blastCorr[3][1])));
         //System.out.println((Math.abs(barrY - blastCorr[4][1])));

	
	 
	 }
	 
	
		
	
	public void addComponentToPane(Container pane) /* This 
	method sets the layout for the game. Play, choose character, pause/resume game, choosing force 
	powers, and going back to the home page */
	{
		//Arjun = new ImageIcon("mandalorian.jpeg").getImage();
		
		String BUTTONPANEL = "1";
		String TEXTPANEL = "2";
		cards = new JPanel(new CardLayout());
		cards2 = new JPanel(new CardLayout());
	    JPanel setSide = new JPanel(); 
		JPanel homePage = new JPanel(); 
		JPanel charPage = new JPanel();		
		//JPanel imageAdd = new JPanel(); 
		//JLabel bagImg = new JLabel(bag); 
	
		
		//imageAdd.setLayout(new BorderLayout()); 
		homePage.setLayout(new GridLayout(7 ,1));
		homePage.add(b1);
		homePage.add(b2);
		homePage.add(b3);
		homePage.add(b4);
		homePage.add(b5);
		homePage.add(b6);
		//b1.setPreferredSize(new Dimension(100, 85)); 
		
		Color col = new Color(r, ge, b);
	    System.out.println(r + " " + ge + " " + b);
		b1.setBackground(col);
		b1.setOpaque(true);
		b2.setBackground(col);
		b2.setOpaque(true);
		b3.setBackground(col);
		b3.setOpaque(true);
		b4.setBackground(col);
		b4.setOpaque(true);
		b5.setBackground(col);
		b5.setOpaque(true);
		b6.setBackground(col);
		b6.setOpaque(true);
	
		//homePage.setLocation(10, 10); 
		JPanel character = new JPanel();	
		setSide.add(homePage, BorderLayout.WEST); 
	    //imageAdd.add(bagImg, BorderLayout.EAST); 
		cards.add(setSide, "1");
		
		
		pane.add(cards, BorderLayout.WEST);
		if(ifHome == false)
		pane.add(cards2, BorderLayout.WEST);
		//if(ifHome == false) b1.setPreferredSize(new Dimension(0, 0)); 
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		

			
		ImageIcon babyYoda = new ImageIcon(getClass().getResource("gorgu.jpeg"));
		gorgu = new JButton("", babyYoda);
		gorgu.setPreferredSize( new Dimension(50, 50));
			
			
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
			
		
			
		character.setLayout(new GridLayout(3,2));
		
		character.add(gorgu); 
		character.add(luke);
		character.add(mando);
		character.add(bo);
		character.add(ahsoka);
		character.add(cara);
			
			
		int width = 100; 
		int height = 100;
		gorgu.setPreferredSize(new Dimension(width, height)); 
		luke.setPreferredSize(new Dimension(width, height)); 
		mando.setPreferredSize(new Dimension(width, height)); 
		bo.setPreferredSize(new Dimension(width, height)); 
		ahsoka.setPreferredSize(new Dimension(width, height)); 
		cara.setPreferredSize(new Dimension(width, height)); 
		
		charPage.add(character);
		cards.add(charPage, "2");
		JPanel play = new JPanel();
		JPanel abilities = new JPanel();
		JPanel playButtons = new JPanel(); 
	    cards.add(abilities, "3"); 
		playButtons.setLayout(new GridLayout(7,7)); 
		
		play.setLayout(new BorderLayout());
		JButton returnHome = new JButton("Home");
		returnHome.addActionListener(this);
		
		cards.add(play, "4");
		
		play.add(playButtons, BorderLayout.WEST); 
		JButton power = new JButton("Force Powers");
		pause = new JButton("Pause Game");
		pause.addActionListener(this); 
		power.addActionListener(this);
		playButtons.add(returnHome); 
		playButtons.add(power); 
		playButtons.add(pause);
		JPanel powerButtons = new JPanel();
		powerButtons.setLayout(new GridLayout(7, 1)); 
		abilities.setLayout(new BorderLayout()); 
		abilities.add(powerButtons);
		JButton Lightning2 = new JButton("Force Lightning 500 coins"); 
		JButton ForceDeflect = new JButton("Force deflect 100 coins"); 
		JButton Speed = new JButton("Force Speed 100 coins"); 
		JButton ForceCrush = new JButton("Force Push 100 coins"); 
		JButton Choke = new JButton("Force Choke  300 coins "); 
		JButton Shield = new JButton("Force Shield 500 coins"); 
		
		powerButtons.add(Lightning2); 
		powerButtons.add(ForceDeflect); 
		powerButtons.add(Speed); 
		powerButtons.add(ForceCrush); 
		powerButtons.add(Choke); 
		powerButtons.add(Shield); 

 
		Lightning2.addActionListener(this);
		
		
	 
	}
	
	
	
	public void mousePressed(MouseEvent e) {} // If mouse is pressed - Not doing anything at the moment 
	
	public void mouseReleased(MouseEvent e) {} // If mouse click is released -  Not doing anything at the moment
	
	public void mouseClicked(MouseEvent e) {} //If mouse is clicked - Not doing anything at the moment 
	
	public void mouseEntered(MouseEvent e) {}  // If the mouse enters the panel - Not doing anything at the moment 
	
	public void mouseExited(MouseEvent e) {} // If the mouse exits the panel - Not doing anything at the moment 
	
	
	public void mouseDragged(MouseEvent e) {} // If you are draggin anything Not doing anything at the moment 
	
	
	public void mouseMoved(MouseEvent e) {} // Checks if mouse is moving -  Not doing anything at the moment 
	
	
	
	public void keyPressed(KeyEvent e) /* This method dectects if the right or up key has been pressed. 
	It controlls the boolean that gives permission to move baby Yoda around*/
	{
		grabFocus();

		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			System.out.println(true);
			ifRight = true; 
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
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
			 cl.show(cards, "2"); 
			// System.out.println(true + " eee");
			 ifHome = false;
			 repaint();
			 //System.out.println(ifHome + "ddd");
		  }
		  
		  if (evt.getActionCommand().equals("Play"))
		  {
			 cl.show(cards, "4");
			 System.out.println(true + " eee");
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
		  
		  if (evt.getActionCommand().equals("Force Powers"))
		  {
			 //System.out.println("hi");
		     cl.show(cards, "3");
			 stop = true; 
			 ifHome = false;
		     ifGame = false; 
		     repaint();
		     
		  }
		  
		  if (evt.getActionCommand().equals("Force Lightning 500 coins"))
		     {
				 //System.out.println("hi");
				 cl.show(cards, "4");
				 stop = false; 
				 ifHome = false;
				 ifGame = true; 
				 coins = coins - 500; 
				 forceLightningClicked = true; 
				 repaint();
		     }
		  if(evt.getActionCommand().equals("Pause Game"))
		   {
				 System.out.println("hi");
				 cl.show(cards, "4");
		
				 ifHome = false;
				 ifGame = true; 
				 pause.setText("Resume");
				 stop = !(stop); 
				 repaint();
		    }
		  if(evt.getActionCommand().equals("Resume"))
		  {
				 System.out.println("hi");
				 cl.show(cards, "4");
				 pause.setText("Pause Game");
				 ifHome = false;
				 ifGame = true; 
				 stop = !(stop);  
				 repaint();
		   }
			  
		
	}
	
	class DroidMover implements ActionListener // This class contains the timer that moves the droid 
	{
		public void actionPerformed(ActionEvent e) // Moves the droid 
		{
			if(!(stop) && !(ifHome))
			{
				droidCorr[0][0] -= 4;  
				droidCorr[1][0] -= 4;  
				droidCorr[2][0] -= 4;  
				droidCorr[3][0] -= 4;  
				droidCorr[4][0] -= 4;  
			
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
			if(!(stop))
			{
				if(!(ifHome))
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
			}
			repaint();
			
			
		}
		
		public void changeLoc() // Constantly moves the blasts unless the they come in contact with babyYoda
		
		{
			
			int speed = 20;
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
			if(!(stop))
		    {
				
				if(ifRight == true)
				{
					//System.out.println(1);
					
					yodaX += 7; 
					ifRight = false; 
					repaint();
				}
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
			if(!stop)
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
			if(!stop)
			currentScore += 1; 
			
		}
		
	}

}
	
	

	

		
