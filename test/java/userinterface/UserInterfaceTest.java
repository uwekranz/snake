package userinterface;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import application.SnakeGame;

public class UserInterfaceTest {
	private UserInterface userInterface;

	@Before
	public void setUp() {
		userInterface = new UserInterface();
	}

	@Test
	public void itShould_BeVisible_WhenGameStarted() {
		new SnakeGame().startWithUserInsterface(userInterface);

		assertThat(userInterface.isVisible(), is(true));
	}

}
