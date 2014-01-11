package Sporcle;

public final class Order {
	public static int generate(int xLoc, int yLoc, Order.TYPE type){
		return ((((xLoc & 0x007F) << 7) | (yLoc & 0x007F)) << 2) | (type.value & 0x003);
	}
	
	public static int getXLoc(int message){
		return (message >> 9) & 0x007F;
	}
	
	public static int getYLoc(int message){
		return (message >> 2) & 0x007F;
	}
	
	public static TYPE getType(int message){
		return TYPE.values()[message & 0x0003];
	}
	
	public enum TYPE{
		ATK (0),
		DEF (1),
		HERD (2),
		PASTR (3);
		private final int value;
		TYPE(int v){
			value = v;
		}
	}
}

