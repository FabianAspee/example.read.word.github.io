using ReadWordAspNET.RabbitMQ.ContractConfig;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ReadWordAspNET.RabbitMQ
{
    public class Sender
    {
        private static readonly IConfigSender configSender = ConfigSender.Instance; 
        private static readonly Lazy<Sender> instance = new(() => new Sender());

        private Sender() { }
        public static Sender Instance => instance.Value;

        public async Task SendViaDirectExchange(string message) => 
            await Task.Run(()=> configSender.ConvertAndSend(ConnectionRabbit.DIRECT_EXCHANGE_NAME, "", message));
    }
}
