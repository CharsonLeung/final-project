package project_stock_data.project_stock_data.lib;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DbCon {

  public static List<String> getWholeColumn(String query, String table) throws Exception {
    Class.forName("org.postgresql.Driver");
    String url = "jdbc:postgresql://localhost:5432/bc2504p";
    String username = "postgres";
    String password = "Admin1234$";
    Connection con = DriverManager.getConnection(url, username, password);

    Statement st = con.createStatement();
    String query2 = String.format("select \"%s\" from " + table + ";", query);
    System.out.println("query=" + query2);
    ResultSet rs = st.executeQuery(query2);
    ArrayList<String> queryColumn = new ArrayList<>();
    while (rs.next()) {
      String queryColumnContent = rs.getString(query);
      queryColumn.add(queryColumnContent);
    }
    //rs.close();
    st.close();
    con.close();
    return queryColumn;
  }

  public static void main(String[] args) {
    String column = "name";
    String table = "stock_profiles";

    try {
      //for (int i = 0; i < getWholeColumn(query).size(); i++) {
      //System.out.println(getWholeColumn(query).get(i));
      System.out.println(getWholeColumn(column, table));
      System.out.println(getWholeColumn(column, table).size());
      
      //System.out.println(request.size());
    } catch (Exception ex) {
      System.out.println("Error: " + ex);
    }

}
}