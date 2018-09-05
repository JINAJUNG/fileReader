package batch_address;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import batch_address.dao.AddressDAO;
import batch_address.dao.impl.AddressDAOImpl;

public class BatchTest {
	public static void main(String[] args) {
		String path = "C:\\jsp_study\\zipcode_DB";
		File f = new File(path);
		System.out.println("폴더 인지아닌지 : " + f.isDirectory());
		System.out.println("폴더가 실제로 존재하느지 : " + f.exists());
		File[] fList = f.listFiles();
		List<List<String>> list ;
		for (File fi : fList) {
			if (fi.getName().endsWith(".txt")) { // txt확장자만 가져오기(-.endsWith : 마지막 자리가 ()안에껄로 끝나면 true-)
				list = new ArrayList<List<String>>();

				try {
					FileInputStream fis = new FileInputStream(fi);
					InputStreamReader fr = new InputStreamReader(fis, "euc-kr");
					BufferedReader br = new BufferedReader(fr);
					String line;
					br.readLine(); //첫줄은 필요없으니까 읽고 시작
					while ((line = br.readLine()) != null) {
						String[] strs = line.split("\\|");
						List<String> strList = new ArrayList<>();
						for(int i=0,max=13;i<max;i++) {
							if(i==10) {
								continue;
							}
							strList.add(strs[i]);
						}
						list.add(strList);
					}
					br.close();
					fr.close();
					fis.close();
					
					AddressDAO a = new AddressDAOImpl();
					System.out.println(a.insertBatch(list));
					System.out.println(fi.getName());
					/*
					for(List<String> sList : list) {
						for(String s : sList) {
						
							System.out.print(s+",");
						}
						System.out.println();
					}
					System.out.println("exits");*/
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
	}
}
