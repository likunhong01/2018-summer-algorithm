package 常规题目;



import java.util.ArrayList;  
import java.util.List;  

class Frog{
	public int number;
	public int position;
	public char direction;//跳动方向，r右，l左
	public boolean canJump;
	public boolean isEmpty;
	//普通构造方法
	public Frog(int number, int position, char direction,boolean canJump) {
		this.canJump = canJump;
		this.number = number;
		this.position = position;
		this.direction = direction;
	}
	//空位置构造方法
	public Frog(int number,int position,boolean isempty) {
		this.number = number;
		this.position = position;
		this.isEmpty = isempty;
	}
	//吧某个forg复制给另一个的构造方法
	public Frog(Frog frog) {
		this.canJump = frog.canJump;
		this.number = frog.number;
		this.position = frog.position;
		this.direction = frog.direction;
		this.isEmpty = frog.isEmpty;
	}
}

public class 青蛙过河 {
	
	public static String frogJumpCount(List<Frog> frogQueue, int emptyPosition) {
		int frogJumpCount = 0;
		String frogjumpinfo = "";
		//遍历青蛙队列（frogQueue）的每个青蛙
		for (int i = 0; i < frogQueue.size(); i++) {
			
			Frog nowfrog = frogQueue.get(i);
			//如果是空，或者青蛙跳不动，就continue
			if (nowfrog.isEmpty) {
				continue;
			}
			if (!nowfrog.canJump) {
				continue;
			}
			
			//移动青蛙
//			frogJumpCount++;
			frogjumpinfo = "青蛙"+nowfrog.number+ " "+ nowfrog.direction + "跳到" + (emptyPosition+1)+"\n";
			//更改队列,移动青蛙
			int newEmptyposition = nowfrog.position;
			//传入当前队列，当前青蛙信息，空的位置，和青蛙位置
			List<Frog> newfrogQueue = changeQueue(frogQueue,nowfrog,emptyPosition,newEmptyposition);
			
			//如果这个队列还有能跳，继续递归
			if (canFrogJump(newfrogQueue)) {
				frogjumpinfo += frogJumpCount(newfrogQueue, newEmptyposition);
//				frogJumpCount ++;
			}
			//如果不能跳，就判断是否成功
			else {
				//如果成功了就跳出，
				if (isComplete(newfrogQueue)) {
					frogjumpinfo += "成功" ;
					break;
				}
				//没成功就继续循环
			}
			if (frogjumpinfo.contains("成功")) {
				frogJumpCount++;
				break;
			}
			
		}
		return frogjumpinfo;
	}

	
	private static List<Frog> changeQueue(List<Frog> frogQueue, Frog nowfrog, int emptyPosition, int newEmptyposition) {
		int newfrogposition = emptyPosition;
		Frog empty = new Frog(frogQueue.get(emptyPosition));
		Frog xinde = new Frog(nowfrog);
		
		int number = xinde.number;
		char direction = xinde.direction;
		
		//复制
		List<Frog> frogQueue1 = new ArrayList<Frog>();
		for (int i = 0; i < frogQueue.size(); i++) {
			Frog frog = new Frog(frogQueue.get(i));
			frogQueue1.add(frog);
		}
		
		//把之前青蛙的位置置空
		frogQueue1.get(newEmptyposition).isEmpty = true;
		frogQueue1.get(newEmptyposition).number = empty.number;
		frogQueue1.get(newEmptyposition).direction = empty.direction;
		frogQueue1.get(newEmptyposition).canJump = false;
		
		//把之前空的位置放上xinde青蛙
		frogQueue1.get(newfrogposition).isEmpty = false;
		frogQueue1.get(newfrogposition).number = number;
		frogQueue1.get(newfrogposition).direction = direction;
		
		//还要重置一遍每个青蛙的canjump
		setCanJump(frogQueue1);
		return frogQueue1;
	}
	
	private static void setCanJump(List<Frog> frogQueue) {
		for (int i = 0; i < frogQueue.size(); i++) {
			if (frogQueue.get(i).isEmpty) {
				continue;
			}
			//如果青蛙是向右跳的，前面两格之内有number为3的，就能跳
			if (frogQueue.get(i).direction == 'r') {
				if ( (i<6 && frogQueue.get(i+1).number == 4) || (i<5 && frogQueue.get(i+2).number == 4)) {
					frogQueue.get(i).canJump = true;
				} else {
					frogQueue.get(i).canJump =false;
				}
			}else {
				if (frogQueue.get(i).direction == 'l') {
					if ( (i>0 && frogQueue.get(i-1).number == 4) || (i>1 && frogQueue.get(i-2).number == 4)) {
						frogQueue.get(i).canJump = true;
					} else {
						frogQueue.get(i).canJump =false;
					}
				}
			}
		}
		
	}


	private static boolean isComplete(List<Frog> frogQueue) {
		for (int i = 0; i < 3; i++) {
			if (frogQueue.get(i).number < 3) {
				return false;
			}
			if (frogQueue.get(i+4).number > 3) {
				return false;
			}
		}
		return true;
	}
	
	private static List<Frog> initFrogQueue() {
		List<Frog> frogQueue = new ArrayList();
		frogQueue.add(new Frog(1, 0, 'r', false));
		frogQueue.add(new Frog(2, 1, 'r', true));
		frogQueue.add(new Frog(3, 2, 'r', true));
		frogQueue.add(new Frog(4, 3,true));
		frogQueue.add(new Frog(5, 4, 'l', true));
		frogQueue.add(new Frog(6, 5, 'l', true));
		frogQueue.add(new Frog(7, 6, 'l', false));
		return frogQueue;
	}
	
	
	public static void main(String[] args) {
		List<Frog> frogQueue = initFrogQueue();
		System.out.println(frogJumpCount(frogQueue, 3));
	}
	
	private static boolean canFrogJump(List<Frog> frogQueue) {
		for (int i = 0; i < frogQueue.size() ; i++) {
			if (frogQueue.get(i).canJump == true) {
				return true;
			}
		}
		return false;
	}
}  
