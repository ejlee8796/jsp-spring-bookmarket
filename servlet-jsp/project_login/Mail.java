/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.mail.Address
 *  javax.mail.Authenticator
 *  javax.mail.Message
 *  javax.mail.Message$RecipientType
 *  javax.mail.MessagingException
 *  javax.mail.PasswordAuthentication
 *  javax.mail.Session
 *  javax.mail.Transport
 *  javax.mail.internet.InternetAddress
 *  javax.mail.internet.MimeMessage
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package project_login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Mail"})
public class Mail
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String host = "smtp.naver.com";
        String user = "adnerwin";
        String password = "{MAIL_PASSWORD}";
        String notice = request.getParameter("noticeList");
        String useremail = request.getParameter("useremail");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String to = "adnerwin@naver.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", (Object)587);
        Session session = Session.getDefaultInstance((Properties)props, (Authenticator)new Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication({MAIL_USER}, {MAIL_PASSWORD});
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom((Address)new InternetAddress("adnerwin"));
            message.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(to));
            message.setSubject("[" + notice + "]" + title);
            message.setText(String.valueOf(content) + "\n 답변 받을 고객님 메일 주소 : " + useremail);
            Transport.send((Message)message);
            PrintWriter out = response.getWriter();
            out.print("<script>alert('전송 성공'); location.href='loginBoard/footer_2.jsp';</script>");
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
