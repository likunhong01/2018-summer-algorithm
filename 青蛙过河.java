package ������Ŀ;



import java.util.ArrayList;  
import java.util.List;  

class Frog{
	public int number;
	public int position;
	public char direction;//��������r�ң�l��
	public boolean canJump;
	public boolean isEmpty;
	//��ͨ���췽��
	public Frog(int number, int position, char direction,boolean canJump) {
		this.canJump = canJump;
		this.number = number;
		this.position = position;
		this.direction = direction;
	}
	//��λ�ù��췽��
	public Frog(int number,int position,boolean isempty) {
		this.number = number;
		this.position = position;
		this.isEmpty = isempty;
	}
	//��ĳ��forg���Ƹ���һ���Ĺ��췽��
	public Frog(Frog frog) {
		this.canJump = frog.canJump;
		this.number = frog.number;
		this.position = frog.position;
		this.direction = frog.direction;
		this.isEmpty = frog.isEmpty;
	}
}

public class ���ܹ��� {
	
	public static String frogJumpCount(List<Frog> frogQueue, int emptyPosition) {
		int frogJumpCount = 0;
		String frogjumpinfo = "";
		//�������ܶ��У�frogQueue����ÿ������
		for (int i = 0; i < frogQueue.size(); i++) {
			
			Frog nowfrog = frogQueue.get(i);
			//����ǿգ�������������������continue
			if (nowfrog.isEmpty) {
				continue;
			}
			if (!nowfrog.canJump) {
				continue;
			}
			
			//�ƶ�����
//			frogJumpCount++;
			frogjumpinfo = "����"+nowfrog.number+ " "+ nowfrog.direction + "����" + (emptyPosition+1)+"\n";
			//���Ķ���,�ƶ�����
			int newEmptyposition = nowfrog.position;
			//���뵱ǰ���У���ǰ������Ϣ���յ�λ�ã�������λ��
			List<Frog> newfrogQueue = changeQueue(frogQueue,nowfrog,emptyPosition,newEmptyposition);
			
			//���������л��������������ݹ�
			if (canFrogJump(newfrogQueue)) {
				frogjumpinfo += frogJumpCount(newfrogQueue, newEmptyposition);
//				frogJumpCount ++;
			}
			//��������������ж��Ƿ�ɹ�
			else {
				//����ɹ��˾�������
				if (isComplete(newfrogQueue)) {
					frogjumpinfo += "�ɹ�" ;
					break;
				}
				//û�ɹ��ͼ���ѭ��
			}
			if (frogjumpinfo.contains("�ɹ�")) {
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
		
		//����
		List<Frog> frogQueue1 = new ArrayList<Frog>();
		for (int i = 0; i < frogQueue.size(); i++) {
			Frog frog = new Frog(frogQueue.get(i));
			frogQueue1.add(frog);
		}
		
		//��֮ǰ���ܵ�λ���ÿ�
		frogQueue1.get(newEmptyposition).isEmpty = true;
		frogQueue1.get(newEmptyposition).number = empty.number;
		frogQueue1.get(newEmptyposition).direction = empty.direction;
		frogQueue1.get(newEmptyposition).canJump = false;
		
		//��֮ǰ�յ�λ�÷���xinde����
		frogQueue1.get(newfrogposition).isEmpty = false;
		frogQueue1.get(newfrogposition).number = number;
		frogQueue1.get(newfrogposition).direction = direction;
		
		//��Ҫ����һ��ÿ�����ܵ�canjump
		setCanJump(frogQueue1);
		return frogQueue1;
	}
	
	private static void setCanJump(List<Frog> frogQueue) {
		for (int i = 0; i < frogQueue.size(); i++) {
			if (frogQueue.get(i).isEmpty) {
				continue;
			}
			//����������������ģ�ǰ������֮����numberΪ3�ģ�������
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
