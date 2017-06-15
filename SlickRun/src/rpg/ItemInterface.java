package rpg;

import java.util.ArrayList;

public interface ItemInterface {
	int getdef();
	int getAttack();
	String getName();
	int getAttackSpd();
	int getNum();
	void incNum(int num);
	int getStackNum();
	int getHeal();
}
