/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilkders;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author argunpc
 */
    public class DatabaseConnection {
    private static Connection conn=null;
   private static DatabaseConnection databaseConnection=null;
   
   public  static Connection BaglantiKur()
	{
		try 
		{
                 Class.forName("com.mysql.cj.jdbc.Driver");
               String metin="jdbc:mysql://localhost:3306/kutuphane?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String kul="root";
            String sifre="bedir123456";
        conn=DriverManager.getConnection(metin,kul,sifre);
                 System.out.println("Bağlandı");
		 return conn;
		}  
		catch (Exception e) 
		{
			return null;
		}
	}
    
}
