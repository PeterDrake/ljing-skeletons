import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KlondikeTest {

	private Klondike klondike;
	
	@Before
	public void setUp() throws Exception {
		klondike = new Klondike();
	}

	@Test
	public void testImageFilename() {
		Card c = new Card(3, Card.HEARTS);
		assertEquals("card-images" + java.io.File.separator + "47.png", klondike.imageFilename(c));
		c.setFaceUp(false);
		assertEquals("card-images" + java.io.File.separator + "b2fv.png", klondike.imageFilename(c));		
	}

}
