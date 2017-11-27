package servlet;

//imports
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.AuthBean;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //create new bean
        AuthBean auth = new AuthBean();
        
        //set desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
        
        //set variables from index page post method
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String address = request.getParameter("address");
        String number = request.getParameter("number");
        String sec = request.getParameter("sec");
        java.util.Date exp = null;
       
        //try to match date format given to desired format
        try {
            exp = sdf.parse(request.getParameter("exp"));
        } catch (ParseException ex) {
            Logger.getLogger(MyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        //get patterns and matchers for validation
        //set boolean to determine unwanted characters
        Pattern let = Pattern.compile("[^a-z-]", Pattern.CASE_INSENSITIVE);
        Pattern num = Pattern.compile("[^0-9]");
        Matcher fm = let.matcher(first);
        Matcher lm = let.matcher(last);
        Matcher nm = num.matcher(number);
        Matcher am = num.matcher(address);
        Matcher sm = num.matcher(sec);
        boolean fn = fm.find();
        boolean ln = lm.find();
        boolean n = nm.find();
        boolean sn = sm.find();
        boolean an = am.find();
        
        //if the following are false, validation successful
        //first or last has special characters
        //number not equal to 16 characters or contains letters
        //exp was not able to parse correctly
        //sec not equal to 3 characters or contains letters
        if(fn == true || ln == true || number.length() != 16 || 
                n == true || address.length() != 5 || an == true || exp == null || sec.length() != 3 || sn == true){
        RequestDispatcher r = request.getRequestDispatcher("error.xhtml");
        r.forward(request, response);
        }
        
        //check if entry matches info from file
        //data not being properly saved to value array
        //array reference returning null
        /*else{
            if(auth.checkEntry(first, last, address, number, sec, exp) == true){
                RequestDispatcher r = request.getRequestDispatcher("error.xhtml");
                r.forward(request, response);
            }*/
        
            else{
            RequestDispatcher r = request.getRequestDispatcher("Display.jsp");
            r.forward(request, response);
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
