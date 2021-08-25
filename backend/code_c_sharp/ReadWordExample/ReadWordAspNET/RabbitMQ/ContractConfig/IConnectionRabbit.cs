using RabbitMQ.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ReadWordAspNET.RabbitMQ.ContractConfig
{
    public interface IConnectionRabbit
    {
        IModel GetChannel();
    }
}
