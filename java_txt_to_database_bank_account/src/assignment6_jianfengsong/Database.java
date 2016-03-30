package assignment6_jianfengsong;


import Lab6.AccountLockedException;
import Lab6.BankAccount;
import Lab6.InsufficientFundsException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
public class Database implements AutoCloseable{
    private String strConnection = "jdbc:oracle:thin:n01039590/oracle@dilbert.humber.ca:1521:grok";
    private Connection con;
    private OracleDataSource dataSource;
    private Statement st;
    //private PreparedStatement ps;
    
    public Database() throws SQLException{
       
        dataSource = new OracleDataSource();
        dataSource.setURL(strConnection);
        con = dataSource.getConnection();
        st = con.createStatement();
    }
    
    public void createTable() throws SQLException{
        
        //st = con.createStatement();
        st.execute("drop table n01039590_bankaccount purge");
        st.execute("create table n01039590_bankaccount (account# number(8), balance number(8,2), locked varchar2(8))");
    }
    
    public ArrayList<String> readFile(String file) throws IOException{
        ArrayList<String> strList = new ArrayList<String>();
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        Scanner scan = new Scanner(fileReader);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            strList.add(line);
            
        }
        return strList;
    }
    
    public void initializeBankAccount(ArrayList<String> ls) throws SQLException{
        String strCreateAccount = "insert into n01039590_bankaccount values (?, ?, ?)";
        for(String s : ls){
            Scanner scan = new Scanner(s);
            String transactionType = scan.next();
            //System.out.println(transactionType);
            if(transactionType.equals("Account")){
                //System.out.println("hi");
                int accountNumber = scan.nextInt();
                scan.next();
                double balance = scan.nextDouble();
                String locked = scan.next();                  
                //System.out.println(accountNumber+balance+locked);
                try(PreparedStatement ps = con.prepareStatement(strCreateAccount)){
                    
                    ps.setInt(1, accountNumber);
                    ps.setDouble(2, balance);
                    ps.setString(3, locked);
                    ps.executeUpdate();
                }
            }
        }
    }
    
    
    public void updateBankAccout(ArrayList<String> strList) throws SQLException, AccountLockedException{
        String strUpdate = "update n01039590_bankaccount set balance = balance+? where account# = ?";
        //int count = 0;
        for(String s : strList){
            Scanner scan = new Scanner(s);
            String transactionType = scan.next();
            if(transactionType.equals("Transfer")){
                try{ 
                    PreparedStatement ps = con.prepareStatement(strUpdate);
                    con.setAutoCommit(false);
                    double transfer = scan.nextDouble();
                    scan.next();
                    int outAccount = scan.nextInt();
                    scan.next();
                    int inAccount = scan.nextInt();

                    ps.setDouble(1, -transfer);
                    ps.setInt(2, outAccount);
                    ps.executeUpdate();

                    ps.setDouble(1, transfer);
                    ps.setInt(2, inAccount);
                    ps.executeUpdate();
              
                    
                    ResultSet rsOut = st.executeQuery("select locked from n01039590_bankaccount where account# = "+outAccount);
                    while (rsOut.next()) {
                        if(rsOut.getString(1).equals("locked"))
                             throw (new AccountLockedException("Account Locked"));
                    }
                    ResultSet rsIn = st.executeQuery("select locked from n01039590_bankaccount where account# = "+inAccount);
                    while(rsIn.next()){
                        if(rsIn.getString(1).equals("locked"))
                             throw new AccountLockedException("Account Locked");
                    }
                }catch(Exception ale){
                    //System.out.println(count);
                    con.rollback();
                    
                }finally{
                    con.setAutoCommit(true);
                }

                
            }
       }
       
    }
    
    public ArrayList<String> updatedBankAccount() throws SQLException{
        st = con.createStatement();
        ArrayList<String> list = new ArrayList<String>();
        String strQuery = "select account#, balance, locked from n01039590_bankaccount";
        ResultSet rs = st.executeQuery(strQuery);
        while(rs.next()){
            list.add(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            //list.add(rs.getString(1));
        }
        return list;
    }
    
    public void close() throws Exception{
        con.close();
    }
}
