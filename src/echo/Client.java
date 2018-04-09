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
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		// 소켓 만들기
		Socket socket = new Socket();
		System.out.println("<클라이언트 시작>");
		System.out.println("=================");
		System.out.println("[서버에 연결을 요청합니다.]");

		// 소켓끼리 연결해주기 ==>connect
		socket.connect(new InetSocketAddress("192.168.1.2", 10001));
		System.out.println("[서버에 연결되었습니다.]");

		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();// stream내놔ㅏㅏ
		Writer osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		Reader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//키보드 입력
		Scanner sc = new Scanner(System.in);
		/*String str = sc.nextLine();*/
		String str;
		
		while(true) {
			str = sc.nextLine();
			
			if("/q".equals(str)) {
				System.out.println("[접속종료되었습니다.]");
				break;
			}
			
			/****계속 입력받아야되므로 while문 안으로 옮기기*****/
			bw.write(str);
			bw.newLine();
			bw.flush();
			
			String reMsg = br.readLine();
			System.out.println("server:[" + reMsg + "]");
			
		}

		// 메세지 보내기
		/*String str = "테스트입니다.";*/
		/*bw.write(str);
		bw.newLine();
		bw.flush();
		
		String reMsg = br.readLine();
		System.out.println("server:[" + reMsg + "]");
		*/
		br.close();
		bw.close();

		System.out.println("=================");
		System.out.println("<클라이언트 종료>");

		socket.close();
	}

}
/*********** cmd창에서 client 실행 ****************/
/*
 * C: cd Users cd USER cd Desktop cd javaStudy cd workspace cd chapter06 cd bin
 * java echo.ex01.Client 하면 클라이언트 실행 완료
 * 
 * 
 */
