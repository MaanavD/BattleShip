// ______________________________________________________________________________
// Final Project Game, ICS208
// January 18th,2015, Maanav Dalal
// BattleShip - Two players
// Click the resize button to aid in seeing screens properly, as many won't resize correctly
// Current bugs: Reset isn't perfect, Missing AI

// Links to Pictures used:
//http://www.medinasound.com/wordpress/wp-content/uploads/2012/11/Battleship-logo-Banner.jpg - Title logo
//https://www.the-blueprints.com - for all ships in game
//http://youvscomputer.com/images/shiplayout.gif - for buttons and previews
//https://image.freepik.com/free-icon/rotate-circle_318-29870.png - rotate icon
//https://image.freepik.com/free-icon/pass-track-button_318-85681.png - pass button

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import sun.audio.*;
import java.io.*;
import java.io.FileInputStream.*;

public class BattleShip extends Applet implements ActionListener
{
    Panel p_card;
    Panel HomeSC, InstructionsSC, Player1SC, Player2SC;
    CardLayout cdLayout = new CardLayout ();
    int p1array[] [] = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    int p1enemy[] [] = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    String shipnames[] = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
    int p2array[] [] = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    int p2enemy[] [] = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    int instgiven = 0;

    JLabel p1title, etitle, p2title, e2title;

    JButton p1b[] = new JButton [100];
    JButton p1eb[] = new JButton [100];
    JButton rotate, rotate2;
    JButton p2b[] = new JButton [100];
    JButton p2eb[] = new JButton [100];

    //Player 1 Ships
    JButton Carrier = new JButton (createImageIcon ("Carrier.png"));
    JButton Battleship = new JButton (createImageIcon ("Battleship.png"));
    JButton Cruiser = new JButton (createImageIcon ("Cruiser.png"));
    JButton Submarine = new JButton (createImageIcon ("Submarine.png"));
    JButton Destroyer = new JButton (createImageIcon ("Destroyer.png"));
    //Player 2 Ships
    JButton Carrier2 = new JButton (createImageIcon ("Carrier.png"));
    JButton Battleship2 = new JButton (createImageIcon ("Battleship.png"));
    JButton Cruiser2 = new JButton (createImageIcon ("Cruiser.png"));
    JButton Submarine2 = new JButton (createImageIcon ("Submarine.png"));
    JButton Destroyer2 = new JButton (createImageIcon ("Destroyer.png"));
    int shipscheck[] = {0, 0, 0, 0, 0};
    JButton Pass, Pass2;

    int home = 1;


    char selectedship = 'n';
    //int numdots[] = {0, 5, 4, 3, 3, 2};

    int checkcounter = 0;
    int stop = 0;
    int rotatecounter = 1;
    int turn = 0;
    String name1 = "Player 1";
    String name2 = "Player 2";
    char mode = 's';

    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	Homescreen ();
	Instructions ();
	Player1 ();
	Player2 ();
	resize (500, 450);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    public void Homescreen ()
    {
	HomeSC = new Panel ();
	HomeSC.setBackground (new Color (120, 120, 120));
	JLabel title = new JLabel (createImageIcon ("bship.jpg"));
	JButton inst = new JButton ("Instructions"); //Resize & Add Stuff to Buttons
	inst.setActionCommand ("instructionscall");
	inst.addActionListener (this);
	inst.setPreferredSize (new Dimension (150, 100));
	inst.setBackground (new Color (51, 98, 106));
	inst.setFont (new Font ("Corbel", Font.BOLD, 20));
	JButton play = new JButton ("Play");
	play.setActionCommand ("initializegame");
	play.addActionListener (this);
	play.setPreferredSize (new Dimension (150, 100));
	play.setBackground (new Color (51, 98, 106));
	play.setFont (new Font ("Corbel", Font.BOLD, 20));
	HomeSC.add (title);
	HomeSC.add (inst);
	HomeSC.add (play);
	p_card.add ("1", HomeSC);
    }


    public void Instructions ()
    {
	InstructionsSC = new Panel ();
	InstructionsSC.setBackground (new Color (120, 120, 120));
	JLabel title = new JLabel ("Instructions for BattleShip");
	title.setFont (new Font ("Corbel", Font.BOLD, 45));
	JLabel obj = new JLabel ("The objective of this game is to sink all 5 of your opponent's ships.");
	obj.setFont (new Font ("Corbel", Font.PLAIN, 20));
	JButton start = new JButton ("Starting the Game");
	start.setActionCommand ("startinst");
	start.addActionListener (this);
	start.setPreferredSize (new Dimension (150, 100));
	start.setBackground (new Color (106, 139, 146));
	JButton gameplay = new JButton ("Gameplay");
	gameplay.setActionCommand ("gameplayinst");
	gameplay.addActionListener (this);
	gameplay.setPreferredSize (new Dimension (150, 100));
	gameplay.setBackground (new Color (106, 139, 146));
	JButton back = new JButton ("Back");
	back.setActionCommand ("home");
	back.addActionListener (this);
	back.setPreferredSize (new Dimension (150, 100));
	back.setBackground (new Color (51, 98, 106));
	JPanel Shipspanel = new JPanel (new FlowLayout (FlowLayout.CENTER));
	Shipspanel.setBackground (new Color (51, 98, 106));
	JPanel buttons = new JPanel (new FlowLayout (FlowLayout.CENTER));
	JLabel key = new JLabel ("Legend");
	key.setFont (new Font ("Segoe UI", Font.BOLD, 50));
	JLabel ships = new JLabel ("The following are all the ships in the game. Click one to see information about it");
	JButton CarrierInst = new JButton (createImageIcon ("Carrier.png"));
	CarrierInst.setActionCommand ("cInst");
	CarrierInst.addActionListener (this);
	CarrierInst.setBackground (new Color (106, 139, 146));

	JButton BattleshipInst = new JButton (createImageIcon ("Battleship.png"));
	BattleshipInst.setActionCommand ("bInst");
	BattleshipInst.addActionListener (this);
	BattleshipInst.setBackground (new Color (106, 139, 146));

	JButton CruiserInst = new JButton (createImageIcon ("Cruiser.png"));
	CruiserInst.setActionCommand ("crInst");
	CruiserInst.addActionListener (this);
	CruiserInst.setBackground (new Color (106, 139, 146));

	JButton SubmarineInst = new JButton (createImageIcon ("Submarine.png"));
	SubmarineInst.setActionCommand ("sInst");
	SubmarineInst.addActionListener (this);
	SubmarineInst.setBackground (new Color (106, 139, 146));
	JButton DestroyerInst = new JButton (createImageIcon ("Destroyer.png"));
	DestroyerInst.setActionCommand ("dInst");
	DestroyerInst.addActionListener (this);
	DestroyerInst.setBackground (new Color (106, 139, 146));

	InstructionsSC.add (title);
	InstructionsSC.add (obj);
	buttons.setBackground (new Color (51, 98, 106));
	buttons.add (start);
	buttons.add (gameplay);

	Shipspanel.add (CarrierInst);
	Shipspanel.add (BattleshipInst);
	Shipspanel.add (CruiserInst);
	Shipspanel.add (SubmarineInst);
	Shipspanel.add (DestroyerInst);
	InstructionsSC.add (buttons);
	InstructionsSC.add (back);
	InstructionsSC.add (key);
	InstructionsSC.add (ships);
	InstructionsSC.add (Shipspanel);
	p_card.add ("2", InstructionsSC);
    }


    public void Player1 ()
    {
	Player1SC = new Panel ();
	Player1SC.setBackground (new Color (120, 120, 120));
	p1title = new JLabel (name1 + "'s Naval Base");

	String[] options = {"Starting", "Gameplay"};
	JComboBox insts = new JComboBox (options);
	insts.setSelectedIndex (0);
	insts.setActionCommand ("ibox");
	insts.addActionListener (this);
	insts.setPreferredSize (new Dimension (100, 100));
	etitle = new JLabel (name2 + "'s Naval Base");
	etitle.setForeground (Color.white);
	Panel p = new Panel (new GridLayout (10, 10));
	Panel g = new Panel (new GridLayout (10, 10));
	JPanel EnemyBase = new JPanel (new FlowLayout (FlowLayout.CENTER)); //For Organization
	EnemyBase.setBackground (new Color (0, 33, 71));
	JPanel FriendlyBase = new JPanel (new FlowLayout (FlowLayout.LEFT));
	FriendlyBase.setBackground (new Color (50, 145, 175));
	JPanel CommandC = new JPanel (new FlowLayout (FlowLayout.RIGHT));
	for (int i = 0 ; i < p1b.length ; i++)
	{
	    p1b [i] = new JButton (" ");
	    p1b [i].addActionListener (this);
	    p1b [i].setActionCommand ("a" + i);
	    p1b [i].setBorder (null);
	    p1b [i].setBorderPainted (false);
	    p1b [i].setPreferredSize (new Dimension (32, 32));
	    p.add (p1b [i]);

	    p1eb [i] = new JButton (" ");
	    p1eb [i].addActionListener (this);
	    p1eb [i].setActionCommand ("b" + i);
	    p1eb [i].setBorder (null);
	    p1eb [i].setBorderPainted (false);
	    p1eb [i].setPreferredSize (new Dimension (32, 32));
	    g.add (p1eb [i]);
	}


	rotate = new JButton (createImageIcon ("rotate.png"));
	rotate.setActionCommand ("RotateP1");
	rotate.addActionListener (this);
	rotate.setPreferredSize (new Dimension (100, 100));
	rotate.setBackground (new Color (51, 98, 106));

	JButton reset = new JButton (createImageIcon ("reset.png"));
	reset.setPreferredSize (new Dimension (100, 100));
	reset.setActionCommand ("reset");
	reset.addActionListener (this);
	reset.setBackground (new Color (51, 98, 106));

	Pass = new JButton (createImageIcon ("crosshair.png"));
	Pass.setEnabled (false);
	Pass.setActionCommand ("pass");
	Pass.addActionListener (this);
	Pass.setBackground (new Color (51, 98, 106));
	Pass.setPreferredSize (new Dimension (100, 100));

	Panel f = new Panel (new GridLayout (2, 3));
	Panel e = new Panel (new GridLayout (1, 3));

	Carrier.setPreferredSize (new Dimension (100, 100));
	Carrier.setActionCommand ("5");
	Carrier.addActionListener (this);
	Carrier.setBackground (new Color (106, 139, 146));
	Battleship.setPreferredSize (new Dimension (100, 100));
	Battleship.setActionCommand ("4");
	Battleship.addActionListener (this);
	Battleship.setBackground (new Color (106, 139, 146));
	Cruiser.setPreferredSize (new Dimension (100, 100));
	Cruiser.setActionCommand ("3a");
	Cruiser.addActionListener (this);
	Cruiser.setBackground (new Color (106, 139, 146));
	Submarine.setPreferredSize (new Dimension (100, 100));
	Submarine.setActionCommand ("3b");
	Submarine.addActionListener (this);
	Submarine.setBackground (new Color (106, 139, 146));
	Destroyer.setPreferredSize (new Dimension (100, 100));
	Destroyer.setActionCommand ("2");
	Destroyer.addActionListener (this);
	Destroyer.setBackground (new Color (106, 139, 146));

	f.setBackground (new Color (120, 120, 120));

	f.add (Carrier);
	f.add (Battleship);
	f.add (Cruiser);
	f.add (Submarine);
	f.add (Destroyer);
	e.add (reset);
	e.add (rotate);
	e.add (Pass);
	f.add (insts);
	EnemyBase.add (etitle);
	EnemyBase.add (g);
	FriendlyBase.add (p1title);
	FriendlyBase.add (p);
	Player1SC.add (EnemyBase);
	Player1SC.add (f);
	Player1SC.add (FriendlyBase);
	Player1SC.add (e);
	//Player1SC.add (insts);
	redraw ();
	p_card.add ("3", Player1SC);
    }


    public void Player2 ()
    {
	Player2SC = new Panel ();
	Player2SC.setBackground (new Color (120, 120, 120));
	redraw ();
	p2title = new JLabel (name2 + "'s Naval Base");

	String[] options = {"Starting", "Gameplay"};
	JComboBox insts = new JComboBox (options);
	insts.setSelectedIndex (0);
	insts.setActionCommand ("ibox");
	insts.addActionListener (this);
	insts.setPreferredSize (new Dimension (100, 100));

	e2title = new JLabel (name1 + "'s Naval Base");
	e2title.setForeground (Color.white);
	Panel p = new Panel (new GridLayout (10, 10));
	Panel g = new Panel (new GridLayout (10, 10));
	JPanel EnemyBase = new JPanel (new FlowLayout (FlowLayout.CENTER)); //For Organization
	EnemyBase.setBackground (new Color (0, 33, 71));
	JPanel FriendlyBase = new JPanel (new FlowLayout (FlowLayout.LEFT));
	FriendlyBase.setBackground (new Color (50, 145, 175));
	JPanel CommandC = new JPanel (new FlowLayout (FlowLayout.RIGHT));
	for (int i = 0 ; i < p2b.length ; i++)
	{
	    p2b [i] = new JButton (" ");
	    p2b [i].addActionListener (this);
	    p2b [i].setActionCommand ("a" + i);
	    p2b [i].setBorder (null);
	    p2b [i].setBorderPainted (false);
	    p2b [i].setPreferredSize (new Dimension (32, 32));
	    p.add (p2b [i]);

	    p2eb [i] = new JButton (" ");
	    p2eb [i].addActionListener (this);
	    p2eb [i].setActionCommand ("b" + i);
	    p2eb [i].setBorder (null);
	    p2eb [i].setBorderPainted (false);
	    p2eb [i].setPreferredSize (new Dimension (32, 32));
	    g.add (p2eb [i]);
	}


	rotate2 = new JButton (createImageIcon ("rotate.png"));
	rotate2.setActionCommand ("RotateP2");
	rotate2.addActionListener (this);
	rotate2.setPreferredSize (new Dimension (100, 100));
	rotate2.setBackground (new Color (51, 98, 106));
	JButton reset2 = new JButton (createImageIcon ("reset.png"));
	reset2.setPreferredSize (new Dimension (100, 100));
	reset2.setActionCommand ("reset");
	reset2.addActionListener (this);
	reset2.setBackground (new Color (51, 98, 106));
	Pass2 = new JButton (createImageIcon ("crosshair.png"));
	Pass2.setEnabled (false);
	Pass2.setActionCommand ("pass2");
	Pass2.addActionListener (this);
	Pass2.setPreferredSize (new Dimension (100, 100));
	Pass2.setBackground (new Color (51, 98, 106));
	Panel f = new Panel (new GridLayout (2, 3));
	Panel e = new Panel (new GridLayout (1, 3));

	Carrier2.setPreferredSize (new Dimension (100, 100));
	Carrier2.setActionCommand ("5");
	Carrier2.addActionListener (this);
	Carrier2.setBackground (new Color (106, 139, 146));
	Battleship2.setPreferredSize (new Dimension (100, 100));
	Battleship2.setActionCommand ("4");
	Battleship2.addActionListener (this);
	Battleship2.setBackground (new Color (106, 139, 146));
	Cruiser2.setPreferredSize (new Dimension (100, 100));
	Cruiser2.setActionCommand ("3a");
	Cruiser2.addActionListener (this);
	Cruiser2.setBackground (new Color (106, 139, 146));
	Submarine2.setPreferredSize (new Dimension (100, 100));
	Submarine2.setActionCommand ("3b");
	Submarine2.addActionListener (this);
	Submarine2.setBackground (new Color (106, 139, 146));
	Destroyer2.setPreferredSize (new Dimension (100, 100));
	Destroyer2.setActionCommand ("2");
	Destroyer2.addActionListener (this);
	Destroyer2.setBackground (new Color (106, 139, 146));

	f.setBackground (new Color (120, 120, 120));

	f.add (Carrier2);
	f.add (Battleship2);
	f.add (Cruiser2);
	f.add (Submarine2);
	f.add (Destroyer2);
	e.add (reset2);
	e.add (rotate2);
	e.add (Pass2);
	f.add (insts);
	EnemyBase.add (e2title);
	EnemyBase.add (g);
	FriendlyBase.add (p2title);
	FriendlyBase.add (p);

	Player2SC.add (EnemyBase);
	Player2SC.add (f);
	Player2SC.add (FriendlyBase);
	Player2SC.add (e);
	redraw2 ();
	p_card.add ("4", Player2SC);
    }


    public void shipdraw (int i, char which)
    {
	int x = i / 10;
	int y = i % 10;
	if (p1array [x] [y] != 1)
	{
	    JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
	}
	else if (turn == 0)
	{
	    if (rotatecounter % 2 == 0 && which == 'a')
	    {
		if (selectedship == 'a' && x > 1 && x < 8)
		{
		    if (p1array [x + 1] [y] != 1 || p1array [x + 2] [y] != 1 || p1array [x - 1] [y] != 1 || p1array [x - 2] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 53;
			p1array [x + 1] [y] = 54;
			p1array [x + 2] [y] = 55;
			p1array [x - 1] [y] = 52;
			p1array [x - 2] [y] = 51;
			Carrier.setEnabled (false);
			shipscheck [0] = 1;
		    }
		}

		else if (selectedship == 'b' && x > 0 && x < 8)
		{
		    if (p1array [x + 1] [y] != 1 || p1array [x + 2] [y] != 1 || p1array [x - 1] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 42;
			p1array [x + 1] [y] = 43;
			p1array [x + 2] [y] = 44;
			p1array [x - 1] [y] = 41;
			Battleship.setEnabled (false);
			shipscheck [1] = 1;
		    }
		}
		else if (selectedship == 'c' && x > 0 && x < 9)
		{
		    if (p1array [x + 1] [y] != 1 || p1array [x - 1] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 32;
			p1array [x + 1] [y] = 33;
			p1array [x - 1] [y] = 31;
			Cruiser.setEnabled (false);
			shipscheck [2] = 1;
		    }
		}
		else if (selectedship == 'd' && x > 0 && x < 9)
		{
		    if (p1array [x + 1] [y] != 1 || p1array [x - 1] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 22;
			p1array [x + 1] [y] = 23;
			p1array [x - 1] [y] = 21;
			Submarine.setEnabled (false);
			shipscheck [3] = 1;
		    }
		}
		else if (selectedship == 'e' && x < 9)
		{
		    if (p1array [x + 1] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 11;
			p1array [x + 1] [y] = 12;
			Destroyer.setEnabled (false);
			shipscheck [4] = 1;
		    }
		}
		else
		    JOptionPane.showMessageDialog (null, "Sorry, your ship wouldn't fit on the grid if you placed it there!", "Misplay - Trying to sneak around enemy lines!", JOptionPane.ERROR_MESSAGE);

	    }
	    else if (which == 'a')
	    {
		if (selectedship == 'a' && y > 1 && y < 8)
		{
		    if (p1array [x] [y + 1] != 1 || p1array [x] [y + 2] != 1 || p1array [x] [y - 1] != 1 || p1array [x] [y - 2] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 253;
			p1array [x] [y + 1] = 254;
			p1array [x] [y + 2] = 255;
			p1array [x] [y - 1] = 252;
			p1array [x] [y - 2] = 251;
			Carrier.setEnabled (false);
			shipscheck [0] = 1;
		    }
		}
		else if (selectedship == 'b' && y > 0 && y < 8)
		{
		    if (p1array [x] [y + 1] != 1 || p1array [x] [y + 2] != 1 || p1array [x] [y - 1] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 242;
			p1array [x] [y + 1] = 243;
			p1array [x] [y + 2] = 244;
			p1array [x] [y - 1] = 241;
			Battleship.setEnabled (false);
			shipscheck [1] = 1;
		    }
		}
		else if (selectedship == 'c' && y > 0 && y < 9)
		{
		    if (p1array [x] [y + 1] != 1 || p1array [x] [y - 1] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 232;
			p1array [x] [y + 1] = 233;
			p1array [x] [y - 1] = 231;
			Cruiser.setEnabled (false);
			shipscheck [2] = 1;
		    }
		}
		else if (selectedship == 'd' && y > 0 && y < 9)
		{
		    if (p1array [x] [y + 1] != 1 || p1array [x] [y - 1] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 222;
			p1array [x] [y + 1] = 223;
			p1array [x] [y - 1] = 221;
			Submarine.setEnabled (false);
			shipscheck [3] = 1;
		    }
		}
		else if (selectedship == 'e' && y < 9)
		{
		    if (p1array [x] [y + 1] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p1array [x] [y] = 211;
			p1array [x] [y + 1] = 212;
			Destroyer.setEnabled (false);
			shipscheck [4] = 1;
		    }
		}
		else
		    JOptionPane.showMessageDialog (null, "Sorry, your ship wouldn't fit on the grid if you placed it there!", "Misplay - Trying to sneak around enemy lines!", JOptionPane.ERROR_MESSAGE);

	    }


	}


	redrawfriendlyboat ();
	shipchecker ();
	selectedship = 'n';
    }


    public void shipdraw2 (int i, char which)
    {
	int x = i / 10;
	int y = i % 10;
	if (p2array [x] [y] != 1)
	{
	    JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
	}
	else if (turn == 1)
	{
	    if (rotatecounter % 2 == 0 && which == 'a')
	    {
		if (selectedship == 'a' && x > 1 && x < 8)
		{
		    if (p2array [x + 1] [y] != 1 || p2array [x + 2] [y] != 1 || p2array [x - 1] [y] != 1 || p2array [x - 2] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 53;
			p2array [x + 1] [y] = 54;
			p2array [x + 2] [y] = 55;
			p2array [x - 1] [y] = 52;
			p2array [x - 2] [y] = 51;
			Carrier2.setEnabled (false);
			shipscheck [0] = 1;
		    }
		}

		else if (selectedship == 'b' && x > 0 && x < 8)
		{
		    if (p2array [x + 1] [y] != 1 || p2array [x + 2] [y] != 1 || p2array [x - 1] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 42;
			p2array [x + 1] [y] = 43;
			p2array [x + 2] [y] = 44;
			p2array [x - 1] [y] = 41;
			Battleship2.setEnabled (false);
			shipscheck [1] = 1;
		    }
		}
		else if (selectedship == 'c' && x > 0 && x < 9)
		{
		    if (p2array [x + 1] [y] != 1 || p2array [x - 1] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 32;
			p2array [x + 1] [y] = 33;
			p2array [x - 1] [y] = 31;
			Cruiser2.setEnabled (false);
			shipscheck [2] = 1;
		    }
		}
		else if (selectedship == 'd' && x > 0 && x < 9)
		{
		    if (p2array [x + 1] [y] != 1 || p2array [x - 1] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 22;
			p2array [x + 1] [y] = 23;
			p2array [x - 1] [y] = 21;
			Submarine2.setEnabled (false);
			shipscheck [3] = 1;
		    }
		}
		else if (selectedship == 'e' && x < 9)
		{
		    if (p2array [x + 1] [y] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 11;
			p2array [x + 1] [y] = 12;
			Destroyer2.setEnabled (false);
			shipscheck [4] = 1;
		    }
		}
		else if (p2array [x] [y] != 1)
		    JOptionPane.showMessageDialog (null, "Sorry, your ship wouldn't fit on the grid if you placed it there!", "Misplay - Trying to sneak around enemy lines!", JOptionPane.ERROR_MESSAGE);

		else
		    JOptionPane.showMessageDialog (null, "Sorry, you need to choose a ship before trying to place one!", "Misplay!", JOptionPane.ERROR_MESSAGE);

	    }
	    else if (which == 'a')
	    {
		if (selectedship == 'a' && y > 1 && y < 8)
		{
		    if (p2array [x] [y + 1] != 1 || p2array [x] [y + 2] != 1 || p2array [x] [y - 1] != 1 || p2array [x] [y - 2] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 253;
			p2array [x] [y + 1] = 254;
			p2array [x] [y + 2] = 255;
			p2array [x] [y - 1] = 252;
			p2array [x] [y - 2] = 251;
			Carrier2.setEnabled (false);
			shipscheck [0] = 1;
		    }
		}
		else if (selectedship == 'b' && y > 1 && y < 8)
		{
		    if (p2array [x] [y + 1] != 1 || p2array [x] [y + 2] != 1 || p2array [x] [y - 1] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 242;
			p2array [x] [y + 1] = 243;
			p2array [x] [y + 2] = 244;
			p2array [x] [y - 1] = 241;
			Battleship2.setEnabled (false);
			shipscheck [1] = 1;
		    }
		}
		else if (selectedship == 'c' && y > 0 && y < 9)
		{
		    if (p2array [x] [y + 1] != 1 || p2array [x] [y - 1] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 232;
			p2array [x] [y + 1] = 233;
			p2array [x] [y - 1] = 231;
			Cruiser2.setEnabled (false);
			shipscheck [2] = 1;
		    }
		}
		else if (selectedship == 'd' && y > 0 && y < 9)
		{
		    if (p2array [x] [y + 1] != 1 || p2array [x] [y - 1] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 222;
			p2array [x] [y + 1] = 223;
			p2array [x] [y - 1] = 221;
			Submarine2.setEnabled (false);
			shipscheck [3] = 1;
		    }
		}
		else if (selectedship == 'e' && y < 9)
		{
		    if (p2array [x] [y + 1] != 1)
			JOptionPane.showMessageDialog (null, "Sorry, you can't place a ship on top of another ship!", "Misplay", JOptionPane.ERROR_MESSAGE);
		    else
		    {
			p2array [x] [y] = 211;
			p2array [x] [y + 1] = 212;
			Destroyer2.setEnabled (false);
			shipscheck [4] = 1;
		    }
		}
		else if (p2array [x] [y] != 1)
		    JOptionPane.showMessageDialog (null, "Sorry, your ship wouldn't fit on the grid if you placed it there!", "Misplay - Trying to sneak around enemy lines!", JOptionPane.ERROR_MESSAGE);

		else
		    JOptionPane.showMessageDialog (null, "Sorry, you need to choose a ship before trying to place one!", "Misplay!", JOptionPane.ERROR_MESSAGE);

	    }


	}


	redrawfriendlyboat ();
	shipchecker ();
	selectedship = 'n';
    }


    public void redrawfriendlyboat ()
    {
	int move = 0;
	for (int i = 0 ; i < 10 ; i++)
	{
	    for (int j = 0 ; j < 10 ; j++)
	    {
		move = i * 10 + j;
		if (turn == 0)
		{
		    p1b [move].setIcon (createImageIcon (p1array [i] [j] + ".jpg"));
		}
		else
		{
		    p2b [move].setIcon (createImageIcon (p2array [i] [j] + ".jpg"));

		}
	    }
	}
    }


    public void shipchecker ()
    {
	if (checkcounter == 0 && shipscheck [0] == 1 && shipscheck [1] == 1 && shipscheck [2] == 1 && shipscheck [3] == 1 && shipscheck [4] == 1)
	{
	    if (turn == 0)
	    {
		JOptionPane.showMessageDialog (null, "Good Job, you're on the first step to victory! Pass your turn by clicking the pass button so that your opponnent can play their turn.", "All Done setting up!",
			JOptionPane.INFORMATION_MESSAGE);
		checkcounter++;
		turndisable ();
		Pass.setEnabled (true);
	    }
	    else
	    {

		checkcounter++;
		turndisable ();
		Pass2.setEnabled (true);
	    }
	}

    }


    // public void checkfire (int i)
    // {
    //     int x = (i / 10);
    //     int y = (i % 10);
    //     if (p1enemy [x] [y] < 1700)
    //
    //         {
    //             if (turn == 0)
    //             {
    //                 p1enemy [x] [y] += 10000;
    //                 redraw ();
    //             }
    //             else
    //             {
    //                 p2enemy [x] [y] += 10000;
    //                 redraw2 ();
    //             }
    //         }
    //     for (int j = 0 ; j < p1enemy.length ; j++)
    //     {
    //         int a = (j / 10);
    //         int b = (j % 10);
    //         if ((p1enemy [a] [b] != p1enemy [x] [y]) && (p1enemy [a] [b] > 10000))
    //             p1enemy [a] [b] -= 10000;
    //
    //     }
    //     redraw ();
    //     redraw2 ();
    //     crosshair.setEnabled (true);
    //     crosshair2.setEnabled (true);
    //     current = i; //basically so i can use "i" in the fire method
    // }


    public void fire (int i)
    {

	int x = (i / 10);
	int y = (i % 10);
	if (turn == 0)
	{
	    if (p1enemy [x] [y] == 501 || p1enemy [x] [y] > 1000)
	    {
		JOptionPane.showMessageDialog (null, "You've already fired there, you can't again!", "Can't Refire!", JOptionPane.WARNING_MESSAGE);
	    }
	    else
	    {
		soundEffect ("cannon");
		if (p2array [x] [y] > 1 && p2array [x] [y] < 5000)
		{
		    p1enemy [x] [y] += 1000;
		    p2array [x] [y] += 1000;
		}
		else
		{

		    p1enemy [x] [y] += 500;
		    p2array [x] [y] += 500;
		}
		stop = 1;
		redraw2 ();
		redraw ();
	    }


	}


	else
	{

	    if (p2enemy [x] [y] == 501 || p2enemy [x] [y] > 1000)
	    {
		JOptionPane.showMessageDialog (null, "You've already fired there, you can't again!", "Can't Refire!", JOptionPane.WARNING_MESSAGE);
	    }
	    else
	    {
		soundEffect ("cannon");
		if (p1array [x] [y] > 1 && p1array [x] [y] < 5000)
		{
		    p2enemy [x] [y] += 1000;
		    p1array [x] [y] += 1000;
		}
		else
		{
		    p2enemy [x] [y] += 500;
		    p1array [x] [y] += 500;
		}
		stop = 1;
		redraw2 ();
		redraw ();

	    }

	}
    }


    public void winning ()
    {
	int counter1 = 0;
	int counter2 = 0;

	for (int i = 0 ; i < p1enemy.length ; i++)
	{
	    for (int j = 0 ; j < p1enemy.length ; j++)
	    {
		if (p1enemy [i] [j] > 1000)
		    counter1++;
	    }
	}
	if (counter1 > 16)
	{
	    home = JOptionPane.showConfirmDialog (null, "Now that the game is over, do you want to go back to home?", "" + name2 + " won the game!", JOptionPane.YES_NO_OPTION);
	}
	counter1 = 0;
	for (int i = 0 ; i < p2enemy.length ; i++)
	{
	    for (int j = 0 ; j < p2enemy.length ; j++)
	    {
		if (p2enemy [i] [j] > 1000)
		    counter2++;
	    }
	}
	counter2 = 0;
	if (counter2 > 16)
	{
	    home = JOptionPane.showConfirmDialog (null, "Now that the game is over, do you want to go back to home?", "" + name1 + " won the game!", JOptionPane.YES_NO_OPTION);
	}

    }


    public void redraw ()
    {
	int move = 0;
	for (int i = 0 ; i < 10 ; i++)
	{
	    for (int j = 0 ; j < 10 ; j++)
	    {
		int x = (move / 10);
		int y = (move % 10);
		p1b [move].setIcon (createImageIcon (p1array [i] [j] + ".jpg"));
		p1eb [move].setIcon (createImageIcon (p1enemy [i] [j] + ".jpg"));
		// if (p1enemy [x] [y] > 500)
		//     p1eb [move].setEnabled (false);
		move++;

	    }
	}
    }


    public void redraw2 ()
    {
	int move = 0;
	for (int i = 0 ; i < 10 ; i++)
	{
	    for (int j = 0 ; j < 10 ; j++)
	    {
		int x = (move / 10);
		int y = (move % 10);
		p2b [move].setIcon (createImageIcon (p2array [i] [j] + ".jpg"));
		p2eb [move].setIcon (createImageIcon (p2enemy [i] [j] + ".jpg"));
		// if (p2enemy [x] [y] > 500)
		//     p2eb [move].setEnabled (false);
		move++;

	    }
	}
    }


    public void rotate ()
    {
	if (turn == 0)
	{
	    if ((rotatecounter % 2) == 0)
	    {
		Carrier.setIcon (createImageIcon ("Carrier.png"));
		Battleship.setIcon (createImageIcon ("Battleship.png"));
		Cruiser.setIcon (createImageIcon ("Cruiser.png"));
		Submarine.setIcon (createImageIcon ("Submarine.png"));
		Destroyer.setIcon (createImageIcon ("Destroyer.png"));
	    }
	    else
	    {
		Carrier.setIcon (createImageIcon ("Carrier2.png"));
		Battleship.setIcon (createImageIcon ("Battleship2.png"));
		Cruiser.setIcon (createImageIcon ("Cruiser2.png"));
		Submarine.setIcon (createImageIcon ("Submarine2.png"));
		Destroyer.setIcon (createImageIcon ("Destroyer2.png"));
	    }
	}


	else
	{
	    if ((rotatecounter % 2) == 0)
	    {
		Carrier2.setIcon (createImageIcon ("Carrier.png"));
		Battleship2.setIcon (createImageIcon ("Battleship.png"));
		Cruiser2.setIcon (createImageIcon ("Cruiser.png"));
		Submarine2.setIcon (createImageIcon ("Submarine.png"));
		Destroyer2.setIcon (createImageIcon ("Destroyer.png"));
	    }
	    else
	    {
		Carrier2.setIcon (createImageIcon ("Carrier2.png"));
		Battleship2.setIcon (createImageIcon ("Battleship2.png"));
		Cruiser2.setIcon (createImageIcon ("Cruiser2.png"));
		Submarine2.setIcon (createImageIcon ("Submarine2.png"));
		Destroyer2.setIcon (createImageIcon ("Destroyer2.png"));
	    }
	}


	rotatecounter++;
    }


    public void resetgame ()
    {
	cdLayout.show (p_card, "1");
	for (int i = 0 ; i < p1array.length ; i++)
	{
	    for (int j = 0 ; j < p1array.length ; j++)
	    {
		p1array [i] [j] = 1;
		p2array [i] [j] = 1;
		p1enemy [i] [j] = 1;
		p2enemy [i] [j] = 1;
	    }
	}
	for (int k = 0 ; k < shipscheck.length ; k++)
	{
	    shipscheck [k] = 0;
	}
	home = 1;
	checkcounter = 0;
	stop = 0;
	rotatecounter = 1;
	turn = 0;
	name1 = "Player 1";
	name2 = "Player 2";
	mode = 's';
	Carrier.setEnabled (true);
	Carrier2.setEnabled (true);
	turn = 0;
	Battleship.setEnabled (true);
	Battleship2.setEnabled (true);

	Cruiser.setEnabled (true);
	Cruiser2.setEnabled (true);

	Submarine.setEnabled (true);
	Submarine2.setEnabled (true);

	Destroyer.setEnabled (true);
	Destroyer2.setEnabled (true);

	rotate.setEnabled (true);
	rotate2.setEnabled (true);
	resize (500, 450);
	redraw ();
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("instructionscall"))
	{
	    resize (550, 450);
	    cdLayout.show (p_card, "2");
	}
	else if (e.getActionCommand ().equals ("home"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("RotateP1") || e.getActionCommand ().equals ("RotateP2")) //Redraw all of the ships, rotated
	{
	    rotate ();
	}


	else if (e.getActionCommand ().equals ("initializegame"))
	{
	    soundEffect ("bsm.wav");
	    name1 = JOptionPane.showInputDialog ("Please enter the name of the first player.");
	    if (name1 == (null) || name1.equals (""))
	    {
		name1 = "Player 1";
	    }
	    name2 = JOptionPane.showInputDialog ("Please enter the name of the second player.");
	    if (name2 == (null) || name2.equals (""))
	    {
		name2 = "Player 2";
	    }
	    p1title.setText (name1 + "'s Naval Base");
	    etitle.setText (name2 + "'s Naval Base");
	    e2title.setText (name1 + "'s Naval Base");
	    p2title.setText (name2 + "'s Naval Base");

	    JOptionPane.showMessageDialog (null, "Ensure " + name2 + " cannot see the screen before clicking OK.", "" + name1 + "- Initialize Board", JOptionPane.WARNING_MESSAGE);
	    resize (800, 675);
	    cdLayout.show (p_card, "3");
	}


	else if (e.getActionCommand ().equals ("pass") || e.getActionCommand ().equals ("pass2"))
	{
	    if (turn == 0)
	    {

		cdLayout.show (p_card, "4");
		turn = 1;
		Pass.setEnabled (false);
		stop = 0;
	    }


	    else
	    {

		cdLayout.show (p_card, "3");
		turn = 0;
		Pass2.setEnabled (false);

		stop = 0;

	    }
	    rotate ();

	}


	else if (e.getActionCommand ().equals ("startinst"))
	{
	    JOptionPane.showMessageDialog (null, "* * * Prepare For Battle! * * * \n \n"
		    + "Start by placing each of your five ships on the lower part (your part) of the battlefield.\n"
		    + "Use the rotate button to choose between vertical and horizontal, both orientations are fine!\n"
		    + "Your ships may NOT overlap\n"
		    + "Once your ships are placed, you may not reset their position for the remainder of the game.\n \n", "Starting Instructions", JOptionPane.QUESTION_MESSAGE);
	}


	else if (e.getActionCommand ().equals ("gameplayinst"))
	{
	    JOptionPane.showMessageDialog (null, "* * * The Battle Begins! * * * \n \n"
		    + "When it is your turn, you may click a position, then click lock in to fire at that position.\n" //Lock in or no?
		    + "Upon firing, you will be notified if you have hit a ship, or missed. \n"
		    + "If you hit a ship, you will be notified which one. Based on the key, your following shots should keep in mind the length of the ship\n"
		    + "(If you've hit a carrier, it's a length of five, so ensure to keep firing till it's sunk)\n"
		    + "Ensure to have fun while playing!\n \n", "Gameplay Instructions", JOptionPane.QUESTION_MESSAGE);
	}


	else if (e.getActionCommand ().equals ("cInst"))
	{
	    JOptionPane.showMessageDialog (null, "* * * Carrier Information* * * \n \n"
		    + "The Carrier is a ship 5 units long.\n"
		    + "It places two blocks above and two below when clicked (depending on rotation).\n", "Gameplay Instructions", JOptionPane.QUESTION_MESSAGE);
	}
	else if (e.getActionCommand ().equals ("bInst"))
	{
	    JOptionPane.showMessageDialog (null, "* * * Carrier Information* * * \n \n"
		    + "The Battleship is a ship 4 units long.\n"
		    + "It places one block above and two below when clicked (depending on rotation).\n", "Gameplay Instructions", JOptionPane.QUESTION_MESSAGE);
	}
	else if (e.getActionCommand ().equals ("crInst"))
	{
	    JOptionPane.showMessageDialog (null, "* * * Carrier Information* * * \n \n"
		    + "The Cruiser is a ship 3 units long.\n"
		    + "It places one block above and one below when clicked (depending on rotation).\n", "Gameplay Instructions", JOptionPane.QUESTION_MESSAGE);
	}
	else if (e.getActionCommand ().equals ("sInst"))
	{
	    JOptionPane.showMessageDialog (null, "* * * Carrier Information* * * \n \n"
		    + "The Submarine is a ship 3 units long.\n"
		    + "It places one block above and one below when clicked (depending on rotation).\n", "Gameplay Instructions", JOptionPane.QUESTION_MESSAGE);
	}
	else if (e.getActionCommand ().equals ("dInst"))
	{
	    JOptionPane.showMessageDialog (null, "* * * Carrier Information* * * \n \n"
		    + "The Destroyer is a ship 2 units long.\n"
		    + "It places one block BELOW itself when clicked(depending on rotation).\n", "Gameplay Instructions", JOptionPane.QUESTION_MESSAGE);
	}


	else if (e.getActionCommand ().equals ("5"))
	{
	    selectedship = 'a';
	}


	else if (e.getActionCommand ().equals ("4"))
	{
	    selectedship = 'b';
	}


	else if (e.getActionCommand ().equals ("3a"))
	{
	    selectedship = 'c';
	}


	else if (e.getActionCommand ().equals ("3b"))
	{
	    selectedship = 'd';
	}


	else if (e.getActionCommand ().equals ("2"))
	{
	    selectedship = 'e';
	}

	else if (e.getActionCommand ().equals ("null"))
	{
	    selectedship = 'e';
	}
	else if (e.getActionCommand ().equals ("reset"))
	{
	    int reset = JOptionPane.showConfirmDialog (null, "Are you sure you want to reset?", "Reset?!", JOptionPane.YES_NO_OPTION);
	    if (reset == 0)
		resetgame ();
	}
	else if (e.getActionCommand ().equals ("ibox"))
	{
	    JComboBox cb = (JComboBox) e.getSource ();
	    String people_s = (String) cb.getSelectedItem ();
	    if (people_s.equals ("Starting Instructions"))
	    {
		JOptionPane.showMessageDialog (null, "* * * Prepare For Battle! * * * \n \n"
			+ "Start by placing each of your five ships on the lower part (your part) of the battlefield.\n"
			+ "Use the rotate button to choose between vertical and horizontal, both orientations are fine!\n"
			+ "Your ships may NOT overlap\n"
			+ "Once your ships are placed, you may not reset their position for the remainder of the game.\n \n", "Starting Instructions", JOptionPane.QUESTION_MESSAGE);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "* * * The Battle Begins! * * * \n \n"
			+ "When it is your turn, you may click a position, then click lock in to fire at that position.\n" //Lock in or no?
			+ "Upon firing, you will be notified if you have hit a ship, or missed. \n"
			+ "If you hit a ship, you will be notified which one. Based on the key, your following shots should keep in mind the length of the ship\n"
			+ "(If you've hit a carrier, it's a length of five, so ensure to keep firing till it's sunk)\n"
			+ "Ensure to have fun while playing!\n \n", "Gameplay Instructions", JOptionPane.QUESTION_MESSAGE);
	    }
	}
	else
	{
	    int i = -1;
	    char which = 'e';
	    String s = e.getActionCommand ();
	    if (s.equals ("Crosshair"))
		selectedship = 'e';
	    else
	    {
		which = s.charAt (0);
		String rest = s.substring (1, s.length ());
		i = Integer.parseInt (rest);
	    }
	    if (mode == 's' && turn == 0 && !allDone ())
	    {
		shipdraw (i, which);
		if (allDone ())
		{
		    for (int j = 0 ; j < shipscheck.length ; j++)
			shipscheck [j] = 0;
		    //redraw();
		    //turn = 1;
		    turndisable ();
		    JOptionPane.showMessageDialog (null, "Ensure " + name1 + " cannot see the screen before clicking OK.", "Next Turn", JOptionPane.WARNING_MESSAGE);

		}
	    }
	    else if (mode == 's' && turn == 1 && !allDone ())

		shipdraw2 (i, which);
	    if (allDone ())
	    {
		for (int j = 0 ; j < shipscheck.length ; j++)
		    shipscheck [j] = 0;
		//redraw2();
		//turn = 0;
		Pass2.setEnabled (true);
		turndisable ();

		JOptionPane.showMessageDialog (null, "Good Job, you're on the first step to victory! Pass your turn by clicking the pass button so that your opponnent can play their turn.", "All Done setting up!",
			JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog (null, "Ensure " + name2 + " cannot see the screen before clicking OK.", "Next Turn", JOptionPane.WARNING_MESSAGE);
		rotate.setEnabled (false);
		rotate2.setEnabled (false);
		mode = 'p';



	    }
	    else if (mode == 'p' && turn == 0)
	    {
		// rotate.setEnabled (false);
		// rotate2.setEnabled (false);
		if (stop == 1)
		{
		    JOptionPane.showMessageDialog (null, "You've already fired! Now, it's time for the next player to make their move", "Next Turn", JOptionPane.WARNING_MESSAGE);
		    Pass.setEnabled (true);
		}
		else
		    fire (i);
		Pass.setEnabled (true);
	    }
	    // else if (mode == 'p' && turn == 0)
	    // {
	    //     checkfire (i);
	    // }
	    else if (mode == 'p' && turn == 1)
	    {
		if (stop == 1)
		{
		    JOptionPane.showMessageDialog (null, "You've already fired! Now, it's time for the next player to make their move", "Next Turn", JOptionPane.WARNING_MESSAGE);
		    Pass2.setEnabled (true);
		}
		else
		    fire (i);
		Pass2.setEnabled (true);
	    }
	    // else if (mode == 'p' && turn == 1)
	    // {
	    //     checkfire (i);
	    // }


	}


	see ();
	winning ();
	if (home == 0)
	    resetgame ();

    }


/*    public void see ()
    {
	System.out.println ("------------------Player 1 Base------------------");
	for (int i = 0 ; i < p1array.length ; i++)
	{
	    for (int j = 0 ; j < p1array.length ; j++)
	    {
		System.out.print (p1array [i] [j] + " ");
	    }
	    System.out.println ();
	}


	System.out.println ("------------------Player 1 Enemy Base------------------");
	for (int i = 0 ; i < p1enemy.length ; i++)
	{
	    for (int j = 0 ; j < p1enemy.length ; j++)
	    {
		System.out.print (p1enemy [i] [j] + " ");
	    }
	    System.out.println ();
	}


	System.out.println ("------------------Player 2 Base------------------");
	for (int i = 0 ; i < p2array.length ; i++)
	{
	    for (int j = 0 ; j < p2array.length ; j++)
	    {
		System.out.print (p2array [i] [j] + " ");
	    }
	    System.out.println ();
	}


	System.out.println ("------------------Player 2 Enemy Base------------------");
	for (int i = 0 ; i < p2enemy.length ; i++)
	{
	    for (int j = 0 ; j < p2enemy.length ; j++)
	    {
		System.out.print (p2enemy [i] [j] + " ");
	    }
	    System.out.println ();
	}
    }*/


    public void firestart ()
    {
	JOptionPane.showMessageDialog (null, "*Time to Attack!* \n \n"
		+ "Now that it's your turn, you can fire at enemy ships"
		+ " Remember that to fire correctly, you must FIRST click the area of the enemy grid"
		+ "you want to fire at, and then the lock-in button."
		+ "\nProTip: Ships can only be placed horizontally or vertically, so fire accordingly!", "Gameplay Instructions", JOptionPane.QUESTION_MESSAGE);

	rotate.setEnabled (false);
	if (turn == 0)
	    Pass.setEnabled (false);
	else
	    Pass2.setEnabled (false);
	instgiven++;
    }


    public void turndisable ()
    {
	if (turn == 0 && allDone ())
	{
	    for (int i = 0 ; i < p1b.length ; i++)
	    {
		p1b [i].setActionCommand ("null");
	    }
	}


	else if (turn == 1)
	{
	    for (int i = 0 ; i < p1b.length ; i++)
	    {
		p2b [i].setActionCommand ("null");
	    }
	}
    }


    public boolean allDone ()
    {
	boolean x = true;
	for (int i = 0 ; i < shipscheck.length ; i++)
	{
	    if (shipscheck [i] == 0)
		x = false;
	}


	return x;



    }


    public static void soundEffect (String filepath)
    {
	//initialize objects
	AudioPlayer SEP = AudioPlayer.player; //declare sound effect player
	AudioStream SE; //declare sound effect
	AudioData MA; //declare audio data
	AudioDataStream play = null; //set as single run (NOT LOOP)

	try
	{
	    SE = new AudioStream (new FileInputStream (filepath + ".wav")); //set file
	    MA = SE.getData (); //get data from file
	    play = new AudioDataStream (MA); //set data to play once (NOT
	}
	catch (IOException error)  //error
	{
	    System.out.println ("Audio - File not found.");
	}
	SEP.start (play);
    } //end method soundEffect


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = BattleShip.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}


	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}


