using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ReadWordAspNET.RabbitMQ.ContractConfig
{
    public interface IConfigSender
    {
        void ConvertAndSend(string typeSend, string routingKey, string message);
    }
}
