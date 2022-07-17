using Microsoft.AspNetCore.Mvc;

namespace notification_service.Controllers;

public class DefaultController
{
    [ApiExplorerSettings(IgnoreApi = true)]
    public class DefauiltController : ControllerBase
    {
        [Route("/")]
        [Route("/swagger")]
        public RedirectResult Index()
        {
            return new RedirectResult("~/swagger");
        }
    }
}