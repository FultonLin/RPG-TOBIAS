package rpg;

import java.util.ArrayList;

public interface ItemInterface {
	int getdef();
	int getAttack();
	String getName();
	int getAttackSpd();
	void incNum(int num);
	int getStackNum();
	int getHeal();
}
