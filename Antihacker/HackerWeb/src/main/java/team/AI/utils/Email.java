package team.AI.utils;

import team.AI.bean.UserBean;
import team.AI.serviceIMP.UserServiceIMP;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class Email {

    // 发送邮件的账号
    private static String ownEmailAccount = "1583214829@qq.com";
    // 发送邮件的密码------》授权码
    private static String ownEmailPassword = "xoctcnncpcowbaee";
    // 发送邮件的smtp 服务器 地址
    private static String myEmailSMTPHost = "smtp.qq.com";
    // 发送邮件对方的邮箱
    private static String receiveMailAccount = null;

    public static String SendEmail(String email) {
        String newPWD=genRandomNum();
        receiveMailAccount=email;
        UserBean userBean=new UserBean();
        userBean.setEmail(email);
        userBean.setPassword(newPWD);
        UserServiceIMP userServiceIMP=new UserServiceIMP();
        String name = userServiceIMP.emailFindPhone(userBean).getName();
        userServiceIMP.emailToUpdatePWD(userBean);
        Properties prop = new Properties();
        // 设置邮件传输采用的协议smtp
        prop.setProperty("mail.transport.protocol", "smtp");
        // 设置发送人邮件服务器的smtp地址
        // 这里以网易的邮箱smtp服务器地址为例
        prop.setProperty("mail.smtp.host", myEmailSMTPHost);
        // 设置验证机制
        prop.setProperty("mail.smtp.auth", "true");

        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        // 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        // QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)

        /*final String smtpPort = "465";
        prop.setProperty("mail.smtp.port", smtpPort);
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.port", smtpPort);*/

        // 创建对象回话跟服务器交互
        Session session = Session.getInstance(prop);
        // 会话采用debug模式
        session.setDebug(true);
        // 创建邮件对象
        try {
            Message message = createSimpleMail(session,name,newPWD);
            Transport trans = session.getTransport();
            // 链接邮件服务器
            trans.connect(ownEmailAccount, ownEmailPassword);
            // 发送信息
            trans.sendMessage(message, message.getAllRecipients());
            // 关闭链接
            trans.close();

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }

    /**
     * @param @param  session
     * @param @return
     * @param @throws Exception    设定文件
     * @return Message    返回类型
     * @throws
     * @Title: createSimpleMail
     * @Description: 创建邮件对象
     * @author: chengpeng
     */
    public static Message createSimpleMail(Session session,String name,String newPWD) throws Exception {

        MimeMessage message = new MimeMessage(session);
        // 设置发送邮件地址,param1 代表发送地址 param2 代表发送的名称(任意的) param3 代表名称编码方式
        message.setFrom(new InternetAddress("1583214829@qq.com", "AI反黑客系统", "utf-8"));
        // 代表收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAccount, name, "utf-8"));
        // To: 增加收件人（可选）
        /*message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
        // Cc: 抄送（可选）
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
        // Bcc: 密送（可选）
        message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));*/
        // 设置邮件主题
        message.setSubject("新密码");
        // 设置邮件内容
        message.setContent("尊敬的用户，你的新密码是："+newPWD, "text/html;charset=utf-8");
        // 设置发送时间
        message.setSentDate(new Date());
        // 保存上面的编辑内容
        message.saveChanges();
        // 将上面创建的对象写入本地
        OutputStream out = new FileOutputStream("MyEmail.eml");
        message.writeTo(out);
        out.flush();
        out.close();
        return message;

    }


    public static String genRandomNum(){
        int  maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < 8){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

    public static void main(String[] args) {
        SendEmail("319732708@qq.com");

    }

}
