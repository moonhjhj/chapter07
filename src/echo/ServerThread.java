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
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		// 연결
		System.out.println("[클라이언트가 연결되었습니다.]");

		try {

			// 메세지 받기용 스트림
			InputStream is = socket.getInputStream();
			Reader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			// 메세지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			Writer osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			String msg;

			while (true) {
				msg = br.readLine();// 읽기
				if (msg == null) {
					System.out.println("클라이언트 접속 종료");
					break;
				}
				System.out.println("받은메세지: " + msg);

				bw.write(msg);
				bw.newLine();// 개행문자
				bw.flush();// buffered는 일정량 모아서 보냄. 꽉 안차면 전송이 안됨. 생각한 것보다 작으면 모아놓고 보내지 않음. flush는 강제로 보내는
							// 것.

			}
		} catch (IOException e) {
		}
	}

}
