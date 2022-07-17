using Microsoft.AspNetCore.Mvc; 
using MimeKit; 
using MailKit.Net.Smtp; 
using MailKit.Security; 


namespace notification_service.Controllers; 

[ApiController]
[Route("api/[controller]")]
public class EmailController: ControllerBase{

    [HttpPost]
    public  IActionResult sendEmail(){

        var email = new MimeMessage();
        email.From.Add(MailboxAddress.Parse("jany59@ethereal.email"));
        email.To.Add(MailboxAddress.Parse("jany59@ethereal.email")); 
        email.Subject = "Test Email"; 
        email.Body = new TextPart(MimeKit.Text.TextFormat.Html){Text = "Hello World"}; 
        using var smtp = new SmtpClient(); 
        smtp.Connect("smtp.ethereal.email", 587, SecureSocketOptions.StartTlsWhenAvailable); 
        smtp.Authenticate("jany59@ethereal.email","sBPKqVHe6fhUyemJvU"); 
        smtp.Send(email); 
        smtp.Disconnect(true); 

        return Ok("Email Successful sent"); 
    }
}