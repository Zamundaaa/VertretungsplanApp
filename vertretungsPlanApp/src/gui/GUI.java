package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import core.*;

public class GUI
{
    JFrame f;
    JP mainPanel;
    ImageIcon icon;
    JButton refresh;
    JButton home;
    JButton menu;
    JButton edit;
    JButton forward;
    JButton backward;
    JLabel Datum;
    ArrayList<JTextArea> Fach;
    ArrayList<JLabel> Uhrzeit;
    ArrayList<JLabel> Tag;
    boolean isEditable;
    static int xSize;
    static int ySize;
    static float xScale;
    static float yScale;
    //Login Screen:
    JPanel login;
    JLabel LoginLabel;
    JLabel RegistrierungLabel;
    JTextArea BL;
    JTextArea PL;
    JTextArea BR;
    JTextArea PR;
    ImageIcon logo;
    JButton LButton;
    JButton RButton;
    JCheckBox AGBChecker;
    PLAN Plan;
    //SYSTEM system;
    
    public GUI()//boolean durchZwei, boolean landscape
    {
        f = new JFrame();
        /*if(landscape)
        {
            if(durchZwei)
            {
                f.setSize(960 ,540);
                xSize = 960;
                ySize = 540;
                xScale = 2.1f;
                yScale = 2.8f;
                //f.setUndecorated(true);
            }
            else
            {
                f.setSize(1920, 1080);
                xSize = 1920;
                ySize = 1080;
                f.setUndecorated(true);
            }
        }
        else
        {
            f.setSize(540 ,960);
            xSize = 540;
            ySize = 960;
        }*/
        f.setSize(960 ,540);
                xSize = 960;
                ySize = 540;
                xScale = 2.1f;
                yScale = 2.8f;
                //f.setUndecorated(true);
        
        f.setLocation(0,0);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try{
            icon = Tools.getImageIcon("Logo-Descartes-Gymnasium.png");
            f.setIconImage(icon.getImage());
        }
        catch(Exception e){}
        
        //Panel für die normale Benutzung:
        //AnzeigeVertretungsplan();
        
        //Panel für login-registration:
        AnzeigeLoginScreen();
    }
    
    private void AnzeigeVertretungsplan()
    {
        if(login != null)f.remove(login);
        mainPanel = new JP();
        mainPanel.setLayout(null);
        
        Datum = new JLabel(getDateAsString());
        Datum.setBounds(80, 0, xSize/2,30);
        mainPanel.add(Datum);
        
        refresh = new JButton();
        home = new JButton();
        menu = new JButton();
        edit = new JButton();
        forward = new JButton();
        backward = new JButton();
        refresh.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e)
            {
                refresh();
            }
        });
        home.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e)
            {
                home();
            }
        });
        menu.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e)
            {
                menu();
            }
        });
        edit.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e)
            {
                edit();
            }
        });
        forward.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e)
            {
                forward();
            }
        });
        backward.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e)
            {
                backward();
            }
        });
        refresh.setIcon(Tools.getImageIcon("RefreshSymbol.png"));
        home.setIcon(Tools.getImageIcon("HomeSymbol.png"));
        menu.setIcon(Tools.getImageIcon("MenuSymbol.png"));
        edit.setIcon(Tools.getImageIcon("BearbeitenSymbol.png"));
        forward.setIcon(Tools.getImageIcon("PfeilRechts.png"));
        backward.setIcon(Tools.getImageIcon("PfeilLinks.png"));
        refresh.setBorderPainted(false);
        home.setBorderPainted(false);
        menu.setBorderPainted(false);
        edit.setBorderPainted(false);
        forward.setBorderPainted(false);
        backward.setBorderPainted(false);
        mainPanel.add(refresh);
        mainPanel.add(home);
        mainPanel.add(menu);
        mainPanel.add(edit);
        mainPanel.add(forward);
        mainPanel.add(backward);
        //---------------
        refresh.setBounds(xSize - 35,0,30,30);
        menu.setBounds(0,0,30,30);
        home.setBounds(xSize - 70,0,30,30);
        edit.setBounds(xSize - 230,0,30,30);
        forward.setBounds(xSize - 237,35,15,15);
        backward.setBounds(30 ,35,15,15);
        
        Fach = new ArrayList<>();
        isEditable = false;
        
        for(int x = 1; x < 6; x++)
            {
                for(int y = 0; y < 11; y++)
                {
                        JTextArea ta = new JTextArea();
                        ta.setEditable(isEditable);
                        mainPanel.add(ta);
                        
                        ta.setBounds( (int) (xScale * (x-1)*66 + 62), (int) (yScale * y*13 + 102), (int) (50*xScale), (int) (10*yScale));
                        Fach.add(ta);
                        System.out.println(" xPos: " + (xScale * (x-1)*66 + 32) + " yPos: " + (yScale * y*13 + 72) + " Fach: " + x*y);
                }
            }
            
        Uhrzeit = new ArrayList<>();
        Tag = new ArrayList<>();
        
        for(int k = 0; k < 11; k++)
        {
            JLabel jl = new JLabel();
            mainPanel.add(jl);
            jl.setBounds( 0, (int) (yScale * k*13 + 102), 60, (int) (10*yScale));
            Uhrzeit.add(jl);
        }
        for(int l = 0; l < 5; l++)
        {
            JLabel jl = new JLabel();
            mainPanel.add(jl);
            jl.setBounds( (int) (xScale * (l-1)*66 + 212), 75, (int) (50*xScale), (int) (10*yScale));
            Tag.add(jl);
        }
        TextFuerMainPanel();    
        
        f.add(mainPanel);
        f.setVisible(true);
        mainPanel.repaint();
    }
    
    private void AnzeigeLoginScreen()
    {
        if(mainPanel != null)f.remove(mainPanel);
        
        logo = Tools.getImageIcon("Logo-Descartes-Gymnasium.png");
        
        login = new JPanel(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(new Color(0,0,0));
                g.fillRect(488, 30, 4, ySize - 90);
                
                g.drawImage(logo.getImage(), 0, 0, xSize, ySize, this);
            }
        };
        login.setLayout(null);
        
        LoginLabel = new JLabel("LOGIN");
        RegistrierungLabel = new JLabel("REGISTRIERUNG");
        login.add(LoginLabel);
        login.add(RegistrierungLabel);
        LoginLabel.setBounds(190, 150, 100, 50);
        RegistrierungLabel.setBounds(690, 150, 100 , 50);
        
        BL = new JTextArea();
        PL = new JTextArea();
        BR = new JTextArea();
        PR = new JTextArea();
        login.add(BL);
        login.add(PL);
        login.add(BR);
        login.add(PR);
        
        BL.setBounds(190, 200, 100, 25);
        PL.setBounds(190, 250, 100, 25);
        BR.setBounds(690, 200, 100 , 25);
        PR.setBounds(690, 250, 100 , 25);
        
        AGBChecker = new JCheckBox("Ich habe die Allgemeinen Geschäftsbedingungen gelesen und erkenne sie hiermit an.");
        login.add(AGBChecker);
        AGBChecker.setBounds(640, 300, 200, 25);
        
        LButton = new JButton("LOGIN");
        RButton = new JButton("REGISTRIERUNG");
        login.add(LButton);
        login.add(RButton);
        LButton.setBounds(190, 350 , 100, 25);
        RButton.setBounds(690, 350, 100, 25);
        LButton.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e)
            {
                LoginButton();
            }
        });
        RButton.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e)
            {
                RegistrierungsButton();
            }
        });
        
        f.add(login);
        f.setVisible(true);
        login.repaint();
    }
    
    private void TextFuerMainPanel()
    {
        Uhrzeit.get(0).setText("08:00");
        Uhrzeit.get(1).setText("08:45");
        Uhrzeit.get(2).setText("9:45");
        Uhrzeit.get(3).setText("10:30");
        Uhrzeit.get(4).setText("11:30");
        Uhrzeit.get(5).setText("12:15");
        Uhrzeit.get(6).setText("13:00");
        Uhrzeit.get(7).setText("13:55");
        Uhrzeit.get(8).setText("14:40");
        Uhrzeit.get(9).setText("15:35");
        Uhrzeit.get(10).setText("16:15");
        
        Tag.get(0).setText("Montag");
        Tag.get(1).setText("Dienstag");
        Tag.get(2).setText("Mittwoch");
        Tag.get(3).setText("Donnerstag");
        Tag.get(4).setText("Freitag");
        
    }
    
    public void anzeigen()
    {
        for(int i = 0; i < 5; i++)
        {
            for(int k = 0; k < 11; k++)
            {
                String fach = Plan.Stunde[i][k].GebeFach();
                String lehrer = Plan.Stunde[i][k].GebeLehrer();
                String raum = Plan.Stunde[i][k].GebeRaum();
                if(lehrer.equals(null))
                {
                    lehrer = "";
                }
                if(fach.equals(null))
                {
                    fach = "";
                }
                if(raum.equals(null))
                {
                    raum = "";
                }
                Fach.get(i*11 + k).setText(fach + "\n" + lehrer + "                    " + raum);
            }
        }
    }
    
    public String[] BenutzerAnmelden()
    {
        String[] s = new String[2];
        s[0] = BL.getText();
        s[1] = PL.getText();
        return s;
    }
    
    public String[] BenutzerAnlegen()
    {
        String[] s = new String[2];
        s[0] = BR.getText();
        s[1] = PR.getText();
        return s;
    }
    
    public void PlanAnzeigen(PLAN Plan)
    {
        this.Plan = Plan;
        anzeigen();
    }
    
    public void PlanErstellen()
    {
        AnzeigeVertretungsplan();
    }
    
    public PLAN NeuenPlanGeben()
    {
        PLAN p = new PLAN();
        for(int i = 0; i < 5; i++)
        {
            for(int k = 0; k < 11; k++)
            {
                try{
                    p.Stunde[i][k] = new STUNDE();
                    String Fachinhalt = Fach.get(i*11 + k).getText();
                    String[] s = Fachinhalt.split("                    ");
                    String[] s2 = s[0].split("\n");
                    
                    p.Stunde[i][k].FachEinfuegen(s2[0]);
                    p.Stunde[i][k].LehrerEinfuegen(s2[1]);
                    p.Stunde[i][k].RaumEinfuegen(s[1]);
                }
                catch(Exception e)
                {
                    p.Stunde[i][k] = new STUNDE();
                }
            }
        }
        for(int i = 5; i < 10; i++)
        {
            for(int k = 0; k < 11; k++)
            {
                try{
                    p.Stunde[i][k].FachEinfuegen(Plan.Stunde[i][k].GebeFach());
                    p.Stunde[i][k].LehrerEinfuegen(Plan.Stunde[i][k].GebeLehrer());
                    p.Stunde[i][k].RaumEinfuegen(Plan.Stunde[i][k].GebeRaum());
                }
                catch(Exception e)
                {
                    p.Stunde[i][k] = new STUNDE();
                }
            }
        }
        
        return p;
    }
    
    
    
    private void LoginButton()
    {
        AnzeigeVertretungsplan();
    }
    
    private void RegistrierungsButton()
    {
        
    }
    
    private void refresh()
    {
        System.out.println("refresh");
    }
    
    private void home()
    {
        System.out.println("home");
    }
    
    private void menu()
    {
        System.out.println("menu");
    }
    
    private void edit()
    {
        if(isEditable)
        {
            PLAN pl = NeuenPlanGeben();
            PlanAnzeigen(pl);
        }
        isEditable = !isEditable;
        for(int i = 0; i < Fach.size(); i++)
        {
            Fach.get(i).setEditable(isEditable);
        }
    }
    
    private void forward()
    {
        System.out.println("forward");
    }
    
    private void backward()
    {
        System.out.println("backward");
    }
    
    public String getDateAsString() {
       DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
       return formatter.format(new Date());
    }
    
    public class JP extends JPanel
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(new Color(166,166,166));
            g.fillRect(0,0, xSize, 30);
            g.fillRect(xSize - 200, 0, 200, ySize);
            g.setColor(new Color(0,0,0));
            g.fillRect(xSize - 202,30, 2,ySize);
            g.fillRect(0, 30, xSize, 2);
        }
    }
    
    public static void main(String[] args){
    	new GUI();
    }
    
}