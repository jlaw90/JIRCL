package org.newbiehacker.conlib.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

final class InformationDialog extends JWindow {
    private final String title;
    private final String content;
    public Font titleFont;
    public Font contentFont;
    public Color titleForeground;
    public Color titleBackground;
    public Color contentForeground;
    public Color contentBackground;
    private BufferedImage render;

    InformationDialog(String title, String content, Font titleFont, Font contentFont, Color titleForeground,
                      Color contentForeground, Color titleBackground, Color contentBackground) {
        this.title = title;
        this.content = content;
        this.titleFont = titleFont;
        this.contentFont = contentFont;
        this.titleForeground = titleForeground;
        this.titleBackground = titleBackground;
        this.contentForeground = contentForeground;
        this.contentBackground = contentBackground;
        setAlwaysOnTop(true);
    }

    private void ensureSafePack() {
        if(titleFont == null)
            titleFont = new Font("Tahoma", Font.BOLD, 12);
        if(contentFont == null)
            contentFont = new Font("Tahoma", Font.PLAIN, 10);
        if(titleForeground == null)
            titleForeground = Color.BLACK;
        if(titleBackground == null)
            titleBackground = Color.LIGHT_GRAY;
        if(contentForeground == null)
            contentForeground = Color.BLACK;
        if(contentBackground == null)
            contentBackground = Color.WHITE;
    }
    
    public void pack() {
        Dimension pref = getPreferredSize();
        int width = pref.width;
        int height = pref.height;
        String[] titleLines = title.split("\n");
        String[] contentLines = content.split("\n");
        ensureSafePack();
        FontMetrics tm = getFontMetrics(titleFont);
        FontMetrics cm = getFontMetrics(contentFont);
        int tHeight = tm.getHeight() * titleLines.length;
        int cHeight = cm.getHeight() * contentLines.length;
        if(tHeight + cHeight > height)
            height = tHeight + cHeight;
        int sWidth;
        for(String s: titleLines) {
            sWidth = tm.stringWidth(s);
            if(sWidth > width)
                width = sWidth;
        }
        for(String s: contentLines) {
            sWidth = cm.stringWidth(s);
            if(sWidth > width)
                width = sWidth;
        }
        setPreferredSize(new Dimension(width, height));
        render = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = render.getGraphics();

        // Draw the backgrounds
        g.setColor(titleBackground);
        g.fillRect(0, 0, width, tHeight);
        g.setColor(contentBackground);
        g.fillRect(0, tHeight, width, height - tHeight);

        // Draw the text
        int y = titleFont.getSize();
        g.setColor(titleForeground);
        g.setFont(titleFont);
        for(String t: titleLines) {
            g.drawString(t, (width - tm.stringWidth(t)) / 2, y);
            y += titleFont.getSize();
        }
        g.setColor(contentForeground);
        g.setFont(contentFont);
        y = tHeight + contentFont.getSize();
        for(String c: contentLines) {
            g.drawString(c, (width - cm.stringWidth(c)) / 2, y);
            y += contentFont.getSize();
        }

        // And done, now call super.pack
        super.pack();
    }

    public void dispose() {
        super.dispose();
        titleFont = null;
        contentFont = null;
        titleForeground = null;
        titleBackground = null;
        contentForeground = null;
        contentBackground = null;
        render = null;
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        g.drawImage(render, 0, 0, null);
    }
}