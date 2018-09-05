package txt_test;

import java.io.File;

public class MyTxtTest {
	public static void main(String[] args) {
		File f = new File("C:\\jsp_study\\txt_folder");
		System.out.println(f.isDirectory());
		System.out.println(f.getName()); //폴더를 가져옴
		f = new File("C:\\jsp_study\\txt_folder","commic.txt");
		System.out.println(f.isFile());
		System.out.println(f.getName());//폴더안의 파일을 갖고옴
		
	}
}
