package rpg;

public interface PlayerInterface {
	double getYuushaX();
	double getYuushaY();
	void setYuushaX(double yuushaX);
	void setYuushaY(double yuushaY);
	int getDmg();
	int getHp();
	void decHp(int dmg);
	int getspd(); 
}
 
