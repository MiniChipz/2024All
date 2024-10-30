import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class ConfettiPanel extends JPanel {
    private final ArrayList<Confetti> confettiPieces = new ArrayList<>();
    private Timer animationTimer;
    private final Random random = new Random();

    public ConfettiPanel() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        setupTimer();
    }

    private void setupTimer() {
        animationTimer = new Timer(16, e -> {
            updateConfetti();
            repaint();
        });
    }

    public void startConfetti() {
        confettiPieces.clear();
        for (int i = 0; i < 1000; i++) {
            confettiPieces.add(new Confetti(
                    random.nextInt(getWidth()),
                    -random.nextInt(50)
            ));
        }
        animationTimer.start();
    }

    private void updateConfetti() {
        boolean allSettled = true;
        for (Confetti confetti : confettiPieces) {
            confetti.update();
            if (!confetti.isSettled()) {
                allSettled = false;
            }
        }
        if (allSettled) {
            animationTimer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Confetti confetti : confettiPieces) {
            confetti.draw(g2d);
        }
    }

    private class Confetti {
        private double x, y;
        private double velocityX, velocityY;
        private final Color color;
        private boolean settled;
        private final int size;
        private final double rotationAngle;
        private double currentRotation;

        public Confetti(int startX, int startY) {
            this.x = startX;
            this.y = startY;
            this.velocityX = random.nextDouble() * 8 - 4;
            this.velocityY = random.nextDouble() * 2 + 1;
            this.color = new Color(
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255)
            );
            this.settled = false;
            this.size = random.nextInt(6) + 4;
            this.rotationAngle = random.nextDouble() * 0.2 - 0.1;
            this.currentRotation = random.nextDouble() * Math.PI * 2;
        }

        public void update() {
            if (settled) return;

            x += velocityX;
            y += velocityY;
            velocityY += 0.15;
            currentRotation += rotationAngle;

            velocityX += (random.nextDouble() - 0.5) * 0.3;

            if (y > getHeight()) {
                y = getHeight();
                settled = true;
            }
        }

        public void draw(Graphics2D g) {
            g.setColor(color);

            AffineTransform oldTransform = g.getTransform();

            g.translate(x, y);
            g.rotate(currentRotation);

            int[] xPoints = {0, size/2, 0, -size/2};
            int[] yPoints = {-size/2, 0, size/2, 0};
            g.fillPolygon(xPoints, yPoints, 4);

            g.setTransform(oldTransform);
        }

        public boolean isSettled() {
            return settled;
        }
    }
}

class ConfettiController {
    private final String[] randomStrings = {
            "DO GUITAR",
            "PROGRAM C#"
    };

    private final Random random = new Random();

    public String handleButtonClick() {
        return getRandomString();
    }

    private String getRandomString() {
        return randomStrings[random.nextInt(randomStrings.length)];
    }
}
