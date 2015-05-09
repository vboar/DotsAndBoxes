package ai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import system.Controller;
import entity.Line;

/**
 * EasyAI
 */
public class SocketAI extends AI {

	
	/**
	 * 构造器
	 */
	public SocketAI(int number) {
		super(number);
	}

	/**
	 * 选择一条Line来draw（AI总的核心）
	 */
//	@Override
//	public Line chooseLine() {
//			Socket s;
//			Line line = null;
//			try {
////				s = Controller.getController().serverSocket.accept();
//				byte[] b = new byte[3];
//				s.getInputStream().read(b);
//				if ((char)b[0] == 'h') {
//					line = Controller.getController().getBoard().gethLines()[(int)(b[1]-48)][(int)(b[2]-48)];
//				} else {
//					line = Controller.getController().getBoard().getvLines()[(int)(b[1]-48)][(int)(b[2]-48)];
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		return line;
//	}
	
}
