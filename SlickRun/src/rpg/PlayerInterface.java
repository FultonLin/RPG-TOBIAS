package rpg;

public interface PlayerInterface {
	double getYuushaX();
	double getYuushaY();
	void setYuushaX(double yuushaX);
	void setYuushaY(double yuushaY);
	int getDmg();
	int getHp();
	int getAtkspd();
	void decHp(int dmg); 
}
 
