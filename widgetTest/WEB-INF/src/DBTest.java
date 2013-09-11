
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;

public class DBTest
{
  private Connection con = null; 
 
  private Statement stat = null; 
 
  private ResultSet rs = null; 

  private PreparedStatement pst = null; 
  
 /*
  private String deletedbSQL = "delete TABLE User "; 

  private String createdbSQL = 
  "CREATE TABLE User (" + 
    "    id     INTEGER " + 
    "  , name    VARCHAR(20) " + 
    "  , passwd  VARCHAR(20))"; 
  
  private String insertdbSQL = 
  "insert into User(id,name,passwd) " + 
      "select ifNULL(max(id),0)+1,?,? FROM User"; 
*/
  
  private String selectSQL = "select * from current_posts "; 
  
  public DBTest() 
  { 
    try
    { 
      Class.forName("com.mysql.jdbc.Driver"); 
      con = DriverManager.getConnection
    		  ( 
    				  "jdbc:mysql://localhost/brickProject?useUnicode=true&characterEncoding=Big5", 
    				  "root",""
    		  );   
    } 
    catch(ClassNotFoundException e) 
    { 
      System.out.println("DriverClassNotFound :"+e.toString()); 
    }
    catch(SQLException x)
    { 
      System.out.println("Exception :"+x.toString()); 
    } 
    
  } 
public DBTest(String SQL) 
{
	
}
/*
  public void createTable() 
  { 
    try 
    { 
      stat = con.createStatement(); 
      stat.executeUpdate(createdbSQL); 
    } 
    catch(SQLException e) 
    { 
      System.out.println("CreateDB Exception :" + e.toString()); 
    } 
    finally 
    { 
      Close(); 
    } 
  } 
*/
/*
  public void insertTable( String name,String passwd) 
  { 
    try 
    { 
      pst = con.prepareStatement(insertdbSQL); 
      
      pst.setString(1, name); 
      pst.setString(2, passwd); 
      pst.executeUpdate(); 
    } 
    catch(SQLException e) 
    { 
      System.out.println("InsertDB Exception :" + e.toString()); 
    } 
    finally 
    { 
      Close(); 
    } 
  } 
  //刪除Table, 
  //跟建立table很像  
*/
/*
  public void deleteTable() 
  { 
    try 
    { 
      stat = con.createStatement(); 
      stat.executeUpdate(deletedbSQL); 
    } 
    catch(SQLException e) 
    { 
      System.out.println("deleteDB Exception :" + e.toString()); 
    } 
    finally 
    { 
      Close(); 
    } 
  } 
  //查詢資料 
  //可以看看回傳結果集及取得資料方式 
   */
  public void SelectTable() 
  { 
	String returnString = "";
    try 
    { 
      stat = con.createStatement(); 
      rs = stat.executeQuery(selectSQL); 
      System.out.println("ID\t\tName\t\tPASSWORD"); 
      while(rs.next()) 
      { 
        
        returnString = returnString + rs.getInt("id")+"\t\t"+  rs.getString("name")+"\t\t"+rs.getString("passwd")+"\n";
      } 
      System.out.println(returnString);
    } 
    catch(SQLException e) 
    { 
      System.out.println("deleteDB Exception :" + e.toString()); 
    } 
    finally 
    { 
      Close(); 
    } 
  } 
  
  private void Close() 
  { 
    try 
    { 
      if(rs!=null) 
      { 
        rs.close(); 
        rs = null; 
      } 
      if(stat!=null) 
      { 
        stat.close(); 
        stat = null; 
      } 
      if(pst!=null) 
      { 
        pst.close(); 
        pst = null; 
      } 
    } 
    catch(SQLException e) 
    { 
      System.out.println("Close Exception :" + e.toString()); 
    } 
  } 
  

  public static void main(String[] args) 
  { 
	DBTest test = new DBTest(); 
    test.SelectTable(); 
  } 
}