
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.pool.OracleDataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */
public class Database {
    private String strConnection = 
            "jdbc:oracle:thin:n01039590/oracle@dilbert.humber.ca:1521:grok";
    private Connection con;
    private OracleDataSource dataSource;
    private Statement st;
    
    public Database() throws Exception{
       
        dataSource = new OracleDataSource();
        dataSource.setURL(strConnection);
        con = dataSource.getConnection();
        
    }
    
    public void createTable() throws Exception{
        st = con.createStatement();
        st.execute("create table n01039590_orders_1 "
                + "(customer varchar2(30), product varchar2(30), price number(8))");
    }
    
    public void insertData() throws Exception{
        st = con.createStatement();
        st.executeUpdate("insert into n01039590_orders values('Washington', 'Dress', 119)");
        st.executeUpdate("insert into n01039590_orders values('Adams', 'Shirt', 55)");
        st.executeUpdate("insert into n01039590_orders values('Adams', 'Tie', 22)");
        st.executeUpdate("insert into n01039590_orders values('Washington', 'Blouse', 75)");
        st.executeUpdate("insert into n01039590_orders values('Franklin', 'Hat', 33)");
        st.executeUpdate("insert into n01039590_orders values('Livingston', 'Gloves', 19)");
        st.executeUpdate("insert into n01039590_orders values('Livingston', 'Pants', 89)");
        st.executeUpdate("insert into n01039590_orders values('Read', 'Dress', 99)");
    }
    
    public ArrayList<String> customerBills() throws Exception{
        st = con.createStatement();
        ArrayList<String> list = new ArrayList<String>();
        String strQuery = "select customer, sum(price) from n01039590_orders group by customer";
        ResultSet rs = st.executeQuery(strQuery);
        while(rs.next()){
            list.add(rs.getString(1)+"\t"+rs.getString(2));
            //list.add(rs.getString(1));
        }
        return list;
    }
    
    public void close() throws Exception{
        con.close();
    }
}
