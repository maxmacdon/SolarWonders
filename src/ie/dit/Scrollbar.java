package ie.dit;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Scrollbar
{
	PApplet parent;
	int id, r;
	float width, height;
	PVector infoLocation, infoSize;
	float sPos, newSPos;			// x position of slider
	float sPosMin, sPosMax;
	boolean over, locked;
	float barWidth, barMinLoc, barMaxLoc, barYLoc;
	
	Scrollbar(PApplet p, int id, float iLocX, float iLocY, float iSizeX, float iSizeY, float barYLoc)
	{
		parent = p;
		width = p.width;
		height = p.height;
		this.id = id;
		infoLocation = new PVector(iLocX, iLocY);
		infoSize = new PVector(iSizeX, iSizeY);
		infoLocation.x = iLocX;
		infoLocation.y = iLocY;
		infoSize.x = iSizeX;
		infoSize.y = iSizeY;
		barMinLoc = infoLocation.x + infoSize.x * .25f;
		barMaxLoc = infoLocation.x + infoSize.x * .75f;
		this.barYLoc = barYLoc;
		barWidth = barMaxLoc - barMinLoc;
		sPosMin = barMinLoc;
		sPosMax = barMaxLoc;
		sPos = barMinLoc + barWidth / 2;
		newSPos = sPos;
		r = 10;
	}
	
	public void display()
	{
		parent.fill(247, 255, 28);
		parent.textAlign(PConstants.CENTER);
		parent.text("SIZE:", infoLocation.x + infoSize.x / 2 + -(parent.screenX(0, 0)),
				infoLocation.y + infoSize.y * .1f + -(parent.screenY(0, 0)));
		parent.text("DIST:", infoLocation.x + infoSize.x / 2 + -(parent.screenX(0, 0)),
				infoLocation.y + infoSize.y * .3f + -(parent.screenY(0, 0)));
		
		if (over || locked)
		{
			parent.strokeWeight(2);
			parent.stroke(247, 255, 28);
			parent.fill(247, 255, 28);
			parent.ellipse(sPos + -(parent.screenX(0, 0)), barYLoc + -(parent.screenY(0, 0)), r, r);
			parent.stroke(0, 157, 219);
			parent.strokeWeight(2);
			parent.line(barMinLoc + -(parent.screenX(0, 0)), barYLoc + -(parent.screenY(0, 0)),
					barMaxLoc + -(parent.screenX(0, 0)), barYLoc + -(parent.screenY(0, 0)));
		}
		else
		{

			parent.strokeWeight(2);
			parent.stroke(247, 255, 28);
			parent.fill(119, 112, 127);
			parent.ellipse(sPos + -(parent.screenX(0, 0)), barYLoc + -(parent.screenY(0, 0)), r, r);
			parent.stroke(0, 157, 219);
			parent.strokeWeight(2);
			parent.line(barMinLoc + -(parent.screenX(0, 0)), barYLoc + -(parent.screenY(0, 0)),
					barMaxLoc + -(parent.screenX(0, 0)), barYLoc + -(parent.screenY(0, 0)));
		}
	}
	
	public void update()
	{
		if (overEvent())
		{
			over = true;
		}
		else
		{
			over = false;
		}
		
		if (parent.mousePressed && over)
		{
			locked = true;
		}
		
		if (!parent.mousePressed)
		{
			locked = false;
		}
		
		if (locked) 
		{
			newSPos = parent.mouseX;
	    }
		sPos = newSPos;
		
		if (sPos > barMaxLoc)
		{
			sPos = barMaxLoc;
		}
		
		if (sPos < barMinLoc)
		{
			sPos = barMinLoc;
		}
	}
	
	public float sliderStart()
	{
		return barMinLoc + barWidth / 2;
	}
	
	public boolean overEvent()
	{
		if (parent.mouseX + -(parent.screenX(0, 0)) > sPos + -(parent.screenX(0, 0)) - r
					&& parent.mouseX + -(parent.screenX(0, 0)) < sPos + -(parent.screenX(0, 0)) + r
					&& parent.mouseY + -(parent.screenY(0, 0)) > barYLoc + -(parent.screenY(0, 0)) - r
					&& parent.mouseY + -(parent.screenY(0, 0)) < barYLoc + -(parent.screenY(0, 0)) + r)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}