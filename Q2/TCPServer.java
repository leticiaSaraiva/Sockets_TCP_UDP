import java.net.*;
import java.io.*;
import java.util.*;
public class TCPServer {
	public static void main (String args[]) {
		try{
			
			
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}


class Text {
	public List<String> frases;
	public Text(){
			this.frases = new ArrayList<String>(31);
			frases.add("\"Você provavelmente terá de entrar em uma batalha mais de uma vez para vencê-la\" – Margaret Thatcher");
			frases.add("\"Quanto maior o artista, maiores são os momentos de dúvida. Confiança inabalável é algo garantido para os menos talentosos, como um prêmio de consolação\" – Robert Hughes");
			frases.add("\"A lógica pode levar de um ponto A a um ponto B. A imaginação pode levar a qualquer lugar\" – Albert Einstein");
			frases.add("\"Sonhe como se você fosse viver para sempre. Viva como se você fosse morrer hoje\" – James Dean");
			frases.add("\"Fazer o que você gosta é liberdade. Gostar do que você faz é felicidade\" – Frank Tyger");
			frases.add("\"Seja feliz com o que você tem, mas fique animado com a chance de ter mais\" – Alan Cohen");
			frases.add("\"Seu tempo é curto. Por isso, não o desperdice vivendo a vida de outra pessoa\" – Steve Jobs");
			frases.add("\"Somos nós que forjamos as correntes que usamos em nossas vidas\" – Charles Dickens");
			frases.add("\"A arte de viver bem não consiste em eliminar o que nos faz sofrer, mas crescer com esses problemas\" – Bernard M. Baruch");
			frases.add("\"Você nunca se arrependerá de ser gentil\" – Nicole Shepherd");
			frases.add("\"Em nossas vidas, a mudança é inevitável. A perda é inevitável. A felicidade reside na nossa adaptabilidade em sobreviver a tudo de ruim\" – Buda");
			frases.add("\"Para cuidar de si mesmo, use a cabeça. Para cuidar dos outros, use seu coração\" – Eleanor Roosevelt");
			frases.add("\"Apenas um entre mil é um líder de outros homens – os outros 999 seguem suas mulheres\" – Groucho Marx");
			frases.add("\"Mantenha seus medos consigo, mas compartilhe sua coragem com os outros\" – Robert Louis Stevenson");
			frases.add("\"Muitas das falhas da vida ocorrem quando não percebemos o quão próximos estávamos do sucesso na hora em que desistimos\" – Thomas Edison");
			frases.add("\"A felicidade é uma borboleta que, sempre que perseguida, parecerá inatingível; no entanto, se você for paciente, ela pode pousar no seu ombro\" – Nathaniel Hawthorne");
			frases.add("\"Faça ou não faça. Tentativas não existem\" – Mestre Yoda");
			frases.add("\"Se alguém não se sente agradecido pelo que tem, ele provavelmente nunca será agradecido pelo que conseguir\" – Frank A. Clark");
			frases.add("\"Se você ouvir uma voz dizendo 'não faça', isso significa que você deve fazê-lo, acima de tudo. A voz vai se calar\" – Vincent Van Gogh");
			frases.add("\"Você não se preocuparia tanto sobre o que pensam de você se você soubesse que poucos perdem tempo com isso\" – Eleanor Roosevelt");
			frases.add("\"Autoconfiança é muito importante para alcançar o sucesso. E para se tornar confiante, é importante estar preparado\" – Arthur Ashe");
			frases.add("\"Se todos se propusessem o que são capazes, ficaríamos impressionados com nossas criações\" – Thomas Edison");
			frases.add("\"Sempre se lembre de que você tem mais fibra que acredita, é mais forte que parece e mais esperto do que você pensa que é\" – Christopher Robin Milne");
			frases.add("\"É difícil liderar uma cavalaria se você não sabe montar a cavalo\" – Adlai E. Stevenson II");
			frases.add("\"80% do necessário para o sucesso é aparecer\" – Woody Allen");
			frases.add("\"A melhor vingança é um sucesso estrondoso\" – Frank Sinatra");
			frases.add("\"Um homem de sucesso é aquele que cria uma parede com os tijolos que jogaram nele\" – David Brinkley");
			frases.add("\"Realize seus próprios sonhos. Do contrário, você será contratado para realizar os de outras pessoas\" – Farrah Grey");
			frases.add("\"Nunca é tarde demais para ser aquilo que você sempre desejou ser\" – George Eliot");
			frases.add("\"A altura das suas realizações será igual à profundidade das suas convicções\" – William F. Scolavino");

	}
	public String get_frase(int day){
		return this.frases.get(day);
		
		}

}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	Text text =  new Text();
	public Connection (Socket aClientSocket) {
		try {
			
		
			
			
			
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out = new DataOutputStream( clientSocket.getOutputStream());
			
			this.start();
			
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	
	
	
	
	public void run(){
		try {			                 // an echo server
			
			
			String data = in.readUTF();	                  // read a line of data from the stream
			if(data.equals("Hour")){
				Date date = new Date();
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				//int year = cal.get(Calendar.YEAR);
				//int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				int hours = cal.get(Calendar.HOUR_OF_DAY);
				int min = cal.get(Calendar.MINUTE);
				int sec = cal.get(Calendar.SECOND);
				//System.out.println(this.frases);
				String time = Integer.toString(hours)+ ":" + Integer.toString(min) +":" + Integer.toString(sec)+ "\n";
				out.writeUTF(time + text.get_frase(day-1));
				System.out.println("Read and Send");
	
			}
			else {
				
				out.writeUTF("Comando Inválido");
			} 
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}
