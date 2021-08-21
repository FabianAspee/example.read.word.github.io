using CountWord.Factory;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ReadWordAspNET.Controllers.Contract;
using ReadWordAspNET.RabbitMQ;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks; 

namespace ReadWordAspNET.Controllers.Implementation
{
    [ApiController]
    public class ReadWordController : ControllerBase, IReadWordController
    {
        
        // GET: ReadWordControllercs
        [HttpGet]
        [Route("api/readwordexample")]
        public void ReadWordExample() => SubscriberFactory.Instance.GetReadDirectory(Sender.Instance.SendViaDirectExchange).ReadDirectoryRecursive();
        
    }
}
