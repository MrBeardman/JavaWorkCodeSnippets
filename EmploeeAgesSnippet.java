import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author Benjamin Schiller
 */
public class EmployeeDiagram extends AbstractDiagram
{

	@Override
	protected void render(Graphics2D g2D, int width, int height, Object... data)
	{
		assert g2D != null;
		assert data[0] instanceof List;

		List<Employee> employees = (List<Employee>) data[0];

		// background
		g2D.setColor(Color.white);
		g2D.fillRect(0, 0, width, height);

		int[] ages = new int[101];

		for (Employee employee : employees) {
			ages[employee.getAge()]++;
		}

		int cellWidth = (width - 10) / 10;
		int cellHeight = (height - 10) / 10;

		int xc;
		int yc;

		for (int i = 0; i < 100; ++i) {

			xc = 5 + (i % 10) * cellWidth;
			yc = 5 + (i / 10) * cellHeight;

			// at least on employee has that age -> green
			if (ages[i + 1] > 0) {
				g2D.setColor(new Color(86, 198, 87));
			} // otherwise -> grey
			else {
				g2D.setColor(new Color(230, 230, 230));
			}

			g2D.fillRect(xc + 2, yc + 2, cellWidth - 4, cellHeight - 4);
		}
	}
}