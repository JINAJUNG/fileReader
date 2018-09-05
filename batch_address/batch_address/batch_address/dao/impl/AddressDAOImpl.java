package batch_address.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import batch_address.DBCon;
import batch_address.dao.AddressDAO;

public class AddressDAOImpl implements AddressDAO {
	Connection con = null;
	@Override
	public int insertBatch(List<List<String>> list){
		con = DBCon.getCon();
		String sql = "insert into addr_list values(seq_alNum.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		int cnt=0;
		try {
			ps = con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				List<String> sList = list.get(i);
				for(int j=0;j<sList.size();j++) {
					ps.setString(j+1, sList.get(j));
				}
				ps.addBatch();
				ps.clearParameters();
				if(i+1%100==0 || i+1==list.size()) {
					cnt += ps.executeBatch().length;	
				}
			}
			ps.close();
			DBCon.commit();
		} catch (SQLException e) {
			DBCon.rollback();
			e.printStackTrace();
		}finally {
			DBCon.close();	
		}
		return cnt;
	}
}
