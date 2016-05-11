package com.example.game;

import interfaces.IDrawable;
import interfaces.IUpdateable;
import android.content.res.Resources;
import android.graphics.Canvas;

public class Figure implements IDrawable, IUpdateable {
	LifeBlock bl[] = new LifeBlock[4];
	int side;
	int random;
	boolean CanRight = true;
	boolean CanLeft = true;
	boolean CanGo;
	int size;
	int form = 1;
	boolean moveable;

	public Figure(int size, int x, int y, Resources res, int random) {
		this.random = random;
		this.size = size;
		CanGo = true;
		switch (random) {
		case 1:
			Form1(size, x, y, res);
			break;
		case 2:
			Form2(size, x, y, res);
			break;
		case 3:
			Form3(size, x, y, res);
			break;
		case 4:
			Form4(size, x, y, res);
			break;
		case 5:
			Form5(size, x, y, res);
			break;
		case 6:
			Form6(size, x, y, res);
			break;
		case 7:
			Form7(size, x, y, res);
			break;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		bl[0].draw(canvas);
		bl[1].draw(canvas);
		bl[2].draw(canvas);
		bl[3].draw(canvas);

	}

	public void Form1(int size, int x, int y, Resources res) { // пр€мой
		bl[0] = new LifeBlock(size, x, y, res, this, 1);
		bl[1] = new LifeBlock(size, x, y + size, res, this, 1);
		bl[2] = new LifeBlock(size, x, y + 2 * size, res, this, 1);
		bl[3] = new LifeBlock(size, x, y + 3 * size, res, this, 1);
	}

	public void Form2(int size, int x, int y, Resources res) { // √-образный
																// слева
		bl[0] = new LifeBlock(size, x - size, y, res, this, 2);
		bl[1] = new LifeBlock(size, x, y, res, this, 2);
		bl[2] = new LifeBlock(size, x, y + size, res, this, 2);
		bl[3] = new LifeBlock(size, x, y + 2 * size, res, this, 2);
		;
	}

	public void Form3(int size, int x, int y, Resources res) { // √-образный
																// справа
		bl[0] = new LifeBlock(size, x + size, y, res, this, 3);
		bl[1] = new LifeBlock(size, x, y, res, this, 3);
		bl[2] = new LifeBlock(size, x, y + size, res, this, 3);
		bl[3] = new LifeBlock(size, x, y + 2 * size, res, this, 3);
	}

	public void Form4(int size, int x, int y, Resources res) { // лесенка-образный
																// слева
		bl[0] = new LifeBlock(size, x - size, y, res, this, 4);
		bl[1] = new LifeBlock(size, x, y, res, this, 4);
		bl[2] = new LifeBlock(size, x, y + size, res, this, 4);
		bl[3] = new LifeBlock(size, x + size, y + size, res, this, 4);
	}

	public void Form5(int size, int x, int y, Resources res) { // лесенка-образный
																// справа
		bl[0] = new LifeBlock(size, x + size, y, res, this, 5);
		bl[1] = new LifeBlock(size, x, y, res, this, 5);
		bl[2] = new LifeBlock(size, x, y + size, res, this, 5);
		bl[3] = new LifeBlock(size, x - size, y + size, res, this, 5);
	}

	public void Form6(int size, int x, int y, Resources res) { // квадрат
		bl[0] = new LifeBlock(size, x + size, y, res, this, 6);
		bl[1] = new LifeBlock(size, x, y, res, this, 6);
		bl[2] = new LifeBlock(size, x, y + size, res, this, 6);
		bl[3] = new LifeBlock(size, x + size, y + size, res, this, 6);
	}

	public void Form7(int size, int x, int y, Resources res) { // пирамида
		bl[0] = new LifeBlock(size, x, y, res, this, 7);
		bl[1] = new LifeBlock(size, x, y + size, res, this, 7);
		bl[2] = new LifeBlock(size, x - size, y + size, res, this, 7);
		bl[3] = new LifeBlock(size, x + size, y + size, res, this, 7);
	}

	public void Turn(int x, int y) {
		switch (random) {
		case 1:
			SetForm1(x, y);
			break;
		case 2:
			SetForm2(x, y);
			break;
		case 3:
			SetForm3(x, y);
			break;
		case 4:
			SetForm4(x, y);
			break;
		case 5:
			SetForm5(x, y);
			break;
		case 6:
			break;
		case 7:
			SetForm7(x, y);
			break;
		default:
			break;
		}
	}

	void SetForm1(int x, int y) {
		if (form == 1) {
			if (TryMove(-2, -1, 0, 1, 2, 1, 0, -1)) {
				bl[0].SetCoods(-2 + x, 2 + y);
				bl[1].SetCoods(-1 + x, 1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(1 + x, -1 + y);
				form = 0;
			}
		} else {
			if (TryMove(2, 1, 0, -1, -2, -1, 0, 1)) {
				bl[0].SetCoods(2 + x, -2 + y);
				bl[1].SetCoods(1 + x, -1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(-1 + x, 1 + y);
				form = 1;
			}
		}
	}

	void SetForm2(int x, int y) { // желтый
		switch (form) {
		case 1: {
			if (TryMove(2, 1, 0, -1, 0, 1, 0, -1)) {
				bl[0].SetCoods(2 + x, 0 + y);
				bl[1].SetCoods(1 + x, 1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(-1 + x, -1 + y);
			}
			form = 2;
			break;
		}
		case 2: {
			if (TryMove(0, -1, 0, 1, 2, 1, 0, -1)) {
				bl[0].SetCoods(0 + x, 2 + y);
				bl[1].SetCoods(-1 + x, 1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(1 + x, -1 + y);
			}
			form = 3;
			break;
		}
		case 3: {
			if (TryMove(-2, -1, 0, 1, 0, -1, 0, 1)) {
				bl[0].SetCoods(-2 + x, 0 + y);
				bl[1].SetCoods(-1 + x, -1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(1 + x, 1 + y);
			}
			form = 0;
			break;
		}
		case 0: {
			if (TryMove(0, 1, 0, -1, -2, -1, 0, 1)) {
				bl[0].SetCoods(0 + x, -2 + y);
				bl[1].SetCoods(1 + x, -1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(-1 + x, 1 + y);
			}
			form = 1;
			break;
		}
		}
	}

	public void ResetForm() {
		form = 1;
	}

	void SetForm3(int x, int y) { // синий
		switch (form) {
		case 1: {
			if (TryMove(0, 1, 0, -1, 2, 1, 0, -1)) {
				bl[0].SetCoods(0 + x, 2 + y);
				bl[1].SetCoods(1 + x, 1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(-1 + x, -1 + y);
			}
			form = 2;
			break;
		}
		case 2: {
			if (TryMove(-2, -1, 0, 1, 0, 1, 0, -1)) {
				bl[0].SetCoods(-2 + x, 0 + y);
				bl[1].SetCoods(-1 + x, 1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(1 + x, -1 + y);
			}
			form = 3;
			break;
		}
		case 3: {
			if (TryMove(0, -1, 0, 1, -1, -1, 0, 1)) {
				bl[0].SetCoods(x, -2 + y);
				bl[1].SetCoods(-1 + x, -1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(1 + x, 1 + y);
			}
			form = 0;
			break;
		}
		case 0: {
			if (TryMove(2, 1, 0, -1, 0, -1, 0, 1)) {
				bl[0].SetCoods(2 + x, y);
				bl[1].SetCoods(1 + x, -1 + y);
				bl[2].SetCoods(0 + x, 0 + y);
				bl[3].SetCoods(-1 + x, 1 + y);
			}
			form = 1;
			break;
		}
		}
	}

	void SetForm4(int x, int y) {
		if (form == 1) {
			if (TryMove(1, 0, -1, -2, -1, 0, -1, 0)) {
				bl[0].SetCoods(1 + x, -1 + y);
				bl[1].SetCoods(0 + x, 0 + y);
				bl[2].SetCoods(-1 + x, -1 + y);
				bl[3].SetCoods(-2 + x, 0 + y);
			}
			form = 0;
		} else {
			if (TryMove(-1, 0, 1, 2, 1, 0, 1, 0)) {
				bl[0].SetCoods(-1 + x, 1 + y);
				bl[1].SetCoods(0 + x, 0 + y);
				bl[2].SetCoods(1 + x, 1 + y);
				bl[3].SetCoods(2 + x, 0 + y);
			}
			form = 1;
		}
	}

	void SetForm5(int x, int y) {
		if (form == 1) {
			if (TryMove(-1, 0, 1, 2, -1, 0, -1, 0)) {
				bl[0].SetCoods(-1 + x, -1 + y);
				bl[1].SetCoods(0 + x, 0 + y);
				bl[2].SetCoods(1 + x, -1 + y);
				bl[3].SetCoods(2 + x, 0 + y);
			}
			form = 0;
		} else {
			if (TryMove(1, 0, -1, -2, 1, 0, 1, 0)) {
				bl[0].SetCoods(1 + x, 1 + y);
				bl[1].SetCoods(0 + x, 0 + y);
				bl[2].SetCoods(-1 + x, 1 + y);
				bl[3].SetCoods(-2 + x, 0 + y);
			}
			form = 1;
		}
	}

	void SetForm7(int x, int y) { // синий
		switch (form) {
		case 1: {
			if (TryMove(1, 0, 1, -1, 1, 0, -1, 1)) {
				bl[0].SetCoods(1 + x, 1 + y);
				bl[1].SetCoods(0 + x, 0 + y);
				bl[2].SetCoods(1 + x, -1 + y);
				bl[3].SetCoods(-1 + x, 1 + y);
			}
			form = 2;
			break;
		}
		case 2: {
			if (TryMove(-1, 0, 1, -1, 1, 0, 1, -1)) {
				bl[0].SetCoods(-1 + x, 1 + y);
				bl[1].SetCoods(0 + x, 0 + y);
				bl[2].SetCoods(1 + x, 1 + y);
				bl[3].SetCoods(-1 + x, -1 + y);
			}
			form = 3;
			break;
		}
		case 3: {
			if (TryMove(-1, 0, -1, 1, -1, 0, 1, -1)) {
				bl[0].SetCoods(-1 + x, -1 + y);
				bl[1].SetCoods(0 + x, 0 + y);
				bl[2].SetCoods(-1 + x, 1 + y);
				bl[3].SetCoods(1 + x, -1 + y);
			}
			form = 4;
			break;
		}
		case 4: {
			if (TryMove(1, 0, -1, 1, -1, 0, -1, 1)) {
				bl[0].SetCoods(1 + x, -1 + y);
				bl[1].SetCoods(0 + x, 0 + y);
				bl[2].SetCoods(-1 + x, -1 + y);
				bl[3].SetCoods(1 + x, 1 + y);
			}
			form = 1;
			break;
		}
		}
	}

	boolean TryMove(int x0, int x1, int x2, int x3, int y0, int y1, int y2,
			int y3) {
		moveable = true;
		if (!(bl[0].Move(x0, y0)))
			moveable = false;
		if (!(bl[1].Move(x1, y1)))
			moveable = false;
		if (!(bl[2].Move(x2, y2)))
			moveable = false;
		if (!(bl[3].Move(x3, y3)))
			moveable = false;
		return moveable;
	}

	public LifeBlock ReturnBL1() {
		return bl[0];
	}

	public LifeBlock ReturnBL2() {
		return bl[1];
	}

	public LifeBlock ReturnBL3() {
		return bl[2];
	}

	public LifeBlock ReturnBL4() {
		return bl[3];
	}

	public boolean CanGoB() {
		if (bl[0].canGOB && bl[1].canGOB && bl[2].canGOB && bl[3].canGOB)
			return true;
		else
			return false;
	}

	@Override
	public void update(int side, boolean canY) {
		if (bl[0].canGOLeft && bl[1].canGOLeft && bl[2].canGOLeft
				&& bl[3].canGOLeft) {
			CanLeft = true;
		} else {
			CanLeft = false;
		}

		if (bl[0].canGORight && bl[1].canGORight && bl[2].canGORight
				&& bl[3].canGORight) {
			CanRight = true;
		} else {
			CanRight = false;
		}
		if (CanGoB())
			CanGo = true;
		else
			CanGo = false;

		bl[0].update(side, canY);
		bl[1].update(side, canY);
		bl[2].update(side, canY);
		bl[3].update(side, canY);
		bl[0].canGo();
		bl[1].canGo();
		bl[2].canGo();
		bl[3].canGo();
	}

}
