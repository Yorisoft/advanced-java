// This is a program meant to help users manage their pokemons in battle
// by providing information such as type strength / weakness
// 
// Yelsin Sepulveda
// CSC 239

import java.io.*;
import java.awt.*;
import java.awt.event.*;


public class WritePokeRec extends Frame implements ActionListener {

	private TextField tf_recNum, tf_pokeLevel, tf_pokeName, tf_pokeSex, 
			tf_pokeType, tf_pokeHealth, tf_pokeDef, tf_pokeStrengths,
			tf_pokeWeaknesses, tf_pokeExp;
								
	private Button next, finished;
					
	private RandomAccessFile output;
	private PokeRecord poke_data;
		
		
	public WritePokeRec() {
		super("Examine your Pokemon Team");
		   
		poke_data = new PokeRecord();
		   
		try {
			output = new RandomAccessFile ("myPokemonTeam.dat", "rw");	
			output.seek(output.length());//EOF			
		} //try
		catch ( IOException e ) {
			System.err.println( "Error accoured with file: \n" +e.toString() );
			System.exit( 1 );
		}//catch
		
		setSize (700, 400);
		setLayout(new GridLayout(11,2));
		setFont (new Font("verdana", Font.BOLD, 12));
		
		add( new Label( " Record Number " ) );
		tf_recNum = new TextField();
		add( tf_recNum );
		
		add( new Label( " Pokemon Level " ) );
		tf_pokeLevel = new TextField();
		add( tf_pokeLevel );
		
		add (new Label (" Name "));
		tf_pokeName = new TextField(20);
		add( tf_pokeName );

		add(new Label (" Sex "));
		tf_pokeSex = new TextField(20);
		add( tf_pokeSex );
	   
		add(new Label (" Type ") );
		tf_pokeType = new TextField (20);
		add(tf_pokeType);
		
		add(new Label (" Health ") );
		tf_pokeHealth = new TextField (20);
		add(tf_pokeHealth);
		
	    add(new Label (" Defence ") );
		tf_pokeDef = new TextField (20);
		add(tf_pokeDef);
		
		add(new Label (" Strengths ") );
		tf_pokeStrengths = new TextField (20);
		add(tf_pokeStrengths);
		tf_pokeStrengths.setEditable(false);
		
		add(new Label (" Weaknesses ") );
		tf_pokeWeaknesses = new TextField (20);
		add(tf_pokeWeaknesses);
		tf_pokeWeaknesses.setEditable(false);
		
		add(new Label (" Experience Needed To Lvl") );
		tf_pokeExp = new TextField (20);
		add(tf_pokeExp);
		tf_pokeExp.setEditable(false);
		
		next = new Button( "Next" );
        next.addActionListener( this );
        add( next );      

        finished = new Button( "Finished" );
        finished.addActionListener( this );
	    add( finished );
	  
		setVisible(true); 
	}	
    
	public void addRecord() {
		int recNum = 0;
		int pokemonLevel = 0;
		String name;
		char sex;
		String type;
		int health;
		int defence;
		String strengths;
		String weaknesses;  
	   
	    if ( !tf_recNum.getText().equals( "" ) ) {

			try {
				recNum = Integer.parseInt( tf_recNum.getText() );
				pokemonLevel = Integer.parseInt( tf_pokeLevel.getText() );
			} catch(NumberFormatException nfe) {
				System.err.println("Record Number & Pokemon level must be entered as an Integer \n" + nfe.toString() );       
			}

			if ( recNum > 0 && recNum <= 100 ) {      
				poke_data.setRecNum( recNum );
				poke_data.setPokeLevel( pokemonLevel );
        
				try {
					poke_data.setPokeName( tf_pokeName.getText() );
					
					String temp = tf_pokeSex.getText();
					sex = temp.charAt(0);
					poke_data.setPokeSex( sex ); 
					
					poke_data.setPokeType( tf_pokeType.getText() );
					
				} catch(NumberFormatException nfe) {
					System.err.println("Pokemon sex must be entered as string or character \n" + nfe.toString());       
				}
				
				try {
					
					health = Integer.parseInt( tf_pokeHealth.getText() );
					poke_data.setPokeHealth( health );
					
					defence = Integer.parseInt( tf_pokeDef.getText() );
					poke_data.setPokeDef( defence );
					
				} catch(NumberFormatException nfe) {
					System.err.println("Pokemon Health and Defence must be entered as a Integer \n" + nfe.toString());     
				}
				
				//Strength
				poke_data.setPokeStrengths( poke_data.cal_strengths( tf_pokeType.getText() ) );
					
				//Weakness
				poke_data.setPokeWeaknesses( poke_data.cal_weakness( tf_pokeType.getText() ) );
					
				try {
					poke_data.setPokeExp( poke_data.calc_xp_needed( poke_data.getPokeLevel() ) );
					
				} catch(NumberFormatException nfe){
					System.err.println("Pokemon Experience must be entered as an a double value \n" + nfe.toString());
				}
		
				try{   
					output.seek( (long) ( recNum-1 ) * PokeRecord.size() );
					poke_data.write( output );
	    
	            } catch ( IOException io ) {
					System.err.println(	"Error during write to file\n" + io.toString() );
					System.exit( 1 );
				}//catch
			}
		 
			//Clear TextFields!!!!!	
			tf_recNum.setText(""); 
			tf_pokeLevel.setText(""); 
			tf_pokeName.setText("");
			tf_pokeSex.setText(""); 
			tf_pokeType.setText("");
			tf_pokeHealth.setText(""); 
			tf_pokeDef.setText("");
			tf_pokeStrengths.setText("");
			tf_pokeWeaknesses.setText(""); 
			tf_pokeExp.setText("");	 	 
		}		 		 
	}
		 
    public void actionPerformed( ActionEvent event ) {
		addRecord();
      
		if ( event.getSource() == finished ) {
			try {
				output.close();
			}//try
			catch ( IOException io ) {
				System.err.println( "File not closed properly\n" + io.toString() );
				System.exit(1);
			}
        System.exit( 0 );
      }
	}
		
	public static void main( String args[] )  {
		new WritePokeRec();
	} 
					
}	   