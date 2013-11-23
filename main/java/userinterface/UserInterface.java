package userinterface;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class UserInterface extends JFrame {

	private CageGraphic cageGraphic;

	public UserInterface() {
		super();
		this.cageGraphic = new CageGraphic();
		setContentPane(cageGraphic);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public UserInterface(CageGraphic cageGraphic) {
		super();
		this.cageGraphic = cageGraphic;
		setContentPane(cageGraphic);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void display() {
		setVisible(true);
	}

	public Boolean hasComponentOfType(Class<? extends TypedJComponent> type) {
		TypedJComponent[] typedComponents = this.getTypedComponents();
		for (TypedJComponent typedComponent : typedComponents) {
			if (typedComponent.getSubClass() == type) {
				return true;
			}
		}
		return false;
	}

	public Boolean hasContentPaneOfType(Class<? extends TypedJComponent> type) {
		return ((TypedJComponent) getContentPane()).getSubClass() == type;
	}

	private TypedJComponent[] getTypedComponents() {
		Container contentPane = this.getContentPane();
		Component[] components = contentPane.getComponents();
		TypedJComponent[] typedComponents = new TypedJComponent[components.length + 1];
		for (int index = 0; index < components.length; index++) {
			typedComponents[index] = (TypedJComponent) components[index];
		}
		typedComponents[components.length] = (TypedJComponent) contentPane;
		return typedComponents;
	}

	public CageGraphic getCageGraphic() {
		return (CageGraphic) getContentPane();
	}

	public void setCageGraphic(CageGraphic cageGraphic) {
		this.cageGraphic = cageGraphic;
	}

}
