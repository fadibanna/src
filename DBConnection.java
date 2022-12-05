package application;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import javax.swing.JOptionPane;

public final class DBConnection {

    private static DBConnection handler = null;

    private static final String DB_URL = "jdbc:mysql:libdb;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    ResultSet result;
    void createConnection() {
        try {
            String dburl = "jdbc:mysql://localhost:3306/invdbfx";
            String user = "root";
            String password = "ashraf";
             Class.forName("com.mysql.jdbc.Driver").newInstance();
             conn = DriverManager.getConnection( dburl, user, password );
        } catch (Exception e) {
            System.out.println(" Error Connection " + e);
        }
    }

    public DBConnection() {
        createConnection();
        setupUsersTable();
//        setupBokkTable();
//        setupMemberTable();
//        setupIssueTable();
    }

    // DBConnection can't be a public that is why we made the below getInstance so we can use it in all classes
    public static DBConnection getInstance() {

        if (handler == null) {
            handler = new DBConnection();
        }

        return handler;

    }

    public ResultSet execQuery(String query) {
        // Newcon = ConnecrDb();
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Exception at execQuery:Handler " + e.getLocalizedMessage());
        }
        return result;
    }

    public boolean execAction(String qu) {
        // PreparedStatement stmt = null;

        try {

            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getLocalizedMessage(), "Error on execAcyion: ", JOptionPane.ERROR_MESSAGE);
            System.out.println(" Error exec : " + e);
            return false;
        }

    }
    /*
    CREATE TABLE `users` (
  `UsersId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `UserName` varchar(45) DEFAULT NULL,
  `Password` varchar(265) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `TEL1` varchar(45) DEFAULT NULL,
  `TEL2` varchar(45) DEFAULT NULL,
  `Admin` int(1) DEFAULT NULL,
  `company_companyId` int(11) NOT NULL,
  PRIMARY KEY (`UsersId`),
  UNIQUE KEY `UsersId_UNIQUE` (`UsersId`),
  KEY `fk_Users_company1_idx` (`company_companyId`),
  CONSTRAINT `fk_Users_company1` FOREIGN KEY (`company_companyId`) REFERENCES `companys` (`companyId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8
    */
    private void setupUsersTable() {
        String TABLE_NAME = "Users";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + "already exsist. Ready for go!.");
            } else {//+ " isAvail boolean default true ,"
                stmt.execute("CREATE TABLE " + TABLE_NAME + "( "
                        + "UserId int(11) NOT NULL AUTO_INCREMENT, "
                        + "FirstName varchar(45) DEFAULT NULL, "
                        + "LastName varchar(45) DEFAULT NULL,"
                        + "CompanyName varchar(45) DEFAULT NULL, "
                        + "UserName varchar(45) DEFAULT NULL, "
                        + "Password varchar(265) DEFAULT NULL, "
                        + "Email varchar(45) DEFAULT NULL,"
                        + "Mobile varchar(45) DEFAULT NULL,"
                        + "LandLine varchar(45) DEFAULT NULL,"
                        + "isAdmin int(1) DEFAULT NULL,"
                        + " PRIMARY KEY (`UserId`) "
                        + " ) ");
                System.out.println("Table " + TABLE_NAME + " Created and Ready for go!.");
            }
        } catch (SQLException e) {
            System.out.println(" Error setupUserTable : " + e);
        }
    }

    private void setupBookTable() {
        /*
        CREATE TABLE `book` (
  `id` varchar(200) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `publisher` varchar(200) DEFAULT NULL,
  `isAvail` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
         */
    }

    private void setupMemberTable() {
        //Newcon = ConnecrDb();
        String TABLE_NAME = "Member";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + "already exsist. Ready for go!.");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "( "
                        + "id varchar(200) NOT NULL, "
                        + "name varchar(200), "
                        + " mobile varchar(20),"
                        + " email varchar(100), "
                        + " PRIMARY KEY (`id`) "
                        + " ) ");
            }
        } catch (Exception e) {
            System.out.println(" Error : " + e);
        }
    }

    private void setupBokkTable() {
        String TABLE_NAME = "Book";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + "already exsist. Ready for go!.");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "( "
                        + "id varchar(200) NOT NULL, "
                        + "title varchar(200), "
                        + " author varchar(20),"
                        + " publisher varchar(100), "
                        + " isAvail boolean default true ,"
                        + " PRIMARY KEY (`id`) "
                        + " ) ");
            }
        } catch (SQLException e) {
            System.out.println(" Error setupBookTable : " + e);
        }
    }
    
        private void setupIssueTable() {
        String TABLE_NAME = "ISSUE";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + "already exsist. Ready for go!.");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "( "
                        + "bookId varchar(200) NOT NULL, "
                        + "memberId varchar(200), "
                        + " issueTime timestamp default CURRENT_TIMESTAMP ,"
                        + " renew_count integer default 0,"
                        + " PRIMARY KEY (`bookId`), "
                        + " FOREIGN KEY (bookId) REFERENCES BOOK(ID), "
                        + " FOREIGN KEY (memberId) REFERENCES Member(ID)"
                        
                        + " ) ");
            }
        } catch (Exception e) {
            System.out.println(" Error setupIssueTable : " + e);
        }
    }


}

//    public static Connection ConnecrDb() {
////public static Connection DBConnection(){
//      try {
//          //* Normal connection
//            String dburl = "jdbc:mysql://localhost:3306/libdb";
//            String user = "root";
//            String password = "ashraf";
//            
//            // use properties file 
////            Properties props= new Properties();
////            props.load(new FileInputStream("INVProperties.properties"));
////            String user = props.getProperty("user");
////            String password = props.getProperty("password");
////            String dburl=props.getProperty("dburl");
//           
////            
////            System.out.println(user);
////            System.out.println(password);
////            System.out.println(dburl);
////            
////        
//            Connection Newcon = DriverManager.getConnection( dburl, user, password );
//
//          System.out.println("connection is OK ");
//         // System.out.println("Newcon : " + Newcon);
//             return Newcon;
//            }catch(Exception e){
//              // System.out.println("connection Error");
//                JOptionPane.showMessageDialog(null, e);
//             return null;
//           }
//    
//    }
