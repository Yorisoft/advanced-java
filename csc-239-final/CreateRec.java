// This is a program meant to help users manage their pokemons in battle
// by providing information such as type strength / weakness
// 
// Yelsin Sepulveda
// CSC 239

import java.io.*;

public class CreateRec{
	private PokeRecord pokemon;
	private RandomAccessFile file;

	public CreateRec() {
		pokemon = new Recordxe();
		
		try{
			file = new RandomAccessFile( "myPokemonTeam.dat", "rw" );
			// file.seek(file.length());//EOF
			for ( int i = 0; i < 142; i++ )
				pokemon.write( file );
			file.close();
		}//try
		catch( IOException e ) {
			System.err.println( "File not opened properly\n" + e.toString() );
			System.exit( 1 );	
		}
	}
	
	public static void main(String args[]){
		CreateRec poke_rec = new CreateRec();
	}
}