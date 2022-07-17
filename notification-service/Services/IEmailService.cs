namespace notification_service.Services;

public interface IEmailService
{
    void sendStandardEmail(EmailDto request);
}