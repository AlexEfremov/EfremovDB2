package db.efremov;



import java.sql.*;

class EmployeeList {
	static public Connection conn=null;
	static public PreparedStatement stmt=null;
	static public ResultSet rs;

public static void main(String argv[]) throws Exception {

try {
 conn = DriverManager.getConnection( "jdbc:derby://localhost:1527/Lesson22;create=false"); 

 stmt=conn.prepareStatement("SELECT * from Employee WHERE ename Like  ?");
 
 String[] wantedNames = {"%Efremov%","%Allen%","%Bush%"}; // пришлось включть значки процентов в 
 			// список фамилий, так как setString добавляет одинарные кавычки
 for (int i=0;i<wantedNames.length; i++){
	 stmt.setString(1,wantedNames[i]);
	 rs = stmt.executeQuery();

	 while (rs.next()){ 
 	 	 int empNo = rs.getInt("EMPNO");
    	 String eName = rs.getString("ENAME");
    	 String job = rs.getString("JOB_TITLE");
	     System.out.println(""+ empNo + ", " + eName + ", " + job ); 
 		 }
 	 }//for i

} catch( SQLException se ) {
   System.out.println ("SQLError: " + se.getMessage ()
        + " code: " + se.getErrorCode ());

} catch( Exception e ) {
   System.out.println(e.getMessage()); 
   e.printStackTrace(); 
} finally{
    // clean up the system resources
    try{
	   rs.close();     
	   stmt.close(); 
	   conn.close();  
    } catch(Exception e){
        e.printStackTrace();
    } 
}
}


}
