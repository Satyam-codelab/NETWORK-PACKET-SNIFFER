import java.awt.*;
import javax.swing.border.Border;

public class RoundedBorder implements Border
{
    private int r;
    RoundedBorder(int r) {
        this.r = r;
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(this.r+1, this.r+1, this.r+2, this.r);
    }
    public boolean isBorderOpaque() {
        return true;
    }

   // public RoundedBorder frame= BorderFactory.createLineBorder(Color.BLUE, 5);

    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, r, r);
    }
}