using MimeKit;
using MailKit.Net.Smtp;
using MailKit.Security;


namespace notification_service.Services;

public class EmailService : IEmailService
{
    private readonly IConfiguration _configuration;

    public EmailService(IConfiguration configuration)
    {
        _configuration = configuration;
    }

    public void sendStandardEmail(EmailDto request)
    {
        var email = new MimeMessage();
        email.From.Add(MailboxAddress.Parse(_configuration.GetSection("Username").Value));
        email.To.Add(MailboxAddress.Parse(request.To));
        email.Subject = request.Subject;
        email.Body = new TextPart(MimeKit.Text.TextFormat.Html) { Text = request.HtmlBody };
        using var smtp = new SmtpClient();
        smtp.Connect(_configuration.GetSection("Host").Value, 587, SecureSocketOptions.StartTlsWhenAvailable);
        smtp.Authenticate(_configuration.GetSection("Username").Value, _configuration.GetSection("Password").Value);
        smtp.Send(email);
        smtp.Disconnect(true);
    }
}