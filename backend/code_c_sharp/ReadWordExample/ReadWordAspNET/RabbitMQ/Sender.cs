using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ReadWordAspNET.RabbitMQ
{
    public class Sender
    {
        private static readonly ConfigSender configSender = ConfigSender.Instance; 
        private static readonly Lazy<Sender> instance = new(() => new Sender());

        private Sender() { }
        public static Sender Instance => instance.Value;

        public void SendViaDirectExchange(string message) => 
            configSender.ConvertAndSend(ConnectionRabbit.DIRECT_EXCHANGE_NAME, "", message);
    }
}
