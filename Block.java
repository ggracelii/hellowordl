import java.awt.Color;
import java.awt.Graphics;

public class Block {
	private int side;
	private int xPos;
	private int yPos;
	private int height;
	private int width;
	private Color color;

	public Block() {
		side = 10;
		xPos = 10;
		yPos = 10;
		color = Color.black;
	}

	public Block(int side, int xPos, int yPos) {
		this.side = side;
		this.height = side;
		this.width = side;
		this.xPos = xPos;
		this.yPos = yPos;
		color = Color.black;
	}

	public Block(int side, int xPos, int yPos, Color color) {
		this.side = side;
		this.height = side;
		this.width = side;
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = color;
	}

	public Block(int width, int height, int xPos, int yPos) {
		this.height = height;
		this.width = width;
		this.xPos = xPos;
		this.yPos = yPos;
		color = Color.black;
	}

	public void draw(Graphics window) {
		window.setColor(color);
		window.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	public void draw(Graphics window, Color col) {
		window.setColor(col);
		window.fillRect(getX(), getY(), getSide(), getSide());
	}

	public void setColor(Color col) {
		color = col;
	}

	public void setX(int x) {
		xPos = x;
	}

	public void setY(int y) {
		yPos = y;
	}

	public void setPos(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void setSide(int side) {
		this.side = side;
		width = side;
		height = side;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public int getSide() {
		return side;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
}