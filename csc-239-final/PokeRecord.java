// This is a program meant to help users manage their pokemons in battle
// by providing information such as type strength / weakness
// 
// Yelsin Sepulveda
// CSC 239

import java.io.*;
import java.lang.Math;

public class PokeRecord{  //object creating
	private int rec_num;          	//4 
	private int poke_level;          	//4 
	private String poke_name;      		//15
	private char poke_sex;				//2
	private String poke_type;   		//15
	private int poke_health;			//4
	private int poke_defence; 			//4
	private String poke_strengths;		//15
	private String poke_weaknesses;		//15
	private double poke_exp;			//8


	public void read( RandomAccessFile file ) throws IOException {
		rec_num = file.readInt();
		poke_level = file.readInt();
		  
		char n[] = new char[ 15 ];
		  
		for ( int i = 0; i < n.length; i++ )
			n[ i ] = file.readChar();
		poke_name = new String( n );

		poke_sex = file.readChar();

		// char title[] = new char[ 15 ];  // variables make programs slower and more expensive 
		for ( int i = 0; i < n.length; i++ )
			n[ i ] = file.readChar();
		poke_type = new String( n );
		  
		poke_health  = file.readInt();
		poke_defence = file.readInt();
		 
		for ( int i = 0; i < n.length; i++ )
			n[ i ] = file.readChar();
		poke_strengths = new String( n );
		  
		for ( int i = 0; i < n.length; i++ )
			n[ i ] = file.readChar();
		poke_weaknesses = new String( n );
		  
		poke_exp = file.readDouble();
	  
   }//read

    // Write a record to the specified RandomAccessFile
    public void write( RandomAccessFile file ) throws IOException {
		StringBuffer buffer;
		// rec_num
		file.writeInt(rec_num );
		// poke_level
		file.writeInt(poke_level );
		// poke_name
		if ( poke_name != null ) 
			buffer = new StringBuffer( poke_name );
		else 
			buffer = new StringBuffer( 15 );
		buffer.setLength( 15 );
		file.writeChars( buffer.toString() );
		// poke_sex
		if ( (int)poke_sex != 0 ) 
			buffer = new StringBuffer( poke_sex );
		else 
			buffer = new StringBuffer( 1 );
		buffer.setLength( 1 );
		file.writeChars( buffer.toString() );
		System.out.println(buffer.toString());
		// poke_type
		if ( poke_type != null ) 
			buffer = new StringBuffer( poke_type );
		else 
			buffer = new StringBuffer( 15 );
		buffer.setLength( 15 );
		file.writeChars( buffer.toString() );
		//poke_health
		file.writeInt( poke_health );
		//poke_defence
		file.writeInt( poke_defence );
		// poke_strengths
		if ( poke_strengths != null ) 
			buffer = new StringBuffer( poke_strengths );
		else 
			buffer = new StringBuffer( 15 );
		buffer.setLength( 15 );
		file.writeChars( buffer.toString() );
		// poke_weaknesses
		if ( poke_weaknesses != null ) 
			buffer = new StringBuffer( poke_weaknesses );
		else 
			buffer = new StringBuffer( 15 );
		buffer.setLength( 15 );
		file.writeChars( buffer.toString() );
		// poke_exp
		file.writeDouble( poke_exp );

     
   }//write
   
	//poke_num
	public void setRecNum( int n ) {  rec_num = n; }
	public int getRecNum() { return rec_num; }
   
	//poke_num
	public void setPokeLevel( int p_level ) {  poke_level = p_level; }
	public int getPokeLevel() { return poke_level; }
   
	//poke_name
	public void setPokeName( String p_name ) {  poke_name = p_name; }
	public String getPokeName() { return poke_name; }
   
	//poke_sex
	public void setPokeSex( char p_sex ) {  poke_sex = p_sex; }
	public char getPokeSex() { return poke_sex; }
   
	//poke_type
	public void setPokeType( String p_type ) {  poke_type = p_type; }
	public String getPokeType() { return poke_type; }
   
	//poke_health
	public void setPokeHealth( int p_health ) {  poke_health = p_health; }
	public int getPokeHealth() { return poke_health; }
   
	//poke_defence
	public void setPokeDef( int p_def ) {  poke_defence = p_def; }
	public int getPokeDef() { return poke_defence; }
   
	//poke_strengths
	public void setPokeStrengths( String p_strengths ) {  poke_strengths = p_strengths; }
	public String getPokeStrengths() { return poke_strengths; }
   
	//poke_weaknesses
	public void setPokeWeaknesses( String p_weaknesses ) {  poke_weaknesses = p_weaknesses; }
	public String getPokeWeaknesses() { return poke_weaknesses; }
   
	//poke_weaknesses
	public void setPokeExp( double p_exp ) {  poke_exp = p_exp; }
	public double getPokeExp() { return poke_exp; }
   
	public static int size() { return 142; }  //SIZE OF RECORD IN BYTES!!!!
	
	public String cal_strengths( String type ) {
		type = type.toLowerCase();
		String poke_strengths = " Incorrect Pokemon Type ";
		String normal_pk_strengths, fire_pk_strengths, water_pk_strengths,
			electric_pk_strengths, grass_pk_strengths, ice_pk_strengths, 
			fighting_pk_strengths, poison_pk_strengths, ground_pk_strengths,
			flying_pk_strengths, psychic_pk_strengths, bug_pk_strengths,
			rock_strengths, ghost_pk_strengths, dragon_pk_strengths;
		
		System.out.println(type);
		
		normal_pk_strengths = "None";
		fire_pk_strengths = "Grass, Ice, Bug";
		water_pk_strengths = "Fire, Ground, Rock";
		electric_pk_strengths = "Water, Flying";
		grass_pk_strengths = "Water, Ground, Rock";
		ice_pk_strengths = "Grass, Flying, Dragon";
		fighting_pk_strengths = "Normal, Ice, Rock";
		poison_pk_strengths = "Grass, Bug";
		ground_pk_strengths = "Fire, Electric, Poison, Rock";
		flying_pk_strengths = "Grass, Fighting, Bug";
		psychic_pk_strengths = "Fighting, Poison";
		bug_pk_strengths = "Grass, Poison, Psychic";
		rock_strengths = "Fire, Ice, Flying, Bug";
		ghost_pk_strengths = "Ghost";
		dragon_pk_strengths = "Dragon";
		
		poke_strengths = (type.equals("normal") ? normal_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("fire") ? fire_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("water") ? water_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("electric") ? electric_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("grass") ? grass_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("ice") ?  ice_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("fighting") ?  fighting_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("poison") ?  poison_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("ground") ?  ground_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("flying") ?  flying_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("psychic") ?  psychic_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("bug") ?  bug_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("rock") ?  rock_strengths : poke_strengths);
		poke_strengths = (type.equals("ghost") ?  ghost_pk_strengths : poke_strengths);
		poke_strengths = (type.equals("dragon") ?  dragon_pk_strengths : poke_strengths);

		return poke_strengths;
   }
	
	public String cal_weakness( String type ) {
		type = type.toLowerCase();
		String poke_weakness = " Incorrect Pokemon Type ";
		String normal_pk_weakness, fire_pk_weakness, water_pk_weakness,
			electric_pk_weakness, grass_pk_weakness, ice_pk_weakness, 
			fighting_pk_weakness, poison_pk_weakness, ground_pk_weakness,
			flying_pk_weakness, psychic_pk_weakness, bug_pk_weakness,
			rock_weakness, ghost_pk_weakness, dragon_pk_weakness;
		
		System.out.println(type);
		
		normal_pk_weakness = "Rock, Ghost x2";
		fire_pk_weakness = "Fire, Water, Rock, Dragon";
		water_pk_weakness = "Water, Grass, Dragon";
		electric_pk_weakness = "Electric, Grass, Ground x2, Dragon";
		grass_pk_weakness = "Fire, Ice, Poison, Flying, Bug, Dragon";
		ice_pk_weakness = "Water, Ice";
		fighting_pk_weakness = "Poison, Flying, Psychic, Bug, Ghost x2";
		poison_pk_weakness = "Poison, Ground, Rock, Ghost";
		ground_pk_weakness = "Grass, Flying x2, Bug";
		flying_pk_weakness = "Electric, Rock";
		psychic_pk_weakness = "Psychic";
		bug_pk_weakness = "Fire, Fighting, Flying, Ghost";
		rock_weakness = "Fighting, Ground";
		ghost_pk_weakness = "None";
		dragon_pk_weakness = "None";
		
		poke_weakness = (type.equals("normal") ?  normal_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("fire") ?  fire_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("water") ?  water_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("electric") ?  electric_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("grass") ?  grass_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("ice") ?  ice_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("fighting") ?  fighting_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("poison") ?  poison_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("ground") ?  ground_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("flying") ?  flying_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("psychic") ?  psychic_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("bug") ?  bug_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("rock") ?  rock_weakness : poke_weakness);
		poke_weakness = (type.equals("ghost") ?  ghost_pk_weakness : poke_weakness);
		poke_weakness = (type.equals("dragon") ?  dragon_pk_weakness : poke_weakness);

		return poke_weakness;
   }
   
	// not based on real game calculations
	public double calc_xp_needed( int p_level ) {  
		poke_exp = Math.pow((p_level*(1+1.5)), 2.1);

		return 	poke_exp;	
	}

}