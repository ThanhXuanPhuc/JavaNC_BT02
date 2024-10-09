/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class SaveServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        //b1. Lấy giá trị tham số từ Client
        String uname = request.getParameter("uname");
        PrintWriter out = response.getWriter();
        String upass = request.getParameter("upass");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        //b2. Xử lý yêu cầu (truy cập CSDL để thêm mới user)
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1. Nạp driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //2.Thiết lập kết nối CSDL
            conn = DriverManager.getConnection("jdbc:sqlserver://pc317;databaseName=demodb", "sa", "sa");

            //3. Tạo đối tượng thi hành truy vấn
            ps = conn.prepareStatement("insert into users(name,password, email, country) values(?,?,?,?)");
            //Truyền giá trị cho các tham số trong câu lệnh SQL
            ps.setString(1, uname);
            ps.setString(2, upass);
            ps.setString(3, email);
            ps.setString(4, country);
            //4. Thi hành truy vấn
            int kq = ps.executeUpdate();
            //5. Xử lý kết quả trả về
            if(kq>0){
                out.println("<h2>Thêm mới user thành công");
            }
        }     
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
