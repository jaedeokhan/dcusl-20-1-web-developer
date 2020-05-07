package _0429WED;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InterfaceEx2 extends Frame implements WindowListener{
	
	public InterfaceEx2() {
		setSize(300, 300);
		setVisible(true);
		addWindowListener(this);
	}

	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InterfaceEx2();
		
		// 창을 닫는 기능 구현하기.
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}
