namespace notification_service.Dto;

public class EmailDto
{
    public String Subject {get; set;} = String.Empty; 

    public String To { get; set; } = String.Empty;

    public String HtmlBody { get; set; } = String.Empty; 
}