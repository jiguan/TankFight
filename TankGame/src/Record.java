import java.io.*;
import java.util.Vector;

public class Record {
	private static FileWriter fw;
	private static BufferedWriter bw;
	private static FileReader fr;
	private static BufferedReader br;
	private static int score = 0;
	private static Vector<Enemy> ets = new Vector<Enemy>();
	private static Vector<Enemy> ats = new Vector<Enemy>();
	//save score to file
	public static void saveRecord()
	{
		try {
			fw = new FileWriter("/home/jiguan/workspace/TankGame/src/saving.txt");
			bw = new BufferedWriter(fw);
			bw.write(TankGame.getScore()+"\r\n");
			
			//save enemy position and bullets
			for(int i=0;i<ets.size();i++)
			{
				Enemy enemy_tank = ets.get(i);
				if(enemy_tank.isLive)
				{
					String position = enemy_tank.getX()+" "+enemy_tank.getY()+" "+enemy_tank.getDirect();
					bw.write(position+"\r\n");
					
				}
			}
			bw.write("\n");
			for(int i=0;i<ats.size();i++)
			{
				Enemy ally_tank = ats.get(i);
				if(ally_tank.isLive)
				{
					String position = ally_tank.getX()+" "+ally_tank.getY()+" "+ally_tank.getDirect();
					bw.write(position+"\r\n");
					
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void saveScore()
	{
		try {
			fw = new FileWriter("/home/jiguan/workspace/TankGame/src/highest_score.txt");
			bw = new BufferedWriter(fw);
			bw.write(TankGame.getScore()+"\r\n");
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	
	public static Vector[] readRecord()
	{
		boolean flag = true;
		Vector result[] = new Vector[2];
		Vector<Node> nodes0 = new Vector<Node>();
		Vector<Node> nodes1 = new Vector<Node>();
		try {
			fr = new FileReader("/home/jiguan/workspace/TankGame/src/saving.txt");
			br = new BufferedReader(fr);
			String n = br.readLine();
			score = Integer.parseInt(n);
			//continue read
			while((n=br.readLine())!=null||flag) {
				if (flag && n.length()==0) {
					flag = false;
				}
				if (n.length()>0 && flag) {
					String position[] = n.split(" ");
					Node node = new Node(Integer.parseInt(position[0]),Integer.parseInt(position[1]),Integer.parseInt(position[2])); 
					nodes0.add(node);
					result[0] = nodes0;
				}
				else if (!flag && n.length()>0) {
					String position[] = n.split(" ");
					Node node = new Node(Integer.parseInt(position[0]),Integer.parseInt(position[1]),Integer.parseInt(position[2])); 
					nodes1.add(node);
					result[1] = nodes1;
				}
				
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result;
	}
	
	public static int readScore()
	{
		try {
			fr = new FileReader("/home/jiguan/workspace/TankGame/src/highest_score.txt");
			br = new BufferedReader(fr);
			String n = br.readLine();
			score = Integer.parseInt(n);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return score;
	}
	
	public static int getScore()
	{
		return score;
	}
	
	public Vector<Enemy> getEts() {
		return ets;
	}
	public static void setEts(Vector<Enemy> ets) {
		Record.ets = ets;
	}
	public static void setAts(Vector<Enemy> ats) {
		Record.ats = ats;
	}
}

