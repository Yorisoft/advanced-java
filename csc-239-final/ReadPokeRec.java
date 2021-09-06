// This is a program meant to help users manage their pokemons in battle
// by providing information such as type strength / weakness
// 
// Yelsin Sepulveda
// CSC 239

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat ;
import java.util.Scanner;

class Pokemon{
	int rec_num;          		//4 
	int poke_level;          	//4 
	String poke_name;      		//15
	char poke_sex;				//2
	String poke_type;   		//15
	int poke_health;			//4
	int poke_defence; 			//4
	String poke_strengths;		//15
	String poke_weaknesses;		//15
	double poke_exp;			//8
	
	Pokemon(int recNum, int p_level, String p_name, char p_sex, String p_type, int p_health, 
	int p_defence, String p_strength, String p_weaknesses, double p_exp){
		this.rec_num = recNum;
		this.poke_level = p_level;
		this.poke_name = p_name;
		this.poke_sex = p_sex;
		this.poke_type = p_type;
		this.poke_health = p_health;
		this.poke_defence = p_defence;//
		this.poke_strengths = p_strength;//
		this.poke_weaknesses = p_weaknesses;
		this.poke_exp = p_exp;//
	}
	
	public String toString(){
		DecimalFormat formatter = new DecimalFormat("00000.00");
		return 
			( "\t Record Number: " + this.rec_num + '\n' +
			"\t Pokemon Level: " + this.poke_level + '\n' +
			"\t Pokemon Name: " + this.poke_name + '\n' +
			"\t Pokemon Sex: " + this.poke_sex + '\n' +
			"\t Pokemon Type: " + this.poke_type + '\n' +
			"\t Pokemon Health: " + this.poke_health + '\n' +
			"\t Pokemon Defence: " + this.poke_defence + '\n' +
			"\t Pokemon Strength: " + this.poke_strengths + '\n' +
			"\t Pokemon Weaknesses: " + this.poke_weaknesses + '\n' +
			String.format("\t Experience Needed level up: %.2f", this.poke_exp ));
	}
}

public class ReadPokeRec extends Frame implements ActionListener {
	Pokemon pokemon;
	private TextField tf_recNum, tf_pokeLevel, tf_pokeName, tf_pokeSex, 
			tf_pokeType, tf_pokeHealth, tf_pokeDef, tf_pokeStrengths,
			tf_pokeWeaknesses, tf_pokeExp;
			
	private Button next, finished;
					
	private RandomAccessFile input;
	private PokeRecord poke_data;
	
	public ReadPokeRec() {
		super("Examine your Pokemon Team");
		
		poke_data = new PokeRecord();
		
		try {
			input = new RandomAccessFile ("myPokemonTeam.dat", "rw");							
		} //try
		catch ( IOException e ) {
			System.err.println( "Error accoured with file: \n" + e.toString() );
			System.exit( 1 );
		}//catch
		
		setSize (700, 400);
		setLayout(new GridLayout(11,2));
		setFont (new Font("verdana", Font.BOLD, 12));
		
		
		add( new Label( " Record Number " ) );
		tf_recNum = new TextField();
		tf_recNum.setEditable( false );
		add( tf_recNum );
		
		add( new Label( " Pokemon Level " ) );
		tf_pokeLevel = new TextField();
		tf_pokeLevel.setEditable( false );
		add( tf_pokeLevel );
		
		add (new Label (" Name "));
		tf_pokeName = new TextField(20);
		tf_pokeName.setEditable( false );
		add( tf_pokeName );

		add(new Label (" Sex "));
		tf_pokeSex = new TextField(20);
		tf_pokeSex.setEditable( false );
		add( tf_pokeSex );
	   
		add(new Label (" Type ") );
		tf_pokeType = new TextField (20);
		tf_pokeType.setEditable( false );
		add(tf_pokeType);
		
		add(new Label (" Health ") );
		tf_pokeHealth = new TextField (20);
		tf_pokeHealth.setEditable( false );
		add(tf_pokeHealth);
		
	    add(new Label (" Defence ") );
		tf_pokeDef = new TextField (20);
		tf_pokeDef.setEditable( false );
		add(tf_pokeDef);
		
		add(new Label (" Strengths ") );
		tf_pokeStrengths = new TextField (20);
		tf_pokeStrengths.setEditable(false);
		add(tf_pokeStrengths);
		
		add(new Label (" Weaknesses ") );
		tf_pokeWeaknesses = new TextField (20);
		tf_pokeWeaknesses.setEditable(false);
		add(tf_pokeWeaknesses);
		
		add(new Label (" Experience Needed To Lvl") );
		tf_pokeExp = new TextField (20);
		tf_pokeExp.setEditable(false);
		add(tf_pokeExp);
		
		next = new Button( "Next" );
        next.addActionListener( this );
        add( next );      

        finished = new Button( "Finished" );
        finished.addActionListener( this );
	    add( finished );
	  
		setVisible(true); 
	}
   
   	public void actionPerformed( ActionEvent event ) {
		if ( event.getSource() == next )
			readRecord();
		else
			closeFile();
	}
	
	public void readRecord() {
		int recNum;
		int pokemonLevel;
		String name;
		char sex;
		String type;
		int health;
		int defence;
		String strengths;
		String weaknesses;
		double xp;
		
		try{
			do{ 
			poke_data.read( input );
			
			recNum = poke_data.getRecNum();
			pokemonLevel = poke_data.getPokeLevel();
			name = poke_data.getPokeName();
			sex = poke_data.getPokeSex();
			type = poke_data.getPokeType();
			health = poke_data.getPokeHealth();
			defence = poke_data.getPokeDef();
			strengths = poke_data.cal_strengths(type);
			weaknesses = poke_data.cal_weakness(type);
			xp = poke_data.getPokeExp();
			
			pokemon = new Pokemon(recNum, pokemonLevel, name, sex, type, health, defence, strengths, weaknesses, xp);
			
			System.out.println(pokemon);
			
			tf_recNum.setText(String.valueOf(recNum));
			tf_pokeLevel.setText(String.valueOf(pokemonLevel)); 
			tf_pokeName.setText(String.valueOf(name)); 
			tf_pokeSex.setText(String.valueOf(sex)); 
			tf_pokeType.setText(String.valueOf(type)); 
			tf_pokeHealth.setText(String.valueOf(health));
			tf_pokeDef.setText(String.valueOf(defence));
			tf_pokeStrengths.setText(String.valueOf(strengths));
			tf_pokeWeaknesses.setText(String.valueOf(weaknesses));
			tf_pokeExp.setText(String.valueOf(xp));
			
			} while(poke_data.getRecNum() == 0);
			
		} catch ( EOFException eof ) {
			closeFile();
		} catch ( IOException e ) {
			System.err.println( "Error during read from file\n" + e.toString() );
			System.exit( 1 );
      }
	}
	
	private void closeFile() {
		try {
			input.close();
			System.exit( 0 );
		}//try
		catch ( IOException e ) {
			System.err.println( "Error closing file\n" + e.toString() );
			System.exit( 1 );
		}//catch
	}//closeFile
	
	public static void main( String args[] )  {
		 new ReadPokeRec();
	}
}