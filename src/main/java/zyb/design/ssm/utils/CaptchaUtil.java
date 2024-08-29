package zyb.design.ssm.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaUtil {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int CODE_COUNT = 4;
    private static final int FONT_HEIGHT = 30;

    private static final char[] CODE_SEQUENCE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static BufferedImage generateCaptchaImage(StringBuffer captchaCode) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        Font font = new Font("Fixedsys", Font.PLAIN, FONT_HEIGHT);
        graphics.setFont(font);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        Random random = new Random();
        int red, green, blue;

        for (int i = 0; i < 40; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            graphics.setColor(new Color(red, green, blue));
            graphics.drawLine(x, y, x + x1, y + y1);
        }

        for (int i = 0; i < CODE_COUNT; i++) {
            String code = String.valueOf(CODE_SEQUENCE[random.nextInt(CODE_SEQUENCE.length)]);
            captchaCode.append(code);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            graphics.setColor(new Color(red, green, blue));
            graphics.drawString(code, (i + 1) * 20, 30);
        }

        return image;
    }
}
