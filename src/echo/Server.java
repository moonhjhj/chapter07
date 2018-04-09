package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		// 서버 소켓(연결하는거)
		ServerSocket serverSocket = new ServerSocket();

		// bind (IP, port 정해주기==>그래야 거기서 프로그램이 돔.)
		serverSocket.bind(new InetSocketAddress("192.168.1.48", 10001)); // 이 IP에서 10001번 포트를 지켜보고 있는 것.

		System.out.println("<서버시작>");
		System.out.println("====================");
		System.out.println("[연결을 기다리고 있습니다.]");
		///////////////////////// 세팅 완료  //////////////////////////////////
		while (true) {
			Socket socket = serverSocket.accept();
			Thread thread = new ServerThread(socket); //해야할 일 new 시킴//(socket) : serverthread에 socket에 생성자를 받는 애가 있어야 함.
			thread.start();//내보냄(==>어디로?)

		}

		
	/*	System.out.println("====================");
		System.out.println("<서버종료>");

		serverSocket.close();*/

	}

}
