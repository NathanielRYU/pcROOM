package friends;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Friends;
import bean.MembersInfo;

public class DataAccessObject extends webpos.DataAccessObject{
   private ResultSet rs;

   /*친구 로그인 상태 리스트 불러오기*/
   ArrayList<Friends> getFriendList(Connection conn, Friends fr){
      ArrayList<Friends> list = new ArrayList<>();
      
      String query = "SELECT AH.AH_USID AS USID, ST.ST_NAME AS STNAME, PC.PC_NAME AS PCNAME "
      		         + "FROM AH INNER JOIN (SELECT MAX(AH.AH_ACCESSTIME) AS MAXACCESSTIME , AH.AH_USID AS USID "
      		         + "                    FROM AH WHERE AH.AH_STCODE = 9 AND AH.AH_USID IN(SELECT REPLACE ((FR.FR_REQUSID||FR.FR_RESUSID),?,'') AS FRIEND "
      		         + "                                                                      FROM FR  WHERE (FR.FR_REQUSID = ? OR FR.FR_RESUSID = ?) AND FR.FR_STCODE = 1) "
      		         + "                     GROUP BY AH.AH_USID)H ON AH.AH_ACCESSTIME = H.MAXACCESSTIME AND AH.AH_USID = H.USID "
      		         + "         INNER JOIN ST ON AH.AH_STCODE = ST.ST_CODE "
      		         + "         INNER JOIN PC ON AH.AH_PCCODE = PC.PC_CODE";

      try { this.psmt = conn.prepareStatement(query);
             
              this.psmt.setNString(1, fr.getUserId());
              this.psmt.setNString(2, fr.getUserId());
              this.psmt.setNString(3, fr.getUserId());
              this.rs = this.psmt.executeQuery();
             
           while(rs.next()) {
        	  
              Friends f = new Friends();
              f.setUserId(rs.getNString("USID"));
              f.setPcRoomName(rs.getNString("PCNAME"));
              f.setStName(rs.getNString("STNAME"));
              list.add(f);
              
              
           }
      
      }catch(SQLException e){
         e.printStackTrace();
      }finally {
    	  try {if(!rs.isClosed()) {rs.close();}}catch(SQLException e) {e.printStackTrace();}}
          
         return list;
      }
   
   /*아이디 찾기 -> 유효성 검사*/
   /*아이디 찾기 -> 차단상태인지 확인*/   

   boolean idCheckInfo1(Connection connection, Friends fr) {

      ResultSet rs = null;
      boolean result = false;

      String sql = "SELECT COUNT(*) "
            + "FROM FR "
            + "WHERE FR_REQUSID = ? AND FR_RESUSID = ? AND FR_STCODE = -2";

      try {
         this.psmt = connection.prepareStatement(sql);
         this.psmt.setNString(1, fr.getUserId());
         this.psmt.setNString(2, fr.getReqUserId());
         


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
   /*아이디 찾기 -> 유효성 검사*/
   boolean idCheckInfo2(Connection connection, Friends fr) {

      ResultSet rs = null;
      boolean result = false;

      String sql = "SELECT COUNT(*) FROM US WHERE US_ID = ?";

      try {
         this.psmt = connection.prepareStatement(sql);
         this.psmt.setNString(1, fr.getUserId());


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
   
   /*나와 친구의 관계 상태 insert*/
   boolean insFrTable(Connection connection, Friends fr) {
	   ResultSet rs = null;
		boolean result = false;

		String dml = "INSERT INTO FR(FR.FR_REQUSID, FR.FR_RESUSID, FR.FR_STCODE) VALUES (?,?, ?)";
	

		try {
			this.psmt = connection.prepareStatement(dml);
			this.psmt.setNString(1, fr.getReqUserId());
			this.psmt.setNString(2, fr.getResUserId());
			this.psmt.setInt(3, fr.getStCode());
			
			result = this.convertToBoolean(this.psmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return result;
	}
   
   /*나와 친구의 record가 FR테이블에 존재하는지*/
  boolean checkFriendTable(Connection connection, Friends fr) {

		ResultSet rs = null;
		boolean result = false;

		String sql = "SELECT COUNT(*) FROM FR WHERE FR_REQUSID = ? AND FR_STCODE = 3";

		try {
			this.psmt = connection.prepareStatement(sql);
			this.psmt.setNString(1, fr.getReqUserId());


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
   
   boolean updFrd(Connection conn, Friends fr) {
	   boolean result = false;
	   String dml = "UPDATE FR SET FR.FR_STCODE = ? "
	   		+ "WHERE FR.FR_REQUSID = ? "
	   		+ "AND FR.FR_RESUSID = ?";
	   
	   try {
			this.psmt = conn.prepareStatement(dml);
			this.psmt.setInt(1, fr.getStCode());
			this.psmt.setNString(2, fr.getReqUserId());
			this.psmt.setNString(3, fr.getUserId());
			
			result = this.convertToBoolean(this.psmt.executeUpdate());
		}catch(SQLException e) {e.printStackTrace();}
		return result;
	}
   
   boolean acceptFrd(Connection conn, Friends fr) {
	   boolean result = false;
	   String dml = "UPDATE FR SET FR.FR_STCODE = ? "
	   		+ "WHERE FR.FR_REQUSID = ? "
	   		+ "AND FR.FR_RESUSID = ?";
	   
	   try {
			this.psmt = conn.prepareStatement(dml);
			
			this.psmt.setInt(1, fr.getStCode());
			this.psmt.setNString(2, fr.getReqUserId());
			this.psmt.setNString(3, fr.getUserId());
			
			result = this.convertToBoolean(this.psmt.executeUpdate());
		}catch(SQLException e) {e.printStackTrace();}
		return result;
	}
   
   ArrayList<Friends> getReqFrdInfo(Connection conn, Friends fr){
	   ArrayList<Friends> list = new ArrayList<Friends>();
	   String query = "SELECT FR.FR_RESUSID AS RESNAME "
	   				+ "FROM FR "
	   				+ "WHERE FR.FR_REQUSID = ? AND FR_STCODE = -1";
	   
	   try {
		   this.psmt = conn.prepareStatement(query);
		   this.psmt.setNString(1, fr.getUserId());

		   this.rs = this.psmt.executeQuery();
		   while(rs.next()) {
			   Friends frd = new Friends();
			   frd.setUserId(rs.getNString("RESNAME"));
			   list.add(frd);
		   }
		   
	   }catch(SQLException e){e.printStackTrace();}
	   finally {
		   try {if(!rs.isClosed()) {rs.close();}} catch (SQLException e) {e.printStackTrace();}
		}
	   return list;
   }
   
   ArrayList<Friends> getResFrdInfo(Connection conn, Friends fr){
	   ArrayList<Friends> list = new ArrayList<Friends>();
	   String query = "SELECT FR.FR_REQUSID AS REQNAME "
	   				+ "FROM FR "
	   				+ "WHERE FR.FR_RESUSID = ? AND FR_STCODE = -1";
	   
	   try {
		   this.psmt = conn.prepareStatement(query);
		   this.psmt.setNString(1, fr.getUserId());

		   this.rs = this.psmt.executeQuery();
		   while(rs.next()) {
			   Friends frd = new Friends();
			   frd.setUserId(rs.getNString("REQNAME"));
			   list.add(frd);
		   }
		   
	   }catch(SQLException e){e.printStackTrace();}
	   finally {
		   try {if(!rs.isClosed()) {rs.close();}} catch (SQLException e) {e.printStackTrace();}
		}
	   return list;
   }
   
   ArrayList<Friends> getBlkFrdInfo(Connection conn, Friends fr){
	   ArrayList<Friends> list = new ArrayList<Friends>();
	   String query = "SELECT REPLACE ((FR.FR_REQUSID||FR.FR_RESUSID),?,'') AS BLKNAME "
	   		+ "FROM FR WHERE FR.FR_REQUSID = ? "
	   		+ "AND FR.FR_STCODE = -2";
	   
	   try {
		   this.psmt = conn.prepareStatement(query);
		   this.psmt.setNString(1, fr.getUserId());
		   this.psmt.setNString(2, fr.getUserId());
		   
		   this.rs = this.psmt.executeQuery();
		   while(rs.next()) {
			   Friends frd = new Friends();
			   frd.setUserId(rs.getNString("BLKNAME"));
			   list.add(frd);
		   }
		   
	   }catch(SQLException e){e.printStackTrace();}
	   finally {
		   try {if(!rs.isClosed()) {rs.close();}} catch (SQLException e) {e.printStackTrace();}
		}
	   return list;
   }
  }