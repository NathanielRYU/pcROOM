package webpos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Friends;
import bean.MembersInfo;
import bean.PcRoom;

public class DataAccessObject {

	ResultSet rs;
	public PreparedStatement psmt;
	MembersInfo mi;


	public DataAccessObject() {
		rs = null;
		psmt = null;
		this.getConnection();
	}


	public Connection getConnection() { 
		Connection connection = null; 

		String[] total = { "jdbc:oracle:thin:@icia4.ct9vznssg1m8.ap-northeast-2.rds.amazonaws.com:1521:ORCL", "bejuseung1230", "01064162205!" };

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			connection = DriverManager.getConnection(total[0], total[1], total[2]); 


		} catch (Exception e) {

			e.printStackTrace();

		}
		return connection;

	}
	//오토커밋 방지
	public void modifyTranStatus(Connection connection, boolean status) {

		try {
			if (connection != null && connection.isClosed()) {
				connection.setAutoCommit(status);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Transaction처리
	public void setTransaction(Connection connection, boolean tran) {

		try {
			if (connection != null && connection.isClosed()) {
				if (tran) {
					connection.commit();
				} else {
					connection.rollback();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// Close Connection
	public void closeConnection(Connection connection) {

		try {
			if (connection != null && connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/* convertToBoolean */
	public boolean convertToBoolean(int value) {
		return (value > 0) ? true : false;
	}

	//====================================================================================
	/* insAccessHistory */
	public boolean insAccessHistory(Connection connection, MembersInfo mi) {

		boolean result = false;

		String query = "INSERT INTO AH(AH_USID, AH_STCODE, AH_ACCESSTIME, AH_PCCODE) "
				+ "VALUES(?,?, DEFAULT,?)";

		try {
			psmt = connection.prepareStatement(query);
			psmt.setNString(1, mi.getNickName());
			psmt.setInt(2, mi.getState());
			psmt.setNString(3, mi.getMemPcCode());

			result = this.convertToBoolean(psmt.executeUpdate());

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (!psmt.isClosed()) {
					psmt.isClosed();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/*PC방 이름 가져오기*/
	public boolean getPcRoomName(Connection connection, MembersInfo mi) {
		String dml = " SELECT PC_NAME AS PCNAME FROM PC WHERE PC.PC_CODE = ?";
		boolean result = false;

		try {
			this.psmt = connection.prepareStatement(dml);
			this.psmt.setNString(1, mi.getMemPcCode());
			this.rs = this.psmt.executeQuery();

			while(rs.next()) {

				mi.setMemPcRoomName(rs.getNString("PCNAME"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	/* getAccessInfo */
	public ArrayList<MembersInfo> getAccessInfo(Connection connection, MembersInfo mi){
		ArrayList<MembersInfo> list = new ArrayList<MembersInfo>();
		ResultSet rs = null;
		/* 상점명(상점코드) 직원명(직원코드) 최근접속기록 */
		String sql = "SELECT NICKNAME, MEMNAME, ACCESSTIME FROM ACCESSINFO "
				+ "WHERE ACCESSTIME = (SELECT TO_CHAR(MAX(AI_STIME), 'YYYY-MM-DD HH24:MI:SS') "
				+ "                     FROM AINFO "
				+ "						WHERE AI_MIID=?)";

		try {
			this.psmt = connection.prepareStatement(sql);
			this.psmt.setNString(1, mi.getNickName());

			rs = this.psmt.executeQuery();
			while(rs.next()) {

				MembersInfo mIf = new MembersInfo();

				mIf.setMemName(rs.getString("MEMNAME"));
				mIf.setNickName(rs.getString("NICKNAME"));
				mIf.setAccessTime(rs.getString("ACCESSTIME"));

				list.add(mIf);

				System.out.println(mIf.getMemName());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if(!rs.isClosed()) rs.close();} catch (SQLException e) {e.printStackTrace();}
		}

		return list;
	}
	//=============================================================================
	public boolean idCheckInfo(Connection connection, MembersInfo mi) {

		ResultSet rs = null;
		boolean result = false;

		String sql = "SELECT COUNT(*) FROM US WHERE US_ID = ?";

		try {
			this.psmt = connection.prepareStatement(sql);
			this.psmt.setNString(1, mi.getNickName());


			rs = this.psmt.executeQuery();

			while (rs.next()) {

				result = this.convertToBoolean(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/* id && comparePassword -- 처음 로그인 할때*/
	public	boolean isMember(Connection connection, MembersInfo mi) {

		ResultSet rs = null;
		boolean result = false;

		String sql = "SELECT COUNT(*) FROM US WHERE US_ID = ? AND US_PASSWORD = ?";

		try {
			this.psmt = connection.prepareStatement(sql);
			this.psmt.setNString(1, mi.getNickName());
			this.psmt.setNString(2, mi.getPassWord());
			rs = this.psmt.executeQuery();

			while (rs.next()) {

				result = this.convertToBoolean(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	//============================회원등록
	public boolean setMember(Connection connection, MembersInfo mi) {

		boolean result = false;

		String query = "INSERT INTO US(US_ID, US_NAME, US_NUMBER, US_PASSWORD)"
				+ "VALUES(?,?,?,?)";

		try {
			psmt = connection.prepareStatement(query);
			psmt.setNString(1, mi.getNickName());
			psmt.setNString(2, mi.getMemName());
			psmt.setNString(3, mi.getPhoneNum());
			psmt.setNString(4, mi.getPassWord());

			result = this.convertToBoolean(psmt.executeUpdate());

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (!psmt.isClosed()) {
					psmt.isClosed();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
}

