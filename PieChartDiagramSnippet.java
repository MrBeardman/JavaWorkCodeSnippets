import de.jenomics.jst.core.AbstractDiagram;
import de.jenomics.jst.bm.dailyproductivityreport.DailyProductivityReportString;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import static java.lang.Double.parseDouble;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JComponent;

/**
 *
 * @author honza
 */
public class MtShareDiagram extends AbstractDiagram
{
/*
	public String replaceAll(String regex, String replacement)
	{
		return Pattern.compile(regex).matcher((CharSequence) this).replaceAll(replacement);
	}
*/

	@Override
	protected void render(Graphics2D g2D, int width, int height, Object... data)
	{
		assert g2D != null;
		assert data != null;
		assert data[0] instanceof String;

		// asign value of dailyTurnoverMT1ShareString
		String dailyTurnoverMT1ShareString = (String) data[0];

		//declare variable outside of if statement so its accesible
		double dailyTurnoverMT1ShareDegrees = 0;
		// check if String contains "%"/ so its not coming empty from database
		if (dailyTurnoverMT1ShareString.contains("%")) {
			//remove "%" so String can be converted to double
			String dailyTurnoverMT1ShareStringStripped = dailyTurnoverMT1ShareString.replace("%", "");
			//convert String to double
			double dailyTurnoverMT1ShareDouble = parseDouble(dailyTurnoverMT1ShareStringStripped);
			//convert double to degrees in a circle
			dailyTurnoverMT1ShareDegrees = (dailyTurnoverMT1ShareDouble / 100) * 360;
		}

		String dailyTurnoverMT2ShareString = (String) data[1];
		
		double dailyTurnoverMT2ShareDegrees = 0;
		if (dailyTurnoverMT2ShareString.contains("%")) {
			String dailyTurnoverMT2ShareStringStripped = dailyTurnoverMT2ShareString.replace("%", "");
			double dailyTurnoverMT2ShareDouble = parseDouble(dailyTurnoverMT2ShareStringStripped);
			dailyTurnoverMT2ShareDegrees = (dailyTurnoverMT2ShareDouble / 100) * 360;
		}

		String dailyTurnoverMT3ShareString = (String) data[2];

		double dailyTurnoverMT3ShareDegrees = 0;
		if (dailyTurnoverMT3ShareString.contains("%")) {
			String dailyTurnoverMT3ShareStringStripped = dailyTurnoverMT3ShareString.replace("%", "");
			//convert String to double
			double dailyTurnoverMT3ShareDouble = parseDouble(dailyTurnoverMT3ShareStringStripped);
			dailyTurnoverMT3ShareDegrees = (dailyTurnoverMT3ShareDouble / 100) * 360;
		}

		
		//the jenomics color codes from https://team18.jenomics.local/confluence/display/PROZ/JEN_1+Corporate+Design#JEN_1CorporateDesign-Farben
		Color JBlue = new Color(12,130,192);
		Color JGreen = new Color(86,198,87);
		Color JOrange = new Color(243, 113, 35);
		
		// background
		g2D.setColor(Color.white);
		g2D.fillRect(0, 0, width, height);
		
		
		Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
		arc.setFrame(10,(height -150)/2, 150, 150);

		// 0 - 80 degrees MT1 degrees (13,52%)
		arc.setAngleStart(0);
		arc.setAngleExtent(dailyTurnoverMT1ShareDegrees);//48,672
		g2D.setColor(Color.gray);
		g2D.draw(arc);
		g2D.setColor(JGreen);
		g2D.fill(arc);
		g2D.fillRect(250, 75, 15, 15);
		g2D.setColor(Color.black);
		g2D.drawString("MT1",285,87);
		g2D.drawString(dailyTurnoverMT1ShareString,315,87);

		// 80 - 200 degrees (51,27%))
		arc.setAngleStart(dailyTurnoverMT1ShareDegrees);//48,672
		arc.setAngleExtent(dailyTurnoverMT2ShareDegrees); //184.572
		g2D.setColor(Color.gray);
		g2D.draw(arc);
		g2D.setColor(JBlue);
		g2D.fill(arc);
		g2D.fillRect(250, 105, 15, 15);
		g2D.setColor(Color.black);
		g2D.drawString("MT2",285,117);
		g2D.drawString(dailyTurnoverMT2ShareString,315,117);
		
		

		// 200 - 360 degrees (38,25%)
		arc.setAngleStart(dailyTurnoverMT1ShareDegrees + dailyTurnoverMT2ShareDegrees); //233,244
		arc.setAngleExtent(dailyTurnoverMT3ShareDegrees);//137.7
		g2D.setColor(Color.gray);
		g2D.draw(arc);
		g2D.setColor(JOrange);
		g2D.fill(arc);
		g2D.fillRect(250, 135, 15, 15);
		g2D.setColor(Color.black);
		g2D.drawString("MT3",285,147);
		g2D.drawString(dailyTurnoverMT3ShareString,315,147);
		
		
	}

}