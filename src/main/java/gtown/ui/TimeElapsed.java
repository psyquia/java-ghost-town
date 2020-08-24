package gtown.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimeElapsed {
	public long timeElapsed = 0;
	public String timeString = "test";
	private Instant start;
	
	public TimeElapsed() {
		start = Instant.now();
		start();
	}
	
	/**
	 * Render the timer to canvas
	 * @param g graphics2D object
	 */
	public void render(Graphics2D g) {
		Font font = new Font("bahnschrift", Font.PLAIN, 24);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(timeString, 500, 30);
	}
	
	/**
	 * Start the timer
	 */
	public void start() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {			// use timer to set game tick rate
			public void run() {
				Instant finish = Instant.now();
				timeElapsed = Duration.between(start, finish).toMillis();
				timeString = String.format("%d min, %d sec", 
					    TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
					    TimeUnit.MILLISECONDS.toSeconds(timeElapsed) - 
					    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed))
					);
			}								
		}, 0, 1000);		
	}
	
	/**
	 * Reset the timer
	 */
	public void reset() {
		start = Instant.now();
		timeElapsed = 0;
	}
}
