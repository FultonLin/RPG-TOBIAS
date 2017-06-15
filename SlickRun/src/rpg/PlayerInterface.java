package rpg;

public interface PlayerInterface {
	double getYuushaX();
	double getYuushaY();
	void setYuushaX(double yuushaX);
	void setYuushaY(double yuushaY);
	int getHp();
	void decHp(int dmg);
	int getspd();
	int getDmg(); 
}
