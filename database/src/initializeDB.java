import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class initializeDB {
	
	private static final long serialVersionUID = 1L;
	public static Connection connect = null;
	public static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public initializeDB(){}
	
    protected static void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=LilPlug2323!");
            System.out.println(connect);
        }
    }
    
    protected static void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public static void initializeDatabase() throws SQLException, FileNotFoundException, IOException{
    	try {
    		connect_func();
    		
            statement = connect.createStatement();
            
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
            
            statement.executeUpdate("DROP TABLE IF EXISTS OrderOfWork");
            statement.executeUpdate("DROP TABLE IF EXISTS Bill");
            statement.executeUpdate("DROP TABLE IF EXISTS Quote");
            statement.executeUpdate("DROP TABLE IF EXISTS TreeRequest");
            statement.executeUpdate("DROP TABLE IF EXISTS Client");
            statement.executeUpdate("DROP TABLE IF EXISTS User");
            
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1");

            System.out.println("Tables removed succesfully");
            
            String sqlstmt = "CREATE TABLE IF NOT EXISTS User " + 
            " (username VARCHAR(255) UNIQUE, " +
            " password VARCHAR(255), " +
            "role VARCHAR(50)); ";
            statement.executeUpdate(sqlstmt);
            
            
            sqlstmt = "CREATE TABLE IF NOT EXISTS Client( " + 
            		"clientID INT AUTO_INCREMENT PRIMARY KEY, " +
            	    "firstName VARCHAR(255), " +
            	    "lastName VARCHAR(255), " +
            	    "address VARCHAR(255), " +
            	    "creditCardInfo VARCHAR(255), " +
            	    "phoneNumber VARCHAR(15), " +
            	    "email VARCHAR(255) UNIQUE ); ";
            statement.executeUpdate(sqlstmt);
            
            sqlstmt = "CREATE TABLE IF NOT EXISTS TreeRequest( " + 
            		"requestID INT AUTO_INCREMENT PRIMARY KEY, " +
            	    "size DECIMAL(10, 2), " +
            	    "height DECIMAL(10, 2), " +
            	    "location VARCHAR(255), " +
            	    "nearHouse BOOLEAN, " +
            	    "note TEXT, " +
            	    "clientID INT, " +
            	    "rejected BOOLEAN DEFAULT 0, " +  
            	    "FOREIGN KEY (clientID) REFERENCES Client(clientID) ON DELETE CASCADE);";
            statement.executeUpdate(sqlstmt);
            
            sqlstmt = "CREATE TABLE IF NOT EXISTS Quote( " + 
            		 "quoteID INT AUTO_INCREMENT PRIMARY KEY, " +
            		 "initialPrice DECIMAL(10, 2), " +
            		 "timeWindow VARCHAR(255), " +
            		 "requestID INT, " +
            		 "FOREIGN KEY (requestID) REFERENCES TreeRequest(requestID) ON DELETE CASCADE);";
            statement.executeUpdate(sqlstmt);
	
            sqlstmt = "CREATE TABLE IF NOT EXISTS OrderOfWork( " + 
            		"orderID INT AUTO_INCREMENT PRIMARY KEY, "+
            		"workStatus VARCHAR(255),"+
            		"quoteID INT,"+
            		"FOREIGN KEY (quoteID) REFERENCES Quote(quoteID) ON DELETE CASCADE);";
            statement.executeUpdate(sqlstmt);
            
            sqlstmt = "CREATE TABLE IF NOT EXISTS Bill( " + 
            	    "billID INT AUTO_INCREMENT PRIMARY KEY,"+
            	    "billStatus VARCHAR(255),"+
            	    "quoteID INT,"+
            	    "note TEXT,"+
            	    "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID) ON DELETE CASCADE);";
            statement.executeUpdate(sqlstmt);

            System.out.println("Tables created succesfully");
            
            sqlstmt = "insert into User(username, password, role)"+
        			"values ('jacobarduin', '1234', 'Client')";
            statement.executeUpdate(sqlstmt);

            
            sqlstmt = "insert into Client(firstName, lastName, address, creditCardInfo, phoneNumber, email)"+
            		"values ('Jacobe ', ' Arduin ', '2543 Roco','4556456334452314', '2489818188', 'arr12@gmail.com' )";
            statement.executeUpdate(sqlstmt);

            
            sqlstmt = "insert into TreeRequest(size, height, location,nearHouse, note )"+
            		"values ( 10.2, 15, 'In front of house', TRUE, 'Need this done ASAP')";
            statement.executeUpdate(sqlstmt);

            sqlstmt = "insert into Quote(initialPrice, timeWindow)"+
            		"values ( 340, 'ASAP' )";
            statement.executeUpdate(sqlstmt);

            sqlstmt = "insert into OrderOfWork(workStatus)"+
            		"values ( 'incomplete')";
            statement.executeUpdate(sqlstmt);

            sqlstmt = "insert into Bill(billStatus, note)"+
            		"values ( 'unpaid', 'Hasnt started work yet')";
            statement.executeUpdate(sqlstmt);

            System.out.println("Tables succesfully taken 1 order");


    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		disconnect();
    	}
    }
}
